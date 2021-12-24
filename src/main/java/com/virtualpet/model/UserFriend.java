package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_friend")
@Getter
@Setter
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
}
