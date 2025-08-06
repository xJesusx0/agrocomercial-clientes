/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this
 * license Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.agrocomercial.clientes.views.products;

import static com.agrocomercial.clientes.utils.WindowUtils.getTableModel;

import com.agrocomercial.clientes.context.AppContext;
import com.agrocomercial.clientes.controller.products.ProductController;
import com.agrocomercial.clientes.events.ProductCreatedEventListener;
import com.agrocomercial.clientes.models.Product;
import com.agrocomercial.clientes.utils.WindowUtils;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jesus
 */
public class ListProductsView extends javax.swing.JFrame implements ProductCreatedEventListener {

  private final transient AppContext localAppContext;
  private final transient ProductController productController;

  public ListProductsView(AppContext appContext, ProductController productController) {
    initComponents();
    this.localAppContext = appContext;
    this.productController = productController;
    loadProducts();
    subscribeToProductCreated();
  }

  private void loadProducts() {
    List<Product> products = productController.getAllProducts();
    DefaultTableModel defaultTableModel = getTableModel(productsTable);
    products.forEach(product -> defaultTableModel
            .addRow(new Object[] {product.getName(), product.getDescription(), product.getPrice()}));
  }

  private void subscribeToProductCreated() {
    productController.subscribe(this);
  }

  private void initComponents() {
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JButton addProductButton;

    jScrollPane1 = new javax.swing.JScrollPane();
    productsTable = new javax.swing.JTable();
    addProductButton = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    productsTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

    }, new String[] {"Nombre", "Descripcion", "Precio"}) {
      final Class[] types = new Class[] {java.lang.String.class, java.lang.String.class, java.lang.Double.class};
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
    jScrollPane1.setViewportView(productsTable);
    if (productsTable.getColumnModel().getColumnCount() > 0) {
      productsTable.getColumnModel().getColumn(0).setResizable(false);
      productsTable.getColumnModel().getColumn(1).setResizable(false);
      productsTable.getColumnModel().getColumn(2).setResizable(false);
    }

    addProductButton.setText("agregar");
    addProductButton.addActionListener(evt -> addProductButtonActionPerformed());

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup().addComponent(addProductButton).addGap(0, 0,
                                    Short.MAX_VALUE)))
                    .addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
            javax.swing.GroupLayout.Alignment.TRAILING,
            layout.createSequentialGroup().addContainerGap().addComponent(addProductButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void addProductButtonActionPerformed() {
    WindowUtils.closeAndShowPanel(this, localAppContext.getCreateProductView());
  }

  private javax.swing.JTable productsTable;

  @Override
  public void onProductCreated(Product product) {
    DefaultTableModel tableModel = getTableModel(productsTable);
    tableModel.addRow(new Object[] {product.getName(), product.getDescription(), product.getPrice()});
  }
}
