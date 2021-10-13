package com.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.demo.dao.IComplianceRepository;
import com.demo.entities.Compliance;
import com.demo.entities.StatusReport;
import com.demo.service.IComplianceServiceImpl;
// extend with - a repeatable annotation that is used to register extensions for the annotated test class 
@ExtendWith(MockitoExtension.class) // MockitoExtension - Extension that initializes mocks and handles strict stubbings

public class IComplianceServiceTest {
	@Mock
	private IComplianceRepository complianceRepository;
	
	@InjectMocks
	private IComplianceServiceImpl complianceService;
	
	@Test
	// to test get all compliance 
	void getAllCompliance() {
		List<Compliance> list = new ArrayList<Compliance>();
		Compliance comp1 = new Compliance(111,"working");
		Compliance comp2 = new Compliance(222,"works");
		list.add(comp1);
		list.add(comp2);
		//Enables stubbing methods
		when(complianceRepository.findAll()).thenReturn(list);//Sets a return value to be returned 
		List<Compliance> compList = complianceService.getAllRl();
		assertEquals(2,compList.size());	
	}
	
	@Test
	// to test get all compliance by id
	void getAllComp() {
		Compliance comp1 = new Compliance(111,"working");
		complianceRepository.findById(comp1.getComplianceId());
		assertEquals(111,comp1.getComplianceId());	
	}
	
	@Test
	// to test addition of compliance
	void saveComplianceTest() {
		Compliance comp = new Compliance(111,"working");
		when(complianceRepository.save(comp)).thenReturn(comp);
		Compliance savedComp = complianceService.createRL(comp);
		assertThat(savedComp).isNotNull();
		assertEquals(111,savedComp.getComplianceId());
		assertEquals("working",savedComp.getDetails());
	}
	
	@Test
	// to test addition of status report 
	void saveStatusReportTest() {
		Compliance comp = new Compliance(111,"working");
		StatusReport sts=new StatusReport(comp,"test");
		when(complianceRepository.save(sts)).thenReturn(sts);
		StatusReport savedsts = complianceService.createStatusReport(sts);
		assertThat(savedsts).isNotNull();
		assertEquals(111,savedsts.getCompliance().getComplianceId());
	}
}
