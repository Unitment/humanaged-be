package vn.fsoft.humanaged.controller;

import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fsoft.humanaged.domain.Account;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.dto.AccountDTO;
import vn.fsoft.humanaged.dto.EmployeeDTO;
import vn.fsoft.humanaged.service.impl.AccountService;
import vn.fsoft.humanaged.service.impl.EmailSenderService;
import vn.fsoft.humanaged.service.impl.EmployeeService;

import javax.mail.MessagingException;
import javax.security.auth.login.AccountNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailSenderService emailSenderService;


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

    @PutMapping("/reset/{username}")
    public ResponseEntity<?> processResetPassword(@PathVariable("username") String username) {
        Optional<Employee> oEmployee = employeeService.findByAccountName(username);
        String token = RandomString.make(45);
        try {
            accountService.updateResetPasswordToken(token, username);
            String resetPasswordLink = "http://localhost:4200/reset-password/" + token;
            emailSenderService.sendEmail(oEmployee.get().getPersonalMail(), resetPasswordLink);
        } catch (AccountNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (MessagingException | UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return ResponseEntity.ok(token);
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<AccountDTO> getAccountByToken(@PathVariable("token") String token) {
        Optional<Account> oAccount = accountService.getAccountByResetPasswordToken(token);
        return oAccount
                .map(account -> ResponseEntity.ok(modelMapper.map(account, AccountDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/change-password")
    public ResponseEntity<Account> processResetPassword(@RequestBody Account account) {
        accountService.updatePassword(account, account.getPassword());
        return ResponseEntity.ok(account);
    }
}