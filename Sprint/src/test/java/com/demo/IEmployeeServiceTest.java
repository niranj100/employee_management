package com.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.demo.dao.IEmployeeRepository;
import com.demo.entities.Employee;
import com.demo.service.IEmployeeServiceImpl;

//extend with - a repeatable annotation that is used to register extensions for the annotated test class 
@ExtendWith(MockitoExtension.class)// MockitoExtension - Extension that initializes mocks and handles strict stubbings
public class IEmployeeServiceTest {
	@Mock
	private IEmployeeRepository employeeRepository;
	
	@InjectMocks
	private IEmployeeServiceImpl employeeService;
	
	@Test
	// to test get all employees
	void getAllEmployees() {
		List<Employee> list = new ArrayList<Employee>();
		Employee emp1 = new Employee(101,"nir@gmail.com","niranj","mani");
		Employee emp2 = new Employee(102,"pavi@gmail.com","pavi","pv");
		Employee emp3 = new Employee(103,"po@gmail.com","pooja","lion");
		Employee emp4 = new Employee(104,"pr@gmail.com","priya","dhar");
		list.add(emp1);
		list.add(emp2);
		list.add(emp3);
		list.add(emp4);
		when(employeeRepository.findAll()).thenReturn(list);
		
		List<Employee> empList = employeeService.getAllEmployees();
		assertEquals(4,empList.size());	
	}
	
	@Test
	// to test addition of employees
	void saveEmployeeTest() {
		Employee emp = new Employee(101,"niran","niranj","mani@gmail.com");
		when(employeeRepository.save(emp)).thenReturn(emp);
		Employee savedEmp = employeeService.addEmployee(emp);
		assertThat(savedEmp).isNotNull();
		assertEquals("niran",savedEmp.getFirstName());
	}
	
	
	@Test
	// to test deletion of employees
	public void deleteEmployeeTest() {
		Employee emp1 = new Employee(102,"pavi@gmail.com","pavi","pv");
		employeeRepository.deleteById(emp1.getUserId());
		boolean deleteEmp = employeeService.deleteEmployee(102);
		assertThat(deleteEmp).isNotNull();
		assertEquals(true,deleteEmp);
		
	}
	
// 	@Test
// 	// to test updation of employees
//     void updateEmployeeTest() {
// 	Employee emp = new Employee(101,"niranj","mani","nir@gmail.com");
// 	when(employeeRepository.findById(emp.getUserId())).thenReturn(emp);
// 	emp.setEmail("ben@gmail.com");
//     @SuppressWarnings("unused")
// 	Employee updateEmp = employeeService.updateEmployee(emp);
	
// 	}
}
