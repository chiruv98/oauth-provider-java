package com.oauthprovider.models;

import java.io.Serializable;

import lombok.Data;

/* 
 * @author Chiranjeevi
*/

@Data
public class GroupDetailsModel implements Serializable{
    
    private String id;
    private String groupName;
    private String description;

}
