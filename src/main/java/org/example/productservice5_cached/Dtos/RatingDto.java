package org.example.productservice5_cached.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatingDto {
    private Double rate;
    private Long count;
}
