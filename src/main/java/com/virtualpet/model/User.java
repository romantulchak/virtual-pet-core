package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.SubView.class, Views.FriendView.class})
    private long id;

    @NotBlank
    @JsonView({Views.SubView.class,Views.FriendView.class})
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Sub> subs;


    @ManyToMany
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name ="user_id"), inverseJoinColumns = @JoinColumn(name =  "friend_id"))
    private Set<User> friends = new LinkedHashSet<>(1);


    public User() {
    }

    public User(@NotBlank String username, @Email @NotBlank String email, @NotBlank String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @ManyToMany
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    private Integer maxNumberOfSubs = 5;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Sub> getSubs() {
        return subs;
    }

    public void setSubs(List<Sub> subs) {
        this.subs = subs;
    }

    public Integer getMaxNumberOfSubs() {
        return maxNumberOfSubs;
    }

    public void setMaxNumberOfSubs(Integer maxNumberOfSubs) {
        this.maxNumberOfSubs = maxNumberOfSubs;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
