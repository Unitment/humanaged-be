package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fsoft.humanaged.domain.Account;

public interface IAccountRepository extends JpaRepository<Account, String> {
//    public Account checkLogin(String accountName,String password);
//    public Account findByAccountName(String accountName);
    public Account findByAccountNameAndPassword(String accountName, String password);
}