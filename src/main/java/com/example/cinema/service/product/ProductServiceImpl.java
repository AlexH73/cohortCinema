package com.example.cinema.service.product;

import com.example.cinema.model.product.IProduct;
import com.example.cinema.model.product.Product;
import com.example.cinema.repository.product.IProductRepository;
import com.example.cinema.util.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepo;

    public ProductServiceImpl(IProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public IProduct createProduct(IProduct p) {
        // Привести IProduct → Product
        Product prod = (Product)p;
        return productRepo.save(prod);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<IProduct> getAllProducts() {
        return new ArrayList<>(productRepo.findAll());
    }

    @Override
    public IProduct updateProduct(Long id, IProduct p) {
        Product existing = getProductById(id);
        existing.setName(p.getName());
        existing.setDescription(p.getDescription());
        existing.setPrice(p.getPrice());
        existing.setStockQuantity(p.getStockQuantity());
        return productRepo.save(existing);
    }
}

