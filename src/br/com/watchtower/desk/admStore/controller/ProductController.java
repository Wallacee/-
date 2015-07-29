/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.Product;
import br.com.watchtower.desk.admStore.model.ProductType;
import br.com.watchtower.desk.admStore.queryAssembler.ProductBean;
import br.com.watchtower.desk.admStore.queryAssembler.ProductBrandBean;
import br.com.watchtower.desk.admStore.queryAssembler.ProductTypeBean;
import java.util.List;

/**
 *
 * @author Wallace
 */
public class ProductController extends BaseController {

    private ProductBean productBean;

    private ProductBrandBean productBrandBean;

    private ProductTypeBean productTypeBean;


    public ProductController() {
        setProductBean(new ProductBean());
        setProductBrandBean(new ProductBrandBean());
        setProductTypeBean(new ProductTypeBean());

    }

    public String save(Product product) {
        try {
            if (product.getId() == null) {
                getProductBean().create(product);
            } else {
                getProductBean().edit(product);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

    public List<ProductType> findAllProduct() {
        return getProductTypeBean().findAll();
    }

    public String[] toProductStringArray(List<ProductType> productTypes) {
        String[] nameProductTypes = new String[productTypes.size()+1];
        int count = 0;
        nameProductTypes[count] = "Tipo de produto";
        for (ProductType productType : productTypes) {
            count++;
            nameProductTypes[count] = productType.getType();
        }
        return nameProductTypes;
    }
    private ProductBrandBean getProductBrandBean() {
        return productBrandBean;
    }
    private void setProductBrandBean(ProductBrandBean productBrandBean) {
        this.productBrandBean = productBrandBean;
    }
    private ProductTypeBean getProductTypeBean() {
        return productTypeBean;
    }

    private void setProductTypeBean(ProductTypeBean productTypeBean) {
        this.productTypeBean = productTypeBean;
    }

    private ProductBean getProductBean() {
        return productBean;
    }

    private void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

}
