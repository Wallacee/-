/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.ProductType;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductTypeBean extends DAO<ProductType>{
    
    public List<ProductType> findAll() {
        TypedQuery<ProductType> namedQuery = getEntityManager().createNamedQuery("ProductType.findAll", ProductType.class);
        List<ProductType> productTypes;
        try {
            productTypes = namedQuery.getResultList();
        } catch (Exception e) {
            productTypes = null;
        }
        return productTypes;
    }
    
}
