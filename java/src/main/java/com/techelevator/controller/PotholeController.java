package com.techelevator.controller;

import com.techelevator.dao.JdbcPotholeDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.PotholeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.PotholeNotFoundException;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.security.Principal;
import java.time.LocalDate;
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

    @RequestMapping(path = {"/potholes", "/potholes/map"}, method = RequestMethod.GET)
    public List<Pothole> viewPotholes(@RequestParam(required = false) String zipcode,
                                      @RequestParam(required = false)String address,
                                      @RequestParam(defaultValue = "0")int severity){
        return jdbcPotholeDao.filterPotholes(severity, zipcode, address);
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
     @PreAuthorize("hasRole('ADMIN')")
    public Pothole getPotholeWithUserById(@PathVariable int id){
        Pothole pothole = jdbcPotholeDao.getPotholeById(id);
        Reporter reporter = new Reporter(jdbcUserDao.getUserById(pothole.getReporterId()).getUsername(), jdbcUserDao.getUserById(pothole.getReporterId()).getPhoneNumber());
        pothole.setReporter(reporter);

        if (pothole == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pothole found");
        }
        return pothole;
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
    @RequestMapping(path = "potholes/{id}", method = RequestMethod.PUT)
    public Pothole updatePothole(@PathVariable int id, @RequestBody PotholeReivew potholeReview) {
        Pothole updated = null;
        try {
            potholeReview.setId(id);
            updated = potholeDao.getPotholeById(id);

            if (updated == null) {
                throw new PotholeNotFoundException("Pothole not found");
            }

            if (potholeReview.isInspected() && potholeReview.getInspectedDate() == null) {
                throw new ValidationException("Inspected date is required");
            }

            if (potholeReview.isRepaired() && potholeReview.getRepairDate() == null) {
                throw new ValidationException("Repair date is required");
            }

            if (potholeReview.getInspectedDate() != null && potholeReview.getRepairDate() != null) {
                if (potholeReview.getRepairDate().compareTo(potholeReview.getInspectedDate()) <= 0) {
                    throw new ValidationException("Repair date must be after inspected date");
                }
            }

            Pothole updatedPothole = potholeDao.updatePothole(potholeReview);
            return updatedPothole;
        } catch (PotholeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pothole not found", e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }
    }
}
