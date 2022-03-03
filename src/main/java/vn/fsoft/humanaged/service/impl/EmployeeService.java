package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fsoft.humanaged.domain.Status;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.repository.IAccountRepository;
import vn.fsoft.humanaged.repository.IEmployeeRepository;
import vn.fsoft.humanaged.service.IAccountService;
import vn.fsoft.humanaged.service.IEmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getById(String key) {
        return employeeRepository.findById(key);
    }

    @Override
    public Employee save(Employee entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public void deleteById(String key) {

    }

    @Override
    public List<Employee> findEmployeeByStatus(Status status) {
        return employeeRepository.findAllByStatus(status);
    }
}