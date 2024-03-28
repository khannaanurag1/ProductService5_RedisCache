package org.example.productservice5_cached.Services;

import org.example.productservice5_cached.Dtos.ProductDto;
import org.example.productservice5_cached.Dtos.SearchRequestDto;
import org.example.productservice5_cached.Models.Product;
import org.example.productservice5_cached.Models.SortParam;
import org.example.productservice5_cached.Models.SortType;
import org.example.productservice5_cached.Repositories.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private ProductRepo productRepo;

    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    //For Searching,Sorting and Paging class-23
    //Step1 - Return list of product by just sending query parameter
    //Step2 - Send query, PageRequest.of(pageNumber,SizeOfPage) and return type will be list
    //Step3 - Return Page<Product> and Send query, PageRequest.of(pageNumber,SizeOfPage) - by default ascending order of id
    //Step4 - Return Page<Product> and Send query, PageRequest.of(pageNumber,SizeOfPage,sort) , sort will be hardcoded

    //public List<Product> searchProducts(String query, int pageNumber, int sizeOfPage) {
    public Page<Product> searchProducts(String query, int pageNumber, int sizeOfPage) {
       //return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber,sizeOfPage));

        Sort sort = Sort.by("description").and(Sort.by("price").descending());
        return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber,sizeOfPage,sort));
    }
}


//Based on Sort Parameters provided by Client
//public Page<Product> searchProducts(String query, int pageNumber, int sizeOfPage, List<SortParam> sortParams) {
//    Sort sort = null;
//    if(!sortParams.isEmpty()) {
//        if(sortParams.get(0).getSortType().equals(SortType.ASC)) {
//            sort = Sort.by(sortParams.get(0).getParamName());
//        } else {
//            sort = Sort.by(sortParams.get(0).getParamName()).descending();
//        }
//
//        for(int i=1;i<sortParams.size();i++) {
//            if(sortParams.get(i).getSortType().equals(SortType.ASC)) {
//                sort = sort.and(Sort.by(sortParams.get(i).getParamName()));
//            } else {
//                sort = sort.and(Sort.by(sortParams.get(i).getParamName()).descending());
//            }
//        }
//    }
//
//     if(sort != null)
//     return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber,sizeOfPage,sort));
//     else return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber,sizeOfPage));
//}