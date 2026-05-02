package storeapp.repository;

import storeapp.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {


    List<Product> products = new ArrayList<>();

    public Product saveProduct(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> findAllProducts() {
        return products;
    }

    public Optional<Product> findById(int id) {
        for (Product product : products) {
            if (product.getIdProduct() == id) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public Optional<Product> updateProduct(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getIdProduct() == updatedProduct.getIdProduct()) {
                products.set(i, updatedProduct);
                return Optional.of(updatedProduct);
            }
        }
        return Optional.empty();
    }

    public boolean deleteById(int id) {
        return products.removeIf(p -> p.getIdProduct() == id);
    }
}