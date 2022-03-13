package org.example.dao;

import org.example.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO{
    private final SessionFactory factory;

    public ProductDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Product product){
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public Product readById(int id){
        Product product = null;
        try (Session session = factory.openSession()) {
            product = session.get(Product.class, id);
        }
        return product;
    }

    @Override
    public List<Product> readAll(){
        List<Product> product = new ArrayList<>();

        try (Session session = factory.openSession()) {
            product  = (List<Product>)session.createSQLQuery("SELECT * FROM Products").addEntity(Product.class).list();
        }
        return product;
    }

    @Override
    public void update(Product product, int id){
        Product newProduct = new Product(id, product.getUserId(), product.getDescription(), product.getPrice(), product.getAuctionTimer());
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(newProduct);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete (int id){
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Product product = session.find(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }
}