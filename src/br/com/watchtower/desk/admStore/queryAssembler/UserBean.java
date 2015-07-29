/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.queryAssembler;

import br.com.watchtower.desk.admStore.dao.DAO;
import br.com.watchtower.desk.admStore.model.User;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wallace
 */
public class UserBean extends DAO<User> {

    public List<User> findAll() {
        TypedQuery<User> namedQuery = getEntityManager().createNamedQuery("User.findAll", User.class);
        List<User> users;
        try {
            users = namedQuery.getResultList();
        } catch (Exception e) {
            users = null;
        }
        return users;
    }

}
