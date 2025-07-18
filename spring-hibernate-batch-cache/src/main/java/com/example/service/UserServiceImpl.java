package com.example.service;

import com.example.entity.User;
import com.example.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"users"})
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    @CacheEvict(value = "allUsers", allEntries = true)
    public User save(User user) {
        userDao.save(user);
        return user;
    }

    @Override
    @Transactional
    @Caching(
            put = @CachePut(key = "#result.id"),
            evict = @CacheEvict(value = "allUsers", allEntries = true)
    )
    public User update(User user) {
        userDao.update(user);
        return userDao.getById(user.getId()); // ✅ return fresh updated entity
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(key = "#id"),
            @CacheEvict(value = "allUsers", allEntries = true)
    })
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(key = "#id")
    public User getById(Long id) {
        System.out.println("🔍 DB Call: Fetching User with id = " + id);
        return userDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "allUsers")
    public List<User> getAll() {
        System.out.println("📋 DB Call: Fetching All Users");
        return userDao.getAll();
    }

    @Override
    @Transactional
    @CacheEvict(value = "allUsers", allEntries = true)
    public void saveAll(List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            userDao.save(users.get(i));
            if (i % 20 == 0) { // Batch size 20
                userDao.flushAndClear();  // You’ll define this in UserDao
            }
        }
    }
    @Override
    public void deleteUsersInBatch(List<Long> userIds) {
        // You can break it into chunks if needed
        final int BATCH_SIZE = 100;
        for (int i = 0; i < userIds.size(); i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, userIds.size());
            List<Long> batch = userIds.subList(i, end);
            userDao.deleteUsersByIds(batch);
        }
    }


}
