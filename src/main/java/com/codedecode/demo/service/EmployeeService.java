package com.codedecode.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.custom.exception.BusinessException;
import com.codedecode.demo.entity.Employee;
import com.codedecode.demo.repos.EmployeeCRUDRepo;

@Service
public class EmployeeService implements EmployeeServiceInterface {
	
	@Autowired
	private EmployeeCRUDRepo employeeCRUDRepo;

	public Employee addEmployee(Employee employee) {
		if(employee.getFname().isEmpty() || employee.getFname().length() == 0) {
			throw new BusinessException("601","Please send proper name, It blank");
		}
		try {
			Employee savedEmployee = employeeCRUDRepo.save(employee);
			return savedEmployee;
		}catch (IllegalArgumentException e) {
			throw new BusinessException("602","Given employee is null"+ e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("603","Something went wrong in service layer"+ e.getMessage());
		}
	}

	public List<Employee> getAllEmployees() {
		List<Employee> empList = null;
		try {
			empList = employeeCRUDRepo.findAll();
		}catch (Exception e) {
			throw new BusinessException("605","Something went wrong in service layer"+ e.getMessage());
		}
		if(empList.isEmpty())
			throw new BusinessException("604", "Hey list completely empty , we have nothing to return");
		return empList;
	}

	public Optional<Employee> updateEmployee(Long empid) {
		Optional<Employee> updateEmployee = employeeCRUDRepo. findById(empid);
		return updateEmployee;
	}

	public Employee getEmployees(Long empid) {
		try {
			return employeeCRUDRepo.findById(empid).get();
		}catch (IllegalArgumentException e) {
			throw new BusinessException("606","Given employee id is null, please send some id to be searched"+ e.getMessage());
		}catch (NoSuchElementException e) {
			throw new BusinessException("607","given employee id doesnot exist in DB"+ e.getMessage());
		}
	}

	public void deleteEmployeeByID(Long empid) {
		try {
			employeeCRUDRepo.deleteById(empid);	
		}catch (IllegalArgumentException e) {
			throw new BusinessException("608","Given employee id is null, please send some id to be deleted"+ e.getMessage());
		}catch (NoSuchElementException e) {
			throw new BusinessException("609","given employee id doesnot exist in DB"+ e.getMessage());
		}		
	}

}
