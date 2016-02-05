/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.Phone;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class PhoneBean extends DAO<Phone> {

    public List<Phone> findAll() {
        TypedQuery namedQuery = getEntityManager().createNamedQuery("Phone.findAll", Phone.class);
        try {
            return namedQuery.getResultList();
        } catch (Exception e) {
            return null;
        }

    }

}
