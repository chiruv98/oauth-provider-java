package com.oauthprovider.services;

import org.springframework.http.ResponseEntity;

import com.oauthprovider.models.GroupDetailsModel;
import com.oauthprovider.models.GroupRequestModel;

/* 
 * @author Chiranjeevi
*/

public interface UserGroupService {

    ResponseEntity<GroupDetailsModel> createGroup (GroupRequestModel request);
    ResponseEntity<GroupDetailsModel> getGroup (String id);
    
}
