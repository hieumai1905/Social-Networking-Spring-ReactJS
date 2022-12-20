package com.codegym.repository;

import com.codegym.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppUserRepo extends JpaRepository<AppUser, Long> {
    @Query(value = "insert into app_user_friends values (?1, ?2)", nativeQuery = true)
    void addFriend( Long currentUserId,
                    Long targetID);
//    void blockUser(Long userID);
//
//    void followUser(Long userID);
    AppUser findAppUserByAccount_Id(Long accountId);
    AppUser existsAppUserByAccount_Id(Long accountId);

    List<AppUser> findAppUsersByDisplayNameContaining(String displayName);
}
