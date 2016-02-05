/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.ProductCategory;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductCategoryBean extends DAO<ProductCategory>{
    
    public List<ProductCategory> findAll(){
        TypedQuery<ProductCategory> namedQuery = getEntityManager().createNamedQuery("ProductCategory.findAll",ProductCategory.class);
        List<ProductCategory> productCategorys;
        try {
            productCategorys = namedQuery.getResultList();
        } catch (Exception e) {
            productCategorys = null;
        }
        return productCategorys;
    }
    
    public ProductCategory findByCategory(String category){
    TypedQuery<ProductCategory> typedQuery = getEntityManager().createNamedQuery("ProductCategory.findByCategory",ProductCategory.class);
    typedQuery.setParameter("category", category);
        try {
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
