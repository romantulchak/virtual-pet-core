package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Sub;
import com.virtualpet.model.User;
import com.virtualpet.model.Views;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
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

    public UserDTO(User user, User currentUser){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.subs = user.getSubs();
        this.friends = user.getFriends();
        this.isFriend = this.friends.contains(currentUser);
    }

}
