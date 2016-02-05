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
public class ProductBean extends DAO<Product> {

    public List<Product> products;

    public List<Product> findAll() {
        TypedQuery<Product> namedQuery = getEntityManager().createNamedQuery("Product.findAll", Product.class);
        List<Product> products;
        try {
            products = namedQuery.getResultList();
        } catch (Exception e) {
            products = null;
        }
        return products;
    }

    public List<Product> findByMeasureUnit(boolean measureUnit) {
        TypedQuery<Product> namedQuery = getEntityManager().createNamedQuery("Product.findByMeasureUnit", Product.class);
        namedQuery.setParameter("measureUnit", measureUnit);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Product findByAll(String brandName, String category, String cover, String detail, String nameMeasure, String shortNameMeasure, String type, float valueMeasureUnit, boolean measureUnit) {
        TypedQuery<Product> namedQuery = getEntityManager().createNamedQuery("Product.findByAll", Product.class);
        namedQuery.setParameter("brandName", brandName);
        namedQuery.setParameter("category", category);
        namedQuery.setParameter("cover", cover);
        namedQuery.setParameter("detail", detail);
        namedQuery.setParameter("nameMeasure", nameMeasure);
        namedQuery.setParameter("shortNameMeasure", shortNameMeasure);
        namedQuery.setParameter("type", type);
        namedQuery.setParameter("valueMeasureUnit", valueMeasureUnit);
        namedQuery.setParameter("measureUnit", measureUnit);
        namedQuery.setParameter("active", true);
        try {
            return namedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
