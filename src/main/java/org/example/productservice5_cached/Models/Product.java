package org.example.productservice5_cached.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonMerge;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Setter
@Getter
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private Boolean isSpecial;
}

//JsonManagedReference is added here and JsonBackReference will be added in Category
//to make sure, when we will make call from Postman for getProduct(2), it will get all
//values of product from product table and then it goes to category table and again try
//to get value from product table , hence converting into infinite loop.
//So this managedreference and backreference helps in solving that problem
//Source - https://www.baeldung.com/jackson-annotations