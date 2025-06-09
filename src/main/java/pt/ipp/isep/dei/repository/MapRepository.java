package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Map;
import pt.isep.lei.esoft.auth.UserSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing maps in the system.
 *
 * <p>The {@code MapRepository} is a singleton class responsible for the central
 * management of maps. It handles actions such as storing maps, managing the
 * active map, and retrieving all stored maps.</p>
 */
public class MapRepository {
    private static MapRepository instance;
    private final List<Map> maps;
    private Map activeMap;

    /**
     * Constructor to initialize the repository with a list of maps.
     */
    public MapRepository() {
        this.maps = new ArrayList<>();
    }

    /**
     * Retrieves the singleton instance of {@code MapRepository}.
     *
     * @return The instance of {@code MapRepository}.
     */
    public static MapRepository getInstance() {
        if (instance == null) {
            instance = new MapRepository();
        }
        return instance;
    }

    /**
     * Retrieves the active map set in the repository.
     *
     * @return The current active {@code Map}.
     * @throws IllegalStateException if no active map has been set.
     */
    public Map getActiveMap(){
        if(activeMap == null){
            throw new IllegalStateException("No active map");
        }
        return this.activeMap;
    }

    /**
     * Sets a specific map as the active map in the repository.
     *
     * @param map The {@code Map} to be set as active.
     * @return {@code true} if the map is successfully set as active; {@code false}
     * if an active map is already set.
     */
    public boolean setActiveMap(Map map) {
        if(this.activeMap != null){
            return false;
        }
        activeMap = map;
        return true;
    }

    /**
     * Adds a new map to the repository.
     *
     * @param map The {@code Map} to be added.
     * @return {@code true} if the map is added successfully; {@code false} if the map
     * is null or if the map already exists in the repository.
     */
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

    /**
     * Retrieves the list of all maps stored in the repository.
     *
     * @return A {@code List} of all {@code Map} objects in the repository.
     */
    public List<Map> getMaps() {
        return this.maps;
    }


    /**
     * Retrieves the most recently added map.
     *
     * @return The last {@code Map} in the repository, or {@code null} if no maps exist.
     */
    public Map getLastCreated() {
        if (maps.isEmpty()) {
            return null;
        }
        return maps.get(maps.size() - 1);
    }
}
