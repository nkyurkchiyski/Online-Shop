package com.example.shop.base.dao.impl;


import com.example.shop.base.dao.ProductDao;
import com.example.shop.base.model.Product;
import org.apache.aries.blueprint.annotation.bean.Bean;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Bean(id = "productDao")
public class ProductDaoImpl implements ProductDao
{
    @PersistenceContext(unitName = "online-shop")
    EntityManager em;


    @Override
    public Product findByName(String productName)
    {
        Product product;
        try
        {
            product = this.em.createQuery("SELECT p FROM Product p WHERE name = :name", Product.class)//
                             .setParameter("name", productName)
                             .getSingleResult();
        }
        catch (NoResultException e)
        {
            product = null;
        }
        return product;
    }


    @Override
    public Product save(Product entity)
    {
        this.em.getTransaction().begin();
        this.em.persist(entity);
        this.em.getTransaction().commit();
        return entity;
    }


    @Override
    public void update(Product entity)
    {
        this.em.getTransaction().begin();
        this.em.merge(entity);
        this.em.getTransaction().commit();
    }


    @Override
    public void delete(Product entity)
    {
        this.em.getTransaction().begin();
        this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
        this.em.getTransaction().commit();
    }


    @Override
    public List<Product> findAll()
    {
        return this.createFindAllTypedQuery().getResultList();
    }


    @Override
    public Product findById(Integer id)
    {
        Product product;
        try
        {
            product = this.em.createQuery("SELECT p FROM Product p " //
                                          + "LEFT JOIN FETCH p.categories " //
                                          + "LEFT JOIN FETCH p.orders " //
                                          + "WHERE p.id = :id",
                                          Product.class)//
                             .setParameter("id", id)
                             .getSingleResult();
        }
        catch (NoResultException e)
        {
            product = null;
        }
        return product;
    }


    @Override
    public Long size()
    {
        return this.em.createQuery("SELECT count(p) FROM Product p", Long.class)//
                      .getSingleResult();
    }


    @Override
    public List<Product> findAllByFilters(String[] searchTerms, String categoryIds, BigDecimal minPrice, BigDecimal maxPrice)
    {
        final String query = this.createFindAllByFiltersQuery(searchTerms, categoryIds, minPrice, maxPrice);
        return this.em.createQuery(query, Product.class).getResultList();
    }


    @Override
    public List<Product> findAllByFiltersPaginated(String[] searchTerms, String categoryIds, BigDecimal minPrice, BigDecimal maxPrice, int firstResult, int maxResults)
    {
        final String query = this.createFindAllByFiltersQuery(searchTerms, categoryIds, minPrice, maxPrice);
        return this.em.createQuery(query, Product.class)//
                      .setFirstResult(firstResult)
                      .setMaxResults(maxResults)
                      .getResultList();
    }


    @Override
    public List<Product> findAllPaginated(int firstResult, int maxResults)
    {
        return this.createFindAllTypedQuery()//
                   .setFirstResult(firstResult)
                   .setMaxResults(maxResults)
                   .getResultList();
    }


    private String createFindAllByFiltersQuery(String[] searchTerms, String categoryIds, BigDecimal minPrice, BigDecimal maxPrice)
    {
        final StringBuilder sb = new StringBuilder("SELECT DISTINCT p FROM Product p LEFT JOIN p.categories c WHERE p.isActive = true ");
        sb.append(String.format("AND p.price BETWEEN %s AND %s ", minPrice, maxPrice));

        if (categoryIds != null)
        {
            sb.append(String.format("AND c.id IN(%s) ", categoryIds));
        }

        if (searchTerms != null)
        {
            final String terms = String.join(" OR ",
                                             Arrays.asList(searchTerms)
                                                   .stream()//
                                                   .map(x -> String.format("p.name LIKE '%%%s%%'", x))//
                                                   .collect(Collectors.toList()));
            sb.append(String.format("AND (%s) ", terms));
        }

        return sb.toString();
    }


    private TypedQuery<Product> createFindAllTypedQuery()
    {
        return this.em.createQuery("SELECT p FROM Product p WHERE p.isActive = true", Product.class);
    }

}
