package com.siwar.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.siwar.demo.entities.Employee;
import com.siwar.demo.service.EmployeeService;

@SpringBootApplication
public class DemoApplication  implements CommandLineRunner  {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

		
		repositoryRestConfiguration.exposeIdsFor(Employee.class);
	}
}
