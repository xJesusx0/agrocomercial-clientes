package com.agrocomercial.clientes;

import com.agrocomercial.clientes.context.AppContext;

/**
 * App agrocomercial clientes
 * @author Jesús Perea
 * @author Daniel Dussan
 * @author Alberto perez
 */
public class Main {
    public static void main(String[] args) {
        AppContext appContext = AppContext.getInstance();
        appContext.loginView.setLocationRelativeTo(null);
        appContext.loginView.setVisible(true);
        appContext.loginView.setResizable(false);
    }
}