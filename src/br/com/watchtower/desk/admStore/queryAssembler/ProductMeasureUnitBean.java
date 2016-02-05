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
public class ProductMeasureUnitBean extends DAO<ProductMeasureUnit> {

    public List<ProductMeasureUnit> findAll() {
        TypedQuery<ProductMeasureUnit> namedQuery = getEntityManager().createNamedQuery("ProductMeasureUnit.findAll", ProductMeasureUnit.class);
        List<ProductMeasureUnit> productMeasureUnits;
        try {
            productMeasureUnits = namedQuery.getResultList();
        } catch (Exception e) {
            productMeasureUnits = null;
        }
        return productMeasureUnits;
    }

    public ProductMeasureUnit findByNameMeasure(String nameMeasure) {
        TypedQuery<ProductMeasureUnit> typedQuery = getEntityManager().createNamedQuery("ProductMeasureUnit.findByNameMeasure", ProductMeasureUnit.class);
        typedQuery.setParameter("nameMeasure", nameMeasure);
        try {
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    public ProductMeasureUnit findByShortNameMeasure(String shortNameMeasure) {
        TypedQuery<ProductMeasureUnit> typedQuery = getEntityManager().createNamedQuery("ProductMeasureUnit.findByShortNameMeasure", ProductMeasureUnit.class);
        typedQuery.setParameter("shortNameMeasure", shortNameMeasure);
        try {
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    public ProductMeasureUnit findByNameMeasureNShortNameMeasure(String nameMeasure, String shortNameMeasure) {
        TypedQuery<ProductMeasureUnit> typedQuery = getEntityManager().createNamedQuery("ProductMeasureUnit.findByNameMeasureNShortNameMeasure", ProductMeasureUnit.class);
        typedQuery.setParameter("nameMeasure", nameMeasure);
        typedQuery.setParameter("shortNameMeasure", shortNameMeasure);
        try {
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
