/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.User;
import br.com.watchtower.desk.admStore.queryAssembler.UserBean;
import br.com.watchtower.desk.admStore.view.user.UserPanel;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Wallace
 */
public class UserController extends BaseController {

    UserBean userBean = new UserBean();

    public String save(User user) {
        String retorno;
        try {
            userBean.create(user);
            retorno = "Salvo com sucesso";
        } catch (Exception e) {
            retorno = "Erro ao tentar salvar";
        }
        return retorno;
    }

    public List<User> listarTodos() {
        return userBean.findAll();

    }

    public UserPanel view() {
        return new UserPanel();
    }

}
