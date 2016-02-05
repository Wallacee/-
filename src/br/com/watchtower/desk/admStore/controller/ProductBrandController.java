/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductBrand;
import br.com.watchtower.desk.admStore.queryAssembler.ProductBrandBean;
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
public class ProductBrandController extends BaseController {

    ProductBrandBean productBrandBean;
    private DataTable dataTable;

    public ProductBrandController() {
        setProductBrandBean(new ProductBrandBean());
        setDataTable(new DataTable(getProductBrandTableColumnNames()));
    }

    public ProductBrandBean getProductBrandBean() {
        return productBrandBean;
    }

    private void setProductBrandBean(ProductBrandBean productBrandBean) {
        this.productBrandBean = productBrandBean;
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    private void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public String save(ProductBrand productBrand) {
        try {
            if (productBrand.getId() == null) {
                getProductBrandBean().create(productBrand);
            } else {
                getProductBrandBean().edit(productBrand);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

    public List<ProductBrand> findAll() {
        setProductBrandBean(new ProductBrandBean());
        return getProductBrandBean().findAll();
    }

    public ProductBrand findByBrandName(String brandName) {
        return getProductBrandBean().findByBrandName(brandName);
    }

    public void remove(ProductBrand productBrand) {
        try {
            getProductBrandBean().remove(productBrand);
        } catch (Exception e) {
        }
    }

    public void setTable(JTable jtable) {
        getDataTable().setTableRowSorter(jtable);
        List<ProductBrand> productBrands = findAll();
        for (ProductBrand productBrand : productBrands) {
            getDataTable().addRow(new Object[]{
                productBrand.getBrandName(),
                DateFormat.getDateInstance().format(productBrand.getDateRegistration()) + " - " + DateFormat.getTimeInstance().format(productBrand.getDateRegistration())});
        }
        jtable.setModel(getDataTable());
    }

    public void setTableFilter(JTextField jTextField) {
        String keyWord = jTextField.getText();
        if (keyWord.length() == 0) {
            getDataTable().getTableRowSorter().setRowFilter(null);
        } else {
            getDataTable().getTableRowSorter().setRowFilter(RowFilter.regexFilter(keyWord));
        }
    }

}
