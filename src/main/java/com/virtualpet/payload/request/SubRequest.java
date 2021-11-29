package com.virtualpet.payload.request;

import com.virtualpet.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubRequest {
    private String name;
    private User user;
    private Long subId;
}
