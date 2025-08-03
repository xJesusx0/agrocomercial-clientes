package com.agrocomercial.clientes.utils;

import javax.swing.*;

public class WindowUtils {

    private WindowUtils () {}

    public static void closeAndShowPanel(JFrame frameToCLose, JFrame frameToOpen){
        frameToCLose.setVisible(false);
        frameToOpen.setLocationRelativeTo(null);
        frameToOpen.setVisible(true);
    }

}
