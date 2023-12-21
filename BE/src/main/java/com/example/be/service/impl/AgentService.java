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

import java.util.List;

@Service
public class AgentService implements IAgentService {

    @Autowired
    private IAgentRepository iAgentRepository;

    @Override
    public Agent createAgent(final Agent agent) {
        return iAgentRepository.save(agent);
    }

    @Override
    public Agent findById(final Integer id) {
        return iAgentRepository.findById(id).orElse(null);
    }

    @Override
    public Agent updateAgent(final Agent agent) {
        return iAgentRepository.save(agent);
    }

    @Override
    public void deleteAgent(final Integer id) {
        iAgentRepository.deleteAgent(id);
    }

    @Override
    public List<Integer> getAgentIds(final List<Integer> idList) {
        return iAgentRepository.getAgentIds(idList);
    }

    @Override
    public List<Agent> getAll() {
        return iAgentRepository.getAll();
    }

    @Override
    public List<Integer> findByListId(final List<Integer> idList) {
        return iAgentRepository.findByListId(idList);
    }

    @Override
    public void removeByListId(final List<Integer> idList) {
        iAgentRepository.removeByListId(idList);
    }

    @Override
    public void updateListId(final Integer id, final List<Integer> idList) {
        iAgentRepository.updateListId(id , idList);
    }

    @Override
    public Page<IAgentEmployeeDto> getAgentsEmployee(final AgentsEmployeeDto agentsEmployeeDto, final Pageable pageable) {
        return iAgentRepository.getAgentsEmployee(agentsEmployeeDto,pageable);
    }

    @Override
    public Page<IAgentAdminDto> getAgentsAdmin(final AgentsAdminDto agentsAdminDto, final Pageable pageable) {
        return iAgentRepository.getAgentsAdmin(agentsAdminDto,pageable);
    }

    @Override
    public Page<IAgentAdminDto> getAgentsAdminRestore(final AgentsAdminDto agentsAdminDto, final Pageable pageable) {
        return iAgentRepository.getAgentsAdminRestore(agentsAdminDto,pageable);
    }

    @Override
    public List<IAgentAdminDto> getListAgent(final List<Integer> idList) {
        return iAgentRepository.getListAgent(idList);
    }
}
