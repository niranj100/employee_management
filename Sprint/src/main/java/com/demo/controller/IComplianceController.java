package com.demo.controller;

import java.net.URI;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.demo.entities.Compliance;
import com.demo.entities.StatusReport;
import com.demo.service.IComplianceService;

@RestController // This annotation are treated as controllers where @RequestMapping methods assume @ResponseBody semantics by default
@RequestMapping("/compliance") //Annotation for mapping web requests
public class IComplianceController {
	@Autowired
	IComplianceService service;
	
    @PostMapping
    //Marks a method or exception class with the status code and reason that should be returned. 
    @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Compliance> createRL(@Valid @RequestBody Compliance co) {
    	Compliance comp = service.createRL(co);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{com_id}")
				.buildAndExpand(comp.getComplianceId())
				.toUri();
		return ResponseEntity.created(location).body(comp);
	}
    
    @GetMapping
	public List<Compliance> getAllRl(){
		return service.getAllRl();
	}
    
    @GetMapping("/{compid}")
    //Extension of HttpEntity that adds an HttpStatus status code.
	public ResponseEntity<Compliance> getAllRlById(@PathVariable int compid){
    	if(service.getAllRlById(compid)==null) {
    		throw new EntityNotFoundException("compliance Id not found");
    	}else {
    		return new ResponseEntity<Compliance>(service.getAllRlById(compid),HttpStatus.OK);
    	}
	}
    
    @PostMapping("/status")
    @ResponseStatus(HttpStatus.CREATED) 
	public void createStatusReport(@RequestBody StatusReport statusreport) { //Request Body - Annotation indicating a method parameter should be bound to the body of the web request.
		service.createStatusReport(statusreport);
	}
    
    @GetMapping("/statusReport/{userid}")
	public List<StatusReport> getAllStatusReport(@PathVariable int userid) {// Path Variable - Annotation which indicates that a method parameter should be bound to a URI templatevariable
		return service.getAllStatusReport(userid);
	}
}
