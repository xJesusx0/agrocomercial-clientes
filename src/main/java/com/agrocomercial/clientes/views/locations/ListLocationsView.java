package com.agrocomercial.clientes.views.locations;

import com.agrocomercial.clientes.context.AppContext;
import com.agrocomercial.clientes.controller.locations.LocationController;
import com.agrocomercial.clientes.models.Location;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListLocationsView extends JFrame {

    private final AppContext appContext;
    private final LocationController locationController;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Componentes de la interfaz
    private JTable tblLocations;
    private DefaultTableModel tableModel;
    private JButton btnClose;
    // End of variables declaration//GEN-END:variables

    public ListLocationsView(AppContext appContext, LocationController locationController) {
        this.appContext = appContext;
        this.locationController = locationController;

        setupTable();
        initComponents();
        setupListeners();
        loadData();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setTitle("Lista de Ubicaciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        btnClose = new JButton("Cerrar");

        // Estilo de botones
        btnClose.setBackground(new Color(149, 165, 166));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        
        // Crear JScrollPane con la tabla
        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(tblLocations);
        jScrollPane1.setPreferredSize(new Dimension(650, 300));
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void setupTable() {
        String[] columnNames = {"ID", "Nombre", "Dirección"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };

        tblLocations = new JTable(tableModel);
        tblLocations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblLocations.getTableHeader().setReorderingAllowed(false);
        tblLocations.setVisible(true);
        tblLocations.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Configurar el ancho de las columnas
        tblLocations.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
        tblLocations.getColumnModel().getColumn(1).setPreferredWidth(200); // Nombre
        tblLocations.getColumnModel().getColumn(2).setPreferredWidth(400); // Dirección
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:setupListeners
    private void setupListeners() {
        btnClose.addActionListener(e -> {
            dispose();
            appContext.getMainMenuView().setVisible(true);
        });
    }// </editor-fold>//GEN-END:setupListeners

    private void loadData() {
        tableModel.setRowCount(0);

        try {
            List<Location> locations = locationController.findAll();

            for (Location location : locations) {
                Object[] row = {
                    location.getId(),
                    location.getName(),
                    location.getAddress()
                };
                tableModel.addRow(row);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar las ubicaciones: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
