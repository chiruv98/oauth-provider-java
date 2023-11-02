package com.oauthprovider.models;

import java.io.Serializable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/* 
 * @author Chiranjeevi
*/

@Data
public class GroupRequestModel implements Serializable{

    @Valid
    @NotNull (message = "groupName cannot be Null")
    private String groupName;
    private String description;
}
