/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductClass;
import br.com.watchtower.desk.admStore.queryAssembler.ProductClassBean;
import java.util.List;

/**
 *
 * @author Wallace
 */
public class ProductClassController extends BaseController {

    private ProductClassBean productClassBean;

    public ProductClassController() {
        setProductClassBean(new ProductClassBean());
    }

    public ProductClassBean getProductClassBean() {
        return productClassBean;
    }

    private void setProductClassBean(ProductClassBean productClassBean) {
        this.productClassBean = productClassBean;
    }

    public List<ProductClass> findAll() {
        return getProductClassBean().findAll();
    }
    
    public String save(ProductClass productClass){
        try {
            if (productClass.getId()==null) {
                getProductClassBean().create(productClass);
            } else {
                getProductClassBean().edit(productClass);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

}
