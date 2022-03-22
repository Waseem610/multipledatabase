package com.example.demo.Manager;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Manager {
	@Id
	private Integer id;
	private String name;
	private Double salary;
	private Integer fla;
}
