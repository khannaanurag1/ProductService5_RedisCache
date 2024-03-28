package org.example.productservice5_cached.Services;

import org.example.productservice5_cached.Dtos.UserDto;
import org.example.productservice5_cached.Models.Product;
import org.example.productservice5_cached.Repositories.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class StorageProductService implements IProductService {
    ProductRepo productRepo;

    RestTemplate restTemplate;

    public StorageProductService(ProductRepo productRepo, RestTemplate restTemplate) {
        this.productRepo = productRepo;
        this.restTemplate = restTemplate;
    }
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getProduct(Long id) {
       Optional<Product> optionalProduct = productRepo.findProductById(id);
        return  optionalProduct.get();
    }

    @Override
    public Product getSpecialProduct(Long productId, Long userId) {
        //we will call userservice to get userId here
        //First demonstrate this
        //RestTemplate restTemplate = new RestTemplate();
        //UserDto userDto = restTemplate.getForEntity("http://localhost:8080/users/{userid}", UserDto.class,userId).getBody();

        UserDto userDto = restTemplate.getForEntity("http://userservice/users/{userid}", UserDto.class,userId).getBody();
        //System.out.println(userDto);
        Product product = productRepo.findProductById(productId).get();
        return product;
    }

    @Override
    public Product createProduct(Product product) {
        Product resultProduct = productRepo.save(product);
        return resultProduct;
    }

    @Override
    public Product updateProduct(Product product, Long productId) {
        return null;
    }

    @Override
    public String deleteProduct(Long id) {
        return null;
    }
}
