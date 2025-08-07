package com.agrocomercial.clientes.views.administrators;

import com.agrocomercial.clientes.context.AppContext;
import com.agrocomercial.clientes.controller.administrators.AdministratorController;
import com.agrocomercial.clientes.models.Administrator;
import com.agrocomercial.clientes.models.Customer;
import com.agrocomercial.clientes.models.User;
import com.agrocomercial.clientes.services.CustomerService;
import com.agrocomercial.clientes.services.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListUsersView extends JFrame {

    private final AppContext appContext;
    private final AdministratorController administratorController;
    private final UserService userService;
    private final CustomerService customerService;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Componentes de la interfaz
    private JTable tblAdministrators;
    private DefaultTableModel tableModel;
    private JButton btnClose;
    // End of variables declaration//GEN-END:variables

    public ListUsersView(AppContext appContext, AdministratorController administratorController) {
        this.appContext = appContext;
        this.administratorController = administratorController;
        this.userService = appContext.getServiceContext().getUserService();
        this.customerService = appContext.getServiceContext().getCustomerService();

        setupTable();
        initComponents();
        setupListeners();
        loadData();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setTitle("Gestión de Usuarios del Sistema");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        btnClose = new JButton("Cerrar");

        // Estilo de botones
        btnClose.setBackground(new Color(149, 165, 166));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        
        // Crear JScrollPane con la tabla
        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(tblAdministrators);
        jScrollPane1.setPreferredSize(new Dimension(850, 400));
        jScrollPane1.setVisible(true);
        
        // Configurar el layout y agregar componentes
        setLayout(new javax.swing.GroupLayout(getContentPane()));
        getContentPane().setLayout(new javax.swing.GroupLayout(getContentPane()));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void setupTable() {
        String[] columnNames = {"ID Usuario", "Username", "Rol", "ID Persona", "Nombre", "Apellido", "Teléfono",
            "Identificación"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };

        tblAdministrators = new JTable(tableModel);
        tblAdministrators.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblAdministrators.getTableHeader().setReorderingAllowed(false);
        tblAdministrators.setVisible(true);
        tblAdministrators.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Configurar el ancho de las columnas
        tblAdministrators.getColumnModel().getColumn(0).setPreferredWidth(80); // ID Usuario
        tblAdministrators.getColumnModel().getColumn(1).setPreferredWidth(100); // Username
        tblAdministrators.getColumnModel().getColumn(2).setPreferredWidth(100); // Rol
        tblAdministrators.getColumnModel().getColumn(3).setPreferredWidth(80); // ID Persona
        tblAdministrators.getColumnModel().getColumn(4).setPreferredWidth(120); // Nombre
        tblAdministrators.getColumnModel().getColumn(5).setPreferredWidth(120); // Apellido
        tblAdministrators.getColumnModel().getColumn(6).setPreferredWidth(100); // Teléfono
        tblAdministrators.getColumnModel().getColumn(7).setPreferredWidth(100); // Identificación
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:setupListeners
    private void setupListeners() {
        btnClose.addActionListener(evt -> btnCloseActionPerformed());
    }// </editor-fold>//GEN-END:setupListeners

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:btnCloseActionPerformed
    private void btnCloseActionPerformed() {
        dispose();
        appContext.getMainMenuView().setVisible(true);
    }// </editor-fold>//GEN-END:btnCloseActionPerformed

    private void loadData() {
        tableModel.setRowCount(0);

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
                tableModel.addRow(row);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los usuarios: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
