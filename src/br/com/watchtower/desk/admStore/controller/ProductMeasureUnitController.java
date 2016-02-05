/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductMeasureUnit;
import br.com.watchtower.desk.admStore.queryAssembler.ProductMeasureUnitBean;
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
public class ProductMeasureUnitController extends BaseController {

    private ProductMeasureUnitBean productMeasureUnitBean;
    private DataTable dataTable;

    public ProductMeasureUnitController() {
        setProductMeasureUnitBean(new ProductMeasureUnitBean());
        setDataTable(new DataTable(getProductMeasureUnitTableColumnNames()));
    }

    public ProductMeasureUnitBean getProductMeasureUnitBean() {
        return productMeasureUnitBean;
    }

    private void setProductMeasureUnitBean(ProductMeasureUnitBean productMeasureUnitBean) {
        this.productMeasureUnitBean = productMeasureUnitBean;
    }
    private DataTable getDataTable() {
        return dataTable;
    }

    private void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }
    

    public List<ProductMeasureUnit> findAll() {
        setProductMeasureUnitBean(new ProductMeasureUnitBean());
        return getProductMeasureUnitBean().findAll();
    }

    public ProductMeasureUnit findByNameMeasureNShortNameMeasure(String nameMeasure, String shortNameMeasure) {
        return getProductMeasureUnitBean().findByNameMeasureNShortNameMeasure(nameMeasure, shortNameMeasure);
    }

    public ProductMeasureUnit findByNameMeasure(String nameMeasure) {
        return getProductMeasureUnitBean().findByNameMeasure(nameMeasure);
    }

    public ProductMeasureUnit findByShortNameMeasure(String shortNameMeasure) {
        return getProductMeasureUnitBean().findByShortNameMeasure(shortNameMeasure);
    }

    public String save(ProductMeasureUnit productMeasureUnit) {
        try {
            if (productMeasureUnit.getId() == null) {
                getProductMeasureUnitBean().create(productMeasureUnit);
            } else {
                getProductMeasureUnitBean().edit(productMeasureUnit);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

    public void remove(ProductMeasureUnit productMeasureUnit) {
        try {
            getProductMeasureUnitBean().remove(productMeasureUnit);
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
    List<ProductMeasureUnit> productMeasureUnits = findAll();
        for (ProductMeasureUnit productMeasureUnit : productMeasureUnits) {
            getDataTable().addRow(new Object[]{
                productMeasureUnit.getNameMeasure(),
                productMeasureUnit.getShortNameMeasure(),
                DateFormat.getDateInstance().format(productMeasureUnit.getDateRegistration()) + " - " + DateFormat.getTimeInstance().format(productMeasureUnit.getDateRegistration())});
        }
        jTable.setModel(getDataTable());
    }

    

    

}
