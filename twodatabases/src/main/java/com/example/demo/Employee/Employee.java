package com.example.demo.Employee;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Employee {
	@Id
	private Integer id;
	private String name;
	private Double salary;
	private Integer fla;
	
}
