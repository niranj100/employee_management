package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.IComplianceRepository;
import com.demo.entities.Compliance;
import com.demo.entities.StatusReport;

@Service  //Indicates that an annotated class is a "Service"
public class IComplianceServiceImpl implements  IComplianceService{
	@Autowired // Marks a constructor, field, setter method, or config method as to be autowired bySpring's dependency injection facilities
	private  IComplianceRepository repository;

	@Override
	public Compliance createRL(Compliance co) {
		return repository.save(co);
	}

	@Override
	public List<Compliance> getAllRl() {
		return (List<Compliance>) repository.findAll();
	}

	@Override
	public Compliance getAllRlById(int compid) {
		return repository.findById(compid).get();
	}

	@Override
	public StatusReport createStatusReport(StatusReport statusreport) {
		return repository.save(statusreport);
	}

	@Override
	public List<StatusReport> getAllStatusReport(int userid) {
		return repository.getByUserId(userid);
	}
}
