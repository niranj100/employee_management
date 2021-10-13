package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.demo.entities.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer>,PagingAndSortingRepository<Employee,Integer> {
	Employee findById(int id);
}
