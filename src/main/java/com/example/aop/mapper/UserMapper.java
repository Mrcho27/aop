package com.example.aop.mapper;

import com.example.aop.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void insertUser(UserDTO userDTO);
    Optional<UserDTO> selectUser(@Param("loginId")String loginId,
                                @Param("password")String password);
}
