/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductMeasureUnit;
import br.com.watchtower.desk.admStore.queryAssembler.ProductMeasureUnitBean;
import java.util.List;

/**
 *
 * @author Wallace
 */
public class ProductMeasureUnitController extends BaseController {

    private ProductMeasureUnitBean productMeasureUnitBean;

    public ProductMeasureUnitController() {
        setProductMeasureUnitBean(new ProductMeasureUnitBean());
    }

    public ProductMeasureUnitBean getProductMeasureUnitBean() {
        return productMeasureUnitBean;
    }

    private void setProductMeasureUnitBean(ProductMeasureUnitBean productMeasureUnitBean) {
        this.productMeasureUnitBean = productMeasureUnitBean;
    }

    public List<ProductMeasureUnit> findAll() {
        return getProductMeasureUnitBean().findAll();
    }

    public String Save(ProductMeasureUnit productMeasureUnit) {
        try {
            if (productMeasureUnit.getId() == null) {
                getProductMeasureUnitBean().create(productMeasureUnit);
            } else {
                getProductMeasureUnitBean().edit(productMeasureUnit);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

}
