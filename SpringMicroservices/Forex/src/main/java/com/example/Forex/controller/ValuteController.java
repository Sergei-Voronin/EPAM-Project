package com.example.Forex.controller;

import com.example.Forex.model.Valute;
import com.example.Forex.service.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValuteController {
    @Autowired
    ValuteService valuteService;
    @GetMapping(value = "/valute")
    public List<Valute> getAllValutes(){

        return valuteService.getAllValutes();
    }

    @GetMapping(value = "/valute={id}")
    public Valute getValutesById(@PathVariable Long id){
        return valuteService.getById(id);
    }

    @GetMapping(value = "/charCode={charCode}")
    public Valute getValutesByCharCode(@PathVariable String charCode){
        return valuteService.getByCharCode(charCode);
    }
}
