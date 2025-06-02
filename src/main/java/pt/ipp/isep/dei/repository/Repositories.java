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
    private final BuildingTypeRepository buildingTypeRepository;
    private final LocomotiveTypeRepository locomotiveTypeRepository;
    private final RailwayLineRepository railwayLineRepository;
    private final LocomotiveRepository locomotiveRepository;
    private final TrainRepository trainRepository;
    private final RouteRepository routeRepository;
    private final ScenarioRepository scenarioRepository; // ✅ instância única

    private Repositories() {
        organizationRepository = new OrganizationRepository();
        taskCategoryRepository = new TaskCategoryRepository();
        authenticationRepository = new AuthenticationRepository();
        mapRepository = new MapRepository();
        stationRepository = StationRepository.getInstance();
        stationTypeRepository = new StationTypeRepository();
        playerRepository = new PlayerRepository();
        buildingTypeRepository = new BuildingTypeRepository();
        locomotiveTypeRepository = new LocomotiveTypeRepository();
        railwayLineRepository = new RailwayLineRepository();
        locomotiveRepository = new LocomotiveRepository();
        trainRepository = new TrainRepository();
        routeRepository = new RouteRepository();
        scenarioRepository = new ScenarioRepository(); // ✅ corrigido
    }

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
        return scenarioRepository; // ✅ devolve sempre a mesma instância
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

    public LocomotiveRepository getLocomotiveRepository() {
        return locomotiveRepository;
    }

    public LocomotiveTypeRepository getLocomotiveTypeRepository() {
        return locomotiveTypeRepository;
    }

    public TrainRepository getTrainRepository() {
        return trainRepository;
    }

    public RouteRepository getRouteRepository() {
        return routeRepository;
    }

    public RailwayLineRepository getRailwayLineRepository() {
        return railwayLineRepository;
    }
}
