package com.example.aop.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 메소드에 적용시킬 어노테이션을 만든다는 의미
@Target(value = ElementType.METHOD)
// 클래스나 인터페이스는 ElementType.TYPE
// 메소드와 클래스 둘 다 적용하고 싶으면
//@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)// 리텐션은 생명 주기를 의미한다.
// 어노테이션이 얼마나 유지되는지(생명주기)를 의미하며,
// 생명 주기가 끝나 시점부터는 제 기능을 못한다.
public @interface LoggingPointcut {
//    인터페이스 앞에 @를 붙이면 어노테이션을 생성하는 것이다.
}
