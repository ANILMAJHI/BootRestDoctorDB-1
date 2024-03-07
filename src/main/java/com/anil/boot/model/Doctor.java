package com.anil.boot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctor")
@DynamicUpdate
@Setter
@Getter
public class Doctor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	@NotBlank(message = "Name Cannot be Blank")
	private String name;
	@Column
	@NotBlank(message = "Specialize Cannot be Blank")
	private String specialize;
	@Column
	@Pattern(regexp = "\\d+", message = "Salary should be an integer")	
	private int salary;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialize() {
		return specialize;
	}

	public void setSpecialize(String specialize) {
		this.specialize = specialize;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", specialize=" + specialize + ", salary=" + salary + "]";
	}

}
