package org.example.productservice5_cached.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice5_cached.Models.SortParam;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchRequestDto {
    private String query;
    private int pageNumber;
    private int sizeOfPage;
    private List<SortParam> sortParams = new ArrayList();
}
