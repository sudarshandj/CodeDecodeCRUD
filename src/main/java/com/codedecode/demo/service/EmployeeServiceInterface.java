package com.codedecode.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Employee;

@Service
public interface EmployeeServiceInterface {

	public Employee addEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public Optional<Employee> updateEmployee(Long empid);

	public Employee getEmployees(Long empid);

	public void deleteEmployeeByID(Long empid);
	
	
}
