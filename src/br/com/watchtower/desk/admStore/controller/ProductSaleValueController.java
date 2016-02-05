/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductSaleValue;
import br.com.watchtower.desk.admStore.queryAssembler.ProductSaleValueBean;
import java.util.List;

/**
 *
 * @author Wallace
 */
public class ProductSaleValueController extends BaseController {

    private ProductSaleValueBean productSaleValueBean;

    public ProductSaleValueController() {
        setProductSaleValueBean(new ProductSaleValueBean());
    }

    public String save(ProductSaleValue productSaleValue) {
        try {
            if (productSaleValue.getId() == null) {
                getProductSaleValueBean().create(productSaleValue);
            } else {
                getProductSaleValueBean().edit(productSaleValue);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

    public void delete(ProductSaleValue productSaleValue) {
        getProductSaleValueBean().remove(productSaleValue);
    }

    public ProductSaleValue findByStorePratileiraAmountId(int id) {
        return getProductSaleValueBean().findByStorePratileiraAmountId(id);
    }

    public List<ProductSaleValue> findByStoreAmountId(int id) {
        return getProductSaleValueBean().findById(id);
    }

    public List<ProductSaleValue> findByProductId(int id) {
        return getProductSaleValueBean().findByProductId(id);
    }

    public List<ProductSaleValue> findMenorQueValue(float value) {
        return getProductSaleValueBean().findSmallestValue(value);
    }

    public List<ProductSaleValue> findMaiorQueValue(float value) {
        return getProductSaleValueBean().findBiggestValue(value);
    }

    public ProductSaleValue findByProductSaleValue(float value) {
        return getProductSaleValueBean().findByProductSaleValue(value);
    }

    public ProductSaleValueBean getProductSaleValueBean() {
        return productSaleValueBean;
    }

    private void setProductSaleValueBean(ProductSaleValueBean productSaleValueBean) {
        this.productSaleValueBean = productSaleValueBean;
    }

    public String create(ProductSaleValue productSaleValue) {
        try {
            getProductSaleValueBean().create(productSaleValue);
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

}
