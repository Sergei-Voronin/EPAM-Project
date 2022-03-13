package org.example.dao;

import org.example.entities.User;
import java.util.List;

public interface UserDAO {
    void create(User user);
    User readById(int id);
    List<User> readAll();
    void update(User user, int id);
    void delete(int id);
    User findByLogin(String login);
}
