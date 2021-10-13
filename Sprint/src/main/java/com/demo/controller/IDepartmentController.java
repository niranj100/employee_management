package com.demo.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.demo.entities.Department;
import com.demo.service.IDepartmentService;

@RestController
@RequestMapping("/department")
public class IDepartmentController {
	@Autowired
	IDepartmentService deptService;

    @GetMapping
    public List<Department> getAllDepartments() {
    	return deptService.getAllDepartments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Department> addDepartment( @Valid @RequestBody Department dname) {
    	Department dept = deptService.addDepartment(dname);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{dept_id}")
				.buildAndExpand(dept.getDepartId())
				.toUri();
		 return ResponseEntity.created(location).body(dept);
    }

    @GetMapping("/{pagination}")
    public List<Department> getAllDepartmentByPagination(int maxPage, int ofset) {
        return deptService.getAllDepartmentByPagination(maxPage, ofset);
    }

    @GetMapping("/{count}")
    public int getAllDepartCount() {
    	return deptService.getAllDepartCount();
    }

    @DeleteMapping("{/id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteDepartment(int id) {
    	boolean flag = deptService.deleteDepartment(id);
		if(flag)
			return new ResponseEntity<>("department deleted successfully",HttpStatus.OK);
		else
			return new ResponseEntity<>("department deleted successfully",HttpStatus.BAD_REQUEST);
    }
}
