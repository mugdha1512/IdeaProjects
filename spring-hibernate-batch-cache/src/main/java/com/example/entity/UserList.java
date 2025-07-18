package com.example.entity;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class UserList {

    @Valid
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
