package com.example;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record TaskService(TaskRepository taskRepository) {

    public void addTaskService(TaskAddRequest req){

    Task task = Task.builder()
            .description(req.description())
            .groupId(req.groupId())
            .userId(req.userId())
            .isActive(req.isActive())
            .priority(req.priority())
            .dueDate(req.dueDate())
            .build();
    System.out.println("SA");
    taskRepository.save(task);
    }


    public List<Task> getUserActiveTaskList(Integer userId,Boolean isActive){

        return taskRepository.findByUserId(userId,isActive);

    }

    public void deleteTask(Integer taskId){


        taskRepository.deleteById(taskId);
    }

    public Optional<Task> findTask(Integer taskId){

        return taskRepository.findById(taskId);
    }

    public void updateTask(TaskAddRequest taskAddRequest) {

        Task t = findTask(taskAddRequest.Id()).get();
        System.out.println(taskAddRequest.isActive());
        if(t.getDescription()!=taskAddRequest.description() && taskAddRequest.description()!=null)
            taskRepository.updateDesc(taskAddRequest.description(),t.getId());
        if(t.getGroupId()!=taskAddRequest.groupId() && taskAddRequest.groupId()!=null)
            taskRepository.updateGroup(taskAddRequest.groupId(),t.getId());
       if(taskAddRequest.isActive()!=null && t.isActive()!=taskAddRequest.isActive() )
            taskRepository.updateActivity(taskAddRequest.isActive(), t.getId());
       if(taskAddRequest.dueDate()!=null && t.getDueDate()!= taskAddRequest.dueDate())
           taskRepository.updateDueDate(taskAddRequest.dueDate(),t.getId());
       if(taskAddRequest.priority()!=null && t.getPriority()!=taskAddRequest.priority())
           taskRepository.updatePriority(taskAddRequest.priority(),t.getId());
    }

    public void deleteGroup(Integer groupId) {

        List<Task> t= taskRepository.findByGroupId(groupId);
        t.forEach((n)-> n.setGroupId(null));
        taskRepository.saveAll(t);
    }
}
