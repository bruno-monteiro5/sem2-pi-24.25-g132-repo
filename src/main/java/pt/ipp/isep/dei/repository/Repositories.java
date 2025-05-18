package pt.ipp.isep.dei.repository;

/**
 * Inspired on https://refactoring.guru/design-patterns/singleton/java/example
 *
 * The Repositories class works as a Singleton. It defines the getInstance method that serves as an alternative
 * to the constructor and lets client classes access the same instance of this class over and over.
 */
public class Repositories {
    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private final StationRepository stationRepository;
    private final MapRepository mapRepository;
    private final StationTypeRepository stationTypeRepository;
    private final PlayerRepository playerRepository;
    private BuildingTypeRepository buildingTypeRepository;
    private final LocomotiveTypeRepository locomotiveTypeRepository;

    /**
     * The Singleton's constructor should always be private to prevent direct construction calls with the new operator.
     */
    private Repositories() {
        organizationRepository = new OrganizationRepository();
        taskCategoryRepository = new TaskCategoryRepository();
        authenticationRepository = new AuthenticationRepository();
        mapRepository = new MapRepository();
        stationRepository = new StationRepository();
        stationTypeRepository = new StationTypeRepository();
        playerRepository = new PlayerRepository();
        this.buildingTypeRepository = new BuildingTypeRepository();
        this.locomotiveTypeRepository = new LocomotiveTypeRepository();
    }

    /**
     * This is the static method that controls the access to the singleton instance.
     * On the first run, it creates a singleton object and places it into the static attribute.
     * On subsequent runs, it returns the existing object stored in the static attribute.
     */
    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public BuildingTypeRepository getBuildingTypeRepository() {
        return buildingTypeRepository;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }

    public MapRepository getMapRepository() {
        return mapRepository;
    }

    public StationRepository getStationRepository() {
        return stationRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public ScenarioRepository getScenarioRepository() {
        return new ScenarioRepository();
    }

    public CityRepository getCityRepository() {
        return new CityRepository();
    }

    public IndustryRepository getIndustryRepository() {
        return new IndustryRepository();
    }

    public StationTypeRepository getStationTypeRepository() {
        return stationTypeRepository;
    }

    public PlayerRepository getPlayerRepository() {
        return playerRepository;
    }

    public LocomotiveTypeRepository getLocomotiveTypeRepository() {
        return locomotiveTypeRepository;
    }

}
