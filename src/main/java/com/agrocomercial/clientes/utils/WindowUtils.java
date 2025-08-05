package com.agrocomercial.clientes.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class WindowUtils {

    private WindowUtils () {}

    public static void closeAndShowPanel(JFrame frameToCLose, JFrame frameToOpen){
        frameToCLose.setVisible(false);
        frameToOpen.setLocationRelativeTo(null);
        frameToOpen.setVisible(true);
    }

    public static DefaultTableModel getTableModel(JTable table){
        return (DefaultTableModel) table.getModel();
    }
}
