/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.ProductRetailWeight;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductRetailWeightBean extends DAO<ProductRetailWeight>{
    public List<ProductRetailWeight> pRetailWeight;
    
    public List<ProductRetailWeight> findAll(){
        TypedQuery<ProductRetailWeight> namedQuery = getEntityManager().createNamedQuery("ProductRetailWeight.findAll",ProductRetailWeight.class);
        List<ProductRetailWeight> productRetailWeights;
        try {
            productRetailWeights = namedQuery.getResultList();
        } catch (Exception e) {
            productRetailWeights = null;
        }
        return productRetailWeights;
    }
    
}
