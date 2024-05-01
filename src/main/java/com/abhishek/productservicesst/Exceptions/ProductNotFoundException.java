package com.abhishek.productservicesst.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends RuntimeException {
    private Long id;
    public ProductNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }
}
