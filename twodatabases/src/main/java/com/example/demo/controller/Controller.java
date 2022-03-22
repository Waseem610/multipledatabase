package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Employee.Employee;
import com.example.demo.Employee.EmployeeDao;
import com.example.demo.Manager.Manager;
import com.example.demo.Manager.ManagerDao;

@RestController
@RequestMapping("/api/v1/")
public class Controller {
	@Autowired
	EmployeeDao dao;
	@Autowired
	ManagerDao dao2;
	
	@PostMapping("addemployee")
	public String addEmployee(@RequestBody Employee employee) {
		Employee save = dao.save(employee);
		if(save==null)
			return "failed";
		return "dataAdded";
	}
	
	@PostMapping("update")
	public String updateEmployee(@RequestBody Employee employee) {
		Employee save = dao.save(employee);
		if(save==null)
			return "failed";
		return "dataAdded";
	}
	@PostMapping("addmanager")
	public String addManager(@RequestBody Manager manager) {
		 Manager save = dao2.save(manager);
		if(save==null) {
			return "failed";}
		return "dataAdded";
	}
}
