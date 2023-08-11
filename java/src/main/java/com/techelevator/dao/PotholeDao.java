package com.techelevator.dao;

import com.techelevator.model.Pothole;
import com.techelevator.model.RegisterPotholeDto;


import java.util.List;

public interface PotholeDao {
    List<Pothole> getPotholes();

    Pothole getPotholeById(int id);

    Pothole createPothole(RegisterPotholeDto pothole);
    int deletePothole(int id);

    Pothole updatePothole(Pothole pothole);

}
