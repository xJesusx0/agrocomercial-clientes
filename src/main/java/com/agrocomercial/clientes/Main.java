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
        var view = appContext.getLoginView();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setResizable(false);
    }
}