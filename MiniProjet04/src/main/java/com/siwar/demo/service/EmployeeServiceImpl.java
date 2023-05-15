package com.siwar.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.siwar.demo.entities.Employee;
import com.siwar.demo.entities.Team;
import com.siwar.demo.repos.EmployeeRepository;
import com.siwar.demo.repos.TeamRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	TeamRepository teamRepository;
	@Override
	public Employee saveEmployee(Employee e) {
	return employeeRepository.save(e);
	}
	@Override
	public Employee updateEmployee(Employee e) {
	return employeeRepository.save(e);
	}
	@Override
	public void deleteEmployee(Employee e) {
		employeeRepository.delete(e);
	}
	@Override
	public void deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
	}
	@Override
	public Employee getEmployee(Long id) {
	return employeeRepository.findById(id).get();
	}
	@Override
	public List<Employee> getAllEmployees() {
	return employeeRepository.findAll();
	}
	@Override
	public Page<Employee> getAllEmployeeParPage(int page, int size) {
	return employeeRepository.findAll(PageRequest.of(page, size));
	}
	@Override
	public List<Employee> findByNom(String nom) {
		
		return employeeRepository.findByNom(nom);
	}
	@Override
	public List<Employee> findByNomContains(String nom) {
		
		return employeeRepository.findByNomContains(nom);
	}
	@Override
	public List<Employee> findByNomSalaire(String nom, Double salaire) {
		
		return employeeRepository.findByNomSalaire(nom, salaire);
	}
	@Override
	public List<Employee> findByTeam(Team team) {
		
		return employeeRepository.findByTeam(team);
	}
	@Override
	public List<Employee> findByTeamIdTeam(Long id) {
	
		return employeeRepository.findByTeamIdTeam(id);
	}
	@Override
	public List<Employee> trierEmployeesNomsSalaire() {
		
		return employeeRepository.trierEmployeesNomsSalaire();
	}
	@Override
	public List<Team> getAllTeam() {
		return teamRepository.findAll();
	}
}
