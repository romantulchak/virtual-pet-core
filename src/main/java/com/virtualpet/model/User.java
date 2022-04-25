package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.SubView.class, Views.FriendView.class, Views.GameSubView.class})
    private long id;

    @NotBlank
    @Size(max = 16)
    @JsonView({Views.SubView.class,Views.FriendView.class, Views.GameSubView.class})
    private String username;

    @Email
    @Size(max = 95)
    @NotBlank
    private String email;

    @Size(max = 120)
    @NotBlank
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Sub> subs;

    @ManyToMany
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name ="user_id"), inverseJoinColumns = @JoinColumn(name =  "friend_id"))
    private Set<User> friends = new LinkedHashSet<>(1);

    private String avatar;

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
}
