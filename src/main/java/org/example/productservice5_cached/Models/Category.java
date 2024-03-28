package org.example.productservice5_cached.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;
    private String description;

    //It is perfectly fine if we skip below 2 lines, why have we added below 2 lines ?
    //For access pattern, so that it gets easy for us to access products of a category
    //Now, we don't want ORM to consider this relation(association) twice, we have
    //added mappedBy field.
    //Otherwise category in Product class is more than enough , as we put one on many side in
    //cardinality
    @OneToMany(mappedBy = "category")
    @JsonBackReference
    //@Fetch(FetchMode.SELECT)
    //@BatchSize(size=2)
    //@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    //following lazy by default
    private List<Product> products;
}

//case1
//@Fetch(FetchMode.SELECT)
//@OneToMany(mappedBy = "category")
//By default it's lazy loading , so query for products will not run until and unless we try to get c.getProducts()
//one query will be visible

//case2
//@Fetch(FetchMode.SELECT)
//@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
//query for products will also run.
//2 queryies will be visible

//case3
//@Fetch(FetchMode.JOIN)
//@OneToMany(mappedBy = "category")
//Since this is join already , so a left join query of products and category will automatically run
//select c1_0.id,c1_0.created_at,c1_0.description,c1_0.last_updated_at,c1_0.name,c1_0.status,p1_0.category_id,p1_0.id,p1_0.created_at,p1_0.description,p1_0.image_url,p1_0.last_updated_at,p1_0.price,p1_0.status,p1_0.title from category c1_0 left join product p1_0 on c1_0.id=p1_0.category_id where c1_0.id=?
//one query will be visible

//case4
//@Fetch(FetchMode.JOIN)
//@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
//Since this is join already , so a left join query of products and category will automatically run
//select c1_0.id,c1_0.created_at,c1_0.description,c1_0.last_updated_at,c1_0.name,c1_0.status,p1_0.category_id,p1_0.id,p1_0.created_at,p1_0.description,p1_0.image_url,p1_0.last_updated_at,p1_0.price,p1_0.status,p1_0.title from category c1_0 left join product p1_0 on c1_0.id=p1_0.category_id where c1_0.id=?
//one query will be visible

//IN CASE 3 AND 4, THERE WILL BE NO IMPACT OF LAZY OR EAGER LOADING, AS IT IS JOIN STATEMENT
//SO IT WILL AUTOMATICALLY GET JOINED DATA OF ALL TABLES

//case5
//@OneToMany(mappedBy = "category")
//@Fetch(FetchMode.SUBSELECT)
//By default lazy loading , only one query for category ran, no sub-query for getting
//products info ran
//select c1_0.id,c1_0.created_at,c1_0.description,c1_0.last_updated_at,c1_0.name,c1_0.status from category c1_0 where c1_0.id=?
//one query
//In case we mention c.getProducts , 2 queries will ran
//select c1_0.id,c1_0.created_at,c1_0.description,c1_0.last_updated_at,c1_0.name,c1_0.status from category c1_0 where c1_0.id=?
//select p1_0.category_id,p1_0.id,p1_0.created_at,p1_0.description,p1_0.image_url,p1_0.last_updated_at,p1_0.price,p1_0.status,p1_0.title from product p1_0 where p1_0.category_id=?

//case6
//@Fetch(FetchMode.SUBSELECT)
//@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
//2 queries as it is eager , wthout calling c.getProducts();
//select c1_0.id,c1_0.created_at,c1_0.description,c1_0.last_updated_at,c1_0.name,c1_0.status from category c1_0 where c1_0.id=?
//select p1_0.category_id,p1_0.id,p1_0.created_at,p1_0.description,p1_0.image_url,p1_0.last_updated_at,p1_0.price,p1_0.status,p1_0.title from product p1_0 where p1_0.category_id=?

//CASE 5 AND 6 SUBSELECT BEHAVE SIMILAR TO SELECT

//Using SELECT indicates that the property should be loaded lazily.
//FetchMode.JOIN loads them eagerly
//We can only use SUBSELECT with collections.

//if the code doesn’t set FetchMode, the default one is JOIN and FetchType works as defined
//with FetchMode.SELECT or FetchMode.SUBSELECT set, FetchType also works as defined
//with FetchMode.JOIN set, FetchType is ignored and a query is always eager