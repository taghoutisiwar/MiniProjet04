package com.siwar.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siwar.demo.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
