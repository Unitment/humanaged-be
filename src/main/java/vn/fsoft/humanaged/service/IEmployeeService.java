package vn.fsoft.humanaged.service;

import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.domain.Status;

import java.util.List;

// @Service
public interface IEmployeeService extends IService<Employee, String> {

    List<Employee> findEmployeeByStatus(Status status, boolean ignoreDeleted);

    List<Employee> findAllExceptProject(String projectID);

    Employee updateEmployeeIsDelete(String id, boolean isDelete);
}