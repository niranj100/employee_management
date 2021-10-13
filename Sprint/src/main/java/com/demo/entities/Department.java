package com.demo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="department")
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor // a default constructor and a parameterized constructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Annotation that can be used to either suppress serialization of properties 
public class Department implements Serializable {
	//Serializability of a class is enabled by the class implementing thejava.io.Serializable interface. 
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) //for automatic generation of id
    @Column(name = "departid")
    private int departId;
    
    @Column(name="departname")
    //@NotNull(message="department can't be null")
    private String departName;
    
    //joining compliance and Department using one to one association
    @OneToOne(mappedBy="department")
    private Compliance compliance;
    
    //joining employee and Department using one to one association
    @OneToMany(mappedBy="department",cascade = CascadeType.ALL)
    
    private Set<Employee> employee = new HashSet<Employee>();
    
    //joining statusReport and Department using one to one association
    @OneToOne(mappedBy="department")
    private StatusReport statusReport;
    
    public Department(int i, String string) {
		this.departId=i;
		this.departName=string;
	}
	
    @JsonIgnore
	public Set<Employee> getEmployee() {
		return employee;
	}
    
    @JsonIgnore
	public Compliance getCompliance() {
		return compliance;
	}
    
    @JsonIgnore
	public StatusReport getStatusReport() {
		return statusReport;
	}
}