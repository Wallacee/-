/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.ProductFinancial;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductFinancialBean extends DAO<ProductFinancial> {

    public List<ProductFinancial> findAll() {
        TypedQuery<ProductFinancial> namedQuery = getEntityManager().createNamedQuery("ProductFinancial.findAll", ProductFinancial.class);
        List<ProductFinancial> productFinancials;
        try {
            productFinancials = namedQuery.getResultList();
        } catch (Exception e) {
            productFinancials = null;
        }
        return productFinancials;
    }

}
