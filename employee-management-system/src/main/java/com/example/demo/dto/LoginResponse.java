package com.example.demo.dto;

public class LoginResponse {
    private Long id;
    private String name;
    private String email;
    private String department;
    private String role;
    private boolean success;
    private String message;

    // Constructors
    public LoginResponse() {}

    public LoginResponse(Long id, String name, String email, String department, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.role = role;
        this.success = true;
        this.message = "Login successful";
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartment(String department) { this.department = department; }
    public void setRole(String role) { this.role = role; }
    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
}