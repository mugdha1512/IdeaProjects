package com.example.service;

import com.example.entity.User;
import java.util.List;

public interface UserService {
    User save(User user);
    User update(User user);
    void delete(Long id);
    User getById(Long id);
    List<User> getAll();
    void saveAll(List<User> users);
    void deleteUsersInBatch(List<Long> userIds);
}
