package org.example.dao;

import org.example.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final SessionFactory factory;

    public UserDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(User user){
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public User readById(int id){
        User user = null;
        try (Session session = factory.openSession()) {
            user = session.get(User.class, id);
        }
        return user;
    }

    @Override
    public List<User> readAll(){
        List<User> users = null;
        try (Session session = factory.openSession()) {
            users  = (List<User>)session.createSQLQuery("SELECT * FROM Users").addEntity(User.class).list();
        }
        return users;
    }

    @Override
    public void update(User user, int id){
        User newUser = new User(id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword());
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(newUser);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete (int id){

        try (Session session = factory.openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public User findByLogin(String login){
        User user = null;
        try (Session session = factory.openSession()) {
            user = session.get(User.class, login);
        }
        return user;
    }
}
