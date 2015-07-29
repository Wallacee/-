/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wallace
 */
public abstract class BaseController implements Serializable {

    private final String[] userTipoValores = {"Administrador", "Gerente", "Funcionário"};
    private final String[] userTableColunasValores = {"Nome", "Acesso", "Data de registro"};

    private final String feedBackSucesso = "Sucesso";
    private final String feedBackErro = "Erro";

    public String getFeedBackErro() {
        return feedBackErro;
    }

    public String getFeedBackSucesso() {
        return feedBackSucesso;
    }

    public String[] getUserTipoValores() {
        return userTipoValores;
    }

    public String[] getUserTableColunasValores() {
        return userTableColunasValores;
    }

    public DefaultComboBoxModel makeComboBox(String[] valores) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(valores);
        return defaultComboBoxModel;
    }

    public DefaultTableModel makeTable(String[] valores) {
        DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[][]{}, valores);
        return defaultTableModel;
    }
}
