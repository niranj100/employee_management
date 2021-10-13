package com.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="employee_master")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor // a default constructor and a parameterized constructor
public class Employee implements Serializable {
	//Serializability of a class is enabled by the class implementing thejava.io.Serializable interface. 
	private static final long serialVersionUID = 1L;
	
	@Id
	private int userId;
	@GeneratedValue(strategy=GenerationType.IDENTITY) //for automatic generation of id
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="user_id")
	private Users user;
	
	
	@Column(name="first_name")
	@NotNull(message="first name cannot be empty")
	@Size(min=2,max=20 , message="name is not valid")
	private String firstName;
	
	@NotNull(message="last name cannot be empty")
	@Size(min=2,max=20 , message="name is not valid")
	private String lastName;
	
	@NotNull(message="DOB cannot be empty")  
	private LocalDate dob;
	
	@Email
	private String email;
	
	// joining department and employee using one to one association
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="departid",nullable=false)
	private Department department;
	
	
	public Employee(int i, String string, String string2, String string3) {
		this.userId=i;
		this.firstName=string;
		this.lastName=string2;
		this.email=string3;
	}

	@JsonIgnore
	public Users getUser() {
		return user;
	}
}
