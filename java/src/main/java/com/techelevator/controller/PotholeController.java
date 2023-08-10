package com.techelevator.controller;

import com.techelevator.dao.JdbcPotholeDao;
import com.techelevator.dao.PotholeDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Pothole;
import com.techelevator.model.RegisterPotholeDto;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PotholeController {
    private PotholeDao potholeDao;
    private JdbcPotholeDao jdbcPotholeDao;

    public PotholeController(PotholeDao potholeDao, JdbcPotholeDao jdbcPotholeDao) {
        this.potholeDao = potholeDao;
        this.jdbcPotholeDao = jdbcPotholeDao;
    }

    @RequestMapping(path = "/potholes", method = RequestMethod.GET)
    public List<Pothole> viewPotholes(){
        return jdbcPotholeDao.getPotholes();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/potholes", method = RequestMethod.POST)
    public void addPothole(@Valid @RequestBody RegisterPotholeDto newPothole) {
        try {
            Pothole pothole = potholeDao.createPothole(newPothole);
            if (pothole == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User registration failed.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User registration failed.");
        }
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
}
