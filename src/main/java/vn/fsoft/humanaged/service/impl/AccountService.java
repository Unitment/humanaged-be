package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.Account;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.repository.IAccountRepository;
import vn.fsoft.humanaged.repository.IEmployeeRepository;
import vn.fsoft.humanaged.service.IAccountService;
import vn.fsoft.humanaged.util.Normalization;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public String generateAccountForName(String userName) {
        //get first letter
        StringBuilder accountNameBuilder = new StringBuilder();
        String[] temp = userName.split(" ");

        accountNameBuilder.append(Normalization.removeAccent(temp[temp.length - 1].trim().toLowerCase()));
        for (int i = 0; i < temp.length - 1; i++) {
            accountNameBuilder.append(Normalization.removeAccent(temp[i]).charAt(0));
        }

        String accountName = accountNameBuilder.toString().trim().toLowerCase();

        final String NAME_REGEX_SQL = accountName + "[0-9]%";
        int nextAccountNameIndex = accountRepository.countAccountByAccountNameLike(NAME_REGEX_SQL) + 1;

        return accountName + nextAccountNameIndex;
    }

    @Override
    public boolean isExist(String accountName) {
        return accountRepository.existsAccountByAccountNameEquals(accountName);
    }

    @Override
    public Optional<Account> getAccountByAccountName(String accountName) {
        return accountRepository.findById(accountName);
    }

    @Override
    public Optional<Account> getAccountByResetPasswordToken(String ResetPasswordToken) {
        return accountRepository.findByResetPasswordToken(ResetPasswordToken);
    }

    @Override
    public void updatePassword(Account account, String newPassword){
        account.setPassword(bCryptPasswordEncoder.encode(newPassword));
        account.setResetPasswordToken(null);
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> updateResetPasswordToken(String token, String accountName) throws AccountNotFoundException {
        Optional<Employee> employee = employeeRepository.findByAccountName(accountName);
        Optional<Account> oAccount = getAccountByAccountName(accountName);
        if(oAccount.isPresent()){
            oAccount.get().setResetPasswordToken(token);
            accountRepository.save(oAccount.get());
        }else{
            throw new AccountNotFoundException("Could not find any Account with AccountName"+accountName);
        }
        employee.get().getMail();
        return accountRepository.findByResetPasswordToken(token);
    }
}