package com.domain.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.LocationDto;
import com.domain.dto.ResponseData;
import com.domain.models.entities.Location;
import com.domain.services.LocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    
    @Autowired
    private LocationService LocationService;

    @Autowired
    private ModelMapper modelMapper;
    
    /***
     * END POINT
     */

    //  Find all data
    @GetMapping
    public Iterable<Location> findAll(){
        return LocationService.findAll();
    }

    // Find data by Id
    @GetMapping("/{id}")
    public Location findOne(@PathVariable("id") Long id) {
        Location location = LocationService.findOne(id);
        if (location == null) {
            throw new RuntimeException("Location not found with id " + id);
        }
        return location;
    }

    // Insert Data
    @PostMapping
    public ResponseEntity<ResponseData<Location>> create(@Valid @RequestBody LocationDto locationDto, Errors errors) {
        ResponseData<Location> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Location location = modelMapper.map(locationDto, Location.class);

        responseData.setStatus(true);
        responseData.getMessage().add("Berhasil menambahkan data lokasi");
        responseData.setPayload(LocationService.create(location));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    // Updata Data
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Location>> update(@Valid @PathVariable("id") Long id, @RequestBody LocationDto locationDto, Errors errors) {
        ResponseData<Location> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Location location = modelMapper.map(locationDto, Location.class);

        responseData.setStatus(true);
        responseData.getMessage().add("Berhasil memperbarui data lokasi");
        responseData.setPayload(LocationService.update(id, location));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    // Delete Data by Id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeOne(@PathVariable("id") Long id) {
        LocationService.removeOne(id);
        String message = "Berhasil menghapus data proyek";
        return ResponseEntity.ok(message);
    }
}
