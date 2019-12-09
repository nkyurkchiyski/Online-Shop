package com.example.shop.base.dao.impl;


import com.example.shop.base.constants.ErrorMessage;
import com.example.shop.base.dao.ProductDao;
import com.example.shop.base.dto.SearchDto;
import com.example.shop.base.exception.NoSuchEntityException;
import com.example.shop.base.exception.NonUniqueEntityException;
import com.example.shop.base.model.Product;
import org.apache.aries.blueprint.annotation.bean.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Bean(id = "productDao")
public class ProductDaoImpl implements ProductDao
{
    @PersistenceContext(unitName = "online-shop")
    EntityManager em;


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
        return this.em.createQuery("SELECT p FROM Product p WHERE p.isActive = true", Product.class).getResultList();
    }


    @Override
    public Product findById(Integer id) throws NoSuchEntityException, NonUniqueEntityException
    {
        final List<Product> resultList = this.em.createQuery("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.categories LEFT JOIN FETCH p.orders WHERE p.id = :id", Product.class)//
                                                .setParameter("id", id)
                                                .getResultList();

        if (resultList.isEmpty())
        {
            throw new NoSuchEntityException(ErrorMessage.PRODUCT_DOES_NOT_EXIST);
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
        return this.em.createQuery("SELECT count(p) FROM Product p", Long.class)//
                      .getSingleResult();
    }


    @Override
    public List<Product> findAllByFilters(SearchDto dto)
    {
        final CriteriaQuery<Product> query = this.createFindAllByFiltersCriteria(dto);
        return this.em.createQuery(query).getResultList();
    }


    @Override
    public List<Product> findAllPaginated(int firstResult, int maxResults)
    {
        return this.em.createQuery("SELECT p FROM Product p WHERE p.isActive = true", Product.class)//
                      .setFirstResult(firstResult)
                      .setMaxResults(maxResults)
                      .getResultList();
    }


    @Override
    public List<Product> findAllByFiltersPaginated(SearchDto dto, int firstResult, int maxResults)
    {
        final CriteriaQuery<Product> query = this.createFindAllByFiltersCriteria(dto);
        return this.em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }


    @Override
    public Long sizeAllByFilters(SearchDto dto)
    {
        final CriteriaQuery<Long> query = this.createSizeAllByFiltersCriteria(dto);
        return this.em.createQuery(query).getSingleResult();
    }


    private CriteriaQuery<Product> createFindAllByFiltersCriteria(final SearchDto dto)
    {
        final CriteriaBuilder cb = this.em.getCriteriaBuilder();
        final CriteriaQuery<Product> query = cb.createQuery(Product.class).distinct(true);
        final Root<Product> product = query.from(Product.class);
        this.addFiltersToQuery(dto, cb, query, product);
        return query;
    }


    private CriteriaQuery<Long> createSizeAllByFiltersCriteria(final SearchDto dto)
    {
        final CriteriaBuilder cb = this.em.getCriteriaBuilder();
        final CriteriaQuery<Long> query = cb.createQuery(Long.class);
        final Root<Product> product = query.from(Product.class);
        query.select(cb.countDistinct(product));

        this.addFiltersToQuery(dto, cb, query, product);
        return query;
    }


    private void addFiltersToQuery(final SearchDto dto, final CriteriaBuilder cb, final CriteriaQuery< ? > query, final Root<Product> product)
    {
        final Join<Object, Object> categories = product.join("categories", JoinType.LEFT);
        final List<Predicate> conditionsList = new ArrayList<Predicate>();

        conditionsList.add(cb.equal(product.get("isActive"), true));
        conditionsList.add(cb.and(cb.ge(product.get("price"), dto.getMinPrice()), cb.le(product.get("price"), dto.getMaxPrice())));

        if (dto.getCategoryIds() != null)
        {
            conditionsList.add(categories.get("id").in(dto.getCategoryIds()));
        }

        if (dto.getSearchTerms() != null)
        {
            conditionsList.add(cb.or(dto.getSearchTerms().stream().map(x -> cb.like(product.get("name"), x)).toArray(Predicate[]::new)));
        }

        query.where(conditionsList.stream().toArray(Predicate[]::new));
    }

}
