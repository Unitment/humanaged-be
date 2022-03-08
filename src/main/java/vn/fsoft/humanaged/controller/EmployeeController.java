package vn.fsoft.humanaged.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.fsoft.humanaged.domain.Account;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.domain.Status;
import vn.fsoft.humanaged.domain.SystemRole;
import vn.fsoft.humanaged.dto.*;
import vn.fsoft.humanaged.service.IAccountService;
import vn.fsoft.humanaged.service.IEmployeeService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {

        employeeDTO.setStatus(Status.SUPPORT);
        employeeDTO.setCountry("Việt Nam");
        Employee employee = convertToEntity(employeeDTO);

        employee.setAccount(new Account(
                accountService.generateAccountForName(employee.getName()),
                bCryptPasswordEncoder.encode(accountService.generateAccountForName(employee.getName())),
                SystemRole.ROLE_USER
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(employeeService.save(employee), EmployeeDTO.class));
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importEmployeeFromFile(@RequestBody List<EmployeeFlatDTO> employeeFlatList) {
        employeeFlatList.forEach(
                employeeFlat -> {
                    EmployeeDTO employeeDTO = new EmployeeDTO(employeeFlat);
                    Employee employee = convertToEntity(employeeDTO);

                    if (!accountService.isExist(employee.getAccount().getAccountName())) {
                        employee.getAccount().setPassword(bCryptPasswordEncoder.encode(accountService.generateAccountForName(employee.getName())));
                        employeeService.save(employee);
                    }
                }
        );

        return ResponseEntity.ok().build();

    }

    @PostMapping("/check-file")
    public ResponseEntity<HashMap<String, String>> checkData(@RequestBody List<EmployeeFlatDTO> employeeFlatList) {
        HashMap<String, String> existAccountNameList = new HashMap<>();
        employeeFlatList.forEach(
                employeeFlat -> {

                    if (accountService.isExist(employeeFlat.getAccountName())) {
                        existAccountNameList.put(employeeFlat.getAccountName(),
                                employeeFlat.getName());
                    }
                }
        );

        return ResponseEntity.ok(existAccountNameList);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam(required = false) String projectID) {
        List<Employee> employeeList;
        if (projectID == null) {
            employeeList = employeeService.getAll();
        } else {
            employeeList = employeeService.findAllExceptProject(projectID);
        }

        return ResponseEntity.ok(employeeList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("id") String id) {
        Optional<Employee> employeeOptional = employeeService.getById(id);

        return employeeOptional
                .map(employee -> ResponseEntity.ok(modelMapper.map(employee, EmployeeDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> update(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = convertToEntity(employeeDTO);

        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(employeeService.save(employee), EmployeeDTO.class));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByState(@PathVariable("status") Status status) {
        List<Employee> employees = employeeService.findEmployeeByStatus(status);
        List<EmployeeDTO> subEmployees = employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(subEmployees, HttpStatus.OK);
    }

    public Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        return employee;
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

        return employeeDTO;
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public ResponseEntity<EmployeeDetailDTO> getEmployeeDetailByID(@PathVariable("id") String id) {
        Optional<Employee> oEmployee = employeeService.getById(id);
        if (oEmployee.isPresent()) {
            Employee employee = oEmployee.get();
            EmployeeDetailDTO employeeProjectDTO = modelMapper.map(employee, EmployeeDetailDTO.class);
            employeeProjectDTO.setProjectMembers(
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