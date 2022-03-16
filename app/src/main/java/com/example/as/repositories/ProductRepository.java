package com.example.as.repositories;

import com.example.as.api.APIClient;
import com.example.as.services.ProductService;

public class ProductRepository {

    public static ProductService getProductService() {
        return APIClient.getClient().create(ProductService.class);
    }
}
