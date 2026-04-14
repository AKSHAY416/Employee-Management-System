package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.LoginResponse;
import com.example.demo.model.Employee;
import com.example.demo.model.User;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public LoginResponse login(LoginDTO loginDTO) throws Exception {
        System.out.println("Login attempt: " + loginDTO.getEmail());
        
        if (loginDTO.getEmail() == null || loginDTO.getEmail().isEmpty()) {
            throw new Exception("Email is required");
        }
        if (loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty()) {
            throw new Exception("Password is required");
        }

        // Step 1: Check if user exists in USERS table
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user != null) {
            System.out.println("User already exists");
            
            if (user.getPassword().equals(loginDTO.getPassword())) {
                System.out.println("Password correct");
                
                Employee emp = user.getEmployee();
                if (emp != null) {
                    return new LoginResponse(
                        user.getId(),
                        emp.getName(),
                        user.getEmail(),
                        emp.getDepartment(),
                        user.getRole()
                    );
                } else {
                    throw new Exception("Employee record not found");
                }
            } else {
                System.out.println("Wrong password");
                throw new Exception("Invalid password");
            }
        }

        // Step 2: Check EMPLOYEE table and auto-create user
        System.out.println("User not found, checking EMPLOYEE table...");
        Employee emp = employeeRepository.findByEmail(loginDTO.getEmail());
        
        if (emp != null) {
            System.out.println("Employee found: " + emp.getEmail());
            
            // Use employee ID as password
            if (loginDTO.getPassword().equals(String.valueOf(emp.getId()))) {
                System.out.println("Creating new user...");
                
                User newUser = new User();
                newUser.setEmail(emp.getEmail());
                newUser.setPassword(String.valueOf(emp.getId()));
                newUser.setRole("EMPLOYEE");
                newUser.setEmployee(emp);
                
                User savedUser = userRepository.save(newUser);
                System.out.println("New user created successfully");
                
                return new LoginResponse(
                    savedUser.getId(),
                    emp.getName(),
                    savedUser.getEmail(),
                    emp.getDepartment(),
                    savedUser.getRole()
                );
            } else {
                System.out.println("Wrong password");
                throw new Exception("Invalid password");
            }
        }

        System.out.println("Login failed");
        throw new Exception("Invalid email or password");
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id)
            .orElseThrow(() -> new Exception("User not found"));
    }
}