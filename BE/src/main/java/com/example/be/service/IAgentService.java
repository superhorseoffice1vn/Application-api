package com.example.be.service;

import com.example.be.dto.request.agent.AgentsAdminDto;
import com.example.be.dto.request.agent.AgentsEmployeeDto;
import com.example.be.dto.response.Agent.IAgentAdminDto;
import com.example.be.dto.response.Agent.IAgentEmployeeDto;
import com.example.be.model.Agent.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAgentService {

    Agent createAgent(Agent agent);

    Agent findById(Integer id);

    Agent updateAgent(Agent agent);

    @Transactional
    void deleteAgent(Integer id);

    List<Integer> getAgentIds(List<Integer> idList);

    List<Agent> getAll();

    List<Integer> findByListId(List<Integer> idList);

    @Transactional
    void removeByListId(List<Integer> idList);

    @Transactional
    void updateListId(Integer id , List<Integer> idList);

    Page<IAgentEmployeeDto> getAgentsEmployee(AgentsEmployeeDto agentsEmployeeDto, Pageable pageable);

    Page<IAgentAdminDto> getAgentsAdmin(AgentsAdminDto agentsAdminDto, Pageable pageable);

    Page<IAgentAdminDto> getAgentsAdminRestore(AgentsAdminDto agentsAdminDto, Pageable pageable);

    List<IAgentAdminDto> getListAgent(List<Integer> idList);
}
