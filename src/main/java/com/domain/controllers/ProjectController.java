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

import com.domain.dto.ProjectDto;
import com.domain.dto.ResponseData;
import com.domain.models.entities.Location;
import com.domain.models.entities.Project;
import com.domain.services.ProjectService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModelMapper modelMapper;

    /***
     * END POINT
     */

    
    // Find all data
    @GetMapping
    public Iterable<Project> findAll(){
        return projectService.findAll();
    }

    // Find data by Id
    @GetMapping("/{id}")
    public Project findOne(@PathVariable("id") Long id) {
        Project project = projectService.findOne(id);
        if (project == null) {
            throw new EntityNotFoundException("Project not found with id " + id);
        }
        return project;
    }
    
    // Insert Data
    @PostMapping
    public ResponseEntity<ResponseData<Project>> create(@Valid @RequestBody ProjectDto projectDto, Errors errors) {
        ResponseData<Project> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Project project = modelMapper.map(projectDto, Project.class);

        responseData.setStatus(true);
        responseData.getMessage().add("Berhasil menambahkan data proyek");
        responseData.setPayload(projectService.create(project));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }


    // Update Data
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Project>> update(@Valid @PathVariable("id") Long id, @RequestBody ProjectDto projectDto, Errors errors){
        ResponseData<Project> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Project project = modelMapper.map(projectDto, Project.class);

        responseData.setStatus(true);
        responseData.getMessage().add("Berhasil memperbarui data proyek");
        responseData.setPayload(projectService.update(id, project));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    // Delete Data By Id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeOne(@PathVariable("id") Long id) {
        projectService.removeOne(id);
        String message = "Berhasil menghapus data proyek";
        return ResponseEntity.ok(message);
    }

    // add relasi lokasi
    @PostMapping("/{id}")
    public ResponseEntity<String> addLocation(@RequestBody Location location, @PathVariable("id") Long projectId) {
        projectService.addLocation(location, projectId);
        String message = "Berhasil menambahkan relasi lokasi";
        return ResponseEntity.ok(message);
    }
}
