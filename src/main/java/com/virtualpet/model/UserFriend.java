package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "friends_request")
public class UserFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.UserView.class, Views.FriendView.class})
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView({Views.UserView.class, Views.FriendView.class})
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_request_id")
    @JsonView({Views.UserView.class, Views.FriendView.class})
    private User userRequest;

    public UserFriend() {
    }

    public UserFriend(User user, User userRequest) {

        this.user = user;
        this.userRequest = userRequest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(User userRequest) {
        this.userRequest = userRequest;
    }

}
