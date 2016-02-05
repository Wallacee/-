/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.PublicPlace;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class PublicPlaceBean extends DAO<PublicPlace> {

    public List<PublicPlace> findAll() {
        TypedQuery<PublicPlace> namedQuery = getEntityManager().createNamedQuery("PublicPlace.findByCode", PublicPlace.class);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public PublicPlace findByPublicPlace(PublicPlace publicPlace) {
        TypedQuery<PublicPlace> namedQuery = getEntityManager().createNamedQuery("PublicPlace.findByType", PublicPlace.class);
        try {
            return namedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
