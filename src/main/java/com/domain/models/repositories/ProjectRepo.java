package com.domain.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Project;

public interface ProjectRepo extends CrudRepository<Project, Long>{
    
    List<Project> findByName(String name);
}
