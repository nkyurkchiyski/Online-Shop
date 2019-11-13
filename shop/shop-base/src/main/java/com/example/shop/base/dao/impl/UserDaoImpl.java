package com.example.shop.base.dao.impl;


import com.example.shop.base.dao.UserDao;
import com.example.shop.base.model.User;
import org.apache.aries.blueprint.annotation.bean.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;


@Transactional
@Bean(id = "userDao")
public class UserDaoImpl implements UserDao
{
    @PersistenceContext(unitName = "online-shop")
    EntityManager entityManager;


    @Override
    public User findByEmail(String email)
    {
        User user;
        try
        {
            user = this.entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email", User.class)//
                                     .setParameter("email", email)
                                     .getSingleResult();
        }
        catch (Exception e)
        {
            user = null;
        }

        return user;
    }


    @Override
    public User save(User entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }


    @Override
    public User update(User entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }


    @Override
    public void delete(User entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }


    @Override
    public List<User> findAll()
    {
        final List<User> users = this.entityManager.createQuery("SELECT u FROM User u WHERE u.isActive = true", User.class)//
                                                   .getResultList();
        return users;
    }


    @Override
    public User findById(Integer id)
    {
        final User user = this.entityManager.find(User.class, id);
        return user;
    }


    @Override
    public Integer size()
    {
        final Integer size = this.entityManager.createQuery("SELECT count(u) FROM User u", Integer.class).getSingleResult();
        return size;
    }
}
