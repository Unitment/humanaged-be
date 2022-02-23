package vn.fsoft.humanaged.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.fsoft.humanaged.domain.Account;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.repository.IEmployeeRepository;
import vn.fsoft.humanaged.service.impl.AccountService;

@RestController
@RequestMapping("/api/employee")

public class EmployeeController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private IEmployeeRepository employeeRepository;

//    @PostMapping("/information")
//    public Employee displayInformation (@RequestBody Employee employee) {
//        Account account = new Account();
//        String accountName=account.getAccountName();
//        Employee employeeObj=null;
//
//        employeeObj=employeeRepository.findByAccountName(accountName);
//
//        return employeeObj;
//
//    }
}