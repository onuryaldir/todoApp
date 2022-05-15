package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    public final static String FIND_BY_USER_ID_QUERY = "SELECT t FROM Task t WHERE t.userId= :userId and t.isActive= :isActive";
    public static final  String FIND_BY_GROUP_ID_QUERY="SELECT t FROM Task t WHERE t.groupId= :groupId";
    @Query(FIND_BY_USER_ID_QUERY)
    public List<Task> findByUserId(@Param("userId") Integer userId, @Param("isActive") Boolean isActive);

    @Query(FIND_BY_GROUP_ID_QUERY)
    public List<Task> findByGroupId(@Param("groupId") Integer groupId);

    @Modifying
    @Transactional
    @Query("update Task t set t.description =?1 where t.Id=?2")
    public void updateDesc(String desc, Integer Id);

    @Modifying
    @Transactional
    @Query("update Task t set t.isActive =?1 where t.Id=?2")
    public void updateActivity(Boolean isActive, Integer Id);
    @Modifying
    @Transactional
    @Query("update Task t set t.groupId =?1 where t.Id=?2")
    public void updateGroup(Integer groupId, Integer Id);

    @Query("update Task t set t.groupId =?1 where t.groupId=?2")
    public void deleteGroupById(Integer Id,Integer groupId);

    @Query("update Task t set t.dueDate=?1 where t.Id=?2")
    public void updateDueDate(Date dueDate, Integer Id);
    @Query("update Task t set t.priority=?1 where t.Id=?2")
    public void updatePriority(Integer priority, Integer Id);
}
