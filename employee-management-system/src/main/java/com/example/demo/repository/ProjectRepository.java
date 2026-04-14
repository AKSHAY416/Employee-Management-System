package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Find all projects by employee
    List<Project> findByEmployee_Id(Long employeeId);

    // ✅ ADD THIS LINE — deletes projects before employee to fix ORA-02292
    void deleteByEmployee_Id(Long employeeId);

}