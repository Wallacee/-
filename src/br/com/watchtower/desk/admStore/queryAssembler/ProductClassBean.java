/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.ProductClass;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductClassBean extends DAO<ProductClass>{
    public List<ProductClass> pClasses;
    
    public List<ProductClass> findAll(){
        TypedQuery<ProductClass> namedQuery = getEntityManager().createNamedQuery("ProductClass.findAll",ProductClass.class);
        List<ProductClass> productsClasses;
        try {
            pClasses = namedQuery.getResultList();
        } catch (Exception e) {
            pClasses = null;
        }
        return pClasses;
    }
    
}
