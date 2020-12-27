package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Sub;
import com.virtualpet.model.User;
import com.virtualpet.model.Views;

import java.util.List;
import java.util.Set;

public class UserDTO {

    @JsonView({Views.UserView.class, Views.SubView.class, Views.FriendView.class})
    private long id;

    @JsonView({Views.UserView.class, Views.SubView.class, Views.FriendView.class})
    private String username;

    @JsonView({Views.UserView.class, Views.FriendView.class})
    private String email;

    @JsonView({Views.UserView.class, Views.FriendView.class})
    private List<Sub> subs;
    @JsonView({Views.UserView.class, Views.FriendView.class})
    private Set<User> friends;
    @JsonView({Views.UserView.class, Views.FriendView.class}) 
    private boolean isFriend;
    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.subs = user.getSubs();
    }
    public UserDTO(User user, User currentUser){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.subs = user.getSubs();
        this.friends = user.getFriends();
        this.isFriend = this.friends.contains(currentUser);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Sub> getSubs() {
        return subs;
    }

    public void setSubs(List<Sub> subs) {
        this.subs = subs;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }
}
