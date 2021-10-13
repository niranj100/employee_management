package com.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/** author : 
 * Niranjani
 * Pavithra
 * Poojasri
 * Priyadharshini 
 **/

@Entity
@Table(name = "statusreport")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor // a default constructor and a parameterized constructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Annotation that can be used to either suppress serialization ofproperties 
public class StatusReport implements Serializable {
	// Serializability of a class is enabled by the class implementing thejava.io.Serializable interface. 
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="status_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //for automatic generation of id
	private int statusId;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="details")
	private String details;
	
	@Column(name="create_date")
	private LocalDate createDate;
	
	//joining compliance and statusReport using one to one association
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="com_id")
	private Compliance compliance;
	
	//joining user and statusReport using one to one association
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private Users user;
	
	//joining department and statusReport using one to one association
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="departid")
	private Department department;
	
	public StatusReport(Compliance comp, String string) {
		this.compliance=comp;
		this.comments=string;
	}

	@JsonIgnore
	public Department getDepartment() {
		return department;
	}
	
	@JsonIgnore
	public Compliance getCompliance() {
		return compliance;
	}
	
	@JsonIgnore
	public Users getUser() {
		return user;
	}

}
