package com.techelevator.controller;

import com.techelevator.dao.JdbcPotholeDao;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PotholeController {
    private PotholeDao potholeDao;
    private JdbcPotholeDao jdbcPotholeDao;
    private UserDao userDao;

    public PotholeController(PotholeDao potholeDao, JdbcPotholeDao jdbcPotholeDao, UserDao userDao) {
        this.potholeDao = potholeDao;
        this.jdbcPotholeDao = jdbcPotholeDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/potholes", method = RequestMethod.GET)
    public List<Pothole> viewPotholes(){
        return jdbcPotholeDao.getPotholes();
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
    public Pothole getPotholeById(@PathVariable int id){
        Pothole pothole = jdbcPotholeDao.getPotholeById(id);
        if (pothole == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pothole found");
        } else {
            return pothole;
        }
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
    @RequestMapping(path = "/potholes/filter", method = RequestMethod.GET)
    public List<Pothole> getPotholeByFilter(@RequestBody(required = true) Filter filter){
        return jdbcPotholeDao.filterPotholes(filter.getDate(), filter.getZipcode(), filter.getAddress());
    }
}
