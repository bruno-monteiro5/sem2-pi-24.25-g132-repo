package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.repository.CityRepository;

import java.util.List;

public class CreateCityController {

    private CityRepository repository;

    public CreateCityController() {
        this.repository = new CityRepository();
    }

    public City createCity(String name, Position position, int houseblocks) {
        if(repository.cityExists(name)){
            throw new IllegalArgumentException("City with that name already exists.");
        }

        if(repository.positionInvalid(position)){
            throw new IllegalArgumentException("Position is invalid.");
        }

        City city = new City(name,position,houseblocks);
        repository.save(city);
        return city;
    }

    public List<City> getCities() {
        return repository.getCities();
    }
}
