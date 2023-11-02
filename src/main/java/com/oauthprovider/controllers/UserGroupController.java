package com.oauthprovider.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oauthprovider.models.GroupRequestModel;
import com.oauthprovider.models.GroupDetailsModel;
import com.oauthprovider.services.UserGroupService;

import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

/* 
 * @author Chiranjeevi
*/

@RestController
@Slf4j

@RequestMapping ("/api/oauth-provider")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @PostMapping (path = "/group")
    public ResponseEntity<GroupDetailsModel> createGroup(@RequestBody GroupRequestModel request) {
        return userGroupService.createGroup(request);
    }

    @GetMapping (path = "/group/{groupId}")
    public ResponseEntity<GroupDetailsModel> getGroup(@PathVariable("groupId") @NotNull String id) {
        log.info("id: {}", id);
        return userGroupService.getGroup(id);
    }

    @GetMapping (path = "/hello")
    @ResponseBody
    public String helloWorld () {
        return "Hello World!";
    }
}
