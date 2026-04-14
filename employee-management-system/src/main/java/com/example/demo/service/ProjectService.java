package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.ProjectDTO;

public interface ProjectService {
    ProjectDTO saveProject(ProjectDTO projectDTO);
    List<ProjectDTO> getAllProjects();
    void deleteProject(Long id);
    List<ProjectDTO> getProjectsByEmployeeId(Long employeeId);
}