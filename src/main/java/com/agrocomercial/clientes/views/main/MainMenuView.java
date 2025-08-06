/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this
 * license Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.agrocomercial.clientes.views.main;

import com.agrocomercial.clientes.context.AppContext;
import com.agrocomercial.clientes.utils.Roles;
import com.agrocomercial.clientes.utils.WindowUtils;
import javax.swing.*;

/**
 *
 * @author Jesús Perea
 */
public class MainMenuView extends javax.swing.JFrame {

  private final transient AppContext localAppContext;

  public MainMenuView(AppContext localAppContext) {
    initComponents();
    this.localAppContext = localAppContext;
  }


  private void initComponents() {
    javax.swing.JButton customerRedirection;
    javax.swing.JButton ordersRedirection;
    javax.swing.JButton productsRedirection;

    customerRedirection = new javax.swing.JButton();
    ordersRedirection = new javax.swing.JButton();
    productsRedirection = new javax.swing.JButton();


    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    customerRedirection.setText("clientes");
    customerRedirection.addActionListener(evt -> customerRedirectionActionPerformed());

    ordersRedirection.setText("ordenes");
    ordersRedirection.addActionListener(evt -> ordersRedirectionActionPerformed());

    productsRedirection.setText("productos");
    productsRedirection.addActionListener(evt -> productsRedirectionActionPerformed());

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addComponent(customerRedirection)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(ordersRedirection)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(productsRedirection).addGap(0, 134, Short.MAX_VALUE)));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customerRedirection).addComponent(ordersRedirection)
                            .addComponent(productsRedirection))
                    .addGap(0, 277, Short.MAX_VALUE)));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void customerRedirectionActionPerformed() {// GEN-FIRST:event_customerRedirectionActionPerformed
    validateRedirectToCustomerViews(localAppContext.getCustomerView());
  }// GEN-LAST:event_customerRedirectionActionPerformed

  private void ordersRedirectionActionPerformed() {// GEN-FIRST:event_ordersRedirectionActionPerformed
    validateRedirectToCustomerViews(localAppContext.getListOrdersView());
  }// GEN-LAST:event_ordersRedirectionActionPerformed

  private void productsRedirectionActionPerformed() {// GEN-FIRST:event_productsRedirectionActionPerformed
    WindowUtils.closeAndShowPanel(this, localAppContext.getListProductsView());
  }// GEN-LAST:event_productsRedirectionActionPerformed

  private void validateRedirectToCustomerViews(JFrame view) {
    boolean isCustomer = localAppContext.getLoggedUser().getRoles().contains(Roles.CUSTOMER);

    if (!isCustomer) {
      JOptionPane.showMessageDialog(null, "El usuario no tiene acceso a esta vista");
      return;
    }

    WindowUtils.closeAndShowPanel(this, view);
  }
}
