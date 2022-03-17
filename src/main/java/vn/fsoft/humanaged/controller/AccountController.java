package vn.fsoft.humanaged.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.fsoft.humanaged.domain.Account;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.dto.EmployeeDTO;
import vn.fsoft.humanaged.payload.request.LoginRequest;
import vn.fsoft.humanaged.payload.response.LoginResponse;
import vn.fsoft.humanaged.service.impl.AccountService;
import vn.fsoft.humanaged.service.impl.EmployeeService;

import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ModelMapper modelMapper;

//    @GetMapping("/{accountName}")
//    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("accountName") String accountName){
//        Optional<Employee> employeeOptional = employeeService.findByAccountName(accountName);
//
//        return employeeOptional
//                .map(employee -> ResponseEntity.ok(modelMapper.map(employee, EmployeeDTO.class)))
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PostMapping("/info")
    public ResponseEntity<EmployeeDTO> userInfo(@RequestBody Account account) {

        Optional<Employee> employeeOptional = employeeService.findByAccountName(account.getAccountName());

        return employeeOptional
                .map(employee -> ResponseEntity.ok(modelMapper.map(employee, EmployeeDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }
}