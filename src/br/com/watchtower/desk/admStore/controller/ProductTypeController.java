/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductType;
import br.com.watchtower.desk.admStore.queryAssembler.ProductTypeBean;

/**
 *
 * @author Wallace
 */
public class ProductTypeController extends BaseController {

    ProductTypeBean productTypeBean;

    public ProductTypeController() {
        setProductTypeBean(new ProductTypeBean());
    }

    public ProductTypeBean getProductTypeBean() {
        return productTypeBean;
    }

    private void setProductTypeBean(ProductTypeBean productTypeBean) {
        this.productTypeBean = productTypeBean;
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
}
