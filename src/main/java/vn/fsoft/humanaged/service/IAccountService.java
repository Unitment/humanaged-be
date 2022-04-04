package vn.fsoft.humanaged.service;

import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
public interface IAccountService extends IService<Account, String> {

    String generateAccountForName(String name);

    boolean isExist(String accountName);

    Optional<Account> getAccountByAccountName(String accountName);

    Optional<Account> getAccountByResetPasswordToken(String ResetPasswordToken);

    Optional<Account> updateResetPasswordToken(String token, String accountName) throws AccountNotFoundException;

    void updatePassword(Account account, String newPassword);
}