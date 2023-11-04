package com.oauthprovider.seviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.modelmapper.ModelMapper;

import com.oauthprovider.services.UserGroupService;

import lombok.extern.slf4j.Slf4j;

import com.oauthprovider.models.GroupDetailsModel;
import com.oauthprovider.models.GroupRequestModel;
import com.oauthprovider.entities.GroupEntity;
import com.oauthprovider.repositories.GroupRepository;

/* 
 * @author Chiranjeevi
*/

@Service
@Slf4j
public class GroupServiceImpl implements UserGroupService {
 
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ModelMapper modelMapper;
    
    @Override
    public Object createGroup (GroupRequestModel request) {

        GroupDetailsModel groupDetailsModel = null;
        GroupEntity groupEntity = modelMapper.map(request, GroupEntity.class);

        /* groupEntity.setGroupName(request.getGroupName());
        groupEntity.setDescription(request.getDescription()); */

        groupRepository.save(groupEntity);
        groupDetailsModel = modelMapper.map(groupEntity, GroupDetailsModel.class);

        return groupDetailsModel;

    }

    @Override
    public GroupDetailsModel getGroup (String id) {

        log.info("id: {}", id);

        GroupDetailsModel groupDetailsModel = null;

        Optional<GroupEntity> response = groupRepository.findById(id);
        groupDetailsModel = modelMapper.map(response, GroupDetailsModel.class);

        return groupDetailsModel;

    }

}
