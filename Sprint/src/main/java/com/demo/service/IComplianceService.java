package com.demo.service;

import java.util.List;

import com.demo.entities.Compliance;
import com.demo.entities.StatusReport;

public interface IComplianceService {
	public Compliance createRL(Compliance co);
	public List<Compliance> getAllRl();
	public Compliance getAllRlById(int compid);
	public StatusReport createStatusReport(StatusReport statusreport);
	public List<StatusReport> getAllStatusReport(int userid);
}
