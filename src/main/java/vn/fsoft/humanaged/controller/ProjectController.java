package vn.fsoft.humanaged.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.fsoft.humanaged.domain.Project;
import vn.fsoft.humanaged.domain.ProjectState;
import vn.fsoft.humanaged.dto.ProjectDTO;
import vn.fsoft.humanaged.service.IProjectService;

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {
    @Autowired
    private IProjectService projectService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){
        List<Project> projects = projectService.getAll();
        List<ProjectDTO> subProjects = projects.stream()
                .map(project -> modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(subProjects,HttpStatus.OK);
    }
    @GetMapping("/{state}")
    public ResponseEntity<List<ProjectDTO>> getProjectByState(@PathVariable("state") ProjectState state){
        List<Project> projects = projectService.findProjectByState(state);
        List<ProjectDTO> subProjects = projects.stream()
                .map(project -> modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(subProjects,HttpStatus.OK);
    }

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