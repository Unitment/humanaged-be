package vn.fsoft.humanaged.service;

import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.domain.Status;

import java.util.List;
import java.util.Optional;

// @Service
public interface IEmployeeService extends IService<Employee, String> {

    List<Employee> findEmployeeByStatus(Status status, boolean ignoreDeleted);

    List<Employee> findAllExceptProject(String projectID);

    Employee updateEmployeeIsDelete(String id, boolean isDelete);

    Optional<Employee> findByAccountName(String accountName);

    List<Employee> findEmployeeExceptDeleted();

    Optional<Employee> updateAvatar(String id, String avatar);
}