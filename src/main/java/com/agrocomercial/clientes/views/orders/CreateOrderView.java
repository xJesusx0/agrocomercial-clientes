/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this
 * license Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.agrocomercial.clientes.views.orders;

import com.agrocomercial.clientes.context.AppContext;
import com.agrocomercial.clientes.controller.orders.OrderProductController;
import com.agrocomercial.clientes.events.ProductAddedToOrderEventListener;
import com.agrocomercial.clientes.models.Product;
import com.agrocomercial.clientes.utils.WindowUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static com.agrocomercial.clientes.utils.WindowUtils.getTableModel;

/**
 *
 * @author jesus
 */
public class CreateOrderView extends javax.swing.JFrame implements ProductAddedToOrderEventListener {
  @Override
  public void onProductAdded(Product product, Integer quantity, Double subtotal) {
    DefaultTableModel defaultTableModel = (DefaultTableModel) productTable.getModel();

    defaultTableModel.addRow(new Object[] {product.getName(), quantity, subtotal});
  }

  private final transient AppContext localAppContext;
  private final transient OrderProductController controller;

  public CreateOrderView(AppContext appContext, OrderProductController controller) {
    initComponents();
    this.controller = controller;
    this.localAppContext = appContext;
    subscribeToOrderProductEvent();
  }

  private void subscribeToOrderProductEvent() {
    controller.subscribeToProductAddedToOrder(this);
  }

  private void initComponents() {
    javax.swing.JButton addProductButton;
    javax.swing.JLabel jLabel1;
    javax.swing.JButton saveButton;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JButton cancelButton;

    jLabel1 = new javax.swing.JLabel();
    orderNumberField = new javax.swing.JTextField();
    jScrollPane1 = new javax.swing.JScrollPane();
    productTable = new javax.swing.JTable();
    addProductButton = new javax.swing.JButton();
    saveButton = new javax.swing.JButton();
    cancelButton = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setText("Numero de orden");

    productTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

    }, new String[] {"Producto", "Cantidad", "Total"}) {
      final Class[] types = new Class[] {java.lang.String.class, java.lang.Integer.class, java.lang.Double.class};
      final boolean[] canEdit = new boolean[] {false, false, false};

      @Override
      public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
      }

      @Override
      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
      }
    });
    jScrollPane1.setViewportView(productTable);

    addProductButton.setText("añadir producto");
    addProductButton.addActionListener(evt -> addProductButtonActionPerformed());

    saveButton.setText("Guardar");
    saveButton.addActionListener(evt -> saveButtonActionPerformed());

    cancelButton.setText("cancelar");
    cancelButton.addActionListener(evt -> cancelButtonActionPerformed());

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
            .createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                            layout.createSequentialGroup().addGap(24, 24, 24).addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(orderNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 237,
                                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup().addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361,
                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup().addComponent(addProductButton)
                                            .addGap(18, 18, 18).addComponent(cancelButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(saveButton).addGap(23, 23, 23)))))
            .addContainerGap(20, Short.MAX_VALUE)));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
            .createSequentialGroup().addGap(13, 13, 13)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1)
                    .addComponent(orderNumberField, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(
                    jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addProductButton).addComponent(saveButton).addComponent(cancelButton))
            .addContainerGap(58, Short.MAX_VALUE)));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void cancelButtonActionPerformed() {// GEN-FIRST:event_cancelButtonActionPerformed
    WindowUtils.closeAndShowPanel(this, localAppContext.getListOrdersView());

  }// GEN-LAST:event_cancelButtonActionPerformed

  private void addProductButtonActionPerformed() {
    WindowUtils.closeAndShowPanel(this, localAppContext.getAddProductToOrderView());
  }

  private void saveButtonActionPerformed() {

    String orderNumberStr = orderNumberField.getText();
    long orderNumber;

    try {
      orderNumber = Long.parseLong(orderNumberStr);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "El número de orden debe ser un número válido.");
      return;
    }

    if (orderNumber <= 0) {
      JOptionPane.showMessageDialog(null, "El número de orden debe ser mayor que cero.");
      return;
    }

    int rows = getProductsTableModel().getRowCount();

    if (rows == 0) {
      JOptionPane.showMessageDialog(null, "No se puede crear una orden sin productos");
      return;
    }

    controller.saveOrder(orderNumber);
    JOptionPane.showMessageDialog(null, "Orden creada correctamente");
    clearFields();
    WindowUtils.closeAndShowPanel(this, localAppContext.getListOrdersView());
  }

  private void clearFields() {
    orderNumberField.setText("");
    DefaultTableModel defaultTableModel = getProductsTableModel();
    defaultTableModel.setRowCount(0);

  }

  private DefaultTableModel getProductsTableModel() {
    return getTableModel(productTable);
  }

  private javax.swing.JTextField orderNumberField;
  private javax.swing.JTable productTable;
}
