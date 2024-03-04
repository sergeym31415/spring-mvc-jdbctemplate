package ru.meshkov.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.meshkov.library.models.Man;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ManDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ManDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Man> index() {
        return jdbcTemplate.query("SELECT * FROM man ORDER BY id", new BeanPropertyRowMapper<>(Man.class));
    }

    public void save(Man man) {
        jdbcTemplate.update("INSERT INTO man (fio, yearbirth) VALUES (?, ?)", man.getFio(), man.getYearBirth());
    }

    public void update(int id, Man man) {
        jdbcTemplate.update("UPDATE man SET fio=?, yearbirth=? WHERE id=?", man.getFio(), man.getYearBirth(), id);
    }

    public Man show(int id) {
        return jdbcTemplate.query("SELECT * FROM man WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Man.class))
                .stream().findAny().orElse(null);
    }

    public Man show(String fio) {
        return jdbcTemplate.query("SELECT * FROM man WHERE fio=?", new Object[]{fio}, new BeanPropertyRowMapper<>(Man.class))
                .stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM man WHERE id=?", id);
    }

    public Man getManFromBookId(int book_id) {
        return jdbcTemplate.query("SELECT * FROM man JOIN book on man.id=book.man_id WHERE book.id=?", new Object[]{book_id},
                new BeanPropertyRowMapper<>(Man.class)).stream().findAny().orElse(null);
    }
}
