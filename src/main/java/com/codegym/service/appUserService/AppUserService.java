package com.codegym.service.appUserService;

import com.codegym.model.AppUser;
import com.codegym.repository.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements IAppUserService{
    @Autowired
    IAppUserRepo appUserRepo;

    @Override
    public List<AppUser> findAll() {
        return appUserRepo.findAll();
    }

    @Override
    public boolean save(AppUser appUser) {
        return appUserRepo.save(appUser) != null;
    }

    @Override
    public boolean delete(Long id) {
        if (appUserRepo.existsById(id)) {
            appUserRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public AppUser findById(Long id) {
        Optional<AppUser> appUserOptional = appUserRepo.findById(id);
        return appUserOptional.orElse(null);
    }

    @Override
    public AppUser findByAccount(Long id) {
        return appUserRepo.findAppUserByAccount_Id(id);
    }

    @Override
    public List<AppUser> findByDisplayNameContaining(String displayName){
        List<AppUser> userList = appUserRepo.findAppUsersByDisplayNameContaining(displayName);
        return userList;
    }
    @Override
    public void addFriend(Long currentUserId, Long targetID) {
        appUserRepo.addFriend(currentUserId, targetID);
    }

    @Override
    public void blockUser(Long currentUserId, Long userID) {

    }

    @Override
    public void followUser(Long currentUserId, Long userID) {

    }
}
