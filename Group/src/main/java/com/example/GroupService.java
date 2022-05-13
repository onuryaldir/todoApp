package com.example;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record GroupService(GroupRepository groupRepository) {


    public void addGroup(GroupRequest req){

        Groups group= Groups.builder()
                .userId(req.userId())
                .name(req.name())
                .build();


        groupRepository.save(group);
    }

    public List<Groups> getUserGroupList(Integer userId){

       return groupRepository.findByUserId(userId);
    }

    public void deleteGroup(Integer Id){

        groupRepository.deleteById(Id);
    }

    public Optional<Groups> findGroup(Integer Id){

        return groupRepository.findById(Id);
    }

    public void updateGroup(GroupRequest groupRequest){

        Groups group = findGroup(groupRequest.Id()).get();
        if(group.getName()!=groupRequest.name() && groupRequest.name()!=null)
            groupRepository.updateName(groupRequest.name(), group.getId());




    }

}
