package com.agrocomercial.clientes.views.administrators;

import com.agrocomercial.clientes.context.AppContext;
import com.agrocomercial.clientes.controller.administrators.AdministratorController;
import com.agrocomercial.clientes.models.Administrator;
import com.agrocomercial.clientes.models.Customer;
import com.agrocomercial.clientes.models.User;
import com.agrocomercial.clientes.services.CustomerService;
import com.agrocomercial.clientes.services.UserService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListUsersView extends JFrame {

  private final AppContext appContext;
  private final AdministratorController administratorController;
  private final UserService userService;
  private final CustomerService customerService;

  // Componentes de la interfaz
  private JTable tblAdministrators;
  private DefaultTableModel tableModel;
  private JButton btnClose;

  public ListUsersView(AppContext appContext, AdministratorController administratorController) {
    this.appContext = appContext;
    this.administratorController = administratorController;
    this.userService = appContext.getServiceContext().getUserService();
    this.customerService = appContext.getServiceContext().getCustomerService();

    initComponents();
    setupTable();
    setupLayout();
    setupListeners();
    loadData();
  }

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
  }

  private void setupLayout() {
    setLayout(new BorderLayout());

    // Panel principal con padding
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

    // Título
    JLabel lblTitle = new JLabel("Usuarios del Sistema");
    lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
    lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    mainPanel.add(lblTitle);
    mainPanel.add(Box.createVerticalStrut(20));

    // Panel de botones superiores
    JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    mainPanel.add(topButtonPanel);
    mainPanel.add(Box.createVerticalStrut(10));

    // Tabla
    JScrollPane scrollPane = new JScrollPane(tblAdministrators);
    scrollPane.setPreferredSize(new Dimension(850, 400));
    mainPanel.add(scrollPane);
    mainPanel.add(Box.createVerticalStrut(10));

    // Panel de botones inferiores
    JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomButtonPanel.add(btnClose);

    mainPanel.add(bottomButtonPanel);

    add(mainPanel, BorderLayout.CENTER);
  }

  private void setupTable() {
    String[] columnNames = { "ID Usuario", "Username", "Rol", "ID Persona", "Nombre", "Apellido", "Teléfono",
        "Identificación" };
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false; // Hacer la tabla no editable
      }
    };

    tblAdministrators = new JTable(tableModel);
    tblAdministrators.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tblAdministrators.getTableHeader().setReorderingAllowed(false);

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

  private void setupListeners() {
    btnClose.addActionListener(e -> {
      dispose();
      appContext.getMainMenuView().setVisible(true);
    });

  }

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
