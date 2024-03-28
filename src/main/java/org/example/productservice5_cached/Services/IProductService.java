package org.example.productservice5_cached.Services;

import org.example.productservice5_cached.Models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product product, Long productId);

    String deleteProduct(Long id);

    Product getSpecialProduct(Long productId, Long userId);
}
