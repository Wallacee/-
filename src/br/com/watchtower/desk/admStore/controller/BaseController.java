/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.Product;
import br.com.watchtower.desk.admStore.utils.DataTable;
import br.com.watchtower.desk.admStore.view.BaseDialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RowFilter;

/**
 *
 * @author Wallace
 */
public abstract class BaseController implements Serializable {

    private final String[] userTipoValores = {"ADMINISTRADOR", "GERENTE", "FUNCIONÁRIO"};
    private final String[] userTableColunasValores = {"NOME", "ACESSO", "DATA DE REGISTRO"};
    private final String[] productCoverTableColumnNames = {"ENVOLOVRO", "DATA DE REGISTRO"};
    private final String[] productTypeTableColumnNames = {"TIPO", "DATA DE REGISTRO"};
    private final String[] productBrandTableColumnNames = {"MARCA", "DATA DE REGISTRO"};
    private final String[] productCategoryTableColumnNames = {"CATEGORIA", "DATA DE REGISTRO"};
    private final String[] productMeasureUnitTableColumnNames = {"UNIDADE DE MEDIDA", "ABREVIAÇÃO", "DATA DE REGISTRO"};
    private final String[] productValueMeasureUnitTableColumnNames = {"VALOR DE UNIDADE DE MEDIDA", "DATA DE REGISTRO"};
    private final String[] productDetailTableColumnNames = {"ESPECIFICAÇÃO", "DATA DE REGISTRO"};
    private final String[] productTableColumnNames = {"CATEGORIA", "TIPO", "ENVOLOCRO", "MARCA", "VALOR DE VOLUME", "UNIDADE MÉTRICA", "ESPECIFICAÇÃO", "DATA DE REGISTRO"};
    private final String[] newProductsInStoreTableColumnNames = {"DESCRIÇÃO DE PRODUTO", "COMPRA UNITÁRIO", "REVENDA UNITÁRIA", "LIQUIDO UNITÁRIO", "QUANTIDADE DE ITENS", "COMPRA TOTAL", "REVENDA TOTAL", "LIQUIDO TOTAL"};
    private final String[] storeListMeasureType = {"COM USO DE BALANÇA", "SEM USO DE BALANÇA"};
    private final String[] productsInStore = {"CATEGORIA", "DESCRIÇÃO DO PRODUTO", "DATA DE REGISTRO"};
    private final String[] productsInStoreDetail = {"VALOR DE COMPRA", "VALOR DE REVENDA", "TEMPO NO ESTOQUE"};

    private final String feedBackSucesso = "Sucesso";
    private final String feedBackErro = "Erro";
    private final String moedaConstante = " R$";
    private final String feedbackProductNot = "--";

    public String getFeedBackErro() {
        return feedBackErro;
    }

    public String getFeedBackSucesso() {
        return feedBackSucesso;
    }

    public String[] getUserTipoValores() {
        return userTipoValores;
    }

    public String[] getUserTableColunasValores() {
        return userTableColunasValores;
    }

    public String[] getProductCoverTableColumnNames() {
        return productCoverTableColumnNames;
    }

    public String[] getProductTypeTableColumnNames() {
        return productTypeTableColumnNames;
    }

    public String[] getProductBrandTableColumnNames() {
        return productBrandTableColumnNames;
    }

    public String[] getProductCategoryTableColumnNames() {
        return productCategoryTableColumnNames;
    }

    public String[] getProductMeasureUnitTableColumnNames() {
        return productMeasureUnitTableColumnNames;
    }

    public String[] getProductValueMeasureUnitTableColumnNames() {
        return productValueMeasureUnitTableColumnNames;
    }

    public String[] getProductDetailTableColumnNames() {
        return productDetailTableColumnNames;
    }

    public String[] getProductTableColumnNames() {
        return productTableColumnNames;
    }

    public String[] getNewProductsInStoreTableColumnNames() {
        return newProductsInStoreTableColumnNames;
    }

    public String getMoedaConstante() {
        return moedaConstante;
    }

    public String[] getStoreListMeasureType() {
        return storeListMeasureType;
    }

    public String getFeedbackProductNot() {
        return feedbackProductNot;
    }

    public String[] getProductsInStore() {
        return productsInStore;
    }

    public String[] getProductsInStoreDetail() {
        return productsInStoreDetail;
    }

    public String getShortProductName(Product product) {
        return product.getProductCoverId().getCover() + " - "
                + product.getProductTypeId().getType() + " - "
                + product.getProductBrandId().getBrandName() + " - "
                + product.getProductValueMeasureUnitId().getValue() + " - "
                + product.getProductMeasureUnitId().getNameMeasure() + " - (" + product.getProductMeasureUnitId().getShortNameMeasure() + ") - "
                + product.getProductDetailId().getDetail();
    }

    public DefaultComboBoxModel makeComboBox(String[] valores) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(valores);
        return defaultComboBoxModel;
    }

    public String[] splitNameInList(String string) {
        @SuppressWarnings("UnusedAssignment")
        String[] finalNames = new String[3];
        finalNames = string.split(" ");
        return finalNames;
    }

    public String[] splitNameInList(String value, int arrayLenght, String argument) {
        String[] finalLabels;
        finalLabels = new String[arrayLenght];
        finalLabels = value.split(argument);
        return finalLabels;
    }

    public void makeDataTableFilter(String keyWord, DataTable dataTable) {
        if (keyWord.length() == 0) {
            dataTable.getTableRowSorter().setRowFilter(null);
        } else {
            dataTable.getTableRowSorter().setRowFilter(RowFilter.regexFilter(keyWord));
        }
    }

    public boolean makeBooleanValue(int selectedIndex) {
        return (selectedIndex == 0);
    }

    public BaseDialog newBaseDialog(JPanel jPanel) {
        BaseDialog baseDialog = new BaseDialog(new JFrame(), true);
        baseDialog.setContentPane(jPanel);
        baseDialog.setResizable(true);
        baseDialog.setVisible(true);
        baseDialog.pack();
        return baseDialog;
    }

    public void resizeWindow(JFrame jFrame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = new Dimension(toolkit.getScreenSize());
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        jFrame.setSize(width, height);
        jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);
        jFrame.setResizable(true);
        jFrame.setVisible(true);
    }

    public void resetCheckbox(JCheckBox jCheckBox) {
        jCheckBox.setEnabled(false);
        jCheckBox.setSelected(true);
    }
    
}
