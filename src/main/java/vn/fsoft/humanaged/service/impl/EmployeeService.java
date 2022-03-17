package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.domain.Status;
import vn.fsoft.humanaged.repository.IEmployeeRepository;
import vn.fsoft.humanaged.service.IEmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

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
    public List<Employee> findEmployeeByStatus(Status status, boolean ignoreDeleted) {
        if (ignoreDeleted) return this.employeeRepository.findByStatusAndIsDeleteFalse(status);
        else return this.employeeRepository.findAllByStatus(status);
    }

    @Override
    public List<Employee> findAllExceptProject(String projectID) {
        return employeeRepository.findAllExceptProject(projectID);
    }

    @Override
    public Employee updateEmployeeIsDelete(String id, boolean isDelete) {
        Optional<Employee> oEmp = this.employeeRepository.findById(id);

        if (oEmp.isPresent()) {
            Employee employee = oEmp.get();
            employee.setDelete(isDelete);
            return this.employeeRepository.save(employee);
        } else return null;
    }

    @Override
    public Optional<Employee> findByAccountName(String accountName) {
        return this.employeeRepository.findByAccountName(accountName);
    }
}