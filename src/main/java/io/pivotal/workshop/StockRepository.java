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

        jdbcTemplate.update(SQL_INSERT, newId, newStockFields.symbol, newStockFields.price, newStockFields.price, newStockFields.date);

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
}
