package vn.fsoft.humanaged.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.fsoft.humanaged.domain.*;
import vn.fsoft.humanaged.dto.*;
import vn.fsoft.humanaged.service.IAccountService;
import vn.fsoft.humanaged.service.IEmployeeService;
import vn.fsoft.humanaged.service.ILocationService;

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
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ILocationService locationService;

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

    @GetMapping("")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam(required = false) String projectID) {
        List<Employee> employeeList;
        if (projectID == null) {
            employeeList = employeeService.findEmployeeExceptDeleted();
        } else {
            employeeList = employeeService.findAllExceptProject(projectID);
            employeeList = employeeList.stream().filter(employee -> !employee.isDelete()).collect(Collectors.toList());
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
    public ResponseEntity<List<EmployeeDTO>> getWorkingEmployeeByStatus(@PathVariable("status") Status status) {
        List<Employee> employees = employeeService.findEmployeeByStatus(status, true);
        List<EmployeeDTO> subEmployees = employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(subEmployees, HttpStatus.OK);
    }

    @GetMapping("/status/{status}/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeeByStatus(@PathVariable("status") Status status) {
        List<Employee> employees = employeeService.findEmployeeByStatus(status, false);
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
            EmployeeDetailDTO employeeDetailDTO = modelMapper.map(employee, EmployeeDetailDTO.class);
            employeeDetailDTO.setProjectMembers(
                    employee.getProjectMembers()
                            .stream()
                            .filter(pm -> !pm.getProject().isDelete())
                            .map(pm -> {
                                ProjectMemberProjectsDTO pmd = modelMapper.map(pm, ProjectMemberProjectsDTO.class);
                                pmd.setProject(modelMapper.map(pm.getProject(), ProjectDTO.class));
                                return pmd;
                            }).collect(Collectors.toSet())
            );
            return new ResponseEntity<>(employeeDetailDTO, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDTO> removeEmployee(@PathVariable("id") String id) {
        Employee deletedEmployee = this.employeeService.updateEmployeeIsDelete(id, true);
        EmployeeDTO employeeDTO = null;

        if (deletedEmployee != null) employeeDTO = modelMapper.map(deletedEmployee, EmployeeDTO.class);

        return ResponseEntity.ok(employeeDTO);
    }

    @GetMapping("/provinces")
    public ResponseEntity<List<Province>> getAllProvince() {
        return ResponseEntity.ok(locationService.getAllProvince());
    }

    @GetMapping("/provinces/{id}/districts")
    public ResponseEntity<List<District>> getAllDistrictByProvince(@PathVariable int id) {
        return ResponseEntity.ok(locationService.getAllDistrictByProvinceId(id));
    }

    @GetMapping("/districts/{id}/wards")
    public ResponseEntity<List<Ward>> getAllWardByDistrict(@PathVariable int id) {
        return ResponseEntity.ok(locationService.getAllWardByDistrictId(id));
    }
}