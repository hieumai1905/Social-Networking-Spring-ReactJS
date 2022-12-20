package com.codegym.service.appUserService;

import com.codegym.model.AppUser;
import com.codegym.service.IGeneralService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAppUserService extends IGeneralService<AppUser> {
    void addFriend(Long currentUserId, Long targetID);
    void blockUser(Long currentUserId, Long userID);

    void followUser(Long currentUserId, Long userID);

     AppUser findByAccount(Long id);

    List<AppUser> findByDisplayNameContaining(String displayName);

}
