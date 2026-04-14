package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.LoginResponse;
import com.example.demo.model.User;

public interface AuthService {
    
    LoginResponse login(LoginDTO loginDTO) throws Exception;
    
    User getUserById(Long id) throws Exception;
}