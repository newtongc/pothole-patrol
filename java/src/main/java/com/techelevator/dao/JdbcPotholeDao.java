package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Pothole;
import com.techelevator.model.RegisterPotholeDto;
import org.springframework.context.annotation.Conditional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcPotholeDao implements PotholeDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcPotholeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Pothole> getPotholes() {
        List<Pothole> potholes = new ArrayList<>();
        String sql = "SELECT pothole_id, description, location, severity, reported_date FROM potholes";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Pothole pothole = mapRowToPothole(results);
                potholes.add(pothole);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return potholes;
    }

    @Override
    public Pothole getPotholeById(int id) {
        Pothole pothole = null;
        String sql = "SELECT pothole_id, description, severity, location, reported_date FROM potholes WHERE pothole_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                pothole = mapRowToPothole(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pothole;
    }
    @Override
    public Pothole createPothole(RegisterPotholeDto pothole) {
        Pothole newPothole = null;
        String insertPotholeSql = "INSERT INTO potholes (location, description) values (?, ?) RETURNING pothole_id";

        try {
            int newPotholeId = jdbcTemplate.queryForObject(insertPotholeSql, int.class, pothole.getLocation(), pothole.getDescription());
            newPothole = getPotholeById(newPotholeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newPothole;
    }


    private Pothole mapRowToPothole(SqlRowSet rs) {
        Pothole pothole = new Pothole();
        pothole.setDescription(rs.getString("description"));
        pothole.setId(rs.getInt("pothole_id"));
        pothole.setLocation(rs.getString("location"));
        pothole.setSeverity(rs.getInt("severity"));
        pothole.setReportedDate(rs.getDate("reported_date"));
        return pothole;
    }
}
