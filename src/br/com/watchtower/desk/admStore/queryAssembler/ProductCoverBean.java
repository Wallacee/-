/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.ProductCover;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class ProductCoverBean extends DAO<ProductCover> {

    public List<ProductCover> findAll() {
        TypedQuery<ProductCover> namedQuery = getEntityManager().createNamedQuery("ProductCover.findAll", ProductCover.class);
        List<ProductCover> productCovers;
        try {
            productCovers = namedQuery.getResultList();
        } catch (Exception e) {
            productCovers = null;
        }
        return productCovers;
    }

    public ProductCover findByCoverName(String coverName) {
        TypedQuery<ProductCover> namedQuery = getEntityManager().createNamedQuery("ProductCover.findByCover", ProductCover.class);
        namedQuery.setParameter("cover", coverName);
        try {
            return namedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
