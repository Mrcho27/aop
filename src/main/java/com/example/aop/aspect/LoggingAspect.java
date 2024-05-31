package com.example.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect // 이클래스를 Aspect로 등록하는 어노테이션
@Slf4j
@Component
public class LoggingAspect {

//    @Before 타겟으로 지정한 객체가 실행되기 직전에 적용시킬 어드바이스임을 나타낸다.
    @Before("execution(* com.example.aop.service.UserService.*(..))")
//    execution(* com.example.aop.service.UserService.*(..)) 은 포인트 컷
//    포인트 컷은 표현식이며, 어디에 이 어드바이스를 적용시킬지를 나타낸다.
//    지금 적용한 포인트 컷은 UserService의 모든 메소드에 적용시키는 것이다.
//    (서비스의 메소드가 실행되는 경우에만 이 어드바이스가 실행된다.)
//    이렇게 표현식을 사용하는 방법이 스프링 부터 쓰이던 방식이다.

//    첫 번째 * 은 몯느 리턴 타입을 의미한다.
//    만약 리턴 타입이 void인 메소드에만 적용하고 싶다면
//    execution(void com.example.aop.service.UserService.*(..))
//    라고 작성하면 된다.

//    두 번째 * 은 메소드의 이름이다. UserService 클래스 내부에 모든 메소드를 의미한다.
//    특정 메소드를 지정하고 싶다면 메소드 이름을 명시하면 된다.

//    (..)은 매개변수를 의미한다. 매개변수 상관없이 모든 메소드에 적용하는 것을 의미한다.
    public  void logBefore(JoinPoint joinPoint){
//        JoinPoint 는 어드바이스가 적용될 수 있는 위치를 의미한다.
//        매개변수의 JoinPoint 객체는 어드바이스가 적용된 메소드의 정보를 가져온다.
//        getSignature() : 메소드의 시그니쳐를 가져온다.
//        시그니쳐란? 메소드를 구분할 수 있는 고유한 정보를 의미한다.
//        UserDTO com.example.aop.UserService.findUser(String,String) <- 메소드의 시그니처

        log.info("*********** Before : {}", joinPoint.getSignature().getName());
        log.info("*********** Signiture : {}", joinPoint.getSignature().toLongString());
        log.info("********** args : {}", Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* com.example.aop.service.UserService.*(..))")
    public void logAfter(JoinPoint joinPoint){
        log.info("********* After : {}", joinPoint.getSignature().getName());
    }

    @Around("@annotation(com.example.aop.aspect.LoggingPointcut)")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint){
        log.info("********* Around : {}", proceedingJoinPoint.getSignature().getName());
//        ProceedingJoinPoint는 기존의 JoinPoint 인터페이스를 확장시킨 인터페이스이다.
//        Around 어드바이스에서만 사용 가능하다!!!
//        JoinPoint의 기능을 전부 가지고 있고 proceed() 메소드가 추가되었다.

//        proceed()는 이 부가기능(어드바이스)가 적용된 대상의 핵심 로직을 실행시키는 메소드이다.
//        즉, proceed()를 실행하면 어드바이스를 적용한 메소드가 실행된다.
//        만약 어드바이스가 적용된 메소드에게 반환타입이 존재한다면 해당 반환값을 여기서 받아
//        다시 return 해야한다.

        Object result = null;

        try {
           result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        log.info("********* Around : {}", proceedingJoinPoint.getSignature().getName());

        return result;
    }

}
