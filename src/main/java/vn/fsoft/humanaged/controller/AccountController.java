package vn.fsoft.humanaged.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.fsoft.humanaged.domain.Account;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.repository.IAccountRepository;
import vn.fsoft.humanaged.service.impl.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private IAccountRepository accountRepository;

    @CrossOrigin (origins = "https://localhost:4200")
    @PostMapping ("/login")
    public Account login (@RequestBody Account account) throws Exception {
        String accountName= account.getAccountName();
        String password=account.getPassword();
        Account accountObj=null;

        if (accountName !=null && password != null) {
            accountObj=accountService.fetchAccountByAccountNameAndPassword(accountName,password);
        }
        if (accountObj==null) {
            throw new Exception ("Wrong a or password");
        }
        return accountObj;

    }




}
