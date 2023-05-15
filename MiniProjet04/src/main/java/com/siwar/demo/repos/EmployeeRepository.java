package com.siwar.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.siwar.demo.entities.Employee;
import com.siwar.demo.entities.Team;

@Repository
@RepositoryRestResource(path = "rest")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query(value ="Select e FROM Employee e where e.nom LIKE %?1%")
	List<Employee> findByNom(String nom);


	List<Employee> findByNomContains(String nom);

	@Query("select e from Employee e where e.nom like %:nom and e.salaire > :salaire")
	List<Employee> findByNomSalaire(@Param("nom") String nom, @Param("salaire") Double salaire);

	@Query("select e from Employee e where e.team= ?1")
	List<Employee> findByTeam(Team team);

	List<Employee> findByTeamIdTeam(Long id);

	@Query("select e from Employee e order by e.nom ASC, e.salaire DESC")
	List<Employee> trierEmployeesNomsSalaire();







}
