/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductBuyValue;
import br.com.watchtower.desk.admStore.queryAssembler.ProductBuyValueBean;
import java.util.List;

/**
 *
 * @author Wallace
 */
public class ProductBuyValueController extends BaseController {

    private ProductBuyValueBean productBuyValueBean;

    public ProductBuyValueController() {
        setProductBuyValueBean(new ProductBuyValueBean());
    }

    public String save(ProductBuyValue productBuyValue) {
        try {
            if (productBuyValue != null) {
                getProductBuyValueBean().create(productBuyValue);
            } else {
                getProductBuyValueBean().edit(productBuyValue);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

    public List<ProductBuyValue> findByStoreAmountId(int productId) {
        return getProductBuyValueBean().findById(productId);
    }

    public ProductBuyValue findByStorePratileiraAmountId(int id) {
        return getProductBuyValueBean().findByStorePratileiraAmountId(id);
    }

    public List<ProductBuyValue> findByProductId(int productId) {
        return getProductBuyValueBean().findByProductId(productId);
    }

    public List<ProductBuyValue> findMenorQueValue(float value) {
        return getProductBuyValueBean().findSmallestValue(value);
    }

    public List<ProductBuyValue> findMaiorQueValue(float value) {
        return getProductBuyValueBean().findBiggestValue(value);
    }

    public ProductBuyValue findByProductBuyValue(float value) {
        return getProductBuyValueBean().findByValueBuy(value);
    }

    public ProductBuyValueBean getProductBuyValueBean() {
        return productBuyValueBean;
    }

    private void setProductBuyValueBean(ProductBuyValueBean productBuyValueBean) {
        this.productBuyValueBean = productBuyValueBean;
    }

    public String create(ProductBuyValue productBuyValue) {
        try {
            getProductBuyValueBean().create(productBuyValue);
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }

    }

}
