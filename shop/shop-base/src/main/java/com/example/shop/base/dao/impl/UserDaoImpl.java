package com.example.shop.base.dao.impl;


import com.example.shop.base.constants.ErrorMessage;
import com.example.shop.base.dao.UserDao;
import com.example.shop.base.exception.NoSuchEntityException;
import com.example.shop.base.exception.NonUniqueEntityException;
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
    EntityManager em;


    @Override
    public User findByEmail(String email) throws NoSuchEntityException, NonUniqueEntityException
    {
        final List<User> resultList = this.em.createQuery("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = :email AND u.isActive = true", User.class)//
                                             .setParameter("email", email)
                                             .getResultList();

        if (resultList.isEmpty())
        {
            throw new NoSuchEntityException(ErrorMessage.USER_DOES_NOT_EXIST);
        }

        if (resultList.size() > 1)
        {
            throw new NonUniqueEntityException(ErrorMessage.UNIQUE_RESULT_NOT_FOUND);
        }

        return resultList.get(0);
    }


    @Override
    public User save(User entity)
    {
        this.em.getTransaction().begin();
        this.em.persist(entity);
        this.em.getTransaction().commit();
        return entity;
    }


    @Override
    public void update(User entity)
    {
        this.em.getTransaction().begin();
        this.em.merge(entity);
        this.em.getTransaction().commit();
    }


    @Override
    public void delete(User entity)
    {
        this.em.getTransaction().begin();
        this.em.merge(entity);
        this.em.getTransaction().commit();
    }


    @Override
    public List<User> findAll()
    {
        return this.em.createQuery("SELECT u FROM User u WHERE u.isActive = true", User.class)//
                      .getResultList();
    }


    @Override
    public User findById(Integer id) throws NoSuchEntityException, NonUniqueEntityException
    {
        final List<User> resultList = this.em.createQuery("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :id AND u.isActive = true", User.class)//
                                             .setParameter("id", id)
                                             .getResultList();

        if (resultList.isEmpty())
        {
            throw new NoSuchEntityException(ErrorMessage.USER_DOES_NOT_EXIST);
        }

        if (resultList.size() > 1)
        {
            throw new NonUniqueEntityException(ErrorMessage.UNIQUE_RESULT_NOT_FOUND);
        }

        return resultList.get(0);
    }


    @Override
    public Long size()
    {
        return this.em.createQuery("SELECT count(u) FROM User u", Long.class).getSingleResult();

    }


    @Override
    public List<User> findAllPaginated(int firstResult, int maxResults)
    {
        return this.em.createQuery("SELECT u FROM User u WHERE u.isActive = true", User.class)//
                      .setFirstResult(firstResult)
                      .setMaxResults(maxResults)
                      .getResultList();
    }


    @Override
    public boolean existsWithEmail(String email)
    {
        final List<User> resultList = this.em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)//
                                             .setParameter("email", email)
                                             .getResultList();
        return !resultList.isEmpty();
    }
}
