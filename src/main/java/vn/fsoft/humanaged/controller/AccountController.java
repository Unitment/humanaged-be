package vn.fsoft.humanaged.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.fsoft.humanaged.domain.Account;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.dto.AccountDTO;
import vn.fsoft.humanaged.dto.EmployeeDTO;
import vn.fsoft.humanaged.service.impl.AccountService;
import vn.fsoft.humanaged.service.impl.EmployeeService;

import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ModelMapper modelMapper;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<AccountDTO> login (@RequestBody Account account) throws Exception {
        Optional<Account> accountOptional=accountService.getAccountByAccountName(account.getAccountName());
        return (ResponseEntity<AccountDTO>) accountOptional.map(acc -> {
            if (bCryptPasswordEncoder.matches(account.getPassword(),acc.getPassword())) {
                return ResponseEntity.ok(modelMapper.map(acc,AccountDTO.class));
            }
            return new ResponseEntity<String>("Wrong Username or Password",HttpStatus.BAD_REQUEST);
        }).orElseGet(()->new ResponseEntity<String>("Wrong Username or Password",HttpStatus.BAD_REQUEST));
    }

//    @GetMapping("/{accountName}")
//    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("accountName") String accountName){
//        Optional<Employee> employeeOptional = employeeService.findByAccountName(accountName);
//
//        return employeeOptional
//                .map(employee -> ResponseEntity.ok(modelMapper.map(employee, EmployeeDTO.class)))
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PostMapping("/info")
    public ResponseEntity<EmployeeDTO> userInfo(@RequestBody Account account){

        Optional<Employee> employeeOptional = employeeService.findByAccountName(account.getAccountName());

        return employeeOptional
                .map(employee -> ResponseEntity.ok(modelMapper.map(employee, EmployeeDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }
}