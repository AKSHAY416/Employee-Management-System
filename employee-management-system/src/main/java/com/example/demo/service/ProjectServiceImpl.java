package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository; // ✅ FIXED: inject properly

    @Override
    public ProjectDTO saveProject(ProjectDTO projectDTO) {
        Project project = convertToEntity(projectDTO);
        Project saved = projectRepository.save(project);
        return convertToDTO(saved);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectDTO> getProjectsByEmployeeId(Long employeeId) {
        return projectRepository.findByEmployee_Id(employeeId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // --- Conversion helpers ---
    private ProjectDTO convertToDTO(Project project) {
        return new ProjectDTO(
            project.getProjectId(),
            project.getProjectName(),
            project.getDescription(),
            project.getStatus(),
            project.getEmployee() != null ? project.getEmployee().getId() : null,
            project.getEmployee() != null ? project.getEmployee().getEmail() : null
        );
    }

    private Project convertToEntity(ProjectDTO dto) {
        Project project = new Project();
        project.setProjectId(dto.getProjectId());
        project.setProjectName(dto.getProjectName());
        project.setDescription(dto.getDescription());
        project.setStatus(dto.getStatus());

        // ✅ FIXED: Fetch real Employee from DB instead of creating detached object
        if (dto.getEmployeeId() != null) {
            Employee emp = employeeRepository.getReferenceById(dto.getEmployeeId());
            project.setEmployee(emp);
        }

        return project;
    }
}