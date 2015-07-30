/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductRetailWeight;
import br.com.watchtower.desk.admStore.queryAssembler.ProductRetailWeightBean;
import java.util.List;

/**
 *
 * @author Wallace
 */
public class ProductRetailWeightController extends BaseController {

    private ProductRetailWeightBean productRetailWeightBean;

    public ProductRetailWeightController() {
        setProductRetailWeightBean(new ProductRetailWeightBean());
    }

    public ProductRetailWeightBean getProductRetailWeightBean() {
        return productRetailWeightBean;
    }

    private void setProductRetailWeightBean(ProductRetailWeightBean productRetailWeightBean) {
        this.productRetailWeightBean = productRetailWeightBean;
    }

    public List<ProductRetailWeight> findAll() {
        return getProductRetailWeightBean().findAll();
    }

    public String save(ProductRetailWeight productRetailWeight) {
        try {
            if (productRetailWeight.getId() == null) {
                getProductRetailWeightBean().create(productRetailWeight);
            } else {
                getProductRetailWeightBean().edit(productRetailWeight);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }
}
