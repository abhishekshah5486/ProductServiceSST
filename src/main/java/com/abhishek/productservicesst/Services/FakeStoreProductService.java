package com.abhishek.productservicesst.Services;

import com.abhishek.productservicesst.Dtos.FakeStoreProductDto;
import com.abhishek.productservicesst.Exceptions.ProductNotFoundException;
import com.abhishek.productservicesst.Models.Category;
import com.abhishek.productservicesst.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreProductService implements ProductService{

    public Product convertProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
    @Override
    public Product getProductById(Long id) {
        // Call the fakestore api to fetch fakestore product with the given id
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );
        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Invalid Product id", id);
        }
        return convertProductDtoToProduct(fakeStoreProductDto);
    }
    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                    FakeStoreProductDto[].class
        );
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
