package vn.fsoft.humanaged.service;

import java.util.List;

import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.domain.Status;

// @Service
public interface IEmployeeService extends IService<Employee, String> {

    List<Employee> findEmployeeByStatus(Status status);
}