package com.siwar.demo.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTeam;
	private String nomTeam;
	private String descriptionTeam;

	@OneToMany(mappedBy = "team")
	@JsonIgnore
	private List<Employee> employees;

	public Team() {
		super();
	}

	public Team( String nomTeam, String descriptionTeam) {
		super();
		this.nomTeam = nomTeam;
		this.descriptionTeam = descriptionTeam;
	}

	public long getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(long idTeam) {
		this.idTeam = idTeam;
	}

	public String getNomTeam() {
		return nomTeam;
	}

	public void setNomTeam(String nomTeam) {
		this.nomTeam = nomTeam;
	}

	public String getDescriptionTeam() {
		return descriptionTeam;
	}

	public void setDescriptionTeam(String descriptionTeam) {
		this.descriptionTeam = descriptionTeam;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	
}
