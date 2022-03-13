package org.example.dao;

import org.example.entities.Product;
import java.util.List;

public interface ProductDAO {
        void create(Product product);
        Product readById(int id);
        List<Product> readAll();
        void update(Product product, int id);
        void delete(int id);
}
