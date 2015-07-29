/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductBrand;
import br.com.watchtower.desk.admStore.queryAssembler.ProductBrandBean;

/**
 *
 * @author Wallace
 */
public class ProductBrandController extends BaseController{

    ProductBrandBean productBrandBean;
    public ProductBrandController() {
        setProductBrandBean(new ProductBrandBean());
    }

    public ProductBrandBean getProductBrandBean() {
        return productBrandBean;
    }

    private void setProductBrandBean(ProductBrandBean productBrandBean) {
        this.productBrandBean = productBrandBean;
    }
    
    public String save(ProductBrand productBrand){
        try {
            if (productBrand.getId()==null) {
                getProductBrandBean().create(productBrand);
            }else{
            getProductBrandBean().edit(productBrand);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }
    
    
}
