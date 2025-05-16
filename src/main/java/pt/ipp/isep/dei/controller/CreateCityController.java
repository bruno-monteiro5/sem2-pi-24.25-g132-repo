package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.HouseBlock;
import pt.ipp.isep.dei.domain.Position;

import java.util.ArrayList;

public class CreateCityController {

    //private repo

    public CreateCityController() {
        this.repo = new repo();
    }

    public City createCity(String name, Position position, int houseblocks) {
        if(repo.cityExists){
            throw new IllegalArgumentException("City with that name already exists.");
        }

        City city = new City(name,position,houseblocks);
        repo.save(city);
        return city;
    }

}
