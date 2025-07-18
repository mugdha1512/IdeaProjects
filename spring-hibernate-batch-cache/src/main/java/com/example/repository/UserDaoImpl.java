package com.example.repository;

import com.example.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public void save(User user) {
        getSession().merge(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        getSession().merge(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = getSession().get(User.class, id);
        if (user != null) {
            getSession().remove(user);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return getSession().get(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return getSession().createQuery("from User", User.class).getResultList();
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public void flushAndClear() {
        em.flush();
        em.clear();
    }

    @Override
    public void deleteUsersByIds(List<Long> userIds) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("DELETE FROM User u WHERE u.id IN (:ids)");
        query.setParameter("ids", userIds);
        query.executeUpdate();
    }


}
