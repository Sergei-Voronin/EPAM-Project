package com.example.Forex.service;

import com.example.Forex.model.Valute;
import com.example.Forex.repository.ValuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValuteServiceImpl implements ValuteService {

    @Autowired
    ValuteRepository valuteRepository;

    @Override
    public void save(Valute valute) {
        valuteRepository.save(valute);
    }

    @Override
    public List<Valute> getAllValutes(){
        return valuteRepository.findAll();

    }

    @Override
    public Valute getById(Long id){
        return valuteRepository.getById(id);
    }

    @Override
    public Valute getByCharCode(String charCode){
        return valuteRepository.findByCharCode(charCode);
    }
}
