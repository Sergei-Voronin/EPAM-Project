package com.example.DAOTests;

import org.example.dao.BetDAOImpl;
import org.example.dao.ProductDAOImpl;
import org.example.dao.UserDAOImpl;
import org.example.entities.Bet;
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

public class BetDAOTests {
    private static SessionFactory factory;
    private static ProductDAOImpl productDAO;
    private static UserDAOImpl userDAO;
    private static BetDAOImpl betDAO;

    @BeforeAll
    public static void before(){
        factory = new Configuration().configure().buildSessionFactory();
        userDAO = new UserDAOImpl(factory);
        productDAO = new ProductDAOImpl(factory);
        betDAO = new BetDAOImpl(factory);
        userDAO.create(new User("Валерий", "Петров", "valera@mail.ru", "Valera", "123"));
        productDAO.create(new Product(1, "Картридж c игрой \"Поле чудес\"", 560.00, new Timestamp(2022,3,21, 13, 47, 39, 45)));
        betDAO.create(new Bet(1, 1, 50.00));
        betDAO.create(new Bet(1, 1, 10.00));
    }

    @Test
    public void testCreate(){
        int expectedSize = betDAO.readAll().size() + 1;
        betDAO.create(new Bet(1, 1, 20.00));
        assertEquals(expectedSize, betDAO.readAll().size());
    }
    @Test
    public void testReadById(){
        double comparisonBet = 50.00;
        assertEquals(comparisonBet, betDAO.readById(1).getRateValue());
    }

    @Test
    public void testReadAll(){
        List<Bet> listBeforeAdding = betDAO.readAll();
        betDAO.create(new Bet(1, 1, 35.00));
        betDAO.create(new Bet(1, 1, 100.00));
        List<Bet> listAfterAdding = betDAO.readAll();
        assertTrue(listBeforeAdding.size() < listAfterAdding.size());
    }

    @Test
    public void testUpdate(){
        double comparisonBet = 128.00;
        betDAO.update(new Bet(1, 1, 128.00), 1);
        assertEquals(comparisonBet, betDAO.readById(1).getRateValue());
    }

    @Test
    public void testDelete(){
        int sizeAfterDelete = betDAO.readAll().size() - 1;
        betDAO.delete(2);
        assertEquals(sizeAfterDelete, betDAO.readAll().size());
    }
}
