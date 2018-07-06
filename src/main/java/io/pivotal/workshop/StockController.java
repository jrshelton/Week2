package io.pivotal.workshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
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
//        ObjectMapper mapper = new ObjectMapper();
//        NewStockField stocks[];
//
//        try {
//            File data = new File("data.json");
//
//            stocks = mapper.readValue(data, NewStockField[].class);
//
//            System.out.println(stocks[0].price);
//            this.add(stocks[0]);
//
//           for(int i = 0; i < stocks.length ; i++){
//               this.add(stocks[i]);
//            }
//
//
//        } catch (Exception e) {
//            System.out.println("THIS IS THE ARRAY");
//            e.printStackTrace();
//        }



    }
}
