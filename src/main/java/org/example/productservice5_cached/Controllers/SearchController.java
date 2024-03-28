package org.example.productservice5_cached.Controllers;

import org.example.productservice5_cached.Dtos.ProductDto;
import org.example.productservice5_cached.Dtos.SearchRequestDto;
import org.example.productservice5_cached.Models.Category;
import org.example.productservice5_cached.Models.Product;
import org.example.productservice5_cached.Services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    //For Searching,Sorting and Paging class-23
    //Step1 - Return list of product dto by just sending query parameter (commented code)
    //Step2 - Send query, pageNumber and SizeOfPage and return type will be list (commented code)
    //Step3 - Return Page<Product> with unsorted data (commented code)
    //Step4 - Return Page<Product> with sorted data (hardcoded sort conditions)

    @PostMapping
//    public List<ProductDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
//       List<Product> products = searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageNumber(), searchRequestDto.getSizeOfPage());
//       List<ProductDto> searchResults = new ArrayList<>();
//       for(Product p : products) {
//           searchResults.add(getProductDto(p));
//       }
//       return searchResults;
//    }
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        Page<Product> products = searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageNumber(), searchRequestDto.getSizeOfPage());
        return products;
    }

    private ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setImage(product.getImageUrl());
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }


//    Just Trying out Something
//    public Page<ProductDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
//        Page<Product> products = searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageNumber(), searchRequestDto.getSizeOfPage());
//        //In case we want to return Page<ProductDto> , below logic may give incorrect result
//        List<ProductDto> searchResultsList = new ArrayList<>();
//        for(Product p : products.getContent()) {
//            searchResultsList.add(getProductDto(p));
//        }
//        Page<ProductDto> searchResultsPage = new PageImpl<>(searchResultsList);
//        return searchResultsPage;
//    }

}


