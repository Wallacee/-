/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Wallace
 */
public abstract class DAO<T> {

    private final EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("AdmStorePU");
    private final EntityManager entityManager = managerFactory.createEntityManager();
    private final EntityTransaction entityTransaction = entityManager.getTransaction();

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManagerFactory getManagerFactory() {
        return managerFactory;
    }

    public EntityTransaction getEntityTransaction() {
        return entityTransaction;
    }

//    private Class<T> entityClass;
    public void flush() {
        this.getEntityManager().flush();
    }

    public void clearCach() {
        this.getEntityManager().getEntityManagerFactory().getCache().evictAll();
    }

    public void create(T entity) {
        if (!getEntityTransaction().isActive()) {
            getEntityTransaction().begin();
        }
        getEntityManager().persist(entity);
        getEntityTransaction().commit();

    }

    public void edit(T entity) {
        if (!getEntityTransaction().isActive()) {
            getEntityTransaction().begin();
        }
        getEntityManager().persist(entity);
        this.getEntityManager().merge(entity);

    }

    public void detach(T entity) {
        if (!getEntityTransaction().isActive()) {
            getEntityTransaction().begin();
        }
        getEntityManager().persist(entity);
        this.getEntityManager().detach(entity);

    }

//    public void refresh(T enetity) {
//        getEntityTransaction().begin();
//        getEntityManager().persist(entity);
//        this.getEntityManager().refresh(enetity);
//    }
    public void remove(T entity) {
        if (!getEntityTransaction().isActive()) {
            getEntityTransaction().begin();
        }
        getEntityManager().persist(entity);
        this.getEntityManager().remove(this.getEntityManager().merge(entity));

    }

//    public T find(Object id) {
//        getEntityTransaction().begin();
//        getEntityManager().persist(entity);
//        return this.getEntityManager().find(entityClass, id);
//    }
//    public List<T> findAll() {
//        CriteriaQuery<T> criteriaQuery = this.getEntityManager().getCriteriaBuilder().createQuery(entityClass);
//        criteriaQuery.select(criteriaQuery.from(entityClass));
//        return this.getEntityManager().createQuery(criteriaQuery).getResultList();
//    }
//
//    public void getMetamodel(Root<T> root) {
//    }
//
//    public List<T> findRange(int first, int pageSize, String sortField, boolean asc, Map<String, String> filters) {
//        CriteriaBuilder criteruaBuilder = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<T> criteriaQuery = criteruaBuilder.createQuery(entityClass);
//
//        Root<T> root = criteriaQuery.from(entityClass);
//        criteriaQuery.select(root);
//
//        if (filters != null) {
//            Iterator<String> iteratorKeys = filters.keySet().iterator();
//
//            while (iteratorKeys.hasNext()) {
//                Path<?> path = root;
//                String filterKey = iteratorKeys.next();
//
//                String[] filterSplit = filterKey.split("\\.");
//                for (String split : filterSplit) {
//                    path = path.get(split);
//                }
//                criteriaQuery.where(criteruaBuilder.equal(path, filters.get(filterKey)));
//            }
//        }
//
//        if (sortField != null) {
//            Path<?> path = root;
//            String[] sortCols = sortField.split("\\.");
//            for (String sc : sortCols) {
//                path = path.get(sc);
//            }
//            if (asc) {
//                criteriaQuery.orderBy(criteruaBuilder.asc(path));
//            } else {
//                criteriaQuery.orderBy(criteruaBuilder.desc(path));
//            }
//        }
//
//        Query q = getEntityManager().createQuery(criteriaQuery);
//        q.setMaxResults(pageSize);
//        q.setFirstResult(first);
//
//        return q.getResultList();
//    }
//
//    public T findSingleByQuery(String queryName, String... params) {
//        TypedQuery<T> namedQuery = getEntityManager().createNamedQuery(queryName, entityClass);
//
//        if (params != null) {
//            for (int i = 0; i < params.length; i++) {
//                namedQuery.setParameter(i + 1, params[i]);
//            }
//        }
//        return namedQuery.getSingleResult();
//    }
//
//    public List<T> findByQuery(String queryName, String... params) {
//        TypedQuery<T> namedQuery = getEntityManager().createNamedQuery(queryName, entityClass);
//
//        if (params != null) {
//            for (int i = 0; i < params.length; i++) {
//                namedQuery.setParameter(i + 1, params[i]);
//            }
//        }
//        return namedQuery.getResultList();
//    }
//
//    public int count(Map<String, String> filters) {
//        CriteriaBuilder criteruaBuilder = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<Long> criteria = criteruaBuilder.createQuery(Long.class);
//
//        Root<T> root = criteria.from(entityClass);
//        criteria.select(criteruaBuilder.count(root));
//
//        if (filters != null) {
//            Iterator<String> iteratorKeys = filters.keySet().iterator();
//
//            while (iteratorKeys.hasNext()) {
//                Path<?> path = root;
//                String filterKey = iteratorKeys.next();
//
//                String[] filterSplit = filterKey.split("\\.");
//                for (String split : filterSplit) {
//                    path = path.get(split);
//                }
//                criteria.where(criteruaBuilder.equal(path, filters.get(filterKey)));
//            }
//        }
//
//        Query q = getEntityManager().createQuery(criteria);
//
//        return ((Long) q.getSingleResult()).intValue();
//    }
}
