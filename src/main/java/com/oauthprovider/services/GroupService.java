package com.oauthprovider.services;

import com.oauthprovider.models.GroupRequestModel;

/* 
 * @author Chiranjeevi
*/

public interface GroupService {

    Object createGroup (GroupRequestModel request);
    Object getGroup (String id);
    Object updateGroup (String id, GroupRequestModel request);
    
}
