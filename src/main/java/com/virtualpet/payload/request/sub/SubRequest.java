package com.virtualpet.payload.request.sub;

import com.virtualpet.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubRequest {

    private String name;

    private User user;

    private long id;
}
