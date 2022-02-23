package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fsoft.humanaged.domain.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, String> {
//    public Employee findByAccountName(String accountName);
}