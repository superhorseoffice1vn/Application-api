package com.example.be.service;

import com.example.be.dto.request.agent.AgentsAdminDto;
import com.example.be.dto.request.agent.AgentsEmployeeDto;
import com.example.be.dto.response.Agent.IAgentAdminDto;
import com.example.be.dto.response.Agent.IAgentEmployeeDto;
import com.example.be.model.Agent.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface IAgentService {

    Agent createAgent(Agent agent);

    Agent findById(Integer id);

    Agent updateAgent(Agent agent);

    @Transactional
    void deleteAgent(Integer id);

    Page<IAgentEmployeeDto> getAgentsEmployee(AgentsEmployeeDto agentsEmployeeDto, Pageable pageable);

    Page<IAgentAdminDto> getAgentsAdmin(AgentsAdminDto agentsAdminDto, Pageable pageable);
}
