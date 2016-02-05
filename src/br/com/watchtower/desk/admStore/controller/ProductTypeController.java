/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductType;
import br.com.watchtower.desk.admStore.queryAssembler.ProductTypeBean;
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
public class ProductTypeController extends BaseController {

    private ProductTypeBean productTypeBean;
    private DataTable dataTable;

    public ProductTypeController() {
        setProductTypeBean(new ProductTypeBean());
        setDataTable(new DataTable(getProductTypeTableColumnNames()));
    }

    private DataTable getDataTable() {
        return dataTable;
    }

    private void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public ProductTypeBean getProductTypeBean() {
        return productTypeBean;
    }

    private void setProductTypeBean(ProductTypeBean productTypeBean) {
        this.productTypeBean = productTypeBean;
    }

    public List<ProductType> findAll() {
        setProductTypeBean(new ProductTypeBean());
        return getProductTypeBean().findAll();
    }

    public ProductType findByType(String type) {
        return getProductTypeBean().findByType(type);
    }

    public String save(ProductType productType) {
        try {
            if (productType.getId() == null) {
                getProductTypeBean().create(productType);
            } else {
                getProductTypeBean().edit(productType);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

    public void remove(ProductType productType) {
        try {
            getProductTypeBean().remove(productType);
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

    public void setTable(JTable jTable) {
        getDataTable().setTableRowSorter(jTable);
        List<ProductType> productTypes = findAll();
        for (ProductType productType : productTypes) {
            getDataTable().addRow(new Object[]{
                productType.getType(),
                DateFormat.getDateInstance().format(productType.getDateRegistration()) + " - " + DateFormat.getTimeInstance().format(productType.getDateRegistration())});
        }
        jTable.setModel(getDataTable());
    }

    public void newProductTypePanel() {
    
    }
}
