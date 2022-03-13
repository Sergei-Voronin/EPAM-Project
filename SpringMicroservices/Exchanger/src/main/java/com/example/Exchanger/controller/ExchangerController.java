package com.example.Exchanger.controller;
import com.example.Exchanger.model.Exchange;
import com.example.Exchanger.model.Valute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
public class ExchangerController {

    @GetMapping("/from={from}&to={to}&quantity={quantity}")
    public Exchange toExchange (@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
        Exchange ex = new Exchange();
        ex.setFrom(from);
        ex.setTo(to);
        ex.setQuantity(quantity);
        RestTemplate restTemplate = new RestTemplate();

        if (from.equalsIgnoreCase("RUB")){
            ex.setFromName("Российский рубль");
            String address2 = "http://localhost:8080/charCode=" + to;
            Valute valuteTo = restTemplate.getForObject(address2, Valute.class);
            ex.setToName(valuteTo.getName());
            ex.setConversionFactor(new BigDecimal(1.00).divide(valuteTo.getValue(), 2));
        }

        else if (to.equalsIgnoreCase("RUB")){
            ex.setToName("Российский рубль");
            String address1 = "http://localhost:8080/charCode=" + from;
            Valute valuteFrom = restTemplate.getForObject(address1, Valute.class);
            ex.setFromName(valuteFrom.getName());
            ex.setConversionFactor(valuteFrom.getValue());
        }

        else {
            String address1 = "http://localhost:8080/charCode=" + from;
            String address2 = "http://localhost:8080/charCode=" + to;

            Valute valuteFrom = restTemplate.getForObject(address1, Valute.class);
            Valute valuteTo = restTemplate.getForObject(address2, Valute.class);

            ex.setFromName(valuteFrom.getName());
            ex.setToName(valuteTo.getName());

            ex.setConversionFactor(valuteFrom.getValue().divide(valuteTo.getValue(), 2));

        }
        ex.setTotalCost(quantity.multiply(ex.getConversionFactor()));
        return ex;
    }

}
