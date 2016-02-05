/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.utils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Wallace
 */
public class DataTable extends DefaultTableModel {

    private TableRowSorter tableRowSorter;

    public DataTable(String[] columnNames, JTable jTable) {
        super(new Object[][]{}, columnNames);
        setTableRowSorter(jTable);
    }
    public DataTable(String[] columnNames) {
        super(new Object[][]{}, columnNames);
    }
    

    

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

//    @Override
//    public Class getColumnClass(int c) {
//        return getValueAt(0, c).getClass();
//    }
    public void setTableRowSorter(JTable jTable) {
        setTableRowSorter(new TableRowSorter(this));
        jTable.setRowSorter(getTableRowSorter());
    }

    public TableRowSorter getTableRowSorter() {
        return tableRowSorter;
    }

    private void setTableRowSorter(TableRowSorter tableRowSorter) {
        this.tableRowSorter = tableRowSorter;
    }

}
