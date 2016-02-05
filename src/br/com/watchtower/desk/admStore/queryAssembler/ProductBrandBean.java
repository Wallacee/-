/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.ProductBrand;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductBrandBean extends DAO<ProductBrand> {

    public List<ProductBrand> findAll() {
        TypedQuery<ProductBrand> namedQuery = getEntityManager().createNamedQuery("ProductBrand.findAll", ProductBrand.class);
        List<ProductBrand> productBrands;
        try {
            productBrands = namedQuery.getResultList();
        } catch (Exception e) {
            productBrands = null;
        }
        return productBrands;
    }

    public ProductBrand findByBrandName(String brandName) {
        TypedQuery<ProductBrand> typedQuery = getEntityManager().createNamedQuery("ProductBrand.findByBrandName", ProductBrand.class);
        typedQuery.setParameter("brandName", brandName);
        try {
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
