package com.virtualpet.repository;

import com.virtualpet.model.User;
import com.virtualpet.model.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserFriendRepository extends JpaRepository<UserFriend, Long> {
    Set<UserFriend> findAllByUser(User user);

    Set<UserFriend> findAllByUserRequest(User user);
}
