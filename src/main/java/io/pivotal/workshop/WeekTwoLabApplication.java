package io.pivotal.workshop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@SpringBootApplication
public class WeekTwoLabApplication {

    public static void main(String[] args) {
        //runner();
        SpringApplication.run(WeekTwoLabApplication.class, args);

    }

/*
    public static void runner() {

            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();


            String sql =  "insert into stock (id, symbol, price, volume, date)" +
                    " values(?, ?, ?, ?, ?)";

            JdbcTemplate jdbc = new JdbcTemplate();
            jdbc.setDatabaseProductName("stock");

            try {
                URL inputStream = new URL("https://bootcamp-training-files.cfapps.io/week2/week2-stocks.json");
                System.out.println("entered the loop");
                NewStockField[] stocks = mapper.readValue(inputStream, NewStockField[].class);

                for(int i = 0; i < 5; i++) {

                    jdbc.update(sql, (i + ""), stocks[i].symbol, stocks[i].price, stocks[i].volume, stocks[i].date);

                }
                StockRepository repo = new StockRepository(jdbc);
                StockController controller = new StockController(repo, new StockPresenter());
                controller.add(stocks[0]);


                System.out.println("Users Saved!");
            } catch (IOException e) {
                System.out.println("Unable to save users: " + e.getMessage());
            }
    }*/
}
