package com.agrocomercial.clientes.views.locations;

import com.agrocomercial.clientes.context.AppContext;
import com.agrocomercial.clientes.controller.locations.LocationController;
import com.agrocomercial.clientes.models.Location;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListLocationsView extends JFrame {

  private final AppContext appContext;
  private final LocationController locationController;

  // Componentes de la interfaz
  private JTable tblLocations;
  private DefaultTableModel tableModel;
  private JButton btnClose;

  public ListLocationsView(AppContext appContext, LocationController locationController) {
    this.appContext = appContext;
    this.locationController = locationController;

    initComponents();
    setupTable();
    setupLayout();
    setupListeners();
    loadData();
  }

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
  }

  private void setupLayout() {
    setLayout(new BorderLayout());

    // Panel principal con padding
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

    // Título
    JLabel lblTitle = new JLabel("Ubicaciones del Sistema");
    lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
    lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    mainPanel.add(lblTitle);
    mainPanel.add(Box.createVerticalStrut(20));

    // Panel de botones superiores
    JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    mainPanel.add(topButtonPanel);
    mainPanel.add(Box.createVerticalStrut(10));

    // Tabla
    JScrollPane scrollPane = new JScrollPane(tblLocations);
    scrollPane.setPreferredSize(new Dimension(650, 300));
    mainPanel.add(scrollPane);
    mainPanel.add(Box.createVerticalStrut(10));

    // Panel de botones inferiores
    JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomButtonPanel.add(btnClose);

    mainPanel.add(bottomButtonPanel);

    add(mainPanel, BorderLayout.CENTER);
  }

  private void setupTable() {
    String[] columnNames = { "ID", "Nombre", "Dirección" };
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false; // Hacer la tabla no editable
      }
    };

    tblLocations = new JTable(tableModel);
    tblLocations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tblLocations.getTableHeader().setReorderingAllowed(false);

    // Configurar el ancho de las columnas
    tblLocations.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
    tblLocations.getColumnModel().getColumn(1).setPreferredWidth(200); // Nombre
    tblLocations.getColumnModel().getColumn(2).setPreferredWidth(400); // Dirección
  }

  private void setupListeners() {
    btnClose.addActionListener(e -> {
      dispose();
      appContext.getMainMenuView().setVisible(true);
    });

  }

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
