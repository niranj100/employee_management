package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Department;

public interface IDepartmentRepository extends JpaRepository<Department,Integer>{

}
