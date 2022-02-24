package vn.fsoft.humanaged.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.fsoft.humanaged.domain.Project;
import vn.fsoft.humanaged.domain.ProjectState;
import vn.fsoft.humanaged.dto.ProjectDTO;
import vn.fsoft.humanaged.service.IProjectService;

@RestController
@RequestMapping("/api/project")
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

    // @GetMapping("/pm/{id}")
}