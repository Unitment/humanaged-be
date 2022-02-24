package vn.fsoft.humanaged.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.fsoft.humanaged.dto.ProjectDTO;
import vn.fsoft.humanaged.service.IProjectService;

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectDTO> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(
                projectService.getById(id).orElseThrow(() -> new RuntimeException("Project not found")));
    }

    @GetMapping()
    public ResponseEntity<List<ProjectDTO>> getAll() {
        return ResponseEntity.ok(
                projectService.getAll());
    }

    @PostMapping()
    public ResponseEntity<ProjectDTO> save(@RequestBody ProjectDTO entity) {
        return ResponseEntity.ok(
                projectService.save(entity));
    }

    @PutMapping()
    public ResponseEntity<ProjectDTO> update(@RequestBody ProjectDTO entity) {
        return ResponseEntity.ok(
                projectService.updateProject(entity));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") String id) {
        projectService.deleteById(id);
    }
}