package com.demo;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import com.demo.dao.IDepartmentRepository;
import com.demo.entities.Department;
import com.demo.service.IDepartmentServiceImpl;
//extend with - a repeatable annotation that is used to register extensions for the annotated test class 
@ExtendWith(MockitoExtension.class) // MockitoExtension - Extension that initializes mocks and handles strict stubbings
public class IDepartmentServiceTest {
	@Mock
	private IDepartmentRepository departmentRepository;
	
	@InjectMocks
	private IDepartmentServiceImpl departmentService;
	
	@Test
	// to test get all departments
	void getAllDepartments() {
		List<Department> list = new ArrayList<Department>();
		Department dept1 = new Department(1,"Hr");
		Department dept2 = new Department(2,"mrg");
		Department dept3 = new Department(3,"programmer");
		list.add(dept1);
		list.add(dept2);
		list.add(dept3);
		when(departmentRepository.findAll()).thenReturn(list);
		
		List<Department> deptList = departmentService.getAllDepartments();
		assertEquals(3,deptList.size());//Assert that expected and actual are equal.
	}
	
	@Test
	// to test addition of department
	void saveDepartmentTest() {
		Department dept = new Department(1,"Hr");
		when(departmentRepository.save(dept)).thenReturn(dept);
		Department savedDept = departmentService.addDepartment(dept);
		assertThat(savedDept).isNotNull();//Returns the given assertion
		assertEquals("Hr",savedDept.getDepartName());
	}	
	
	@Test
	// to test deletion of department
	public void deleteDepartmentTest() {
		Department emp1 = new Department(3,"programmer");
		departmentRepository.deleteById(emp1.getDepartId());
		boolean deleteDept = departmentService.deleteDepartment(3);;
		assertThat(deleteDept).isNotNull();
		assertEquals(true,deleteDept);
	}
}
