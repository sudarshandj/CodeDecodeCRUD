package com.codedecode.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.custom.exception.BusinessException;
import com.codedecode.demo.custom.exception.ControllerException;
import com.codedecode.demo.entity.Employee;
import com.codedecode.demo.service.EmployeeServiceInterface;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/code")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceInterface employeeServiceInterface;
	
	@PostMapping(value="/save")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		try {
			Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
			return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
		}catch(BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("611", "Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/all")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		
		List<Employee> listOfAllEmployee = employeeServiceInterface.getAllEmployees();
		
		return new ResponseEntity<List<Employee>>(listOfAllEmployee ,HttpStatus.OK);
	}
	
	@GetMapping(value="/emp/{empid}")
	public ResponseEntity<?> getEmployee(@PathVariable("empid") Long empid){
		try {
			Employee getEmployee = employeeServiceInterface.getEmployees(empid);
			return new ResponseEntity<Employee>(getEmployee ,HttpStatus.OK);
		}catch(BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("612", "Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
		return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/delete/{empid}")
	public ResponseEntity<?> deleteEmployeeByID(@PathVariable("empid") Long empid){
		try {
			employeeServiceInterface.deleteEmployeeByID(empid);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("613", "Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
}
