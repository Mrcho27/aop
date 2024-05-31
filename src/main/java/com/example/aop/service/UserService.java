package com.example.aop.service;

import com.example.aop.aspect.LoggingPointcut;
import com.example.aop.dto.UserDTO;
import com.example.aop.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    @LoggingPointcut
    public void registerUser(UserDTO userDTO){
        userMapper.insertUser(userDTO);
    }

    @LoggingPointcut
    public UserDTO findUser(String loginId, String password){
        return userMapper.selectUser(loginId, password)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원 정보"));
    }
}
