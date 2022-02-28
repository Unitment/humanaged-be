package vn.fsoft.humanaged.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.fsoft.humanaged.domain.Project;
import vn.fsoft.humanaged.dto.EmployeeDTO;
import vn.fsoft.humanaged.dto.ProjectDetailDTO;
import vn.fsoft.humanaged.dto.ProjectMemberEmployeesDTO;
import vn.fsoft.humanaged.service.IProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProjectDetailDTO> getEmployeeDetailByID(@PathVariable("id") String id){
        Optional<Project> oProject = projectService.getById(id);
        
        if(oProject.isPresent()){
            Project project = oProject.get();
            ProjectDetailDTO projectDetailDTO = modelMapper.map(project, ProjectDetailDTO.class);
            projectDetailDTO.setProjectMember(
                project.getProjectMembers().stream().map(pm -> {
                    ProjectMemberEmployeesDTO pmd = modelMapper.map(pm, ProjectMemberEmployeesDTO.class);
                    pmd.setEmployee(modelMapper.map(pm.getEmployee(), EmployeeDTO.class));
                    return pmd;
                }).collect(Collectors.toSet())
            );
    
            return new ResponseEntity<>(projectDetailDTO, HttpStatus.OK);
        } else return new ResponseEntity<ProjectDetailDTO>(HttpStatus.NOT_FOUND);
    }
    
}