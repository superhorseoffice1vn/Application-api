package com.example.be.repository.agent;

import com.example.be.dto.request.agent.AgentsAdminDto;
import com.example.be.dto.request.agent.AgentsEmployeeDto;
import com.example.be.dto.response.Agent.IAgentAdminDto;
import com.example.be.dto.response.Agent.IAgentEmployeeDto;
import com.example.be.model.Agent.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgentRepository extends JpaRepository<Agent,Integer> {

    @Query(value = "select a.id as id ,\n" +
            "       a.name_agent as nameAgent ,\n" +
            "       a.phone_number as phoneNumber ,\n" +
            "       a.name_user as nameUser,\n" +
            "       a.address as address,\n" +
            "       a.location_google_map as locationGoogleMap\n" +
            "from agent a " +
            "join user u on u.id = a.id_user " +
            "where a.name_agent like %:#{#agentsEmployeeDto.nameAgent}% " +
            "and u.id = :#{#agentsEmployeeDto.id} " +
            "group by a.id " +
            "order by a.id desc",nativeQuery = true)
    Page<IAgentEmployeeDto>getAgentsEmployee(@Param("agentsEmployeeDto")AgentsEmployeeDto agentsEmployeeDto,
                                             Pageable pageable);

    @Query(value = "select a.id as id ," +
            "       a.name_agent as nameAgent," +
            "       a.phone_number as phoneNumber," +
            "       a.name_user as nameUser," +
            "       a.address as address," +
            "       a.location_google_map as locationGoogleMap," +
            "       u.name as nameEmployee " +
            " from `agent` a " +
            " join `user` u on u.id = a.id_user " +
            " where u.name like %:#{#agentsAdminDto.nameEmployee}%" +
            " order by a.id desc",nativeQuery = true)
    Page<IAgentAdminDto> getAgentsAdmin(@Param("agentsAdminDto")AgentsAdminDto agentsAdminDto,
                                        Pageable pageable);
}
