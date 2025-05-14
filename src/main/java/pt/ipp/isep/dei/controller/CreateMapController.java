package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.repository.MapRepository;

public class CreateMapController {
    private final MapRepository repository;

    public CreateMapController(MapRepository repository) {
        this.repository = repository;
    }

    public Map createMap(String name, int width, int height) {
        if (repository.mapExists(name)) {
            throw new IllegalArgumentException("Map with that name already exists.");
        }

        Map newMap = new Map(name, width, height);
        repository.save(newMap);
        return newMap;
    }
}
