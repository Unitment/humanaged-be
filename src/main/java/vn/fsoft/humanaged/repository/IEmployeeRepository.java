package vn.fsoft.humanaged.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.fsoft.humanaged.domain.Status;
import vn.fsoft.humanaged.domain.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findAllByStatus(Status status);

    @Query("SELECT e FROM Employee e WHERE e.account.accountName = ?1")
    Optional<Employee> findByAccountName(String accountName);

    List<Employee> findAllByAccount_AccountNameContaining(String accountName);
}