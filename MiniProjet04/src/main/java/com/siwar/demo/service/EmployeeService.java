package com.siwar.demo.service;

import java.util.List;
import org.springframework.data.domain.Page;


import com.siwar.demo.entities.Employee;
import com.siwar.demo.entities.Team;

public interface EmployeeService {
	Employee saveEmployee(Employee e);

	Employee updateEmployee(Employee e);

	void deleteEmployee(Employee e);

	void deleteEmployeeById(Long id);

	Employee getEmployee(Long id);

	List<Employee> getAllEmployees();

	Page<Employee> getAllEmployeeParPage(int page, int size);

	List<Employee> findByNom(String nom);

	List<Employee> findByNomContains(String nom);

	List<Employee> findByNomSalaire(String nom, Double salaire);

	List<Employee> findByTeam(Team team);

	List<Employee> findByTeamIdTeam(Long id);

	List<Employee> trierEmployeesNomsSalaire();

	List<Team> getAllTeam();

}
