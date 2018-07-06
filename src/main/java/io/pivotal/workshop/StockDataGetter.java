package io.pivotal.workshop;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class StockDataGetter {

    StockRepository stockRepository;
    public StockDataGetter(){


    }
//
//    public static void newDatabase(){
//        StockRecord[] stock = getData();
//        if(stock != null) {
//            create();
//
//            for (int i = 0; i < stock.length; i++) {
//                try {
//                    StockManager.insert(stock[i]);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static Stock[] getData() {
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            File data = new File("data.txt");
//            Stock[] stock = mapper.readValue(data, Stock[].class);
//            return stock;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
