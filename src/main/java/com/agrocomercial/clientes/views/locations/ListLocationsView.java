package com.agrocomercial.clientes.views.locations;

import com.agrocomercial.clientes.context.AppContext;
import com.agrocomercial.clientes.controller.locations.LocationController;
import com.agrocomercial.clientes.models.Location;

import javax.swing.*;
import java.util.List;

public class ListLocationsView extends JFrame {

    private final AppContext appContext;
    private final LocationController locationController;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLocations;
    // End of variables declaration//GEN-END:variables

    public ListLocationsView(AppContext appContext, LocationController locationController) {
        this.appContext = appContext;
        this.locationController = locationController;

        initComponents();
        setupListeners();
        loadData();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblLocations = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lista de Ubicaciones");
        setResizable(false);

        tblLocations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Dirección"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLocations.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblLocations);
        if (tblLocations.getColumnModel().getColumnCount() > 0) {
            tblLocations.getColumnModel().getColumn(0).setResizable(false);
            tblLocations.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblLocations.getColumnModel().getColumn(1).setResizable(false);
            tblLocations.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblLocations.getColumnModel().getColumn(2).setResizable(false);
            tblLocations.getColumnModel().getColumn(2).setPreferredWidth(400);
        }

        btnClose.setBackground(new java.awt.Color(255, 0, 51));
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblLocations.getModel();
        model.setRowCount(0);

        try {
            List<Location> locations = locationController.findAll();

            for (Location location : locations) {
                Object[] row = {
                    location.getId(),
                    location.getName(),
                    location.getAddress()
                };
                model.addRow(row);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar las ubicaciones: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
