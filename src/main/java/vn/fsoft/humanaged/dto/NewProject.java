package vn.fsoft.humanaged.dto;

import vn.fsoft.humanaged.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class NewProject {
    private String name;
    private List<Employee> employeeList = new ArrayList<>();
}