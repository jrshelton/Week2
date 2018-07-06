package io.pivotal.workshop;


import java.time.LocalDate;
import java.util.Date;


public class StockRecord {


    public final String id;
    public final String symbol;
    public final double price;
    public final int volume;
    public final java.sql.Date date;

    public StockRecord(String id, String symbol, double price, int volume, java.sql.Date date) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }







}
