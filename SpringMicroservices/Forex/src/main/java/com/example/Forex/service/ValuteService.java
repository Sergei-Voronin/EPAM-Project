package com.example.Forex.service;

import com.example.Forex.model.Valute;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ValuteService {
    void save (Valute valute);
    List<Valute> getAllValutes();
    Valute getById(Long id);
    Valute getByCharCode(String charCode);
}
