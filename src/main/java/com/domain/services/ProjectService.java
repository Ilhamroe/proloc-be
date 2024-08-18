package com.domain.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Location;
import com.domain.models.entities.Project;
import com.domain.models.repositories.ProjectRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProjectService {
    
    @Autowired
    private ProjectRepo projectRepo;

    // Find data by Id
    public Project findOne(Long id){
        return projectRepo.findById(id).orElseThrow(()-> new RuntimeException("Project not found"));
    }

    // Find all data
    public Iterable<Project> findAll(){
        return projectRepo.findAll();
    }

    // INSERT data
    public Project create(Project project){
        return projectRepo.save(project);
    }

    // UPDATE data
    public Project update(Long id, Project projectDetails){
        if (!projectRepo.existsById(id)) {
            throw new EntityNotFoundException("Project not found with id " + id);
        }
        projectDetails.setId(id);
        return projectRepo.save(projectDetails);
    }

    // DELETE data
    public void removeOne(Long id){
        if (projectRepo.existsById(id)) {
            projectRepo.deleteById(id);
        } else {
            throw new RuntimeException("Project not found");
        }
    }

    // SEARCH data by nama_proyek
    public List<Project> findByName(String name){
        return projectRepo.findByName(name);
    }

    // Add relasi lokasi
    public void addLocation(Location location, Long projectId) {
        Project project = findOne(projectId);
        if (project == null) {
            throw new RuntimeException("Product with ID :" +projectId+ "not found");
        }
        project.getLokasiList().add(location);
        create(project);
    }
}
