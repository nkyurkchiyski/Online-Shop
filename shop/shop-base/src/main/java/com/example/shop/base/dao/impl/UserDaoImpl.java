package com.example.shop.base.dao.impl;


import com.example.shop.base.dao.UserDao;
import com.example.shop.base.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;


public class UserDaoImpl implements UserDao
{
    @PersistenceContext(unitName = "online-shop")
    EntityManager entityManager;


    @Override
    public User findByUserName(String userName)
    {
        this.entityManager.getTransaction().begin();
        final User user = this.entityManager.createQuery("SELECT u FROM t_Users u WHERE u.cUserName = :userName", User.class)//
                                            .setParameter("userName", userName)
                                            .getSingleResult();
        this.entityManager.getTransaction().commit();
        return user;
    }


    @Override
    public User findByEmail(String email)
    {
        this.entityManager.getTransaction().begin();
        final User user = this.entityManager.createQuery("SELECT u FROM t_Users u WHERE u.cUserEmail = :email", User.class)//
                                            .setParameter("email", email)
                                            .getSingleResult();
        this.entityManager.getTransaction().commit();
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
    public void update(User entity)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }


    @Override
    public void delete(User entity)
    {
        this.entityManager.getTransaction().begin();
        //Change to setActive(false)
        this.entityManager.remove(entity);
        this.entityManager.getTransaction().commit();
    }


    @Override
    public List<User> findAll()
    {
        this.entityManager.getTransaction().begin();
        final List<User> users = this.entityManager.createQuery("SELECT u FROM t_Users u", User.class)//
                                                   .getResultList();
        this.entityManager.getTransaction().commit();
        return users;
    }


    @Override
    public User findById(Integer id)
    {
        this.entityManager.getTransaction().begin();
        final User user = this.entityManager.find(User.class, id);
        this.entityManager.getTransaction().commit();
        return user;
    }


    @Override
    public Integer size()
    {
        this.entityManager.getTransaction().begin();
        final Integer size = this.entityManager.createQuery("SELECT count(u) FROM User u", Integer.class).getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }
}
