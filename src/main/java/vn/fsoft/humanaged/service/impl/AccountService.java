package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.Account;
import vn.fsoft.humanaged.repository.IAccountRepository;
import vn.fsoft.humanaged.service.IAccountService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;
//    public Account fetchAccountByAccountName (String accountName) {
//        return accountRepository.findByAccountName(accountName);
//    }

    public Account fetchAccountByAccountNameAndPassword (String accountName, String password) {
        return accountRepository.findByAccountNameAndPassword(accountName,password);
    }


    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getById(String key) {
        return Optional.empty();
    }

    @Override
    public Account save(Account account) {

        return accountRepository.save(account);
    }

    @Override
    public void deleteById(String key) {

    }




}