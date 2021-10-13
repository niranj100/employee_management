package com.demo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.entities.Employee;
import com.demo.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class IEmployeeController {
	@Autowired
	IEmployeeService employeeService;
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@PostMapping
	// Marks a method or exception class with the status code and reason that should be returned.
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Employee> addEmployee( @Valid @RequestBody Employee emp) {
		Employee employee = employeeService.addEmployee(emp);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{emp_id}")
				.buildAndExpand(employee.getUserId())
				.toUri();
		return ResponseEntity.created(location).body(employee);
	}
	
	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(emp),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
		boolean flag = employeeService.deleteEmployee(id);
		if(flag)
			return new ResponseEntity<>("employee deleted successfully",HttpStatus.OK);
		else
			return new ResponseEntity<>("employee deleted successfully",HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/count")
	public int getEmployeeCount() {
		return employeeService.getEmployeeCount();
	}
	
	@GetMapping("/pagination")
	public List<Employee> getAllEmployeesByPagination(@RequestParam int maxPage,@RequestParam int ofset){
		return (List<Employee>)employeeService.getAllEmployeesByPagination(maxPage, ofset);
	}
}
