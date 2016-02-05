/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.ProductCover;
import br.com.watchtower.desk.admStore.queryAssembler.ProductCoverBean;
import br.com.watchtower.desk.admStore.utils.DataList;
import br.com.watchtower.desk.admStore.utils.DataTable;
import br.com.watchtower.desk.admStore.view.BaseDialog;
import br.com.watchtower.desk.admStore.view.product.ProductCoverPanel;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import javax.persistence.RollbackException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;

/**
 *
 * @author Wallace
 */
public class ProductCoverController extends BaseController {

    private ProductCoverBean productCoverBean;
    private DataTable dataTable;
    private BaseDialog baseDialog;

    public ProductCoverController() {
        setProductCoverBean(new ProductCoverBean());
        setDataTable(new DataTable(getProductCoverTableColumnNames()));
    }

    public List<ProductCover> findAll() {
        setProductCoverBean(new ProductCoverBean());
        return getProductCoverBean().findAll();
    }

    public ProductCover findByCoverName(String coverName) {
        return getProductCoverBean().findByCoverName(coverName);
    }

    public String save(ProductCover productCover) {
        try {
            if (productCover.getId() == null) {
                getProductCoverBean().create(productCover);
            } else {
                getProductCoverBean().edit(productCover);
            }
            return getFeedBackSucesso();
        } catch (RollbackException e) {
            return getFeedBackErro();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

    public void remove(ProductCover productCover) {
        try {
            getProductCoverBean().remove(productCover);
        } catch (Exception e) {
        }
    }

    public void setTableFilter(JTextField jTextField) {
        String keyWord = jTextField.getText();
        if (keyWord.length() == 0) {
            getDataTable().getTableRowSorter().setRowFilter(null);
        } else {
            getDataTable().getTableRowSorter().setRowFilter(RowFilter.regexFilter(keyWord));
        }
    }

    public void setTable(JTable jTable) {
        getDataTable().setTableRowSorter(jTable);
        List<ProductCover> productCovers = findAll();
        for (ProductCover productCover : productCovers) {
            getDataTable().addRow(new Object[]{
                productCover.getCover(),
                DateFormat.getDateInstance().format(productCover.getDateRegistration()) + " - " + DateFormat.getTimeInstance().format(productCover.getDateRegistration())});
        }
        jTable.setModel(getDataTable());

    }

    public void newProductCoverPanel() {
        ProductCoverPanel productCoverPanel = new ProductCoverPanel();
        setBaseDialog(newBaseDialog(productCoverPanel));
    }

    private void setCoverNames(JList jList) {
        List<ProductCover> productCovers = findAll();
        jList.setModel(new DataList(getStringsVector(productCovers)));
    }

    private String[] getStringsVector(List<ProductCover> productCovers) {
        String[] strings = new String[productCovers.size()];
        int count = 0;
        for (ProductCover pCover : productCovers) {
            strings[count] = pCover.getCover();
            ++count;
        }
        return strings;
    }

    public void newCoverProduct(JCheckBox jCheckBox1, JTextField jTextField1, JList jList, JTable jTable, JButton jButton) {
        ProductCover productCover = new ProductCover();
        productCover.setDateRegistration(Calendar.getInstance().getTime());
        productCover.setActive(jCheckBox1.isSelected());
        productCover.setCover(jTextField1.getText());
        save(productCover);
        JOptionPane.showMessageDialog(null, "Sucesso", "", JOptionPane.INFORMATION_MESSAGE);
        setCoverNames(jList);
        jTextField1.setText(null);
        jButton.setEnabled(false);
        resetCheckbox(jCheckBox1);

    }

    public void setInitialSettings(JList jList1, JTable jTable1, JButton jButton4, JCheckBox jCheckBox1) {
        setCoverNames(jList1);
        setTable(jTable1);
        jButton4.setEnabled(false);
        resetCheckbox(jCheckBox1);

    }

    public void removeCoverProduct(JList jList1, JTextField jTextField1, JTable jTable1, JButton jButton4, JCheckBox jCheckBox1) {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o envolocro de produto selecionado?", "Tem certeza?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (resposta == 0) {
            String cover = (String) jList1.getModel().getElementAt(jList1.getSelectedIndex());
            ProductCover productCover = new ProductCover();
            productCover = findByCoverName(cover);
            remove(productCover);
            setCoverNames(jList1);
            jTextField1.setText(null);
            jButton4.setEnabled(false);
            resetCheckbox(jCheckBox1);
        }

    }

    public void editCoverProduct(MouseEvent evt, JButton jButton4, JCheckBox jCheckBox1, JList jList1, JTextField jTextField1, JCheckBox jCheckBox10) {
        if (evt.getClickCount() == 2) {
            {
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente editar o envolocro de produto selecionado?", "Tem certeza?", JOptionPane.YES_NO_CANCEL_OPTION);
                if (resposta == 0) {
                    jButton4.setEnabled(true);
                    jCheckBox1.setEnabled(true);
                    String cover = (String) jList1.getModel().getElementAt(jList1.getSelectedIndex());
                    ProductCover productCover = findByCoverName(cover);
                    jTextField1.setText(productCover.getCover());
                    jCheckBox1.setSelected(productCover.getActive());
                }
            }
        }
    }

    public void cleanFields(JButton jButton4, JCheckBox jCheckBox1, JTextField jTextField1, JTextField jTextField2) {
        jButton4.setEnabled(false);
        resetCheckbox(jCheckBox1);
        jTextField1.setText(null);
        jTextField2.setText(null);
    }

    public void closeCoverProductFeature() {
        getBaseDialog().dispose();
    }

    public ProductCoverBean getProductCoverBean() {
        return productCoverBean;
    }

    private void setProductCoverBean(ProductCoverBean productCoverBean) {
        this.productCoverBean = productCoverBean;
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    private void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public BaseDialog getBaseDialog() {
        return baseDialog;
    }

    public void setBaseDialog(BaseDialog baseDialog) {
        this.baseDialog = baseDialog;
    }

}
