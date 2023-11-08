package com.oauthprovider.seviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import com.oauthprovider.services.UserGroupService;

import lombok.extern.slf4j.Slf4j;

import com.oauthprovider.models.GroupDetailsModel;
import com.oauthprovider.models.GroupRequestModel;
import com.oauthprovider.models.UserRequestModel;
import com.oauthprovider.entities.GroupEntity;
import com.oauthprovider.exception.ErrorResponse;
import com.oauthprovider.exception.GlobalExceptionHandler;
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

    @Autowired
    GlobalExceptionHandler exceptionHandler;
    
    @Override
    public Object createGroup (GroupRequestModel request) {

        GroupDetailsModel groupDetailsModel = null;

        HashMap checkGroupDetails = checkGroupName(request.getGroupName());

        log.info("\n\n -----------map------------- \n\n {}",checkGroupDetails);

        if(!checkGroupDetails.isEmpty()) {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            String currentUri = attr.getRequest().getRequestURI().toString();
            List<String> message = new ArrayList<String>();
            message.add("GroupName already Exists");
            ErrorResponse errorResponse = exceptionHandler.handleCustomException(
                400, 
                "1234", 
                message, 
                "BAD_REQUEST",
                currentUri);
            return errorResponse;
        }

        GroupEntity groupEntity = modelMapper.map(request, GroupEntity.class);

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

    @Override
    public Object updateGroup (String id, GroupRequestModel request) {

        log.info("id: {}", id);

        GroupDetailsModel groupDetailsModel = getGroup(id);
        if (groupDetailsModel == null) {
            return groupDetailsModel;
        }

        HashMap checkGroupDetails = checkGroupName(request.getGroupName());

        log.info("\n\n -----------map------------- \n\n", id, checkGroupDetails);

        if(!(checkGroupDetails.containsKey(id) || checkGroupDetails.isEmpty())) {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            String currentUri = attr.getRequest().getRequestURI().toString();
            List<String> message = new ArrayList<String>();
            message.add("GroupName already Exists");
            ErrorResponse errorResponse = exceptionHandler.handleCustomException(
                400, 
                "1234", 
                message, 
                "BAD_REQUEST",
                currentUri);
            return errorResponse;
        }

        GroupEntity groupEntity = modelMapper.map(request, GroupEntity.class);
        groupEntity.setId(id);

        groupRepository.save(groupEntity);;
        groupDetailsModel = modelMapper.map(groupEntity, GroupDetailsModel.class);

        return groupDetailsModel;
    }

    private HashMap<String, String> checkGroupName (String name) {
        HashMap<String, String> groupIdAndname = new HashMap<>();

        Optional<GroupEntity> groupEntity = groupRepository.findByGroupName(name);

        log.info("\n\n---------Object----------\n\n {}", groupEntity);

        if (groupEntity.isPresent()) {
            GroupEntity response = groupEntity.get();
            groupIdAndname.put(response.getId(), response.getGroupName());
        }

        return groupIdAndname;
    }

    @Override
    public Object createUser (String groupId, UserRequestModel request) {

        return null;

    }

}
