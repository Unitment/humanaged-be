package vn.fsoft.humanaged.service;

import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.Account;

@Service
public interface IAccountService extends IService<Account, String> {

    String generateAccountForName(String name);

    boolean isExist(String accountName);
}