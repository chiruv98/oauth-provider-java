package com.oauthprovider.services;

import com.oauthprovider.models.GroupRequestModel;

/* 
 * @author Chiranjeevi
*/

public interface UserGroupService {

    Object createGroup (GroupRequestModel request);
    Object getGroup (String id);
    
}
