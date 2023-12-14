package com.example.be.repository.user;

import com.example.be.dto.request.employee.UpdateEmployeeDto;
import com.example.be.dto.response.employee.EmployeeDetailDto;
import com.example.be.dto.response.employee.IEmployeeDto;
import com.example.be.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository
        extends JpaRepository<User, Integer> {

    @Query(value = "select u.id as id,\n" +
            "       u.name,\n" +
            "       u.phone_number as phoneNumber,\n" +
            "       a.username\n" +
            "from `user` u\n" +
            "join `account` a on a.id = u.account_id\n" +
            "group by u.id\n" +
            "order by u.id desc ", nativeQuery = true)
    Page<IEmployeeDto> findAllEmployee(Pageable pageable);

    @Query(value = "SELECT new com.example.be.dto.response.employee.EmployeeDetailDto(u.id, u.name, u.phoneNumber, a.username) " +
            "FROM User u JOIN Account a ON a.id = u.account.id WHERE u.id = :id")
    EmployeeDetailDto getDetailEmployee(@Param("id") Integer id);

    @Query(value = "SELECT new com.example.be.dto.request.employee.UpdateEmployeeDto(u.name, u.phoneNumber, a.username , a.password) " +
            "FROM User u JOIN Account a ON a.id = u.account.id WHERE u.id = :id")
    UpdateEmployeeDto getByIdEmployee(@Param("id") Integer id);

    Boolean existsByEmail(String email);
}
