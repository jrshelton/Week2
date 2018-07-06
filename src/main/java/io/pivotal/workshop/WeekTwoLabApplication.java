package io.pivotal.workshop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@SpringBootApplication
public class WeekTwoLabApplication {

    public static void main(String[] args) {

        SpringApplication.run(WeekTwoLabApplication.class, args);
    }
/*
    @Bean
    public CommandLineRunner runner(StockController stockController) {
        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<NewStockField>> typeReference = new TypeReference<List<NewStockField>>() {
            };
            URL inputStream = new URL("https://bootcamp-training-files.cfapps.io/week2/week2-stocks.json");
            try {

                System.out.println("entered the loop");
                NewStockField[] stocks = mapper.readValue(inputStream, NewStockField[].class);

                for(int i = 0; i < 5; i++) {


                    System.out.println(i);
                    //stockController.add(stocks[i]);

                }
                System.out.println("Users Saved!");
            } catch (IOException e) {
                System.out.println("Unable to save users: " + e.getMessage());
            }
        };
    }*/
}
