package com.siwar.demo.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "employeel", types = { Employee.class })
public interface EmployeeProjection {
	public String getNom();
}
