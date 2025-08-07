/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
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
        javax.swing.JButton administratorsRedirection;
        javax.swing.JButton locationsRedirection;
        javax.swing.JButton logoutButton;

        customerRedirection = new javax.swing.JButton();
        ordersRedirection = new javax.swing.JButton();
        productsRedirection = new javax.swing.JButton();
        administratorsRedirection = new javax.swing.JButton();
        locationsRedirection = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        customerRedirection.setText("Clientes");
        customerRedirection.addActionListener(evt -> customerRedirectionActionPerformed());

        ordersRedirection.setText("Ordenes");
        ordersRedirection.addActionListener(evt -> ordersRedirectionActionPerformed());

        productsRedirection.setText("Productos");
        productsRedirection.addActionListener(evt -> productsRedirectionActionPerformed());

        administratorsRedirection.setText("Panel de Usuarios");
        administratorsRedirection.addActionListener(evt -> administratorsRedirectionActionPerformed());

        locationsRedirection.setText("Sedes");
        locationsRedirection.addActionListener(evt -> locationsRedirectionActionPerformed());

        logoutButton.setText("Cerrar Sesión");
        logoutButton.setBackground(new java.awt.Color(231, 76, 60));
        logoutButton.setForeground(java.awt.Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(evt -> logoutButtonActionPerformed());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(customerRedirection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ordersRedirection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(productsRedirection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(administratorsRedirection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(locationsRedirection)
                .addGap(0, 50, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerRedirection)
                    .addComponent(ordersRedirection)
                    .addComponent(productsRedirection)
                    .addComponent(administratorsRedirection)
                    .addComponent(locationsRedirection))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void customerRedirectionActionPerformed() {//GEN-FIRST:event_customerRedirectionActionPerformed
        validateRedirectToAdminViews(localAppContext.getCustomerView());
    }//GEN-LAST:event_customerRedirectionActionPerformed

    private void ordersRedirectionActionPerformed() {//GEN-FIRST:event_ordersRedirectionActionPerformed
        validateRedirectToCustomerViews(localAppContext.getListOrdersView());
    }//GEN-LAST:event_ordersRedirectionActionPerformed

    private void productsRedirectionActionPerformed() {//GEN-FIRST:event_productsRedirectionActionPerformed
        validateRedirectToAdminViews(localAppContext.getListProductsView());
    }//GEN-LAST:event_productsRedirectionActionPerformed

    private void administratorsRedirectionActionPerformed() {//GEN-FIRST:event_administratorsRedirectionActionPerformed
        validateRedirectToAdminViews(localAppContext.getListUsersView());
    }//GEN-LAST:event_administratorsRedirectionActionPerformed

    private void locationsRedirectionActionPerformed() {//GEN-FIRST:event_locationsRedirectionActionPerformed
        validateRedirectToAdminViews(localAppContext.getListLocationsView());
    }//GEN-LAST:event_locationsRedirectionActionPerformed

    private void validateRedirectToCustomerViews(JFrame view){
        boolean isCustomer = localAppContext.getLoggedUser()
                .getRoles()
                .contains(Roles.CUSTOMER);

        if(!isCustomer){
            JOptionPane.showMessageDialog(null, "El usuario no tiene acceso a esta vista");
            return;
        }

        WindowUtils.closeAndShowPanel(this, view);
    }

    private void validateRedirectToAdminViews(JFrame view){
        boolean isAdmin = localAppContext.getLoggedUser()
                .getRoles()
                .contains(Roles.ADMINISTRATOR);

        if(!isAdmin){
            JOptionPane.showMessageDialog(null, "Solo los administradores tienen acceso a esta vista");
            return;
        }

        WindowUtils.closeAndShowPanel(this, view);
    }

    private void logoutButtonActionPerformed() {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro que desea cerrar sesión?",
            "Confirmar Cerrar Sesión",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Limpiar los datos del usuario logueado
            localAppContext.getLoggedUser().clear();
            
            // Limpiar los campos de login
            localAppContext.getLoginView().clearFields();
            
            // Cerrar la ventana actual
            dispose();
            
            // Mostrar la pantalla de login
            localAppContext.getLoginView().setVisible(true);
        }
    }
}
