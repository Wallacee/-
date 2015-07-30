/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.ProductMeasureUnit;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductMeasureUnitBean extends DAO<ProductMeasureUnit>{
    public List<ProductMeasureUnit> findAll(){
        TypedQuery<ProductMeasureUnit> namedQuery = getEntityManager().createNamedQuery("ProductMeasureUnit.findAll",ProductMeasureUnit.class);
        List<ProductMeasureUnit> productMeasureUnits;
        try {
            productMeasureUnits = namedQuery.getResultList();
        } catch (Exception e) {
            productMeasureUnits = null;
        }
        return productMeasureUnits;
    }
    
}
