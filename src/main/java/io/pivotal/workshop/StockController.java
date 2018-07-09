package io.pivotal.workshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.net.URI;

import java.net.URL;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/load")
public class StockController {

    private final StockRepository stockRepository;
    private final StockPresenter stockPresenter;

    public StockController(StockRepository stockRepository, StockPresenter stockPresenter) {
        this.stockRepository = stockRepository;
        this.stockPresenter = stockPresenter;
        getData();

    }


    @GetMapping
    public List<StockInfo> Stocks() {
        return stockRepository.findAll()
                .stream()
                .map(stockPresenter::present)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public StockInfo Stock(@PathVariable("id") String id) {
        StockRecord record = stockRepository.findOne(id);
        return stockPresenter.present(record);
    }

    @GetMapping("/date/{date}")
    public List<StockInfo> findByDate(@PathVariable() String date) {
        List<StockRecord> record = stockRepository.findByDate(date);
        return record.stream().map(stockPresenter::present)
                .collect(toList());
    }

    @GetMapping("/symbol/{symbol}")
    public List<StockInfo> findBySymbol(@PathVariable() String symbol) {
        List<StockRecord> record = stockRepository.findBySymbol(symbol);
        return record.stream().map(stockPresenter::present)
                .collect(toList());
    }

    @GetMapping("/highest/{symbol}/{date}")
    public StockInfo highestStock(@PathVariable() String symbol, @PathVariable() String date){
        try {
            StockRecord record = stockRepository.findHighest(symbol, date);
            return stockPresenter.present(record);
        }catch(Exception e){
            return new StockInfo(null,null,null,null,null);
        }
    }

    @GetMapping("/lowest/{symbol}/{date}")
    public StockInfo lowestStock(@PathVariable() String symbol, @PathVariable() String date){
        try {
            StockRecord record = stockRepository.findLowest(symbol, date);
            return stockPresenter.present(record);

        }catch(Exception e){
            return new StockInfo(null,null,null,null,null);
        }
    }

    @GetMapping("/totalVolume/{symbol}/{date}")
    public int totalVolume(@PathVariable() String symbol, @PathVariable() String date){
        try {
            return stockRepository.findTotalVolume(symbol, date);
        }catch(Exception e){
            return -1;
        }
    }

    @GetMapping("/closingPrice/{symbol}/{date}")
    public int closingPrice(@PathVariable() String symbol, @PathVariable() String date){
        try {
            return stockRepository.closingPrice(symbol, date);
       }catch(Exception e){
            return -1;
       }

    }

    @PostMapping
    public ResponseEntity<StockInfo> add(@RequestBody NewStockField newStockFields) {

        StockRecord savedStockRecord = stockRepository.save(newStockFields);
        StockInfo savedStockInfo = stockPresenter.present(savedStockRecord);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setLocation(buildStockUri(savedStockInfo));


        return new ResponseEntity<>(savedStockInfo, httpHeaders, HttpStatus.CREATED);
    }


    private URI buildStockUri(StockInfo Stock) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + Stock.id)
                .buildAndExpand().toUri();
    }

    private void getData(){
        ObjectMapper mapper = new ObjectMapper();

        try {

            URL inputStream = new URL("https://bootcamp-training-files.cfapps.io/week2/week2-stocks.json");
            NewStockField[] stocks = mapper.readValue(inputStream, NewStockField[].class);

            for(int i = 0; i < stocks.length; i++) {
                stockRepository.save(stocks[i]);
            }

            System.out.println("Users Saved!");
            System.out.println(stocks[0].date.getTime());

        } catch (IOException e) {
            System.out.println("Unable to save users: " + e.getMessage());
        }
    }
}

