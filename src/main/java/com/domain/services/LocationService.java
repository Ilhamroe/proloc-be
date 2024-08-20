package com.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Location;
import com.domain.models.entities.Project;
import com.domain.models.repositories.LocationRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LocationService {
    
    @Autowired
    private LocationRepo locationRepo;

    // Find data by Id
    public Location findOne(Long id) {
        return locationRepo.findById(id).orElseThrow(()-> new RuntimeException("Project not found"));
    }

    // Find all data
    public Iterable<Location> findAll() {
        return locationRepo.findAll();
    }

    // INSERT data
    public Location create(Location location) {
        return locationRepo.save(location);
    }

    // UPDATE data
    public Location update(Long id, Location locationDetails) {
        if(!locationRepo.existsById(id)) {
            throw new RuntimeException("Project not found with id " + id);
        }
        locationDetails.setId(id);
        return locationRepo.save(locationDetails);
    }

    // DELETE data
    public void removeOne(Long id) {
        if (locationRepo.existsById(id)) {
            removeRelation(id);
            locationRepo.deleteById(id);
        } else {
            throw new RuntimeException("Project not found");
        }
    }

    // Delete relasi data
    public void removeRelation(Long id){
        Location location = findOne(id);
        for (Project project : location.getProyekList()) {
            project.getLokasiList().remove(location);
        }
        locationRepo.save(location);
    }

    // SEARCH data by nama_lokasi
    public List<Location> findByName(String name) {
        return locationRepo.findByName(name);
    }

}
