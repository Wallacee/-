/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductFinancial;
import br.com.watchtower.desk.admStore.queryAssembler.ProductFinancialBean;
import java.util.List;

/**
 *
 * @author Wallace
 */
public class ProductFinancialController extends BaseController {

    private ProductFinancialBean productFinancialBean;

    public ProductFinancialController() {
        setProductFinancialBean(new ProductFinancialBean());
    }

    public ProductFinancialBean getProductFinancialBean() {
        return productFinancialBean;
    }

    private void setProductFinancialBean(ProductFinancialBean productFinancialBean) {
        this.productFinancialBean = productFinancialBean;
    }

    public List<ProductFinancial> findAll() {
        return getProductFinancialBean().findAll();
    }

    public String save(ProductFinancial productFinancial) {
        try {
            if (productFinancial.getId() == null) {
                getProductFinancialBean().create(productFinancial);
            } else {
                getProductFinancialBean().edit(productFinancial);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }
}
