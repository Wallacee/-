/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductValueMeasureUnit;
import br.com.watchtower.desk.admStore.queryAssembler.ProductValueMeasureUnitBean;
import java.util.List;

/**
 *
 * @author Wallace
 */
public class ProductValueMeasureUnitController extends BaseController {

    private ProductValueMeasureUnitBean productValueMeasureUnitBean;

    public ProductValueMeasureUnitController() {
        setProductValueMeasureUnitBean(new ProductValueMeasureUnitBean());
    }

    public ProductValueMeasureUnitBean getProductValueMeasureUnitBean() {
        return productValueMeasureUnitBean;
    }

    private void setProductValueMeasureUnitBean(ProductValueMeasureUnitBean productValueMeasureUnitBean) {
        this.productValueMeasureUnitBean = productValueMeasureUnitBean;
    }

    public List<ProductValueMeasureUnit> findAll() {
        return getProductValueMeasureUnitBean().findAll();
    }

    public String save(ProductValueMeasureUnit productValueMeasureUnit) {
        try {
            if (productValueMeasureUnit.getId() == null) {
                getProductValueMeasureUnitBean().create(productValueMeasureUnit);
            } else {
                getProductValueMeasureUnitBean().edit(productValueMeasureUnit);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

}
