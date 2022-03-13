package com.example.DAOTests;

import org.example.dao.ProductDAOImpl;
import org.example.dao.UserDAOImpl;
import org.example.entities.Product;
import org.example.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductDAOTests {
    private static SessionFactory factory;
    private static ProductDAOImpl productDAO;
    private static UserDAOImpl userDAO;

    @BeforeAll
    public static void before(){
        factory = new Configuration().configure().buildSessionFactory();
        productDAO = new ProductDAOImpl(factory);
        userDAO = new UserDAOImpl(factory);
        userDAO.create(new User("Валерий", "Петров", "valera@mail.ru", "Valera", "123"));
        productDAO.create(new Product(1, "Картридж c игрой \"Поле чудес\"", 560.00, new Timestamp(2022,3,21, 13, 47, 39, 45)));
        productDAO.create(new Product(1, "Сборник игр 128 в 1", 2010.00, new Timestamp(2022,1,25, 17, 00, 00, 00)));
    }

    @Test
    public void testCreate(){
        int expectedSize = productDAO.readAll().size() + 1;
        productDAO.create(new Product(1, "Картридж c игрой \"Tank 90\"", 210.00, new Timestamp(2022,3,5, 11, 00, 00, 00)));
        assertEquals(expectedSize, productDAO.readAll().size());
    }

    @Test
    public void testReadById(){
        String comparisonDescription = "Картридж c игрой \"Поле чудес\"";
        assertEquals(comparisonDescription, productDAO.readById(1).getDescription());
    }

    @Test
    public void testReadAll(){
        List<Product> listBeforeAdding = productDAO.readAll();
        productDAO.create(new Product(1, "Картридж с игрой \"Darkwing Duck\"", 255.00, new Timestamp(2022,1,27, 12, 00, 00, 00)));
        productDAO.create(new Product(1, "Картридж с игрой \"Dr. Mario\"", 190.00, new Timestamp(2022,1,18, 10, 30, 00, 00)));
        List<Product> listAfterAdding = productDAO.readAll();
        assertTrue(listBeforeAdding.size() < listAfterAdding.size());
    }

    @Test
    public void testUpdate(){
        String comparisonDescription = "Картридж с игрой \"Flintstones 2\"";
        productDAO.update(new Product(1, "Картридж с игрой \"Flintstones 2\"", 145.0, new Timestamp(2022,4,1, 15, 30, 00, 00)), 1);
        assertEquals(comparisonDescription, productDAO.readById(1).getDescription());
    }

    @Test
    public void testDelete(){
        int sizeAfterDelete = productDAO.readAll().size() - 1;
        productDAO.delete(2);
        assertEquals(sizeAfterDelete, productDAO.readAll().size());
    }
}
