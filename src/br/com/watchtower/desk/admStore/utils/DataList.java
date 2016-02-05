/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.utils;

import javax.swing.AbstractListModel;

/**
 *
 * @author Wallace
 */
public class DataList extends AbstractListModel{
    private String[] listData;

    public DataList(String[] listData) {
        this.listData = listData;
    }

    public String[] getListData() {
        return listData;
    }

    public void setListData(String[] listData) {
        this.listData = listData;
    }
    
    
    @Override
    public int getSize() {
        return getListData().length;
    }

    @Override
    public Object getElementAt(int index) {
        return listData[index];
    }
    
}
