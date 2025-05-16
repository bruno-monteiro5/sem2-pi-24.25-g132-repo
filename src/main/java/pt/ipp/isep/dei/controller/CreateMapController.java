package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.repository.AuthenticationRepository;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;
import pt.isep.lei.esoft.auth.UserSession;

public class CreateMapController {
    private  MapRepository mapRepository;
    private AuthenticationRepository authenticationRepository;


    public CreateMapController() {
        getMapRepository();
        getAuthenticationRepository();
    }

    private boolean isDimensionValid(Map map) {
        return map.getWidth() > 0 && map.getHeight() > 0;
    }

    private boolean isNameValid(Map map) {
        String name = map.getName();
        return name != null && !name.isEmpty();
    }

    private boolean isNameExists(Map map) {
        for(Map existingMap: mapRepository.getMaps()) {
            if(existingMap.getName().equals(map.getName())) {
                return true;
            }
        }
        return false;
    }


    public Map saveMap(Map map) {
        if (!isNameValid(map)){
            System.out.println("Invalid name");
            return null;
        }
        if (!isDimensionValid(map)){
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

    public String getUserEmailFromSession() {
        UserSession userSession = authenticationRepository.getCurrentUserSession();

        if(userSession.isLoggedInWithRole("EDITOR")) {
            return userSession.getUserId().getEmail();
        }
        return null;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        if(authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public MapRepository getMapRepository() {
        if (mapRepository == null) {
            Repositories repositories = Repositories.getInstance();
            mapRepository = repositories.getMapRepository();
        }
        return mapRepository;
    }

}
