/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.controller;

import br.com.watchtower.desk.admStore.view.product.ProductManagerStorePanel;
import br.com.watchtower.desk.admStore.view.product.ProductPanel;
import br.com.watchtower.desk.admStore.view.user.UserPanel;
import javax.swing.JFrame;

/**
 *
 * @author Wallace
 */
public class CenterViewController extends BaseController {

    private UserPanel userPanel;
    private ProductPanel productPanel;
    private ProductManagerStorePanel productManagerStorePanel;
    private JFrame jFrame;

    public CenterViewController(JFrame jFrame) {
        setjFrame(jFrame);
        setProductManagerStorePanel(new ProductManagerStorePanel());
        setProductPanel(new ProductPanel());
        setUserPanel(new UserPanel());
        resizeWindow(getjFrame());
    }

    public void setUserPanel() {
        getUserPanel().setjFrame(getjFrame());
        getjFrame().setContentPane(getUserPanel());
        getjFrame().pack();
        resizeWindow(getjFrame());
    }

//    public void setProductPanel() {
//        getProductPanel().setJframe(getjFrame());
//        getjFrame().setContentPane(getProductPanel());
//        getjFrame().pack();
//        resizeWindow(getjFrame());
//    }

    public void setProductManegeStorePanel() {
        getProductManagerStorePanel().setjFrame(getjFrame());
        getjFrame().setContentPane(getProductManagerStorePanel());
        getjFrame().pack();
        resizeWindow(getjFrame());

    }

    public final JFrame getjFrame() {
        return jFrame;
    }

    private void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public UserPanel getUserPanel() {
        return userPanel;
    }

    private void setUserPanel(UserPanel userPanel) {
        this.userPanel = userPanel;
    }

    public ProductPanel getProductPanel() {
        return productPanel;
    }

    private void setProductPanel(ProductPanel productPanel) {
        this.productPanel = productPanel;
    }

    public ProductManagerStorePanel getProductManagerStorePanel() {
        return productManagerStorePanel;
    }

    private void setProductManagerStorePanel(ProductManagerStorePanel productManagerStorePanel) {
        this.productManagerStorePanel = productManagerStorePanel;
    }

}
