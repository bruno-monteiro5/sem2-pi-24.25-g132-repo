package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.repository.CityRepository;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.List;

public class CreateCityController {

    private CityRepository repository;
    private MapRepository mapRepository;

    public CreateCityController() {
        this.repository = Repositories.getInstance().getCityRepository();
        this.mapRepository = Repositories.getInstance().getMapRepository();
    }

    public List<Map> getMaps() {
        return mapRepository.getMaps();
    }

    public City createCity(Map mapa, String name, Position position, int houseblocks) {
        if (repository.cityExists(name)) {
            throw new IllegalArgumentException("City with that name already exists.");
        }

        if (repository.positionInvalid(position)) {
            throw new IllegalArgumentException("Position is invalid.");
        }

        // Se for entrada manual, verificamos se o valor é positivo
        // Se for entrada manual (não automática), verificamos se o valor é positivo
        if (houseblocks != -1) {  // Não é geração automática
            if (houseblocks <= 0) {
                throw new IllegalArgumentException("Número de house blocks deve ser positivo.");
            }

            // Criar cidade com o valor manual
            City city = new City(mapa, name, position, houseblocks);
            repository.save(city);
            return city;
        } else {
            // Geração automática
            // Usar um valor temporário para o construtor
            City city = new City(mapa, name, position, 1);

            // Gerar house blocks automaticamente
            city.generateRandomHouseBlocks();

            repository.save(city);
            return city;
        }

    }

    public List<City> getCities() {
        return repository.getCities();
    }
}
