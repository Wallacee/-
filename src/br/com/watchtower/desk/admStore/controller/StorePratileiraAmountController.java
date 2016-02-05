 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.Product;
import br.com.watchtower.desk.admStore.model.ProductBuyValue;
import br.com.watchtower.desk.admStore.model.ProductSaleValue;
import br.com.watchtower.desk.admStore.model.ProductStoreTransaction;
import br.com.watchtower.desk.admStore.model.StorePratileiraAmount;
import br.com.watchtower.desk.admStore.queryAssembler.StorePratileiraAmountBean;
import br.com.watchtower.desk.admStore.utils.DataTable;
import br.com.watchtower.desk.admStore.view.BaseDialog;
import br.com.watchtower.desk.admStore.view.product.ProductManagerStorePanel;
import br.com.watchtower.desk.admStore.view.product.ProductValuePanel;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Wallace
 */
public class StorePratileiraAmountController extends BaseController {

    private StorePratileiraAmountBean storePratileiraAmountBean;
    private List<ProductStoreTransaction> productStoreTransactions;
    private ProductBuyValueController productBuyValueController;
    private ProductSaleValueController productSaleValueController;
    private ProductController productController;
    private BaseDialog baseDialog;

    public StorePratileiraAmountController() {
        setStorePratileiraAmountBean(new StorePratileiraAmountBean());
        setProductBuyValueController(new ProductBuyValueController());
        setProductSaleValueController(new ProductSaleValueController());
        setProductController(new ProductController());

    }

    public String save(StorePratileiraAmount storePratileiraAmount) {
        try {
            if (storePratileiraAmount.getId() == null) {
                getStorePratileiraAmountBean().create(storePratileiraAmount);
            } else {
                getStorePratileiraAmountBean().edit(storePratileiraAmount);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

    public void saveAndUpdateData(Product product, JTextField jTextField1, JTextField jTextField2, JTextField jTextField3, JTable jTable, JTable upgradJtable) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente adcionar os seguintes produtos ao estoque?", "Tem certeza", JOptionPane.OK_CANCEL_OPTION) == 0) {
            ProductStoreTransaction productStoreTransaction = new ProductStoreTransaction();
            productStoreTransaction.setProduct(product);
            productStoreTransaction.setBuyValue(Float.parseFloat(jTextField1.getText()));
            productStoreTransaction.setSaleValue(Float.parseFloat(jTextField2.getText()));
            productStoreTransaction.setAmount(Integer.parseInt(jTextField3.getText()));
            setTableAvailableProductForStore(jTable, productStoreTransaction);
            saveProductInStore(product, jTextField1, jTextField2, jTextField3);
            setTableProductInStore(upgradJtable);
            JOptionPane.showMessageDialog(null, "Produtos inseridos no estoque com sucesso", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void setTableAvailableProductForStore(JTable jTable, ProductStoreTransaction productStoreTransaction) {
        DataTable dataTable = new DataTable(getNewProductsInStoreTableColumnNames(), jTable);
        dataTable.addRow(new Object[]{
            productStoreTransaction.getshortProductName(),
            productStoreTransaction.getBuyValue() + getMoedaConstante(),
            productStoreTransaction.getSaleValue() + getMoedaConstante(),
            productStoreTransaction.getUnitariLiquidAmount() + getMoedaConstante(),
            productStoreTransaction.getAmount(),
            productStoreTransaction.getTotalBuy() + getMoedaConstante(),
            productStoreTransaction.getTotalSale() + getMoedaConstante(),
            productStoreTransaction.getTotalLiquidAmount() + getMoedaConstante()});
    }

    public void setTableProductInStore(JTable jTable) {
        DataTable dataTable = new DataTable(getProductsInStore(), jTable);
        List<StorePratileiraAmount> storePratileiraAmounts = getStorePratileiraAmountBean().findAllByMeasureUnit(false);
        List<StorePratileiraAmount> amounts = new ArrayList<>();
        List<Integer> aux = new ArrayList<>();
        for (StorePratileiraAmount storePratileiraAmount : storePratileiraAmounts) {
            if (!aux.contains(storePratileiraAmount.getProductId().getId())) {
                aux.add(storePratileiraAmount.getProductId().getId());
                amounts.add(storePratileiraAmount);
            }
        }
        jTable.setModel(dataTable);
        for (StorePratileiraAmount amount : amounts) {
            dataTable.addRow(new Object[]{
                amount.getProductId().getProductCategoryId().getCategory(),
                getShortProductName(amount.getProductId()),
                amount.getDateRegistration()
            });
        }
    }

    public void setListMeasureUnitType(JComboBox jComboBox) {
        jComboBox.setModel(makeComboBox(getStoreListMeasureType()));
    }

    public List<StorePratileiraAmount> findAllByMeasureUnit(boolean boo) {
        return getStorePratileiraAmountBean().findAllByMeasureUnit(boo);
    }

    public void setTableProductToStore(JTable jTable) {
        DataTable dataTable = new DataTable(getNewProductsInStoreTableColumnNames(), jTable);
        jTable.setModel(dataTable);
    }

    public List<StorePratileiraAmount> findAll() {
        setStorePratileiraAmountBean(new StorePratileiraAmountBean());
        return getStorePratileiraAmountBean().findAll();
    }

    public List<StorePratileiraAmount> findByProductId(int productId) {
        return getStorePratileiraAmountBean().findByProductId(productId);
    }

    public String lastBuyValue(Product product) {
        List<ProductBuyValue> productBuyValues = getProductBuyValueController().findByProductId(product.getId());
        if (!productBuyValues.isEmpty()) {
            return productBuyValues.get(0).getValueBuy() + "" + getMoedaConstante();
        } else {
            return getFeedbackProductNot();
        }

    }

    public String lastSaleValue(Product product) {
        List<ProductSaleValue> productSaleValues = getProductSaleValueController().findByProductId(product.getId());
        if (!productSaleValues.isEmpty()) {
            return "" + productSaleValues.get(0).getValue() + " " + getMoedaConstante();
        } else {
            return getFeedbackProductNot();
        }
    }

    public String totalProductSaleAmount(Product product) {
        List<StorePratileiraAmount> storePratileiraAmounts = getStorePratileiraAmountBean().findByProductSalledId(product.getId(), true);
        if (!storePratileiraAmounts.isEmpty()) {
            return "" + storePratileiraAmounts.size();
        } else {
            return getFeedbackProductNot();
        }

    }

    public String totalLiquidValue(Product product) {
        List<StorePratileiraAmount> storePratileiraAmounts = getStorePratileiraAmountBean().findByProductSalledId(product.getId(), true);
        if (!storePratileiraAmounts.isEmpty()) {
            float totalBuy = 0;
            float totalSale = 0;
            for (StorePratileiraAmount storePratileiraAmount : storePratileiraAmounts) {
                totalBuy += getProductBuyValueController().findByStorePratileiraAmountId(storePratileiraAmount.getId()).getValueBuy();
                totalSale += getProductSaleValueController().findByStorePratileiraAmountId(storePratileiraAmount.getId()).getValue();
            }
            return "" + (totalSale - totalBuy);
        } else {
            return getFeedbackProductNot();
        }

    }

    public String mediumSaleValue(Product product) {
        List<StorePratileiraAmount> storePratileiraAmounts = getStorePratileiraAmountBean().findByProductSalledId(product.getId(), true);
        if (!storePratileiraAmounts.isEmpty()) {
            float totalSale = 0;
            for (StorePratileiraAmount storePratileiraAmount : storePratileiraAmounts) {
                totalSale += getProductSaleValueController().findByStorePratileiraAmountId(storePratileiraAmount.getId()).getValue();
            }
            return "" + (totalSale / Float.parseFloat("" + storePratileiraAmounts.size()));
        } else {
            return getFeedbackProductNot();
        }

    }

    public String liquidValueMedium(Product product) {
        List<StorePratileiraAmount> storePratileiraAmounts = getStorePratileiraAmountBean().findByProductSalledId(product.getId(), true);
        if (!storePratileiraAmounts.isEmpty()) {
            float totalBuy = 0;
            float totalSale = 0;
            for (StorePratileiraAmount storePratileiraAmount : storePratileiraAmounts) {
                totalBuy += getProductBuyValueController().findByStorePratileiraAmountId(storePratileiraAmount.getId()).getValueBuy();
                totalSale += getProductSaleValueController().findByStorePratileiraAmountId(storePratileiraAmount.getId()).getValue();
            }
            return "" + ((totalSale - totalBuy) / Float.parseFloat("" + storePratileiraAmounts.size()));
        } else {
            return getFeedbackProductNot();
        }
    }

    public String totalBuyValue(Product product) {
        List<StorePratileiraAmount> storePratileiraAmounts = getStorePratileiraAmountBean().findByProductSalledId(product.getId(), true);
        if (!storePratileiraAmounts.isEmpty()) {
            float totalBuy = 0;
            for (StorePratileiraAmount storePratileiraAmount : storePratileiraAmounts) {
                totalBuy += getProductBuyValueController().findByStorePratileiraAmountId(storePratileiraAmount.getId()).getValueBuy();
            }
            return "" + totalBuy;
        } else {
            return getFeedbackProductNot();
        }
    }

    public String totalBuyedProduct(Product product) {
        List<StorePratileiraAmount> storePratileiraAmounts = getStorePratileiraAmountBean().findByProductId(product.getId());
        if (!storePratileiraAmounts.isEmpty()) {
            return "" + storePratileiraAmounts.size();
        } else {
            return getFeedbackProductNot();
        }
    }

    public String totalInStore(Product product) {
        List<StorePratileiraAmount> storePratileiraAmounts = getStorePratileiraAmountBean().findByProductSalledId(product.getId(), false);
        if (!storePratileiraAmounts.isEmpty()) {
            return "" + storePratileiraAmounts.size();
        } else {
            return getFeedbackProductNot();
        }
    }

    public Collection<StorePratileiraAmount> findByProduct(Product product) {
        return getStorePratileiraAmountBean().findByProductId(product.getId());
    }

    public void saveProductInStore(Product product, JTextField jTextField, JTextField jTextField1, JTextField jTextField2) {
        StorePratileiraAmount storePratileiraAmount = null;
        float buyValue = Float.parseFloat(jTextField.getText());
        float saleValue = Float.parseFloat(jTextField2.getText());
        int amount = Integer.parseInt(jTextField2.getText());

        ProductBuyValue productBuyValue = new ProductBuyValue();
        ProductSaleValue productSaleValue = new ProductSaleValue();

        //Salva objeto
        if (getProductBuyValueController().findByProductBuyValue(buyValue) == null) {
            productBuyValue.setValueBuy(buyValue);
            productBuyValue.setDateRegistration(Calendar.getInstance().getTime());
            getProductBuyValueController().save(productBuyValue);
        }
        if (getProductSaleValueController().findByProductSaleValue(saleValue) == null) {
            productSaleValue.setValue(saleValue);
            productSaleValue.setDateRegistration(Calendar.getInstance().getTime());
            getProductSaleValueController().save(productSaleValue);
        }
        //Recupera
        productBuyValue = getProductBuyValueController().findByProductBuyValue(buyValue);
        productSaleValue = getProductSaleValueController().findByProductSaleValue(saleValue);

        for (int i = 0; i < amount; i++) {
            storePratileiraAmount = new StorePratileiraAmount();
            storePratileiraAmount.setPratileira(false);
            storePratileiraAmount.setProductId(product);
            storePratileiraAmount.setProductBuyValueId(productBuyValue);
            storePratileiraAmount.setProductSaleValueId(productSaleValue);
            storePratileiraAmount.setDateRegistration(Calendar.getInstance().getTime());
            save(storePratileiraAmount);
        }
        saleValueProductUpdate(product, productSaleValue);
    }

    private void saleValueProductUpdate(Product product, ProductSaleValue productSaleValue) {
        Collection<StorePratileiraAmount> storePratileiraAmounts = getStorePratileiraAmountBean().findByProductSalledId(product.getId(), false);
        for (StorePratileiraAmount storePratileiraAmount : storePratileiraAmounts) {
            storePratileiraAmount.setProductSaleValueId(productSaleValue);
            save(storePratileiraAmount);
        }
    }

    public void setQuickFields(JTable jTable, JTextField jTextField3, JTextField jTextField5, JTextField jTextField6, JTextField jTextField7, boolean isMeasureUnit, JTable resumeTable) {
        String category = jTable.getValueAt(jTable.getSelectedRow(), 0).toString();
        String[] productLabels = splitNameInList(jTable.getValueAt(jTable.getSelectedRow(), 1).toString(), 7, " - ");
        String shortMeasureUnit = productLabels[5].replace("(", "");
        shortMeasureUnit = shortMeasureUnit.replace(")", "");
        Product product = getProductController().findByAll(
                productLabels[2],
                category,
                productLabels[0],
                productLabels[6],
                productLabels[4],
                shortMeasureUnit,
                productLabels[1],
                Float.valueOf(productLabels[3]),
                isMeasureUnit);
        jTextField3.setText("" + findByProduct(product).size());
        jTextField5.setText("" + totalInStore(product));
        jTextField6.setText("" + totalProductSaleAmount(product));

    }

    public StorePratileiraAmountBean getStorePratileiraAmountBean() {
        return storePratileiraAmountBean;
    }

    private void setStorePratileiraAmountBean(StorePratileiraAmountBean storePratileiraAmountBean) {
        this.storePratileiraAmountBean = storePratileiraAmountBean;
    }

    public List<ProductStoreTransaction> getProductStoreTransactions() {
        return productStoreTransactions;
    }

    public void setProductStoreTransactions(List<ProductStoreTransaction> productStoreTransactions) {
        this.productStoreTransactions = productStoreTransactions;
    }

    public ProductBuyValueController getProductBuyValueController() {
        return productBuyValueController;
    }

    private void setProductBuyValueController(ProductBuyValueController productBuyValueController) {
        this.productBuyValueController = productBuyValueController;
    }

    public ProductSaleValueController getProductSaleValueController() {
        return productSaleValueController;
    }

    private void setProductSaleValueController(ProductSaleValueController productSaleValueController) {
        this.productSaleValueController = productSaleValueController;
    }

    public ProductController getProductController() {
        return productController;
    }

    private void setProductController(ProductController productController) {
        this.productController = productController;
    }

    public BaseDialog getBaseDialog() {
        return baseDialog;
    }

    public void setBaseDialog(BaseDialog baseDialog) {
        this.baseDialog = baseDialog;
    }

    public void setNewProductBagInStore(JTable jTable2, JComboBox jComboBox2, ProductManagerStorePanel aThis, MouseEvent evt, JFrame jFrame) {
        if (evt.getClickCount() == 2) {
            setBaseDialog(new BaseDialog(jFrame, true));
            Product product = getProductController().findSelectedProduct(jTable2, jComboBox2);
            ProductValuePanel productValuePanel = new ProductValuePanel();
            productValuePanel.setBaseDialog(getBaseDialog());
            productValuePanel.setProduct(product);
            productValuePanel.setProductManagerStorePanel(aThis);
            productValuePanel.setProductFields();
//            productValuePanel.setjTale(jTable3);
//            productValuePanel.setUpgradJtable(jTable3);
            getBaseDialog().setContentPane(productValuePanel);
            getBaseDialog().pack();
            getBaseDialog().setResizable(true);
            getBaseDialog().setVisible(true);
        }

    }

    public void setComboBox(JComboBox jComboBox2, JTable jTable2) {
        if (jComboBox2.getSelectedIndex() == 0) {
            setTable(jTable2, true);
        } else {
            setTable(jTable2, false);
        }
    }

    public void setTable(JTable jtable, boolean measureUnit) {
        getProductController().setTableByMeasure(jtable, measureUnit);
    }

}
