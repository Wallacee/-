/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.model.Product;
import br.com.watchtower.desk.admStore.model.ProductBrand;
import br.com.watchtower.desk.admStore.model.ProductCategory;
import br.com.watchtower.desk.admStore.model.ProductCover;
import br.com.watchtower.desk.admStore.model.ProductDetail;
import br.com.watchtower.desk.admStore.model.ProductMeasureUnit;
import br.com.watchtower.desk.admStore.model.ProductType;
import br.com.watchtower.desk.admStore.model.ProductValueMeasureUnit;
import br.com.watchtower.desk.admStore.queryAssembler.ProductBean;
import br.com.watchtower.desk.admStore.queryAssembler.ProductBrandBean;
import br.com.watchtower.desk.admStore.queryAssembler.ProductCategoryBean;
import br.com.watchtower.desk.admStore.queryAssembler.ProductCoverBean;
import br.com.watchtower.desk.admStore.queryAssembler.ProductDetailBean;
import br.com.watchtower.desk.admStore.queryAssembler.ProductMeasureUnitBean;
import br.com.watchtower.desk.admStore.queryAssembler.ProductTypeBean;
import br.com.watchtower.desk.admStore.queryAssembler.ProductValueMeasureUnitBean;
import br.com.watchtower.desk.admStore.utils.DataTable;
import br.com.watchtower.desk.admStore.view.MainFrame;
import br.com.watchtower.desk.admStore.view.product.ProductPanel;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Wallace
 */
public class ProductController extends BaseController {

    //Database access indentifiers 
    private ProductBean productBean;

    private ProductBrandBean productBrandBean;

    private ProductTypeBean productTypeBean;

    private ProductCoverBean productCoverBean;

    private ProductCategoryBean productCategoryBean;

    private ProductMeasureUnitBean productMeasureUnitBean;

    private ProductValueMeasureUnitBean productValueMeasureUnitBean;

    private ProductDetailBean productDetailBean;

    //DataTable indentifiers
    private DataTable searchDataTable;

    private DataTable dataTable;

    //Window indentifiers
    private MainFrame mainFrame;

    private ProductPanel productPanel;

    //Constructor
    public ProductController() {
        setProductBean(new ProductBean());
        setProductBrandBean(new ProductBrandBean());
        setProductTypeBean(new ProductTypeBean());
        setProductCoverBean(new ProductCoverBean());
        setProductCategoryBean(new ProductCategoryBean());
        setProductMeasureUnitBean(new ProductMeasureUnitBean());
        setProductValueMeasureUnitBean(new ProductValueMeasureUnitBean());
        setProductDetailBean(new ProductDetailBean());
        setSearchDataTable(new DataTable(getProductTableColumnNames()));
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Query basic method 
    //Create a new product(Write data in database)
    public String save(Product product) {
        try {
            if (!exists(product)) {
                getProductBean().create(product);
            } else {
                getProductBean().edit(product);
            }
            return getFeedBackSucesso();
        } catch (Exception e) {
            return getFeedBackErro();
        }
    }

    public void saveProductObject(ProductPanel productPanel) {

        ProductBrand productBrand = findBrandByName(productPanel.getjComboBox2().getModel().getSelectedItem().toString());
        ProductCategory productCategory = findCategoryByName(productPanel.getjComboBox8().getModel().getSelectedItem().toString());
        ProductCover productCover = findCoverByName(productPanel.getjComboBox6().getModel().getSelectedItem().toString());
        ProductDetail productDetail = findDetailByName(productPanel.getjComboBox3().getModel().getSelectedItem().toString());
        String[] measureUnits = splitNameInList(productPanel.getjComboBox5().getModel().getSelectedItem().toString());
        ProductMeasureUnit productMeasureUnit = findMeasureUnitByName(measureUnits[0], measureUnits[2]);
        ProductType productType = findTypeByName(productPanel.getjComboBox1().getModel().getSelectedItem().toString());
        ProductValueMeasureUnit productValueMeasureUnit = findValueMeasureUnitByName(productPanel.getjComboBox4().getModel().getSelectedItem().toString());
        boolean isPound = false;
        if (!productPanel.getjRadioButton1().isSelected()) {
            isPound = true;
        }

        Product product = new Product();
        product.setActive(true);
        product.setDateRegistration(Calendar.getInstance().getTime());
        product.setMeasureUnit(isPound);
        product.setProductBrandId(productBrand);
        product.setProductCategoryId(productCategory);
        product.setProductCoverId(productCover);
        product.setProductDetailId(productDetail);
        product.setProductTypeId(productType);
        product.setProductValueMeasureUnitId(productValueMeasureUnit);
        product.setProductMeasureUnitId(productMeasureUnit);
        save(product);
    }

    //Know about if the product already exists
    public boolean exists(Product product) {
        Product pCadastred;
        try {
            pCadastred = findByAll(
                    product.getProductBrandId().getBrandName(),
                    product.getProductCategoryId().getCategory(),
                    product.getProductCoverId().getCover(),
                    product.getProductDetailId().getDetail(),
                    product.getProductMeasureUnitId().getNameMeasure(),
                    product.getProductMeasureUnitId().getShortNameMeasure(),
                    product.getProductTypeId().getType(),
                    product.getProductValueMeasureUnitId().getValue(),
                    product.getMeasureUnit());

        } catch (Exception exception) {
            pCadastred = null;
        }
        if (pCadastred != null) {
            return true;
        } else {
            return false;
        }

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Read data in database methods
    public List<Product> findAll() {
        setProductBean(new ProductBean());
        return getProductBean().findAll();
    }

    public List<Product> findByMeasureUnit(boolean measureUnit) {
        return getProductBean().findByMeasureUnit(measureUnit);
    }

    public Product findByAll(String brand, String category, String cover, String detail, String nameMeasure, String shortNameMeasure, String type, float valueMeasureUnit, boolean measureUnit) {
        return getProductBean().findByAll(brand, category, cover, detail, nameMeasure, shortNameMeasure, type, valueMeasureUnit, measureUnit);
    }

    public Product findSelectedProduct(JTable jTable, JComboBox jComboBox) {
        String[] strings = splitNameInList(jTable.getValueAt(jTable.getSelectedRow(), 5).toString());
        return findByAll(
                jTable.getValueAt(jTable.getSelectedRow(), 3).toString(),
                jTable.getValueAt(jTable.getSelectedRow(), 0).toString(),
                jTable.getValueAt(jTable.getSelectedRow(), 2).toString(),
                jTable.getValueAt(jTable.getSelectedRow(), 6).toString(),
                strings[0],
                strings[2],
                jTable.getValueAt(jTable.getSelectedRow(), 1).toString(),
                Float.parseFloat(jTable.getValueAt(jTable.getSelectedRow(), 4).toString()),
                makeBooleanValue(jComboBox.getSelectedIndex()));
    }

    public List<ProductMeasureUnit> findAllProductMeasureUnit() {
        setProductMeasureUnitBean(new ProductMeasureUnitBean());
        return getProductMeasureUnitBean().findAll();
    }

    public List<ProductValueMeasureUnit> findAllProductValueMeasureUnit() {
        setProductValueMeasureUnitBean(new ProductValueMeasureUnitBean());
        return getProductValueMeasureUnitBean().findAll();
    }

    public List<ProductBrand> findAllProductBrand() {
        setProductBrandBean(new ProductBrandBean());
        return getProductBrandBean().findAll();
    }

    public List<ProductType> findAllProductType() {
        setProductTypeBean(new ProductTypeBean());
        return getProductTypeBean().findAll();
    }

    public List<ProductCover> findAllProductCover() {
        setProductCoverBean(new ProductCoverBean());
        return getProductCoverBean().findAll();
    }

    public List<ProductCategory> findAllProductCategory() {
        setProductCategoryBean(new ProductCategoryBean());
        return getProductCategoryBean().findAll();
    }

    public List<ProductDetail> findAllProductDetail() {
        setProductDetailBean(new ProductDetailBean());
        return getProductDetailBean().findAll();
    }

    public ProductMeasureUnit findMeasureUnitByName(String name, String shortName) {
        return getProductMeasureUnitBean().findByNameMeasureNShortNameMeasure(name, shortName);
    }

    public ProductValueMeasureUnit findValueMeasureUnitByName(String name) {
        return getProductValueMeasureUnitBean().findByValueMeasureUnit(name);
    }

    public ProductBrand findBrandByName(String name) {
        return getProductBrandBean().findByBrandName(name);
    }

    public ProductType findTypeByName(String name) {
        return getProductTypeBean().findByType(name);
    }

    public ProductCover findCoverByName(String name) {
        return getProductCoverBean().findByCoverName(name);
    }

    public ProductDetail findDetailByName(String name) {
        return getProductDetailBean().findByDetailName(name);
    }

    public ProductCategory findCategoryByName(String name) {
        return getProductCategoryBean().findByCategory(name);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Table Control
    public void setProductTable(JTable jTable) {
        List<Product> products = findAll();
        DataTable productTable = new DataTable(getProductTableColumnNames(), jTable);
        for (Product product : products) {
            productTable.addRow(new Object[]{
                product.getProductCategoryId().getCategory(),
                product.getProductTypeId().getType(),
                product.getProductCoverId().getCover(),
                product.getProductBrandId().getBrandName(),
                product.getProductValueMeasureUnitId().getValue(),
                product.getProductMeasureUnitId().getNameMeasure() + " - " + product.getProductMeasureUnitId().getShortNameMeasure(),
                product.getProductDetailId().getDetail(),
                DateFormat.getDateInstance().format(product.getDateRegistration()) + " - " + DateFormat.getTimeInstance().format(product.getDateRegistration())});
        }
        jTable.setModel(productTable);
    }

    public void setTableByMeasure(JTable jtable, boolean measureUnit) {
        List<Product> products = findByMeasureUnit(measureUnit);
        DataTable tableByMeasure;
        tableByMeasure = new DataTable(getProductTableColumnNames(), jtable);
        for (Product product : products) {
            tableByMeasure.addRow(new Object[]{
                product.getProductCategoryId().getCategory(),
                product.getProductTypeId().getType(),
                product.getProductCoverId().getCover(),
                product.getProductBrandId().getBrandName(),
                product.getProductValueMeasureUnitId().getValue(),
                product.getProductMeasureUnitId().getNameMeasure() + " - "
                + product.getProductMeasureUnitId().getShortNameMeasure() + "",
                product.getProductDetailId().getDetail(),
                DateFormat.getDateInstance().format(product.getDateRegistration()) + " - "
                + DateFormat.getTimeInstance().format(product.getDateRegistration())
            });

        }

        jtable.setModel(tableByMeasure);
    }

    public void setSearchTable(JTable jTable) {
        getSearchDataTable().setTableRowSorter(jTable);
        List<Product> products = findAll();
        for (Product product : products) {
            getSearchDataTable().addRow(
                    new Object[]{product.getProductCategoryId().getCategory(),
                        product.getProductTypeId().getType(),
                        product.getProductCoverId().getCover(),
                        product.getProductBrandId().getBrandName(),
                        product.getProductValueMeasureUnitId().getValue(),
                        product.getProductMeasureUnitId().getNameMeasure() + " - " + product.getProductMeasureUnitId().getShortNameMeasure(),
                        product.getProductDetailId().getDetail(),
                        product.getMeasureUnit(),
                        product.getActive(),
                        DateFormat.getDateInstance().format(product.getDateRegistration()) + " - " + DateFormat.getTimeInstance().format(product.getDateRegistration())});
        }
        jTable.setModel(getSearchDataTable());
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Filter table control
    public void setProductTableFilter(JTextField jTextField) {
        makeDataTableFilter(jTextField.getText(), getDataTable());
    }

    public void setSearchTableFilter(JTextField jTextField) {
        makeDataTableFilter(jTextField.getText(), getSearchDataTable());
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Combobox data control

    public void resetJComboBox(JComboBox jComboBox1, JComboBox jComboBox2, JComboBox jComboBox3, JComboBox jComboBox4, JComboBox jComboBox5, JComboBox jComboBox6, JComboBox jComboBox8) {
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        jComboBox8.setSelectedIndex(0);
    }

    public void setDataComboBox(JComboBox jComboBox1, JComboBox jComboBox2, JComboBox jComboBox3, JComboBox jComboBox4, JComboBox jComboBox5, JComboBox jComboBox6, JComboBox jComboBox8) {
        setComboBoxProductTypeData(jComboBox1);
        setComboBoxProductBrandData(jComboBox2);
        setComboBoxProductDetailData(jComboBox3);
        setComboBoxProductValueMeasureUnitData(jComboBox4);
        setComboBoxProductMeasureUnitData(jComboBox5);
        setComboBoxProductCoverData(jComboBox6);
        setComboBoxProductCategoryData(jComboBox8);
        resetJComboBox(jComboBox1, jComboBox2, jComboBox3, jComboBox4, jComboBox5, jComboBox6, jComboBox8);
    }

    public void setComboBoxProductTypeData(JComboBox jComboBox) {
        jComboBox.setModel(makeComboBox(toProductStringArray(findAllProductType())));
    }

    public void setComboBoxProductCoverData(JComboBox jComboBox) {
        jComboBox.setModel(makeComboBox(toProductCoverStringArray(findAllProductCover())));
    }

    public void setComboBoxProductBrandData(JComboBox jComboBox) {
        jComboBox.setModel(makeComboBox(toProductBrandStringArray(findAllProductBrand())));
    }

    public void setComboBoxProductCategoryData(JComboBox jComboBox) {
        jComboBox.setModel(makeComboBox(toProductCategoryStringArray(findAllProductCategory())));
    }

    public void setComboBoxProductMeasureUnitData(JComboBox jComboBox) {
        jComboBox.setModel(makeComboBox(toProductMeasureUnitStringArray(findAllProductMeasureUnit())));
    }

    public void setComboBoxProductValueMeasureUnitData(JComboBox jComboBox) {
        jComboBox.setModel(makeComboBox(toProductValueMeasureUnitStringArray(findAllProductValueMeasureUnit())));
    }

    public void setComboBoxProductDetailData(JComboBox jComboBox) {
        jComboBox.setModel(makeComboBox(toProductDetailStringArray(findAllProductDetail())));
    }

    public String[] toProductMeasureUnitStringArray(List<ProductMeasureUnit> productMeasureUnits) {
        String[] nameProductMeasureUnits = new String[productMeasureUnits.size() + 1];
        int count = 0;
        nameProductMeasureUnits[count] = "Unidade de medida";
        for (ProductMeasureUnit productMeasureUnit : productMeasureUnits) {
            count++;
            nameProductMeasureUnits[count] = productMeasureUnit.getNameMeasure() + " - " + productMeasureUnit.getShortNameMeasure();
        }
        return nameProductMeasureUnits;
    }

    public String[] toProductValueMeasureUnitStringArray(List<ProductValueMeasureUnit> productValueMeasureUnits) {
        String[] nameProductValueMeasureUnits = new String[productValueMeasureUnits.size() + 1];
        int count = 0;
        nameProductValueMeasureUnits[count] = "Valor númerico";
        for (ProductValueMeasureUnit productValueMeasureUnit : productValueMeasureUnits) {
            count++;
            nameProductValueMeasureUnits[count] = "" + productValueMeasureUnit.getValue();
        }
        return nameProductValueMeasureUnits;
    }

    public String[] toProductStringArray(List<ProductType> productTypes) {
        String[] nameProductTypes = new String[productTypes.size() + 1];
        int count = 0;
        nameProductTypes[count] = "Tipo de produto";
        for (ProductType productType : productTypes) {
            count++;
            nameProductTypes[count] = productType.getType();
        }
        return nameProductTypes;
    }

    public String[] toProductCategoryStringArray(List<ProductCategory> productCategorys) {
        String[] nameProductCategorys = new String[productCategorys.size() + 1];
        int count = 0;
        nameProductCategorys[count] = "Categoria";
        for (ProductCategory productCategory : productCategorys) {
            count++;
            nameProductCategorys[count] = productCategory.getCategory();
        }
        return nameProductCategorys;
    }

    public String[] toProductCoverStringArray(List<ProductCover> productCovers) {
        String[] nameProductCovers = new String[productCovers.size() + 1];
        int count = 0;
        nameProductCovers[count] = "Envolocro";
        for (ProductCover productCover : productCovers) {
            count++;
            nameProductCovers[count] = productCover.getCover();
        }
        return nameProductCovers;
    }

    public String[] toProductBrandStringArray(List<ProductBrand> productBrands) {
        String[] nameProductBrands = new String[productBrands.size() + 1];
        int count = 0;
        nameProductBrands[count] = "Marca";
        for (ProductBrand productBrand : productBrands) {
            count++;
            nameProductBrands[count] = productBrand.getBrandName();
        }
        return nameProductBrands;
    }

    public String[] toProductDetailStringArray(List<ProductDetail> productDetails) {
        String[] nameProductDetails = new String[productDetails.size() + 1];
        int count = 0;
        nameProductDetails[count] = "Especificação";
        for (ProductDetail productDetail : productDetails) {
            count++;
            nameProductDetails[count] = productDetail.getDetail();
        }
        return nameProductDetails;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//View general setup    
    public void setNewProductPanel(MainFrame aThis) {
        ProductPanel pPanel = null;
        pPanel = new ProductPanel();
        aThis.setContentPane(pPanel);
        setMainFrame(aThis);
        setProductPanel(pPanel);
        aThis.pack();
        resizeWindow(aThis);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Get's and Set's 
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

    private ProductCoverBean getProductCoverBean() {
        return productCoverBean;
    }

    private void setProductCoverBean(ProductCoverBean productCoverBean) {
        this.productCoverBean = productCoverBean;
    }

    public ProductCategoryBean getProductCategoryBean() {
        return productCategoryBean;
    }

    private void setProductCategoryBean(ProductCategoryBean productCategoryBean) {
        this.productCategoryBean = productCategoryBean;
    }

    public ProductValueMeasureUnitBean getProductValueMeasureUnitBean() {
        return productValueMeasureUnitBean;
    }

    private void setProductValueMeasureUnitBean(ProductValueMeasureUnitBean productValueMeasureUnitBean) {
        this.productValueMeasureUnitBean = productValueMeasureUnitBean;
    }

    public ProductMeasureUnitBean getProductMeasureUnitBean() {
        return productMeasureUnitBean;
    }

    private void setProductMeasureUnitBean(ProductMeasureUnitBean productMeasureUnitBean) {
        this.productMeasureUnitBean = productMeasureUnitBean;
    }

    public ProductDetailBean getProductDetailBean() {
        return productDetailBean;
    }

    private void setProductDetailBean(ProductDetailBean productDetailBean) {
        this.productDetailBean = productDetailBean;
    }

    private DataTable getSearchDataTable() {
        return searchDataTable;
    }

    private void setSearchDataTable(DataTable searchDataTable) {
        this.searchDataTable = searchDataTable;
    }

    private DataTable getDataTable() {
        return dataTable;
    }

    private void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public void setInitialRadioButtonValue(JRadioButton jRadioButton1) {
        jRadioButton1.setSelected(true);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public ProductPanel getProductPanel() {
        return productPanel;
    }

    public void setProductPanel(ProductPanel productPanel) {
        this.productPanel = productPanel;
    }

}
