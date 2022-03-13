package com.example.DAOTests;

import org.example.dao.UserDAOImpl;
import org.example.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDAOTests {
    private static SessionFactory factory;
    private static UserDAOImpl userDAO;

    @BeforeAll
    public static void before(){
        factory = new Configuration().configure().buildSessionFactory();
        userDAO = new UserDAOImpl(factory);
        userDAO.create(new User("Валерий", "Петров", "valera@mail.ru", "Valera", "123"));
        userDAO.create(new User("Михаил", "Игнатьев", "max@list.ru", "Мах", "610"));
    }

    @Test
    public void testCreate(){
        int expectedSize = userDAO.readAll().size() + 1;
        userDAO.create(new User("Иван", "Гришаев", "grisha@mail.ru", "Ivan", "777"));
        assertEquals(expectedSize, userDAO.readAll().size());
    }

    @Test
    public void testReadById(){
        String comparisonFirstName = "Валерий";
        String comparisonLastName = "Петров";
        assertTrue(comparisonFirstName.equals(userDAO.readById(1).getFirstName())
                && comparisonLastName.equals(userDAO.readById(1).getLastName()));
    }

    @Test
    public void testReadAll(){
        List<User> listBeforeAdding = userDAO.readAll();
        userDAO.create(new User("Мария", "Туманова", "mary@mail.ru", "Mary", "456"));
        userDAO.create(new User("Татьяна", "Бакеева", "tat@mail.ru", "Tany", "789"));
        List<User> listAfterAdding = userDAO.readAll();
        assertTrue(listBeforeAdding.size() < listAfterAdding.size());
    }

    @Test
    public void testUpdate(){
        String comparisonFirstName = "Виктор";
        userDAO.update(new User("Виктор", "Петров", "valera@mail.ru", "Valera", "123"), 1);
        assertEquals(comparisonFirstName, userDAO.readById(1).getFirstName());
    }

    @Test
    public void testDelete(){
        int sizeAfterDelete = userDAO.readAll().size() - 1;
        userDAO.delete(2);
        assertEquals(sizeAfterDelete, userDAO.readAll().size());
    }
}


