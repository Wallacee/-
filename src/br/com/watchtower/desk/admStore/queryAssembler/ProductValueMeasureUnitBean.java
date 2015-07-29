/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.ProductValueMeasureUnit;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductValueMeasureUnitBean extends DAO<ProductValueMeasureUnit> {

    public List<ProductValueMeasureUnit> pValueMeasureUnit;

    public List<ProductValueMeasureUnit> findAll() {
        TypedQuery<ProductValueMeasureUnit> namedQuery = getEntityManager().createNamedQuery("ProductValueMeasureUnit.findAll", ProductValueMeasureUnit.class);
        List<ProductValueMeasureUnit> productValueMeasureUnits;
        try {
            productValueMeasureUnits = namedQuery.getResultList();
        } catch (Exception e) {
            productValueMeasureUnits = null;
        }
        return productValueMeasureUnits;
    }
}
