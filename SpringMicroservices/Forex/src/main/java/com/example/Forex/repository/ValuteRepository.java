package com.example.Forex.repository;

import com.example.Forex.model.Valute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValuteRepository extends JpaRepository<Valute, Long> {
    Valute findByCharCode(String charCode);
}
