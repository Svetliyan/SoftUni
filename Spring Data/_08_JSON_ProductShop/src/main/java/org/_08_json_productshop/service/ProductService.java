package org._08_json_productshop.service;

import org._08_json_productshop.service.dtos.ProductInRangeDto;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts() throws FileNotFoundException;
    List<ProductInRangeDto> getAllProductsInRange(BigDecimal from, BigDecimal to);
    void printAllProductsInRange(BigDecimal from, BigDecimal to);
}
