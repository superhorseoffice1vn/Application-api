package com.example.be.service.impl;

import com.example.be.model.Agent.Agent;
import com.example.be.repository.agent.IAgentRepository;
import com.example.be.service.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService implements IAgentService {

    @Autowired
    private IAgentRepository iAgentRepository;

    @Override
    public Agent createAgent(final Agent agent) {
        return iAgentRepository.save(agent);
    }
}
