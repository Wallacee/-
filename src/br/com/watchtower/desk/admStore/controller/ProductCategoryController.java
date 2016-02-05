/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductCategory;
import br.com.watchtower.desk.admStore.queryAssembler.ProductCategoryBean;
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
public class ProductCategoryController extends BaseController {

    private ProductCategoryBean productCategoryBean;
    private DataTable dataTable;

    public ProductCategoryController() {
        setProductCategoryBean(new ProductCategoryBean());
        setDataTable(new DataTable(getProductCategoryTableColumnNames()));

    }

    public List<ProductCategory> findAll() {
        setProductCategoryBean(new ProductCategoryBean());
        return getProductCategoryBean().findAll();
    }

    public ProductCategory findByCategory(String category) {
        return getProductCategoryBean().findByCategory(category);
    }

    public String save(ProductCategory productCategory) {
        try {
            if (productCategory.getId() == null) {
                getProductCategoryBean().create(productCategory);
            } else {
                getProductCategoryBean().edit(productCategory);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

    public void remove(ProductCategory productCategory) {
        try {
            getProductCategoryBean().remove(productCategory);
        } catch (Exception e) {
        }
    }

    public void setTable(JTable jTable) {
        getDataTable().setTableRowSorter(jTable);
        List<ProductCategory> productCategorys = findAll();
        for (ProductCategory productCategory : productCategorys) {
            getDataTable().addRow(new Object[]{
                productCategory.getCategory(),
                DateFormat.getDateInstance().format(productCategory.getDateRegistration()) + " - " + DateFormat.getTimeInstance().format(productCategory.getDateRegistration())});
        }
        jTable.setModel(getDataTable());

    }

    public void setTableFilter(JTextField jTextField) {
        String keyWord = jTextField.getText();
        if (keyWord.length() == 0) {
            getDataTable().getTableRowSorter().setRowFilter(null);
        } else {
            getDataTable().getTableRowSorter().setRowFilter(RowFilter.regexFilter(keyWord));
        }

    }

    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public ProductCategoryBean getProductCategoryBean() {
        return productCategoryBean;
    }

    private void setProductCategoryBean(ProductCategoryBean productClassBean) {
        this.productCategoryBean = productClassBean;
    }

}
