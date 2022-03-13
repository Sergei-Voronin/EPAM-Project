package org.example.dao;

import org.example.entities.Bet;
import org.example.entities.User;
import java.util.List;

public interface BetDAO {
    void create(Bet bet);
    Bet readById(int id);
    List<Bet> readAll();
    void update(Bet bet, int id);
    void delete(int id);
}
