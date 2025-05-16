package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Map;
import pt.isep.lei.esoft.auth.UserSession;

import java.util.ArrayList;
import java.util.List;

public class MapRepository {
    private static MapRepository instance;
    private final List<Map> maps;

    public MapRepository() {
        this.maps = new ArrayList<>();
    }

    public static MapRepository getInstance() {
        if (instance == null) {
            instance = new MapRepository();
        }
        return instance;
    }

    public void addMap(Map map) {
        this.maps.add(map);
    }

    public List<Map> getMaps() {
        return this.maps;
    }

    public Map getMapByName(String name) {
        for(Map m : this.maps) {
            if(m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public List<Map> getMapsByPlayer(UserSession userSession) {
        List<Map> maps = new ArrayList<>();
        for (Map map : this.maps) {
            if (map.getEmailEditor().equals(userSession.getUserId().getEmail())) {
                maps.add(map);
            }
        }
        return maps;
    }

}
