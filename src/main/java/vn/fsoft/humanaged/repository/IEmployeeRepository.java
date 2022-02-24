package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fsoft.humanaged.domain.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, String> {
}