package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.domain.Status;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findAllByStatus(Status status);

    List<Employee> findAllByAccount_AccountNameContaining(String accountName);
}