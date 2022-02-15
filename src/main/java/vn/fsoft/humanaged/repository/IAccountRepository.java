package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fsoft.humanaged.domain.Account;

public interface IAccountRepository extends JpaRepository<Account, String> {
}