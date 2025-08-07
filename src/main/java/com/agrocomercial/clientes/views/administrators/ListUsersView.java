package com.agrocomercial.clientes.views.administrators;

import com.agrocomercial.clientes.context.AppContext;
import com.agrocomercial.clientes.controller.administrators.AdministratorController;
import com.agrocomercial.clientes.models.Administrator;
import com.agrocomercial.clientes.models.Customer;
import com.agrocomercial.clientes.models.User;
import com.agrocomercial.clientes.services.CustomerService;
import com.agrocomercial.clientes.services.UserService;

import javax.swing.*;
import java.util.List;

public class ListUsersView extends JFrame {

    private final AppContext appContext;
    private final AdministratorController administratorController;
    private final UserService userService;
    private final CustomerService customerService;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAdministrators;
    // End of variables declaration//GEN-END:variables

    public ListUsersView(AppContext appContext, AdministratorController administratorController) {
        this.appContext = appContext;
        this.administratorController = administratorController;
        this.userService = appContext.getServiceContext().getUserService();
        this.customerService = appContext.getServiceContext().getCustomerService();

        initComponents();
        setupListeners();
        loadData();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblAdministrators = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Usuarios del Sistema");
        setResizable(false);

        tblAdministrators.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Usuario", "Username", "Rol", "ID Persona", "Nombre", "Apellido", "Teléfono", "Identificación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAdministrators.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblAdministrators);
        if (tblAdministrators.getColumnModel().getColumnCount() > 0) {
            tblAdministrators.getColumnModel().getColumn(0).setResizable(false);
            tblAdministrators.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblAdministrators.getColumnModel().getColumn(1).setResizable(false);
            tblAdministrators.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblAdministrators.getColumnModel().getColumn(2).setResizable(false);
            tblAdministrators.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblAdministrators.getColumnModel().getColumn(3).setResizable(false);
            tblAdministrators.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblAdministrators.getColumnModel().getColumn(4).setResizable(false);
            tblAdministrators.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblAdministrators.getColumnModel().getColumn(5).setResizable(false);
            tblAdministrators.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblAdministrators.getColumnModel().getColumn(6).setResizable(false);
            tblAdministrators.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblAdministrators.getColumnModel().getColumn(7).setResizable(false);
            tblAdministrators.getColumnModel().getColumn(7).setPreferredWidth(100);
        }

        btnClose.setBackground(new java.awt.Color(255, 51, 51));
        btnClose.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("Cerrar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnClose)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:setupListeners
    private void setupListeners() {
        // Los listeners ya están configurados en initComponents()
    }// </editor-fold>//GEN-END:setupListeners

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:btnCloseActionPerformed
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        appContext.getMainMenuView().setVisible(true);
    }// </editor-fold>//GEN-END:btnCloseActionPerformed

    private void loadData() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblAdministrators.getModel();
        model.setRowCount(0);

        try {
            List<User> users = userService.findAll();

            for (User user : users) {
                // Buscar si es administrador
                Administrator admin = administratorController.findByUserId(user.getId());
                // Buscar si es customer
                Customer customer = customerService.findByUserId(user.getId());

                String role = "Usuario";
                Integer personId = null;
                String name = "";
                String lastname = "";
                String phone = "";
                String identification = "";

                if (admin != null) {
                    role = "Administrador";
                    personId = admin.getId();
                    name = admin.getName();
                    lastname = admin.getLastname();
                    phone = admin.getPhoneNumber();
                    identification = admin.getIdentification();
                } else if (customer != null) {
                    role = "Cliente";
                    personId = customer.getId();
                    name = customer.getName();
                    lastname = customer.getLastname();
                    phone = customer.getPhoneNumber();
                    identification = customer.getIdentification();
                }

                Object[] row = {
                    user.getId(),
                    user.getUsername(),
                    role,
                    personId,
                    name,
                    lastname,
                    phone,
                    identification
                };
                model.addRow(row);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los usuarios: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
