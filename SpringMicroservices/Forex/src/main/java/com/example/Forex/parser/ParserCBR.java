package com.example.Forex.parser;

import com.example.Forex.model.Valute;
import com.example.Forex.service.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParserCBR {
    private final String pathXML = "src/main/resources/currencies.xml";
//    private final String pathXML = "C:/Users/Atlas/Desktop/currencies.xml";
//    private final String pathXML = "/usr/src/res/currencies.xml";
    private final List<Valute> valuteList = new ArrayList<>();
    private final String parseFrom = "http://www.cbr.ru/scripts/XML_daily.asp";

    @Autowired
    ValuteService valuteService;

    @Scheduled(fixedDelay = 10_000_000)
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public void parse() {
        downloadXML();
        Document document = buildDocument();
        parsing(document);
        saveDataToBase();
    }

    @Recover
    public void parsingFailed(Exception ex) {
        System.out.println("Парсинг не удался. Попробуйте позже.");
    }


    private void saveDataToBase(){
        for (Valute valute: valuteList){
            valuteService.save(valute);
        }
    }

    private void parsing(Document doc) {
        Node nodeRoot = doc.getFirstChild();
        NodeList rootChild = nodeRoot.getChildNodes();

        for (int i = 0; i < rootChild.getLength(); i++){
            if (rootChild.item(i).getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            if (!rootChild.item(i).getNodeName().equalsIgnoreCase("Valute")){
                continue;
            }
            NodeList valute = rootChild.item(i).getChildNodes();
            Valute temp = new Valute();

            for (int j = 0; j < valute.getLength(); j++){
                switch (valute.item(j).getNodeName()) {
                    case "NumCode":
                        temp.setNumCode(Integer.parseInt(valute.item(j).getTextContent()));
                        break;
                    case "CharCode":
                        temp.setCharCode(valute.item(j).getTextContent());
                        break;
                    case "Name":
                        temp.setName(valute.item(j).getTextContent());
                        break;
                    case "Value":
                        String valueStr = valute.item(j).getTextContent().replace(',', '.');
                        temp.setValue(new BigDecimal(valueStr));
                        break;
                }
            }
            valuteList.add(temp);
        }
    }

    private Document buildDocument() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            return dbf.newDocumentBuilder().parse(pathXML);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void downloadXML() {
        try {
            downloadUsingStream(parseFrom, pathXML);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void downloadUsingStream(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

}