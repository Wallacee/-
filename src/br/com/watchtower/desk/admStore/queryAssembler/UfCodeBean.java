/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.UfCode;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class UfCodeBean extends DAO<UfCode> {

    public List<UfCode> findAll() {
        TypedQuery<UfCode> namedQuery = getEntityManager().createNamedQuery("UfCode.findAll", UfCode.class);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public UfCode findByUfCode(UfCode ufCode) {
        TypedQuery<UfCode> namedQuery = getEntityManager().createNamedQuery("UfCode.findByCode", UfCode.class);
        try {
            return namedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
