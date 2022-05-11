package org.example.dao;

import org.example.model.Basket;
import org.example.model.City;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductsDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getProducts() {
        return jdbcTemplate.query("SELECT * FROM Product", new BeanPropertyRowMapper<Product>(Product.class));
    }

    public List<City> getCities() {
        return jdbcTemplate.query("SELECT * FROM city", new BeanPropertyRowMapper<>(City.class));
    }

    public Product getProduct(int id) {
        return jdbcTemplate.query("SELECT * FROM Product WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Product.class)).stream()
                .findAny().orElse(null);
    }

    public void addToBasket(int productId, int quantity) {
        jdbcTemplate.update("INSERT INTO basket(product_id, quantity) VALUES (?, ?)", productId, quantity);
    }

    public List<Basket> getBaskets() {
        return jdbcTemplate.query("SELECT * FROM basket", new BeanPropertyRowMapper<>(Basket.class));
    }

    public void deleteBasket(int id) {
        jdbcTemplate.update("DELETE FROM basket WHERE id=?", id);
    }
}
