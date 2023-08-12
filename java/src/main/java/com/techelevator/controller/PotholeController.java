package com.techelevator.controller;

import com.techelevator.dao.JdbcPotholeDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.PotholeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PotholeController {
    private PotholeDao potholeDao;
    private JdbcPotholeDao jdbcPotholeDao;
    private UserDao userDao;
    private JdbcUserDao jdbcUserDao;

    public PotholeController(PotholeDao potholeDao, JdbcPotholeDao jdbcPotholeDao, UserDao userDao, JdbcUserDao jdbcUserDao) {
        this.potholeDao = potholeDao;
        this.jdbcPotholeDao = jdbcPotholeDao;
        this.userDao = userDao;
        this.jdbcUserDao = jdbcUserDao;
    }

    @RequestMapping(path = "/potholes", method = RequestMethod.GET)
    public List<Pothole> viewPotholes(@RequestParam(required = false) String zipcode,
                                      @RequestParam(required = false)String address,
                                      @RequestParam(required = false)LocalDate reportedDate){
        return jdbcPotholeDao.filterPotholes(reportedDate, zipcode, address);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/potholes", method = RequestMethod.POST )
    public Pothole addPothole(@Valid @RequestBody RegisterPotholeDto newPothole, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        newPothole.setReporterId(user.getId());

        Pothole pothole;
        try {
            pothole = potholeDao.createPothole(newPothole);
            if (pothole == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pothole registration failed. 111");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Pothole registration failed.", e);
        }
        return pothole;
    }
    @RequestMapping(path = "/potholes/{id}", method = RequestMethod.GET)
    public PotholeWithPhoneNumber getPotholeWithUserById(@PathVariable int id){
        Pothole pothole = jdbcPotholeDao.getPotholeById(id);
        if (pothole == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pothole found");
        }
        User user = jdbcUserDao.getUserById(pothole.getId());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return new PotholeWithPhoneNumber(pothole, user.getPhoneNumber());
    }


    @RequestMapping(path = "/potholes/{id}", method = RequestMethod.DELETE)
    public int deletePotholeById(@PathVariable int id) {
        int pothole = jdbcPotholeDao.deletePothole(id);
        if (pothole == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pothole found");
        } else {
            return pothole;
        }
    }
    @RequestMapping(path = "potholes/{id}/review", method = RequestMethod.PUT)
    public Pothole updatePothole(@PathVariable int id, @RequestBody PotholeReivew pothole) {
        Pothole updated = null;
        try {
            pothole.setId(id);
            updated = potholeDao.updatePothole(pothole);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }
}
