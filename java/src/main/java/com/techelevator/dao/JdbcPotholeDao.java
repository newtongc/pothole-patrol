package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Pothole;
import com.techelevator.model.PotholeReivew;
import com.techelevator.model.RegisterPotholeDto;
import com.techelevator.model.Reporter;
import org.apache.tomcat.jni.Local;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class JdbcPotholeDao implements PotholeDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPotholeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Pothole> getPotholes() {
        List<Pothole> potholes = new ArrayList<>();
        String sql = "SELECT pothole_id, description, address, severity, reported_date, inspected_date, repair_date, " +
                "can_contact, inspected, repaired, zipcode, in_traffic, potential_damage, location_details, " +
                "reporter_id FROM potholes";
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
        String sql = "SELECT pothole_id, description, address, severity, reported_date, inspected_date, repair_date, " +
                "can_contact, inspected, repaired, zipcode, in_traffic, potential_damage, location_details, reporter_id, img_url " +
                "FROM potholes WHERE pothole_id = ?";
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
        String insertPotholeSql = "INSERT INTO potholes (address, description, zipcode, location_details, " +
                "potential_damage, in_traffic, can_contact, reporter_id) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?) RETURNING pothole_id";

        try {
            int newPotholeId = jdbcTemplate.queryForObject(insertPotholeSql, int.class, pothole.getAddress(),
                    pothole.getDescription(), pothole.getZipcode(), pothole.getLocationDetails(),
                    pothole.isPotentialDamage(), pothole.isInTraffic(), pothole.isCanContact(), pothole.getReporterId());
            newPothole = getPotholeById(newPotholeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newPothole;
    }

    @Override
    public int deletePothole(int id) {
        int numberOfRows = 0;
        String sql = "DELETE FROM potholes WHERE pothole_id = ?";
        try {
            numberOfRows = jdbcTemplate.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return numberOfRows;
    }

    @Override
    public Pothole updatePothole(PotholeReivew pothole) {
        Pothole updated = null;
        //how are we going to get status? can only update to 2 or 3
        String sql = "UPDATE potholes " +
                "SET severity = ?, " +
                "    inspected_date = ?, " +
                "    repair_date = ?, " +
                "    inspected = ?, " +
                "    repaired = ?, " +
                "    img_url = ? " +
                "WHERE pothole_id = ?";
        try {
            int numberOfRows = jdbcTemplate.update(sql, pothole.getSeverity(), pothole.getInspectedDate(),
                    pothole.getRepairDate(), pothole.isInspected(), pothole.isRepaired(), pothole.getImgUrl(), pothole.getId());

            if(numberOfRows ==0 ){
                throw new DaoException("0 rows affected. Expected at least one");
            } else {
                //may need to use principal to make a user and get the user id
                updated = getPotholeById(pothole.getId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return updated;
    }

    private Pothole mapRowToPothole(SqlRowSet rs) {
        Pothole pothole = new Pothole();
        pothole.setDescription(rs.getString("description"));
        pothole.setId(rs.getInt("pothole_id"));
        pothole.setAddress(rs.getString("address"));
        pothole.setSeverity(rs.getInt("severity"));
        if (rs.getDate("reported_date") != null)
        pothole.setReportedDate(rs.getDate("reported_date").toLocalDate());
        if (rs.getDate("inspected_date") != null)
        pothole.setInspectedDate(rs.getDate("inspected_date").toLocalDate());
        if (rs.getDate("repair_date") != null)
        pothole.setRepairDate(rs.getDate("repair_date").toLocalDate());
        pothole.setCanContact(rs.getBoolean("can_contact"));
        pothole.setInspected(rs.getBoolean("inspected"));
        pothole.setRepaired(rs.getBoolean("repaired"));
        pothole.setZipcode(rs.getString("zipcode"));
        pothole.setInTraffic(rs.getBoolean("in_traffic"));
        pothole.setPotentialDamage(rs.getBoolean("potential_damage"));
        pothole.setLocationDetails(rs.getString("location_details"));
        pothole.setReporterId(rs.getInt("reporter_id"));
        pothole.setImgUrl(rs.getString("img_url"));
        return pothole;
    }
    public List<Pothole> filterPotholes(int severity, String zipcode, String address) {
        List<Pothole> potholes = new ArrayList<>();
        List<Object> args = new ArrayList<>();
        List<Integer> types = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM potholes WHERE 1=1");
        if (severity != 0) {
            queryBuilder.append(" AND severity = ?");
            args.add(severity);
            types.add(Types.INTEGER);
        }
        if (zipcode != null && !zipcode.isEmpty()) {
            queryBuilder.append(" AND zipcode = ?");
            args.add(zipcode);
            types.add(Types.VARCHAR);
        }
        if (address != null && !address.isEmpty()) {
            queryBuilder.append(" AND address ilike ?");
            args.add("%"+address+"%");
            types.add(Types.VARCHAR);
        }
        String sql = queryBuilder.toString();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, args.toArray(),
                    types.stream().mapToInt(Integer::intValue).toArray());
            while (results.next()) {
                Pothole pothole = mapRowToPothole(results);
                potholes.add(pothole);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return potholes;
    }
}
