package com.example.be.controller;

import com.example.be.dto.request.CreateAgentDto;
import com.example.be.dto.request.agent.AgentsAdminDto;
import com.example.be.dto.request.agent.AgentsEmployeeDto;
import com.example.be.dto.response.Agent.IAgentAdminDto;
import com.example.be.dto.response.Agent.IAgentEmployeeDto;
import com.example.be.dto.response.ResponseMessage;
import com.example.be.model.Agent.Agent;
import com.example.be.model.user.User;
import com.example.be.service.IAgentService;
import com.example.be.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/agent")
public class AgentController {

    @Autowired
    private IAgentService iAgentService;

    @Autowired
    private IUserService iUserService;


    @PostMapping("/create")
    public ResponseEntity<?> createAgent(@RequestBody CreateAgentDto createAgentDto){
        Agent agent = new Agent();
        BeanUtils.copyProperties(createAgentDto, agent);
        User user = iUserService.findById(createAgentDto.getIdUser());
        if (user == null) {
            return new ResponseEntity<>(new ResponseMessage("User not found!"), HttpStatus.NOT_FOUND);
        }
        agent.setUser(user);
        iAgentService.createAgent(agent);
        return new ResponseEntity<>(new ResponseMessage("Create agent success!"), HttpStatus.OK);
    }

    @PostMapping("/listEmployee")
    public ResponseEntity<Page<IAgentEmployeeDto>> getAgentsEmployee(
            @RequestBody AgentsEmployeeDto agentsEmployeeDto ,
            @PageableDefault(value = 5) Pageable pageable){
        Page<IAgentEmployeeDto> iAgentEmployeeDtos = iAgentService.getAgentsEmployee(agentsEmployeeDto, pageable);
        if (iAgentEmployeeDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(iAgentEmployeeDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/listAdmin")
    public ResponseEntity<Page<IAgentAdminDto>> getAgentsAdmin(
            @RequestBody AgentsAdminDto agentsAdminDto ,
            @PageableDefault(value = 5) Pageable pageable){
        Page<IAgentAdminDto> iAgentAdminDtos = iAgentService.getAgentsAdmin(agentsAdminDto, pageable);
        if (iAgentAdminDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(iAgentAdminDtos, HttpStatus.OK);
    }
}
