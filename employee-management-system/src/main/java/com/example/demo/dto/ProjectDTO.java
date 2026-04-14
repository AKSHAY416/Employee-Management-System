package com.example.demo.dto;

public class ProjectDTO {

    private Long projectId;
    private String projectName;
    private String description;
    private String status;
    private Long employeeId;
    private String employeeEmail;

    public ProjectDTO() {}

    public ProjectDTO(Long projectId, String projectName, String description,
                      String status, Long employeeId, String employeeEmail) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.employeeId = employeeId;
        this.employeeEmail = employeeEmail;
    }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public String getEmployeeEmail() { return employeeEmail; }
    public void setEmployeeEmail(String employeeEmail) { this.employeeEmail = employeeEmail; }
}