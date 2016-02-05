/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.StorePratileiraAmount;
import java.util.Collection;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class StorePratileiraAmountBean extends DAO<StorePratileiraAmount> {

    public List<StorePratileiraAmount> findAll() {
        TypedQuery<StorePratileiraAmount> namedQuery = getEntityManager().createNamedQuery("StorePratileiraAmount.findAll", StorePratileiraAmount.class);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public int findLastIn() {
        TypedQuery<Integer> namedQuery = getEntityManager().createNamedQuery("StorePratileiraAmount.findLastIn", Integer.class);
        try {
            return namedQuery.getSingleResult();
        } catch (Exception e) {
            return -1;
        }
    }

    public Collection<StorePratileiraAmount> findByBuyValue(float value) {
        TypedQuery<StorePratileiraAmount> namedQuery = getEntityManager().createNamedQuery("StorePratileiraAmount.findByBuyValue", StorePratileiraAmount.class);
        namedQuery.setParameter("value", value);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Collection<StorePratileiraAmount> findBySaleValue(float value) {
        TypedQuery<StorePratileiraAmount> namedQuery = getEntityManager().createNamedQuery("StorePratileiraAmount.findBySaleValue", StorePratileiraAmount.class);
        namedQuery.setParameter("value", value);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Collection<StorePratileiraAmount> findByBuyNSaleValue(float buy, float sale) {
        TypedQuery<StorePratileiraAmount> namedQuery = getEntityManager().createNamedQuery("StorePratileiraAmount.findByBuyNSaleValue", StorePratileiraAmount.class);
        namedQuery.setParameter("buy", buy);
        namedQuery.setParameter("sale", sale);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public StorePratileiraAmount findById(int id) {
        TypedQuery<StorePratileiraAmount> namedQuery = getEntityManager().createNamedQuery("StorePratileiraAmount.findById", StorePratileiraAmount.class);
        namedQuery.setParameter("id", id);
        try {
            return namedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<StorePratileiraAmount> findAllByMeasureUnit(boolean _boolean) {
        TypedQuery<StorePratileiraAmount> namedQuery = getEntityManager().createNamedQuery("StorePratileiraAmount.findAllByMeasureUnit", StorePratileiraAmount.class);
        namedQuery.setParameter("boolean", _boolean);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<StorePratileiraAmount> findByProductId(int productId) {
        TypedQuery<StorePratileiraAmount> namedQuery = getEntityManager().createNamedQuery("StorePratileiraAmount.findByProductId", StorePratileiraAmount.class);
        namedQuery.setParameter("productId", productId);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<StorePratileiraAmount> findByProductSalledId(int productId, boolean pratileira) {
        TypedQuery<StorePratileiraAmount> namedQuery = getEntityManager().createNamedQuery("StorePratileiraAmount.findByProductSalledId", StorePratileiraAmount.class);
        namedQuery.setParameter("productId", productId);
        namedQuery.setParameter("pratileira", pratileira);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<StorePratileiraAmount> findByBuyValueNProductId(int productId, float buy) {
        TypedQuery<StorePratileiraAmount> namedQuery = getEntityManager().createNamedQuery("StorePratileiraAmount.findByBuyValueNProductId", StorePratileiraAmount.class);
        namedQuery.setParameter("buy", buy);
        namedQuery.setParameter("productId", productId);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<StorePratileiraAmount> findBySaleValueNProductId(int productId, float sale) {
        TypedQuery<StorePratileiraAmount> namedQuery = getEntityManager().createNamedQuery("StorePratileiraAmount.findBySaleValueNProductId", StorePratileiraAmount.class);
        namedQuery.setParameter("sale", sale);
        namedQuery.setParameter("productId", productId);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
