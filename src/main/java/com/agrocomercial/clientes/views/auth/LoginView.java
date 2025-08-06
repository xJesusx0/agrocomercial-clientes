package com.agrocomercial.clientes.views.auth;

import com.agrocomercial.clientes.context.AppContext;
import com.agrocomercial.clientes.controller.auth.AuthController;
import com.agrocomercial.clientes.utils.WindowUtils;

import javax.swing.*;

/**
 *
 * @author Jesús Perea
 */
public class LoginView extends JFrame {
    
    private final transient AuthController authController;
    private final transient AppContext localAppContext;

    /**
     * Creates new form MainFrame
     */
    public LoginView(AppContext localAppContext, AuthController authController) {
        initComponents();
        this.authController = authController;
        this.localAppContext = localAppContext;
    }

    private void initComponents() {
        javax.swing.JButton clearButton;
        javax.swing.JLabel jLabel1;
        javax.swing.JLabel jLabel2;
        javax.swing.JButton loginButton;

        jLabel1 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuario");

        jLabel2.setText("Contraseña");

        loginButton.setText("Iniciar sesion");
        loginButton.addActionListener(evt -> loginButtonActionPerformed());

        clearButton.setText("Limpiar");
        clearButton.addActionListener(evt -> clearButtonActionPerformed());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(passwordField)
                    .addComponent(usernameField)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void loginButtonActionPerformed() {//GEN-FIRST:event_loginButtonActionPerformed
        
        char[] passwordArray = passwordField.getPassword();
        String password = new String(passwordArray);
        String username = usernameField.getText();

        if(authController.authenticateUser(username, password) != null){
            JOptionPane.showMessageDialog(null, "Bienvenido " + username);
            WindowUtils.closeAndShowPanel(this, localAppContext.getMainMenuView());
            return;
        }

        JOptionPane.showMessageDialog(null, "Credenciales invalidas");
        
    }//GEN-LAST:event_loginButtonActionPerformed

    private void clearButtonActionPerformed() {//GEN-FIRST:event_clearButtonActionPerformed
        usernameField.setText("");
        passwordField.setText("");

    }//GEN-LAST:event_clearButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
