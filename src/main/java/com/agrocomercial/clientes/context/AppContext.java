package com.agrocomercial.clientes.context;

import com.agrocomercial.clientes.services.DocumentTypeService;
import com.agrocomercial.clientes.services.UserService;
import com.agrocomercial.clientes.views.CustomerView;
import com.agrocomercial.clientes.views.LoginView;
import com.agrocomercial.clientes.views.MainMenuView;

/**
 * Clase usada para manejar el ciclo de vida
 * de la aplicacion
 *
 * @author Jesús Perea
 */
public class AppContext {
    
    private final DocumentTypeService documentTypeService;
    private final UserService userService;

    public LoginView loginView;
    public MainMenuView mainMenuView;
    public CustomerView customerView;

    private static AppContext instance;

    private AppContext() {
        documentTypeService = new DocumentTypeService();
        userService = new UserService();

        loginView = null;
        mainMenuView = null;
        customerView = null;
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
            instance.initViews();
        }
        return instance;
    }

    private void initViews() {
        loginView = new LoginView(this, userService);
        mainMenuView = new MainMenuView(this);
        customerView = new CustomerView(documentTypeService);
    }
}
