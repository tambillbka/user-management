package com.tampn.usermanagement.payloads.responses;

import com.tampn.usermanagement.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FindUserResponse {
    private List<User> users;
    private int total;
}
