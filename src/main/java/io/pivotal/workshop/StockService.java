//  package io.pivotal.workshop;
//
//import java.util.List;
//
//public class StockService {
//    private StockRepository stockRepository;
//
//    public StockService(StockRepository stockRepository) {
//        this.stockRepository = stockRepository;
//    }
//
//    public Iterable<StockRecord> list() {
//        return stockRepository.findAll();
//    }
//
//    public Iterable<NewStockField> save(List<NewStockField> stocks) {
//        return stockRepository.save(stocks);
//    }
//}
