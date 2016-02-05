/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductDetail;
import br.com.watchtower.desk.admStore.queryAssembler.ProductDetailBean;
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
public class ProductDetailController extends BaseController {

    private ProductDetailBean productDetailBean;
    private DataTable dataTable;

    public ProductDetailController() {
        setProductDetailBean(new ProductDetailBean());
        setDataTable(new DataTable(getProductDetailTableColumnNames()));

    }

    private DataTable getDataTable() {
        return dataTable;
    }

    private void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public ProductDetailBean getProductDetailBean() {
        return productDetailBean;
    }

    private void setProductDetailBean(ProductDetailBean productDetailBean) {
        this.productDetailBean = productDetailBean;
    }

    public String save(ProductDetail productDetail) {
        try {
            if (productDetail.getId() == null) {
                getProductDetailBean().create(productDetail);
            } else {
                getProductDetailBean().edit(productDetail);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

    public List<ProductDetail> findAll() {
        setProductDetailBean(new ProductDetailBean());
        return getProductDetailBean().findAll();
    }

    public ProductDetail findByDetailName(String detail) {
        return getProductDetailBean().findByDetailName(detail);
    }

    public void remove(ProductDetail productDetail) {
        try {
            getProductDetailBean().remove(productDetail);
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

    public void seTable(JTable jTable) {
        List<ProductDetail> productDetails = findAll();
        getDataTable().setTableRowSorter(jTable);
        for (ProductDetail productDetail : productDetails) {
            getDataTable().addRow(new Object[]{
                productDetail.getDetail(),
                DateFormat.getDateInstance().format(productDetail.getDateRegistration()) + " - " + DateFormat.getTimeInstance().format(productDetail.getDateRegistration())});
        }
        jTable.setModel(getDataTable());
    }

}
