/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.ProductBuyValue;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductBuyValueBean extends DAO<ProductBuyValue> {

    public List<ProductBuyValue> findById(int id) {
        TypedQuery<ProductBuyValue> namedQuery = getEntityManager().createNamedQuery("ProductBuyValue.findById", ProductBuyValue.class);
        namedQuery.setParameter("id", id);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public ProductBuyValue findByStorePratileiraAmountId(int id) {
        TypedQuery<ProductBuyValue> namedQuery = getEntityManager().createNamedQuery("ProductBuyValue.findByStorePratileiraAmountId", ProductBuyValue.class);
        namedQuery.setParameter("id", id);
        try {
            return namedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ProductBuyValue> findByProductId(int productId) {
        TypedQuery<ProductBuyValue> namedQuery = getEntityManager().createNamedQuery("ProductBuyValue.findByProductId", ProductBuyValue.class);
        namedQuery.setParameter("productId", productId);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ProductBuyValue> findBiggestValue(float value) {
        TypedQuery<ProductBuyValue> namedQuery = getEntityManager().createNamedQuery("ProductBuyValue.findByBiggestValueBuy", ProductBuyValue.class);
        namedQuery.setParameter("valueBuy", value);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ProductBuyValue> findSmallestValue(float value) {
        TypedQuery<ProductBuyValue> namedQuery = getEntityManager().createNamedQuery("ProductBuyValue.findBySmallestValueBuy", ProductBuyValue.class);
        namedQuery.setParameter("valueBuy", value);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public ProductBuyValue findByValueBuy(float value){
        TypedQuery<ProductBuyValue> namedQuery = getEntityManager().createNamedQuery("ProductBuyValue.findByValueBuy", ProductBuyValue.class);
        namedQuery.setParameter("valueBuy", value);
        try {
            return namedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
