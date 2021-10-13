package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Compliance;
import com.demo.entities.StatusReport;

public interface IComplianceRepository extends JpaRepository<Compliance,Integer> {
	//Query - Annotation to declare finder queries directly on repository methods.
	@Query("select s from StatusReport s join s.user u where u.userId = :uid")
	List<StatusReport> getByUserId(@Param("uid") int userid);
   
	StatusReport save(StatusReport statusreport);
	
	

}