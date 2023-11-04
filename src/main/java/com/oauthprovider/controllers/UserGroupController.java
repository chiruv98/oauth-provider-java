package com.oauthprovider.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oauthprovider.models.GroupRequestModel;
import com.oauthprovider.exception.ErrorResponse;
import com.oauthprovider.exception.GlobalExceptionHandler;
import com.oauthprovider.models.GroupDetailsModel;
import com.oauthprovider.services.UserGroupService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

    @Autowired
    private GlobalExceptionHandler exceptionHandler;

    @PostMapping (path = "/group")
    public ResponseEntity<?> createGroup(@Valid @RequestBody GroupRequestModel request) throws MethodArgumentNotValidException {

        /* if (request.getDescription() == null || request.getDescription().isEmpty()) {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            String currentUri = attr.getRequest().getRequestURI().toString();
            ErrorResponse errorResponse = exceptionHandler.handleCustomException(
                400, 
                "1234", 
                "Description cannot be null or empty", 
                "Bad Request",
                currentUri);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
        } */

        return new ResponseEntity<>((GroupDetailsModel) userGroupService.createGroup(request), HttpStatus.OK);
    }

    @GetMapping (path = "/group/{groupId}")
    public ResponseEntity<GroupDetailsModel> getGroup(@PathVariable("groupId") @NotNull String id) {
        log.info("id: {}", id);
        return new ResponseEntity<>((GroupDetailsModel) userGroupService.getGroup(id), HttpStatus.OK); 
    }

    @GetMapping (path = "/hello")
    @ResponseBody
    public String helloWorld () {
        return "Hello World!";
    }
}
