package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.repository.CityRepository;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.List;

public class CreateCityController {

    private final CityRepository cityRepository;

    public CreateCityController() {
        this.cityRepository = Repositories.getInstance().getCityRepository();
    }

    public List<Map> getMaps() {
        return Repositories.getInstance().getMapRepository().getMaps();
    }

    public City createCity(Map map, String name, Position position, int houseBlocks, boolean manual, List<Position> manualPositions) {
        if (cityRepository.cityExists(name)) {
            throw new IllegalArgumentException("City with that name already exists.");
        }

        if (cityRepository.positionInvalid(position)) {
            throw new IllegalArgumentException("Position is already taken by another city.");
        }

        City city = new City(name, position, houseBlocks);

        if (manual) { // Manual assignment
            city.addHouseBlocksManually(manualPositions);
        } else { // Automatic assignment
            city.generateHouseBlocksAutomatically(map);
        }

        cityRepository.save(city);
        return city;
    }

    public List<City> getCities() {
        return cityRepository.getCities();
    }
}