package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Map;
import pt.isep.lei.esoft.auth.UserSession;

import java.util.ArrayList;
import java.util.List;

public class MapRepository {
    private static MapRepository instance;
    private final List<Map> maps;
    private Map activeMap;

    public MapRepository() {
        this.maps = new ArrayList<>();
    }

    public static MapRepository getInstance() {
        if (instance == null) {
            instance = new MapRepository();
        }
        return instance;
    }


    public Map getActiveMap(){
        if(activeMap == null){
            throw new IllegalStateException("No active map");
        }
        return this.activeMap;
    }

    public boolean setActiveMap(Map map) {
        if(this.activeMap != null){
            return false;
        }
        activeMap = map;
        return true;
    }

    public boolean addMap(Map map) {
        if (map == null) {
            return false;
        }

        for (Map existingMap : maps) {
            if (existingMap.equals(map)) {
                return false;
            }
        }

        return this.maps.add(map);
    }


    public List<Map> getMaps() {
        return this.maps;
    }


}
