package org.example.productservice5_cached.Controllers;

import org.example.productservice5_cached.Dtos.ProductDto;
import org.example.productservice5_cached.Models.Category;
import org.example.productservice5_cached.Models.Product;
import org.example.productservice5_cached.Services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> GetAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> GetProduct(@PathVariable Long id)  {
        try {
            MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
            headers.add("calledBy","StupidFrontendEngineer");
            if(id < 1) {
                throw new IllegalArgumentException("Something went bad");
            }
            Product product = productService.getProduct(id);
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(product,headers,HttpStatus.OK);
            return responseEntity;
        } catch(Exception exception) {
            throw exception;
        }
    }

    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<Product> GetSpecialProduct(@PathVariable Long id1, @PathVariable Long id2)  {
       return new ResponseEntity<>(productService.getSpecialProduct(id1,id2),HttpStatus.OK);
    }



    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product product = getProduct(productDto);
       return new ResponseEntity<>(productService.createProduct(product),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto) {
        Product product = getProduct(productDto);
        return new ResponseEntity<>(productService.updateProduct(product,productId),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        return "marking product with id "+productId+" as inactive";
    }

//    @ExceptionHandler({NullPointerException.class,IllegalArgumentException.class})
//    public ResponseEntity<String> handleException(Exception exception) {
//        return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImage());
        product.setPrice(productDto.getPrice());
        product.setId(productDto.getId());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }

    //We will use this in returning from Controller
    //All conversions from Product to ProductDto can happen here.
    private ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setImage(product.getImageUrl());
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }
}
