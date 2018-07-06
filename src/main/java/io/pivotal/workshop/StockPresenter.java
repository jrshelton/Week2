package io.pivotal.workshop;

import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;


@Component
public class StockPresenter {

    SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");

    public StockInfo present(StockRecord record) {
        return new StockInfo(
                record.id +"",
                record.symbol,
                record.price + "",
                record.volume + "",
                formatter.format(record.date)

        );
    }

}
