package org.example.productservice5_cached.Repositories;

import org.example.productservice5_cached.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product save(Product product);

    Optional<Product> findProductById(Long id);

    List<Product> findProductByPriceBetween(double low,double high);

    List<Product> findByPrice(Double price);

    List<Product> findAllByIsSpecialTrue();
    List<Product> findAllByIsSpecialFalse();

    List<Product> findAllByIsSpecial(Boolean value);

    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByOrderByPriceDesc();

    List<Product> findByIdIsNotNullOrderByPrice();

    @Query("SELECT p.title FROM Product p WHERE p.id=?1")
    String getProductNameFromId(Long id);

    @Query("SELECT c.name FROM Product p join Category c on p.category.id=c.id where p.id=:id")
    String getCategoryNameFromProductId(@Param("id") Long id);


    //For Searching,Sorting and Paging class-23
    //Step1 - Return list of product by just sending title parameter
    //Step2 - Send query, Pageable and return type will be list
    //Step3 - Return Page<Product> and Send query, Pageable
    //Step4 - Return Page<Product> and Send query, Pageable and sort at service level , same as Step3

    //List<Product> findByTitleEquals(String title, Pageable pageable);
    Page<Product> findByTitleEquals(String title, Pageable pageable);
    //Page<Product> findProductByTitle(String title, Pageable pageable); Another way which works
}
