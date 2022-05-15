package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,Integer> {

    public final static String FIND_BY_USER_NAME_QUERY = "SELECT u FROM Users u WHERE u.userName= :userName";
    public final static String FIND_BY_USER_CRED_QUERY = "SELECT u FROM Users u WHERE u.userName= :userName and u.password= :password";
    @Transactional
    @Query(FIND_BY_USER_CRED_QUERY)
    public List<Users> findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
    @Transactional
    @Query(FIND_BY_USER_NAME_QUERY)
    public List<Users> findByUserName(@Param("userName") String userName);
}
