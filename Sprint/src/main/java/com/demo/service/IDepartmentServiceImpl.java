package com.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.demo.dao.IDepartmentRepository;
import com.demo.entities.Department;

@Service //Indicates that an annotated class is a "Service"
public class IDepartmentServiceImpl implements IDepartmentService {
	@Autowired // Marks a constructor, field, setter method, or config method as to be autowired bySpring's dependency injection facilities
	private IDepartmentRepository repository;

    @Override
    public Department addDepartment(Department dname) {
        return repository.save(dname);
    }

    @Override
    public List<Department> getAllDepartments() {
    	return (List<Department>)repository.findAll();
    }

    @Override
    public List<Department> getAllDepartmentByPagination(int maxPage, int ofset) {
    	Pageable paging = PageRequest.of(maxPage-1, ofset);
		Page<Department> pageresult = repository.findAll(paging);
		return pageresult.toList();
    }

    @Override
    public int getAllDepartCount() {
    	return (int) repository.count();
    }
    
    @Override
    public boolean deleteDepartment(int id) {
	   repository.deleteById(id);
	   return true;
	}
}
