package com.example.repository;

import com.example.entity.User;
import java.util.List;

public interface UserDao {
    void save(User user);
    void update(User user);
    void delete(Long id);
    User getById(Long id);
    List<User> getAll();
    void flushAndClear();
    void deleteUsersByIds(List<Long> userIds);

}
