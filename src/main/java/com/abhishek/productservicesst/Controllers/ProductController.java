package com.abhishek.productservicesst.Controllers;

import com.abhishek.productservicesst.Dtos.ExceptionDto;
import com.abhishek.productservicesst.Models.Product;
import com.abhishek.productservicesst.Services.FakeStoreProductService;
import com.abhishek.productservicesst.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// This controller is capable to host http APIs Rest Controller
@RequestMapping("/products")
// localhost:8080/products --> product controller
// Request mapping creates a mapping of this end point with this controller in the
// dispatcher servlet
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    // localhost:8080/products
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
//        throw new RuntimeException("Runtime Exception");
//        ResponseEntity responseEntity = null;
//        try{
//            Product product =  productService.getProductById(id);
//            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
//        }catch (Exception e){
//            ExceptionDto exceptionDto = new ExceptionDto();
//            exceptionDto.setMessage("Something Went Wrong");
//            exceptionDto.setResolution("Product Not Found");
//            responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
//            System.out.println("Something went wrong");
//        }
//        return responseEntity;

        return productService.getProductById(id);
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
}
