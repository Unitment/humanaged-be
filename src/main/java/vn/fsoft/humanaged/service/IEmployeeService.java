package vn.fsoft.humanaged.service;

import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.Employee;

@Service
public interface IEmployeeService extends IService<Employee, String> {
}