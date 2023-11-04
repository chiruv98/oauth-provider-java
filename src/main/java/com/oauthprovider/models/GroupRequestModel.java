package com.oauthprovider.models;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/* 
 * @author Chiranjeevi
*/

@Data
public class GroupRequestModel implements Serializable{

    @NotNull (message = "groupName cannot be Null")
    @NotBlank (message = "groupName cannot be empty")
    private String groupName;

    @NotNull (message = "description cannot be Null")
    @NotBlank (message = "description cannot be empty")
    private String description;
}
