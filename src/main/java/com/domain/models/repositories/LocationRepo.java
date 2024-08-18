package com.domain.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Location;

public interface LocationRepo extends CrudRepository<Location, Long>{
 
    List<Location> findByName(String name);
}