package org.example.productservice5_cached.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.productservice5_cached.Models.Category;

@Getter
@Setter
@ToString
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private RatingDto rating;
    private Double price;
    private String category;
}
