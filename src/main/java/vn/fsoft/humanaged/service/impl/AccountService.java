package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import vn.fsoft.humanaged.domain.Account;
import vn.fsoft.humanaged.repository.IAccountRepository;
import vn.fsoft.humanaged.service.IAccountService;

import java.util.List;
import java.util.Optional;

public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Optional<Account> getById(String key) {
        return Optional.empty();
    }

    @Override
    public Account save(Account entity) {
        return null;
    }

    @Override
    public void deleteById(String key) {

    }
}