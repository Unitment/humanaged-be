package vn.fsoft.humanaged.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fsoft.humanaged.domain.Project;
import vn.fsoft.humanaged.domain.ProjectState;
import vn.fsoft.humanaged.dto.EmployeeDTO;
import vn.fsoft.humanaged.dto.ProjectDTO;
import vn.fsoft.humanaged.dto.ProjectDetailDTO;
import vn.fsoft.humanaged.dto.ProjectMemberEmployeesDTO;
import vn.fsoft.humanaged.service.IProjectService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private IProjectService projectService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<Project> projects = projectService.getAll();
        List<ProjectDTO> subProjects = projects.stream()
                .map(project -> modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(subProjects, HttpStatus.OK);
    }

    @GetMapping("/{state}")
    public ResponseEntity<List<ProjectDTO>> getProjectByState(@PathVariable("state") ProjectState state) {
        List<Project> projects = projectService.findProjectByState(state);
        List<ProjectDTO> subProjects = projects.stream()
                .map(project -> modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(subProjects, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectDTO> getById(@PathVariable("id") String id) {
        Optional<Project> projectOptional = projectService.getById(id);
        return projectOptional.map(project -> ResponseEntity.ok(modelMapper.map(project, ProjectDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public ResponseEntity<ProjectDetailDTO> getEmployeeDetailByID(@PathVariable("id") String id) {
        Optional<Project> oProject = projectService.getById(id);

        if (oProject.isPresent()) {
            Project project = oProject.get();
            ProjectDetailDTO projectDetailDTO = modelMapper.map(project, ProjectDetailDTO.class);
            projectDetailDTO.setProjectMembers(
                    project.getProjectMembers().stream().map(pm -> {
                        ProjectMemberEmployeesDTO pmd = modelMapper.map(pm, ProjectMemberEmployeesDTO.class);
                        pmd.setEmployee(modelMapper.map(pm.getEmployee(), EmployeeDTO.class));
                        return pmd;
                    }).collect(Collectors.toSet())
            );

            return new ResponseEntity<>(projectDetailDTO, HttpStatus.OK);
        } else return new ResponseEntity<ProjectDetailDTO>(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<ProjectDTO>> getAll() {
        List<Project> projectList = projectService.getAll();
        return ResponseEntity.ok(
                projectList.stream()
                        .map(project -> modelMapper.map(project, ProjectDTO.class))
                        .collect(Collectors.toList())
        );
    }

//    @PostMapping()
//    public ResponseEntity<ProjectDTO> save(@RequestBody ProjectDTO entity) {
//        return ResponseEntity.ok(projectService.saveDTO(entity));
//    }

    @PostMapping
    public ResponseEntity<Project> saveProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.save(project));
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