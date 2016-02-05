package br.com.watchtower.desk.admStore.queryAssembler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.ProductSaleValue;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductSaleValueBean extends DAO<ProductSaleValue> {

    public List<ProductSaleValue> findById(int productId) {
        TypedQuery<ProductSaleValue> namedQuery = getEntityManager().createNamedQuery("ProductSaleValue.findById", ProductSaleValue.class);
        namedQuery.setParameter("productId", productId);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public ProductSaleValue findByStorePratileiraAmountId(int id) {
        TypedQuery<ProductSaleValue> namedQuery = getEntityManager().createNamedQuery("ProductSaleValue.findByStorePratileiraAmountId", ProductSaleValue.class);
        namedQuery.setParameter("id", id);
        try {
            return namedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ProductSaleValue> findByProductId(int productId) {
        TypedQuery<ProductSaleValue> namedQuery = getEntityManager().createNamedQuery("ProductSaleValue.findByProductId", ProductSaleValue.class);
        namedQuery.setParameter("productId", productId);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ProductSaleValue> findBiggestValue(float value) {
        TypedQuery<ProductSaleValue> namedQuery = getEntityManager().createNamedQuery("ProductSaleValue.findByBiggestValueSale", ProductSaleValue.class);
        namedQuery.setParameter("value", value);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ProductSaleValue> findSmallestValue(float value) {
        TypedQuery<ProductSaleValue> namedQuery = getEntityManager().createNamedQuery("ProductSaleValue.findBySmallestValueSale", ProductSaleValue.class);
        namedQuery.setParameter("value", value);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public ProductSaleValue findByProductSaleValue(float value){
        TypedQuery<ProductSaleValue> namedQuery = getEntityManager().createNamedQuery("ProductSaleValue.findByValue", ProductSaleValue.class);
        namedQuery.setParameter("value", value);
        try {
            return namedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    

}
