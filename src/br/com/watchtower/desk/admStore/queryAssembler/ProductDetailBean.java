/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.ProductDetail;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductDetailBean extends DAO<ProductDetail>{
    
    public List<ProductDetail> findAll() {
        TypedQuery<ProductDetail> namedQuery = getEntityManager().createNamedQuery("ProductDetail.findAll", ProductDetail.class);
        List<ProductDetail> productDetails;
        try {
            productDetails = namedQuery.getResultList();
        } catch (Exception e) {
            productDetails = null;
        }
        return productDetails;
    }

    public ProductDetail findByDetailName(String detail) {
        TypedQuery<ProductDetail> namedQuery = getEntityManager().createNamedQuery("ProductDetail.findByDetail", ProductDetail.class);
        namedQuery.setParameter("detail", detail);
        try {
            return namedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
