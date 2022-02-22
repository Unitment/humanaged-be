package vn.fsoft.humanaged.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.fsoft.humanaged.domain.Status;
import vn.fsoft.humanaged.domain.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findAllByStatus(Status status);
}