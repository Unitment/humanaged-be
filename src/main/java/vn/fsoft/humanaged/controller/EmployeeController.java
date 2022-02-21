package vn.fsoft.humanaged.controller;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.dto.EmployeeDetailDTO;
import vn.fsoft.humanaged.dto.ProjectDTO;
import vn.fsoft.humanaged.dto.ProjectMemberProjectsDTO;
import vn.fsoft.humanaged.service.IEmployeeService;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<EmployeeDetailDTO> getEmployeeByID(@PathVariable("id") String id){
        Employee employee = employeeService.getById(id).orElse(new Employee());
        EmployeeDetailDTO employeeProjectDTO = modelMapper.map(employee, EmployeeDetailDTO.class);
        employeeProjectDTO.setProjects(
            employee.getProjectMembers().stream().map(pm -> {
                ProjectMemberProjectsDTO pmd = modelMapper.map(pm, ProjectMemberProjectsDTO.class);
                pmd.setProject(modelMapper.map(pm.getProject(), ProjectDTO.class));
                return pmd;
            }).collect(Collectors.toSet())
        );

        return new ResponseEntity<>(employeeProjectDTO, HttpStatus.OK);
    }
    
}