package pt.ipp.isep.dei.domain;

import java.util.List;

/**
 * Enum representing detailed data for all predefined industries in the game.
 * Each industry includes information such as its name, introduction year,
 * cargos supplied and demanded, construction cost, profitability, and type.
 */
public enum IndustryData {

    ALUMINUM_MILL("Aluminum Mill", 1910, List.of("Aluminum"), List.of("Bauxite"), 500000, 160, IndustryType.MIXED),
    AUTO_PLANT("Auto Plant", 1900, List.of("Autos"), List.of("Steel", "Tires"), 600000, 70, IndustryType.MIXED),
    BAKERY("Bakery", 1800, List.of("Food"), List.of("Grain", "Sugar"), 300000, 0, IndustryType.MIXED),
    BAUXITE_MINE("Bauxite Mine", 1900, List.of("Bauxite"), List.of(), 150000, 55, IndustryType.PRIMARY),
    CANNERY("Cannery", 1856, List.of("Food"), List.of("Aluminium", "Coffee", "Produce", "Steel"), 250000, 30, IndustryType.MIXED),
    CATTLE_YARD("Cattle Yard", 1800, List.of("Cattle"), List.of(), 150000, 0, IndustryType.PRIMARY),
    CHEMICAL_PLANT("Chemical Plant", 1905, List.of("Chemical"), List.of(), 400000, 20, IndustryType.PRIMARY),
    CEMENT_PLANT("Cement Plant", 1884, List.of("Cement"), List.of("Gravel"), 300000, 45, IndustryType.MIXED),
    COAL_MINE("Coal Mine", 1800, List.of("Coal"), List.of(), 300000, 0, IndustryType.PRIMARY),
    COFFEE_FARM("Coffee Farm", 1800, List.of("Coffee"), List.of("Fertilizer"), 150000, 0, IndustryType.PRIMARY),
    COTTON_FARM("Cotton Farm", 1800, List.of("Cotton"), List.of("Fertilizer"), 200000, 0, IndustryType.PRIMARY),
    DAIRY_FARM("Dairy Farm", 1800, List.of("Dairy"), List.of("Grain"), 200000, 25, IndustryType.PRIMARY),
    DAIRY_PROCESSOR("Dairy Processor", 1881, List.of("Food"), List.of("Dairy"), 300000, 45, IndustryType.MIXED),
    ELECTRIC_PLANT("Electric Plant", 1890, List.of("Waste"), List.of("Coal", "Diesel"), 400000, 50, IndustryType.MIXED),
    FERTILIZER_PLANT("Fertilizer Plant", 1905, List.of("Fertilizer"), List.of("Chemicals"), 300000, 50, IndustryType.MIXED),
    GRAIN_SILO("Grain Silo", 1800, List.of("Grain"), List.of("Fertilizer"), 250000, 100, IndustryType.MIXED),
    GRAVEL_PIT("Gravel Pit", 1884, List.of("Gravel"), List.of(), 200000, 65, IndustryType.PRIMARY),
    HOUSING("Housing", 1800, List.of("Passenger", "Mail"), List.of(), 0, 0, IndustryType.PRIMARY),
    IRON_MINE("Iron Mine", 1800, List.of("Iron"), List.of(), 200000, 35, IndustryType.PRIMARY),
    LANDFILL("Landfill", 1950, List.of(), List.of("Waste"), 300000, 0, IndustryType.TRANSFORMING),
    LOGGING_CAMP("Logging Camp", 1800, List.of("Logs", "Pulpwood"), List.of(), 150000, 0, IndustryType.PRIMARY),
    LUMBER_MILL("Lumber Mill", 1800, List.of("Lumber"), List.of("Logs"), 200000, 30, IndustryType.MIXED),
    MEAT_PACKING_PLANT("Meat Packing Plant", 1800, List.of("Food"), List.of("Cattle"), 400000, 90, IndustryType.MIXED),
    NUCLEAR_PLANT("Nuclear Plant", 1950, List.of("Waste"), List.of("Uranium"), 500000, 110, IndustryType.MIXED),
    OIL_REFINERY("Oil Refinery", 1890, List.of("Diesel"), List.of("Oil"), 500000, 50, IndustryType.MIXED),
    OIL_WELL("Oil Well", 1860, List.of("Oil"), List.of(), 200000, 35, IndustryType.PRIMARY),
    PAPER_MILL("Paper Mill", 1800, List.of("Paper"), List.of("Pulpwood"), 300000, 50, IndustryType.MIXED),
    PORT("Port", 1800, List.of(), List.of(), 300000, 70, IndustryType.MIXED),
    PRODUCE_ORCHARD("Produce Orchard", 1800, List.of("Produce"), List.of("Fertilizer"), 200000, 0, IndustryType.PRIMARY),
    RUBBER_FARM("Rubber Farm", 1900, List.of("Rubber"), List.of("Fertilizer"), 150000, 0, IndustryType.PRIMARY),
    STEEL_MILL("Steel Mill", 1856, List.of("Steel"), List.of("Coal", "Iron"), 800000, 60, IndustryType.MIXED),
    SHEEP_FARM("Sheep Farm", 1800, List.of("Sheep"), List.of("Grain"), 200000, 60, IndustryType.PRIMARY),
    TEXTILE_MILL("Textile Mill", 1800, List.of("Goods"), List.of("Cotton", "Wool"), 400000, 35, IndustryType.MIXED),
    TIRE_FACTORY("Tire Factory", 1900, List.of("Tires"), List.of("Rubber"), 300000, 0, IndustryType.MIXED),
    TOOL_AND_DIE_FACTORY("Tool and Die Factory", 1800, List.of("Goods"), List.of("Iron", "Steel", "Aluminium"), 200000, 20, IndustryType.MIXED),
    URANIUM_MINE("Uranium Mine", 1950, List.of("Uranium"), List.of(), 600000, 35, IndustryType.PRIMARY),
    GEOCORE_PLANT("Geocore Plant", 1800, List.of("Waste"), List.of("Chemicals"), 0, 0, IndustryType.MIXED),
    TOWNHOUSE("Townhouse", 1800, List.of("Passengers", "Mail"), List.of(), 0, 0, IndustryType.PRIMARY),
    COMMERCIAL("Commercial", 1800, List.of(), List.of(), 300000, 35, IndustryType.MIXED),
    RETAIL("Retail", 1800, List.of(), List.of(), 150000, 35, IndustryType.MIXED),
    DISTILLERY("Distillery", 1800, List.of("Alcohol"), List.of("Grain", "Produce", "Sugar"), 400000, 35, IndustryType.MIXED),
    BARRACKS("Barracks", 1800, List.of("Troops"), List.of("Troops", "Food", "Alcohol"), 0, 0, IndustryType.MIXED),
    MILITARY_DEPOT("Military Depot", 1800, List.of(), List.of("Weapons", "Munitions", "Troops"), 0, 0, IndustryType.TRANSFORMING),
    MUNITIONS_FACTORY("Munitions Factory", 1800, List.of("Munitions"), List.of("Chemicals", "Steel", "Aluminium"), 700000, 35, IndustryType.MIXED),
    WEAPONS_FACTORY("Weapons Factory", 1800, List.of("Weapons"), List.of("Rubber", "Steel", "Aluminium"), 500000, 35, IndustryType.MIXED),
    AIRPORT_TERMINAL("Airport Terminal", 1800, List.of(), List.of(), 0, 0, IndustryType.MIXED),
    AIRPORT_TARMAC("Airport Tarmac", 1800, List.of(), List.of(), 0, 0, IndustryType.MIXED);

    private final String name;
    private final int startYear;
    private final List<String> suppliedCargos;
    private final List<String> demandedCargos;
    private final int cost;
    private final int profitability;
    private final IndustryType type;

    IndustryData(String name, int introductionYear, List<String> suppliedCargos, List<String> demandedCargos,
                 int cost, int profitability, IndustryType type) {
        this.name = name;
        this.startYear = introductionYear;
        this.suppliedCargos = suppliedCargos;
        this.demandedCargos = demandedCargos;
        this.cost = cost;
        this.profitability = profitability;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getStartYear() {
        return startYear;
    }

    public List<String> getSuppliedCargos() {
        return suppliedCargos;
    }

    public List<String> getDemandedCargos() {
        return demandedCargos;
    }

    public int getCost() {
        return cost;
    }

    public int getProfitability() {
        return profitability;
    }

    public IndustryType getType() {
        return type;
    }
}

