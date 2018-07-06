package io.pivotal.workshop;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

import static java.util.UUID.randomUUID;

@Repository
public class StockRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<StockRecord> rowMapper = (ResultSet rs, int row) -> new StockRecord(
            rs.getString("id"),
            rs.getString("symbol"),
            rs.getDouble("price"),
            rs.getInt("volume"),
            rs.getDate("date")
    );

    public StockRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private final String SQL_INSERT = "insert into stock (id, symbol, price, volume, date)" +
            " values(?, ?, ?, ?, ?)";

    public StockRecord save(NewStockField newStockFields) {
        String newId = randomUUID().toString();

        jdbcTemplate.update(
                SQL_INSERT, newId, newStockFields.symbol,
                newStockFields.price, newStockFields.price, newStockFields.date);

        return findOne(newId);
    }


    private final String SQL_QUERY_ALL = "select * from Stock";

    public List<StockRecord> findAll() {
        return jdbcTemplate.query(SQL_QUERY_ALL, rowMapper);
    }


    private final String SQL_QUERY_BY_ID = "select * from Stock where id = ?";

    public StockRecord findOne(String id) {
        return jdbcTemplate.queryForObject(SQL_QUERY_BY_ID, new Object[]{id}, rowMapper);
    }

    private final String MAX_PRICE = "select Max(price) from Stock where symbol = ? and date = ?";
    private final String SQL_QUERY_HIGHEST_PRICE = "select * from Stock where price = ? and symbol = ? and date = ?";

    public StockRecord findHighest(String symbol, String date){

        double max = jdbcTemplate.queryForObject(MAX_PRICE, new Object[]{symbol, date}, Double.class);
        return jdbcTemplate.queryForObject(SQL_QUERY_HIGHEST_PRICE, new Object[]{ max, symbol, date}, rowMapper);
    }

    private final String MIN_PRICE = "select Min(price) from Stock where symbol = ? and date = ?";

    public StockRecord findLowest(String symbol, String date){

        double min = jdbcTemplate.queryForObject(MIN_PRICE, new Object[]{symbol, date}, Double.class);
        return jdbcTemplate.queryForObject(SQL_QUERY_HIGHEST_PRICE, new Object[]{ min, symbol, date}, rowMapper);

    }

    private final String TOT_VOLUME = "select sum(volume) from stock where symbol = ? and date = ?";

    public int findTotalVolume(String symbol, String date){

        return jdbcTemplate.queryForObject(TOT_VOLUME, new Object[]{symbol, date}, Integer.class);
    }

    //private final String MAX_PRICE = "select Max(price) from Stock where symbol = ? and date = ?";
    //private final String SQL_QUERY_HIGHEST_PRICE = "select * from Stock where price = ? and symbol = ? and date = ?";


}
