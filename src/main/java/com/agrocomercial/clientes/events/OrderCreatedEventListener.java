package com.agrocomercial.clientes.events;

import com.agrocomercial.clientes.models.Order;

public interface OrderCreatedEventListener {
    void onOrderCreated(Order order);
}
