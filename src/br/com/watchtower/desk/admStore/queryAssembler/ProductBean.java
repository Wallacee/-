/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.Product;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductBean extends DAO<Product>{
    public List<Product> products;
    
    public List<Product> findAll(){
        TypedQuery<Product> namedQuery = getEntityManager().createNamedQuery("Product.findAll",Product.class);
        List<Product> products;
        try {
            products = namedQuery.getResultList();
        } catch (Exception e) {
            products = null;
        }
        return products;
    }
    
}
