package com.demo.service;

import java.util.List;

import com.demo.entities.Department;

public interface IDepartmentService {
	public Department addDepartment(Department dname);
	public List<Department> getAllDepartments();
	public List<Department> getAllDepartmentByPagination(int maxPage,int ofset);
	public int getAllDepartCount();
	public boolean deleteDepartment(int id);
}
