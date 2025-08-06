package com.agrocomercial.clientes.context;

import com.agrocomercial.clientes.controller.auth.AuthController;
import com.agrocomercial.clientes.controller.orders.OrderProductController;
import com.agrocomercial.clientes.controller.auth.LoggedUser;
import com.agrocomercial.clientes.controller.products.ProductController;
import com.agrocomercial.clientes.services.*;
import com.agrocomercial.clientes.services.impl.memory.DocumentTypeServiceImpl;
import com.agrocomercial.clientes.services.impl.memory.OrderProductServiceImpl;
import com.agrocomercial.clientes.services.impl.memory.OrderServiceImpl;
import com.agrocomercial.clientes.services.impl.memory.ProductServiceImpl;
import com.agrocomercial.clientes.views.customers.CustomerView;
import com.agrocomercial.clientes.views.auth.LoginView;
import com.agrocomercial.clientes.views.main.MainMenuView;
import com.agrocomercial.clientes.views.orders.AddProductToOrderView;
import com.agrocomercial.clientes.views.orders.CreateOrderView;
import com.agrocomercial.clientes.views.orders.ListOrdersView;
import com.agrocomercial.clientes.views.products.CreateProductView;
import com.agrocomercial.clientes.views.products.ListProductsView;

/**
 * Clase usada para manejar el ciclo de vida de la aplicacion
 *
 * @author Jesús Perea
 */
@SuppressWarnings("java:S6548")
public class AppContext {

  private final LoggedUser loggedUser = new LoggedUser();

  private final DocumentTypeService documentTypeService;
  private final UserService userService;
  private final CustomerService customerService;
  private final OrderProductService orderProductService;
  private final ProductService productService;
  private final OrderService orderService;

  private final AuthController authController;
  private final OrderProductController orderProductController;
  private final ProductController productController;

  private LoginView loginView;
  private MainMenuView mainMenuView;
  private CustomerView customerView;
  private CreateOrderView orderView;
  private AddProductToOrderView addProductToOrderView;
  private ListOrdersView listOrdersView;
  private CreateProductView createProductView;
  private ListProductsView listProductsView;

  private static AppContext instance;

  public ListProductsView getListProductsView() {
    return listProductsView;
  }

  public CreateProductView getCreateProductView() {
    return createProductView;
  }

  public ListOrdersView getListOrdersView() {
    if (listOrdersView == null) {
      listOrdersView = new ListOrdersView(this, orderProductController);
    }

    return listOrdersView;
  }

  public LoginView getLoginView() {
    return loginView;
  }

  public CustomerView getCustomerView() {
    return customerView;
  }

  public MainMenuView getMainMenuView() {
    return mainMenuView;
  }

  public CreateOrderView getCreateOrderView() {
    return orderView;
  }

  public AddProductToOrderView getAddProductToOrderView() {
    return addProductToOrderView;
  }

  private AppContext() {
    ServiceContext serviceContext = new ServiceContext();

    documentTypeService = serviceContext.getDocumentTypeService();
    userService = serviceContext.getUserService();
    customerService = serviceContext.getCustomerService();
    orderService = serviceContext.getOrderService();
    productService = serviceContext.getProductService();
    orderProductService = serviceContext.getOrderProductService();

    orderProductController = new OrderProductController(orderProductService, productService, orderService, loggedUser);
    authController = new AuthController(userService, customerService, loggedUser);
    productController = new ProductController(productService);
  }

  public static AppContext getInstance() {
    if (instance == null) {
      instance = new AppContext();
      instance.initViews();
    }
    return instance;
  }

  public LoggedUser getLoggedUser() {
    return loggedUser;
  }

  private void initViews() {
    loginView = new LoginView(this, authController);
    mainMenuView = new MainMenuView(this);
    customerView = new CustomerView(documentTypeService);
    orderView = new CreateOrderView(this, orderProductController);
    addProductToOrderView = new AddProductToOrderView(this, orderProductController);
    createProductView = new CreateProductView(this, productController);
    listProductsView = new ListProductsView(this, productController);
  }
}
