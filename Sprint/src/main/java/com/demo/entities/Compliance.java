package com.demo.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** author : 
 * Niranjani
 * Pavithra
 * Poojasri
 * Priyadharshini 
 **/

@Entity
@Table(name="compliance")
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor // a default constructor and a parameterized constructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Annotation that can be used to either suppress serialization of properties 
public class Compliance implements Serializable {
	//Serializability of a class is enabled by the class implementing thejava.io.Serializable interface. 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //for automatic generation of id
	@Column(name = "com_id")
	private int complianceId;
	
	@Column(name = "type")
	//@NotNull(message="type must not be empty")
	private String rlType;
	
	//@NotNull(message="details must not be empty")
	private String details;
	
	@Column(name ="date")
	private Date createDate;
	
	//joining Department and compliance using one to one association
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="departid")
	private Department department;
	
	@Column(name ="emp_count")
	private int empCount;
	
	@Column(name ="status_count")
	private int stsCount;
	
	//@NotNull(message="status must not be empty")
	private String status;
	
	//joining statusReport and compliance using one to one association
	@OneToOne(mappedBy="compliance", cascade = CascadeType.ALL)
	private StatusReport statusReport;
	
	public Compliance(int complianceId,String details){
		this.complianceId=complianceId;
		this.details=details;
	}
	
	@JsonIgnore
	public Department getDepartment() {
		return department;
	}
	
	public StatusReport getStatusReport() {
		return statusReport;
	}
}