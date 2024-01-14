package com.example.be.repository.agent;

import com.example.be.dto.request.agent.AgentsAdminDto;
import com.example.be.dto.request.agent.AgentsEmployeeDto;
import com.example.be.dto.response.Agent.IAgentAdminDto;
import com.example.be.dto.response.Agent.IAgentEmployeeDto;
import com.example.be.model.Agent.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAgentRepository extends JpaRepository<Agent,Integer> {

    @Query(value = "select a.id as id ,\n" +
            "       a.name_agent as nameAgent ,\n" +
            "       a.phone_number as phoneNumber ,\n" +
            "       a.name_user as nameUser,\n" +
            "       a.address as address,\n" +
            "       a.location_google_map as locationGoogleMap,\n" +
            "       a.registration_date as registrationDate\n" +
            " from `agent` a " +
            " join `user` u on u.id = a.id_user " +
            " where ( a.name_agent like %:#{#agentsEmployeeDto.name}% " +
            " OR a.phone_number LIKE %:#{#agentsEmployeeDto.name}%" +
            " OR a.name_user LIKE %:#{#agentsEmployeeDto.name}%" +
            " OR a.address LIKE %:#{#agentsEmployeeDto.name}% )" +
            " and u.id = :#{#agentsEmployeeDto.id} " +
            " and a.delete_status = false " +
            " group by a.id " +
            " ORDER BY " +
            " CASE WHEN :#{#agentsEmployeeDto.sortType} = 'ASC' THEN a.name_agent END ASC, " +
            " CASE WHEN :#{#agentsEmployeeDto.sortType} = 'DESC' THEN a.name_agent END DESC, " +
            " a.id DESC",nativeQuery = true)
    Page<IAgentEmployeeDto>getAgentsEmployee(@Param("agentsEmployeeDto")AgentsEmployeeDto agentsEmployeeDto,
                                             Pageable pageable);

    @Query(value = "SELECT a.id AS id," +
            "a.name_agent AS nameAgent," +
            "a.phone_number AS phoneNumber," +
            "a.name_user AS nameUser," +
            "a.address AS address," +
            "a.location_google_map AS locationGoogleMap," +
            "a.registration_date AS registrationDate," +
            "u.name AS nameEmployee " +
            "FROM `agent` a " +
            "JOIN `user` u ON u.id = a.id_user " +
            "WHERE ( u.name LIKE %:#{#agentsAdminDto.name}%" +
            " OR a.phone_number LIKE %:#{#agentsAdminDto.name}%" +
            " OR a.name_agent LIKE %:#{#agentsAdminDto.name}%" +
            " OR a.name_user LIKE %:#{#agentsAdminDto.name}%" +
            " OR a.address LIKE %:#{#agentsAdminDto.name}% )" +
            " AND a.delete_status = false " +
            "ORDER BY " +
            "CASE WHEN :#{#agentsAdminDto.sortType} = 'ASC' THEN a.name_agent END ASC, " +
            "CASE WHEN :#{#agentsAdminDto.sortType} = 'DESC' THEN a.name_agent END DESC, " +
            "a.id DESC", nativeQuery = true)
    Page<IAgentAdminDto> getAgentsAdmin(@Param("agentsAdminDto")AgentsAdminDto agentsAdminDto,
                                        Pageable pageable);


    @Modifying(clearAutomatically = true)
    @Query(value = "update agent a set a.delete_status = true where id = :id",nativeQuery = true)
    void deleteAgent(@Param("id") Integer id);

    @Query(value = "select a.id from agent a where a.id in (:idList)",nativeQuery = true)
    List<Integer> getAgentIds(@Param("idList")List<Integer> idList);

    @Modifying(clearAutomatically = true)
    @Query(value = "update agent a set a.id_user = :id where id in (:idList)",nativeQuery = true)
    void updateListId(@Param("id") Integer id,
                      @Param("idList")List<Integer> idList);

    @Query(value = "select * from agent a where a.delete_status = false ",nativeQuery = true)
    List<Agent> getAll();

    @Query(value = "select a.id from agent a where id in (:idList) and delete_status = false ",nativeQuery = true)
    List<Integer> findByListId(@Param("idList") List<Integer> idList);

    @Query(value = "select a.id from agent a where id in (:idList) and delete_status = true ",nativeQuery = true)
    List<Integer> findByListIdRestore(@Param("idList") List<Integer> idList);

    @Modifying
    @Query(value = "update agent a set a.delete_status = true where id in (:idList)", nativeQuery = true)
    void removeByListId(@Param("idList") List<Integer> idList);

    @Modifying
    @Query(value = "update agent a set a.delete_status = false where id in (:idList)", nativeQuery = true)
    void restoreByListId(@Param("idList") List<Integer> idList);

    @Query(value = "SELECT a.id AS id," +
            "a.name_agent AS nameAgent," +
            "a.phone_number AS phoneNumber," +
            "a.name_user AS nameUser," +
            "a.address AS address," +
            "a.location_google_map AS locationGoogleMap," +
            "a.registration_date AS registrationDate," +
            "u.name AS nameEmployee " +
            "FROM `agent` a " +
            "JOIN `user` u ON u.id = a.id_user " +
            "WHERE ( u.name LIKE %:#{#agentsAdminDto.name}%" +
            " OR a.phone_number LIKE %:#{#agentsAdminDto.name}%" +
            " OR a.name_agent LIKE %:#{#agentsAdminDto.name}%" +
            " OR a.name_user LIKE %:#{#agentsAdminDto.name}%" +
            " OR a.address LIKE %:#{#agentsAdminDto.name}% )" +
            " AND a.delete_status = true ", nativeQuery = true)
    Page<IAgentAdminDto> getAgentsAdminRestore(@Param("agentsAdminDto")AgentsAdminDto agentsAdminDto,
                                        Pageable pageable);

    @Query(value = "SELECT a.id AS id, " +
            "            a.name_agent AS nameAgent, " +
            "            a.phone_number AS phoneNumber, " +
            "            a.name_user AS nameUser, " +
            "            a.address AS address, " +
            "            a.location_google_map AS locationGoogleMap, " +
            "            u.name AS nameEmployee " +
            "from `agent` a " +
            "join `user` u on u.id = a.id_user " +
            "where a.id in (:idList) and a.delete_status = false ",nativeQuery = true)
    List<IAgentAdminDto> getListAgent(@Param("idList") List<Integer> idList);

    @Query(value = "SELECT a.id AS id, " +
            "            a.name_agent AS nameAgent, " +
            "            a.phone_number AS phoneNumber, " +
            "            a.name_user AS nameUser, " +
            "            a.address AS address, " +
            "            a.location_google_map AS locationGoogleMap, " +
            "            u.name AS nameEmployee " +
            "from `agent` a " +
            "join `user` u on u.id = a.id_user " +
            "where a.id in (:idList) and a.delete_status = true ",nativeQuery = true)
    List<IAgentAdminDto> getListAgentRestore(@Param("idList") List<Integer> idList);
 }
