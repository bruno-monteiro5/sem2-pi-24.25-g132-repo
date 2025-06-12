package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.repository.AuthenticationRepository;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;
import pt.isep.lei.esoft.auth.UserSession;

/**
 * Controller responsible for creating and managing maps.
 * <p>
 * Provides methods for saving maps, validating map dimensions,
 * and retrieving user information from the current session.
 */
public class CreateMapController {
    private  MapRepository mapRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Initializes the CreateMapController.
     * Ensures both the map and authentication repositories are loaded.
     */
    public CreateMapController() {
        getMapRepository();
        getAuthenticationRepository();
    }

    /**
     * Checks if the name of a map already exists in the repository.
     *
     * @param map The {@link Map} to validate.
     * @return {@code true} if the name exists, {@code false} otherwise.
     */
    private boolean isNameExists(Map map) {
        for(Map existingMap: mapRepository.getMaps()) {
            if(existingMap.getName().equals(map.getName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Saves a map after validating its name, dimensions, and uniqueness.
     *
     * @param map The {@link Map} object to be saved.
     * @return The saved {@link Map} instance if the operation is successful,
     *         or {@code null} if validation fails.
     */

    public Map saveMap(Map map) {
        if (!Map.isValidMapName(map.getName())){
            System.out.println("Invalid name");
            return null;
        }
        if (!Map.isDimensionValid(map.getWidth(), map.getHeight())) {
            System.out.println("Invalid dimension");
            return null;
        }

        if (isNameExists(map)){
            System.out.println("Name already exists");
            return null;
        }

        getMapRepository().addMap(map);
        return map;
    }

    /**
     * Retrieves the email of the currently logged-in user if they have the "EDITOR" role.
     *
     * @return The email of the current user if logged in with the "EDITOR" role,
     *         or {@code null} if the user is not logged in or does not have the required role.
     */
    public String getUserEmailFromSession() {
        UserSession userSession = authenticationRepository.getCurrentUserSession();

        if(userSession.isLoggedInWithRole("EDITOR")) {
            return userSession.getUserId().getEmail();
        }
        return null;
    }

    /**
     * Retrieves the authentication repository, initializing it if necessary.
     *
     * @return The {@link AuthenticationRepository} instance.
     */
    public AuthenticationRepository getAuthenticationRepository() {
        if(authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Retrieves the map repository, initializing it if necessary.
     *
     * @return The {@link MapRepository} instance.
     */
    public MapRepository getMapRepository() {
        if (mapRepository == null) {
            Repositories repositories = Repositories.getInstance();
            mapRepository = repositories.getMapRepository();
        }
        return mapRepository;
    }

}
