package com.example.be.repository.agent;

import com.example.be.model.Agent.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgentRepository extends JpaRepository<Agent,Integer> {
}
