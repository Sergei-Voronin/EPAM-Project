package org.example.dao;

import org.example.entities.Bet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class BetDAOImpl implements BetDAO{
    private final SessionFactory factory;

    public BetDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Bet bet) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(bet);
            session.getTransaction().commit();
        }
    }

    @Override
    public Bet readById(int id) {
        Bet bet = null;
        try (Session session = factory.openSession()) {
            bet = session.get(Bet.class, id);
        }
        return bet;
    }

    @Override
    public List<Bet> readAll() {
        List<Bet> bets = null;

        try (Session session = factory.openSession()) {
            bets  = (List<Bet>)session.createSQLQuery("SELECT * FROM Bets").addEntity(Bet.class).list();
        }
        return bets;
    }

    @Override
    public void update(Bet bet, int id) {
        Bet newBet = new Bet(id, bet.getProductId(), bet.getUserId(), bet.getRateValue());
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(newBet);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Bet bet = session.find(Bet.class, id);
            session.delete(bet);
            session.getTransaction().commit();
        }
    }
}
