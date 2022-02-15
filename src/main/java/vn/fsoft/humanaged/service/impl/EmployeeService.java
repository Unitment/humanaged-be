package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.repository.IEmployeeRepository;
import vn.fsoft.humanaged.service.IEmployeeService;

import java.util.List;
import java.util.Optional;

public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public Optional<Employee> getById(String key) {
        return Optional.empty();
    }

    @Override
    public Employee save(Employee entity) {
        return null;
    }

    @Override
    public void deleteById(String key) {

    }
}