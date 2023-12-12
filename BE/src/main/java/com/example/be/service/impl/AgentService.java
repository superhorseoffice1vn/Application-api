package com.example.be.service.impl;

import com.example.be.dto.request.agent.AgentsAdminDto;
import com.example.be.dto.request.agent.AgentsEmployeeDto;
import com.example.be.dto.response.Agent.IAgentAdminDto;
import com.example.be.dto.response.Agent.IAgentEmployeeDto;
import com.example.be.model.Agent.Agent;
import com.example.be.repository.agent.IAgentRepository;
import com.example.be.service.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AgentService implements IAgentService {

    @Autowired
    private IAgentRepository iAgentRepository;

    @Override
    public Agent createAgent(final Agent agent) {
        return iAgentRepository.save(agent);
    }

    @Override
    public Page<IAgentEmployeeDto> getAgentsEmployee(final AgentsEmployeeDto agentsEmployeeDto, final Pageable pageable) {
        return iAgentRepository.getAgentsEmployee(agentsEmployeeDto,pageable);
    }

    @Override
    public Page<IAgentAdminDto> getAgentsAdmin(final AgentsAdminDto agentsAdminDto, final Pageable pageable) {
        return iAgentRepository.getAgentsAdmin(agentsAdminDto,pageable);
    }
}
