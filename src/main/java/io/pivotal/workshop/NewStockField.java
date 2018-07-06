package io.pivotal.workshop;

import java.sql.Date;

public class NewStockField {


    public final String symbol;
    public final double price;
    public final int volume;
    public final Date date;


    public NewStockField(String symbol, double price, int volume, Date date) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }

    // Make jackson happy when parsing JSON into this class
    private NewStockField() {
        this(null, 0, 0, null);
    }
}
