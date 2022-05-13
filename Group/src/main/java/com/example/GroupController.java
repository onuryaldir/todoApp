package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/groups")
public record GroupController(GroupService groupService) {


    @PostMapping
    public String addGroup(@RequestBody GroupRequest groupRequest){

        if(groupRequest.name()!=null && groupRequest.userId()!=null) {
            groupService.addGroup(groupRequest);
            return "done";
        }
        return "missing value";
    }

    @DeleteMapping
    public String deleteGroup(@RequestBody Map<String,Integer> req) {
        if (req.get("Id") != null) {
            groupService.deleteGroup(req.get("Id"));
            return "done";
        }
        return "missing area";
    }
    @PutMapping
    public void updateGroup(@RequestBody GroupRequest groupRequest)
    {
        groupService.updateGroup(groupRequest);
    }

    @PostMapping("grouplist")
    public List<Groups> getUserGroups(@RequestBody Map<String,String> req){

        return groupService.getUserGroupList(Integer.valueOf(req.get("userId")));
    }
}
