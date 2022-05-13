package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupRepository extends JpaRepository<Groups,Integer> {

    public final static String FIND_BY_USER_ID_QUERY = "SELECT g FROM Groups g WHERE g.userId= :userId";

    @Query(FIND_BY_USER_ID_QUERY)
    public List<Groups> findByUserId(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query("update Groups g set g.name =?1 where g.Id=?2")
    public void updateName(String name, Integer Id);


}
