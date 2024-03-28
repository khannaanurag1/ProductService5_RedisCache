package org.example.productservice5_cached.Clients.FakeStore.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FakeStoreClientProductDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String image;
    //private RatingDto rating;
    private Double price;
    private String category;
}
