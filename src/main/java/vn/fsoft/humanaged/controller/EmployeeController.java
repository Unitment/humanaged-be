package vn.fsoft.humanaged.controller;

import java.util.Optional;
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
    public ResponseEntity<EmployeeDetailDTO> getEmployeeDetailByID(@PathVariable("id") String id){
        Optional<Employee> oEmployee = employeeService.getById(id);
        if(oEmployee.isPresent()){
            Employee employee = oEmployee.get();
            EmployeeDetailDTO employeeProjectDTO = modelMapper.map(employee, EmployeeDetailDTO.class);
            employeeProjectDTO.setProjectMember(
                employee.getProjectMembers().stream().map(pm -> {
                    ProjectMemberProjectsDTO pmd = modelMapper.map(pm, ProjectMemberProjectsDTO.class);
                    pmd.setProject(modelMapper.map(pm.getProject(), ProjectDTO.class));
                    return pmd;
                }).collect(Collectors.toSet())
            );
            return new ResponseEntity<>(employeeProjectDTO, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}