package vn.fsoft.humanaged.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.fsoft.humanaged.domain.Status;
import vn.fsoft.humanaged.domain.Employee;

// @Service
public interface IEmployeeService extends IService<Employee, String> {

    List<Employee> findEmployeeByStatus(Status status);
}