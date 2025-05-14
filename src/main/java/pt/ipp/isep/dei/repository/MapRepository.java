package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Map;

import java.util.ArrayList;
import java.util.List;

public class MapRepository {
    private final List<Map> maps;

    public MapRepository() {
        this.maps = new ArrayList<>();
    }

    public boolean save(Map map) {
        if (mapExists(map.getName())) {
            return false; // já existe com esse nome
        }
        maps.add(map);
        return true;
    }

    public boolean mapExists(String name) {
        for (Map m : maps) {
            if (m.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Map> getAllMaps() {
        return new ArrayList<>(maps); // cópia defensiva
    }

    public Map findByName(String name) {
        for (Map m : maps) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
}
