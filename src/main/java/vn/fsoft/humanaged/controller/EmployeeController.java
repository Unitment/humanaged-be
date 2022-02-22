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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.fsoft.humanaged.domain.Status;

import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.dto.EmployeeDto;
import vn.fsoft.humanaged.service.IEmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping("/{status}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByState(@PathVariable("status") Status status){
        List<Employee> employees = employeeService.findEmployeeByStatus(status);
        List<EmployeeDto> subEmployees = employees.stream()
                        .map(emp -> modelMapper.map(emp, EmployeeDto.class))
                        .collect(Collectors.toList());
        return new ResponseEntity<>(subEmployees,HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") String id){
        Employee employee = employeeService.getById(id).orElseThrow();
        EmployeeDto subEmployee = modelMapper.map(employee, EmployeeDto.class);
        return new ResponseEntity<>(subEmployee,HttpStatus.OK);
    }
}