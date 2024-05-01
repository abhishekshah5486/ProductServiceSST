package com.abhishek.productservicesst.Services;

import com.abhishek.productservicesst.Exceptions.ProductNotFoundException;
import com.abhishek.productservicesst.Models.Category;
import com.abhishek.productservicesst.Models.Product;
import com.abhishek.productservicesst.Repositories.CategoryRepository;
import com.abhishek.productservicesst.Repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService{
    private final CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new ProductNotFoundException("Product Not Found",id);
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();
        if (category.getId() == null){
            // Save the category first, then save the product with that id
            product.setCategory(categoryRepository.save(category));
        }
        return productRepository.save(product);
    }
}
