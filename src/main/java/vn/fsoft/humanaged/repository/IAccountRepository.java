package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fsoft.humanaged.domain.Account;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String> {

    int countAccountByAccountNameLike(String regex);

    boolean existsAccountByAccountNameEquals(String accountName);

    Optional<Account> findByResetPasswordToken(String token);
}