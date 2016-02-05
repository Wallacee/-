/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductValueMeasureUnit;
import br.com.watchtower.desk.admStore.queryAssembler.ProductValueMeasureUnitBean;
import br.com.watchtower.desk.admStore.utils.DataTable;
import java.text.DateFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;

/**
 *
 * @author Wallace
 */
public class ProductValueMeasureUnitController extends BaseController {

    private ProductValueMeasureUnitBean productValueMeasureUnitBean;
    private DataTable dataTable;

    public ProductValueMeasureUnitController() {
        setProductValueMeasureUnitBean(new ProductValueMeasureUnitBean());
        setDataTable(new DataTable(getProductValueMeasureUnitTableColumnNames()));
    }

    private DataTable getDataTable() {
        return dataTable;
    }

    private void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public ProductValueMeasureUnitBean getProductValueMeasureUnitBean() {
        return productValueMeasureUnitBean;
    }

    private void setProductValueMeasureUnitBean(ProductValueMeasureUnitBean productValueMeasureUnitBean) {
        this.productValueMeasureUnitBean = productValueMeasureUnitBean;
    }

    public List<ProductValueMeasureUnit> findAll() {
        setProductValueMeasureUnitBean(new ProductValueMeasureUnitBean());
        return getProductValueMeasureUnitBean().findAll();
    }

    public ProductValueMeasureUnit findByValueMeasureUnit(String valueMeasureUnit) {
        setProductValueMeasureUnitBean(new ProductValueMeasureUnitBean());
        return getProductValueMeasureUnitBean().findByValueMeasureUnit(valueMeasureUnit);
    }

    public String save(ProductValueMeasureUnit productValueMeasureUnit) {
        try {
            if (productValueMeasureUnit.getId() == null) {
                getProductValueMeasureUnitBean().create(productValueMeasureUnit);
            } else {
                getProductValueMeasureUnitBean().edit(productValueMeasureUnit);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

    public void remove(ProductValueMeasureUnit productValueMeasureUnit) {
        try {
            getProductValueMeasureUnitBean().remove(productValueMeasureUnit);
        } catch (Exception e) {
        }
    }

    public void setTableFilter(JTextField jTextField) {
        String keyWord = jTextField.getText();
        if (keyWord.length() == 0) {
            getDataTable().getTableRowSorter().setRowFilter(null);
        } else {
            getDataTable().getTableRowSorter().setRowFilter(RowFilter.regexFilter(keyWord));
        }
    }
    
    public void setTable(JTable jTable){
    getDataTable().setTableRowSorter(jTable);
    List<ProductValueMeasureUnit> productValueMeasureUnits = findAll();
        for (ProductValueMeasureUnit productValueMeasureUnit : productValueMeasureUnits) {
            getDataTable().addRow(new Object[]{
                productValueMeasureUnit.getValue(),
                DateFormat.getDateInstance().format(productValueMeasureUnit.getDateRegistration()) + " - " + DateFormat.getTimeInstance().format(productValueMeasureUnit.getDateRegistration())});
        }
        jTable.setModel(getDataTable());
    }

}
