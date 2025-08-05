package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.Product;
import com.agrocomercial.clientes.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductService {

    private final List<Product> productList = new ArrayList<>();

    public ProductService() {
        productList.add(new Product(1, "Papas", "Bolsa de 1kg de papas", 5000.0));
        productList.add(new Product(2, "Tomates", "Canastilla de tomates frescos (5kg)", 12000.0));
        productList.add(new Product(3, "Cebolla cabezona", "Bolsa de cebolla cabezona (2kg)", 7000.0));
        productList.add(new Product(4, "Zanahoria", "Bulto de zanahoria mediana (10kg)", 18000.0));
        productList.add(new Product(5, "Aguacate Hass", "Caja con 20 unidades de aguacate", 35000.0));
        productList.add(new Product(6, "Banano", "Racimo de banano maduro", 8000.0));
        productList.add(new Product(7, "Frijol verde", "Bolsa de frijol verde fresco (1kg)", 9500.0));
        productList.add(new Product(8, "Yuca", "Bolsa de yuca pelada (2kg)", 6500.0));
        productList.add(new Product(9, "Maíz seco", "Costal de maíz para siembra (25kg)", 45000.0));
        productList.add(new Product(10, "Fertilizante orgánico", "Saco de fertilizante compostado (40kg)", 52000.0));
    }

    public List<Product> findAll() {
        return productList;
    }

    public List<Product> findAllByIds(Collection<Integer> ids) {
        return productList.stream()
                .filter(product -> ids.contains(product.getId()))
                .toList();
    }

    public Product save(Product product) {
        Integer id = ServiceUtils.getLastId(productList);
        product.setId(id);
        productList.add(product);
        return product;
    }
}
