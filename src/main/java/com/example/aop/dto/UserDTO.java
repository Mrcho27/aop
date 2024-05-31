package com.example.aop.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String loginId;
    private String name;
    private String password;
}
