package com.agrocomercial.clientes.context;

import com.agrocomercial.clientes.controller.AddProductToOrderController;
import com.agrocomercial.clientes.services.*;
import com.agrocomercial.clientes.views.*;

/**
 * Clase usada para manejar el ciclo de vida
 * de la aplicacion
 *
 * @author Jesús Perea
 */
@SuppressWarnings("java:S6548")
public class AppContext {
    
    private final DocumentTypeService documentTypeService;
    private final UserService userService;
    private final CustomerService customerService;
    private final OrderProductService orderProductService;
    private final ProductService productService;
    private final OrderService orderService;

    private final AddProductToOrderController addProductToOrderController;

    private LoginView loginView;
    private MainMenuView mainMenuView;
    private CustomerView customerView;
    private OrderView orderView;
    private AddProductToOrderView addProductToOrderView;

    private static AppContext instance;

    public LoginView getLoginView() {
        return loginView;
    }

    public CustomerView getCustomerView() {
        return customerView;
    }

    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    public OrderView getOrderView() {
        return orderView;
    }

    public AddProductToOrderView getAddProductToOrderView() {
        return addProductToOrderView;
    }

    private AppContext() {
        documentTypeService = new DocumentTypeService();
        userService = new UserService();
        customerService = new CustomerService();
        orderService = new OrderService();
        productService = new ProductService();
        orderProductService = new OrderProductService();

        addProductToOrderController = new AddProductToOrderController(orderProductService, productService, orderService, userService, customerService);

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
        mainMenuView = new MainMenuView(this, customerService, userService);
        customerView = new CustomerView(documentTypeService);
        orderView = new OrderView(this, addProductToOrderController);
        addProductToOrderView = new AddProductToOrderView(this, addProductToOrderController);
    }
}
