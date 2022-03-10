package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.domain.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findAllByStatus(Status status);
    
    List<Employee> findByStatusAndIsDeleteFalse(Status status);

    @Query("SELECT e FROM Employee e WHERE e.account.accountName = ?1")
    Optional<Employee> findByAccountName(String accountName);

    List<Employee> findAllByAccount_AccountNameContaining(String accountName);
    
    @Query(value = "SELECT *\n" +
            "FROM employee\n" +
            "WHERE employee.id NOT IN (SELECT id\n" +
            "                         FROM employee AS e INNER JOIN project_member pm ON e.id = pm.employee_id\n" +
            "                         WHERE pm.project_id = :projectID)", nativeQuery = true)
    List<Employee> findAllExceptProject(String projectID);
}