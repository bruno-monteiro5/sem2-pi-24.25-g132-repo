package pt.ipp.isep.dei.domain;

import java.util.List;
import java.util.ArrayList;

/**
 * Enum representing detailed data for all predefined industries in the game.
 * Converted to use primitive Java features only (no List.of or Arrays.asList).
 */
public enum IndustryData {

    ALUMINUM_MILL("Aluminum Mill", 1910, new String[]{"Aluminum"}, new String[]{"Bauxite"}, 500000, 160, IndustryType.MIXED),
    AUTO_PLANT("Auto Plant", 1900, new String[]{"Autos"}, new String[]{"Steel", "Tires"}, 600000, 70, IndustryType.MIXED),
    BAKERY("Bakery", 1800, new String[]{"Food"}, new String[]{"Grain", "Sugar"}, 300000, 0, IndustryType.MIXED),
    BAUXITE_MINE("Bauxite Mine", 1900, new String[]{"Bauxite"}, new String[]{}, 150000, 55, IndustryType.PRIMARY),
    CANNERY("Cannery", 1856, new String[]{"Food"}, new String[]{"Aluminium", "Coffee", "Produce", "Steel"}, 250000, 30, IndustryType.MIXED),
    CATTLE_YARD("Cattle Yard", 1800, new String[]{"Cattle"}, new String[]{}, 150000, 0, IndustryType.PRIMARY),
    CHEMICAL_PLANT("Chemical Plant", 1905, new String[]{"Chemical"}, new String[]{}, 400000, 20, IndustryType.PRIMARY),
    CEMENT_PLANT("Cement Plant", 1884, new String[]{"Cement"}, new String[]{"Gravel"}, 300000, 45, IndustryType.MIXED),
    COAL_MINE("Coal Mine", 1800, new String[]{"Coal"}, new String[]{}, 300000, 0, IndustryType.PRIMARY),
    COFFEE_FARM("Coffee Farm", 1800, new String[]{"Coffee"}, new String[]{"Fertilizer"}, 150000, 0, IndustryType.PRIMARY),
    COTTON_FARM("Cotton Farm", 1800, new String[]{"Cotton"}, new String[]{"Fertilizer"}, 200000, 0, IndustryType.PRIMARY),
    DAIRY_FARM("Dairy Farm", 1800, new String[]{"Dairy"}, new String[]{"Grain"}, 200000, 25, IndustryType.PRIMARY),
    DAIRY_PROCESSOR("Dairy Processor", 1881, new String[]{"Food"}, new String[]{"Dairy"}, 300000, 45, IndustryType.MIXED),
    ELECTRIC_PLANT("Electric Plant", 1890, new String[]{"Waste"}, new String[]{"Coal", "Diesel"}, 400000, 50, IndustryType.MIXED),
    FERTILIZER_PLANT("Fertilizer Plant", 1905, new String[]{"Fertilizer"}, new String[]{"Chemicals"}, 300000, 50, IndustryType.MIXED),
    GRAIN_SILO("Grain Silo", 1800, new String[]{"Grain"}, new String[]{"Fertilizer"}, 250000, 100, IndustryType.MIXED),
    GRAVEL_PIT("Gravel Pit", 1884, new String[]{"Gravel"}, new String[]{}, 200000, 65, IndustryType.PRIMARY),
    HOUSING("Housing", 1800, new String[]{"Passenger", "Mail"}, new String[]{}, 0, 0, IndustryType.PRIMARY),
    IRON_MINE("Iron Mine", 1800, new String[]{"Iron"}, new String[]{}, 200000, 35, IndustryType.PRIMARY),
    LANDFILL("Landfill", 1950, new String[]{}, new String[]{"Waste"}, 300000, 0, IndustryType.TRANSFORMING),
    LOGGING_CAMP("Logging Camp", 1800, new String[]{"Logs", "Pulpwood"}, new String[]{}, 150000, 0, IndustryType.PRIMARY),
    LUMBER_MILL("Lumber Mill", 1800, new String[]{"Lumber"}, new String[]{"Logs"}, 200000, 30, IndustryType.MIXED),
    MEAT_PACKING_PLANT("Meat Packing Plant", 1800, new String[]{"Food"}, new String[]{"Cattle"}, 400000, 90, IndustryType.MIXED),
    NUCLEAR_PLANT("Nuclear Plant", 1950, new String[]{"Waste"}, new String[]{"Uranium"}, 500000, 110, IndustryType.MIXED),
    OIL_REFINERY("Oil Refinery", 1890, new String[]{"Diesel"}, new String[]{"Oil"}, 500000, 50, IndustryType.MIXED),
    OIL_WELL("Oil Well", 1860, new String[]{"Oil"}, new String[]{}, 200000, 35, IndustryType.PRIMARY),
    PAPER_MILL("Paper Mill", 1800, new String[]{"Paper"}, new String[]{"Pulpwood"}, 300000, 50, IndustryType.MIXED),
    PORT("Port", 1800, new String[]{}, new String[]{}, 300000, 70, IndustryType.MIXED),
    PRODUCE_ORCHARD("Produce Orchard", 1800, new String[]{"Produce"}, new String[]{"Fertilizer"}, 200000, 0, IndustryType.PRIMARY),
    RUBBER_FARM("Rubber Farm", 1900, new String[]{"Rubber"}, new String[]{"Fertilizer"}, 150000, 0, IndustryType.PRIMARY),
    STEEL_MILL("Steel Mill", 1856, new String[]{"Steel"}, new String[]{"Coal", "Iron"}, 800000, 60, IndustryType.MIXED),
    SHEEP_FARM("Sheep Farm", 1800, new String[]{"Sheep"}, new String[]{"Grain"}, 200000, 60, IndustryType.PRIMARY),
    TEXTILE_MILL("Textile Mill", 1800, new String[]{"Goods"}, new String[]{"Cotton", "Wool"}, 400000, 35, IndustryType.MIXED),
    TIRE_FACTORY("Tire Factory", 1900, new String[]{"Tires"}, new String[]{"Rubber"}, 300000, 0, IndustryType.MIXED),
    TOOL_AND_DIE_FACTORY("Tool and Die Factory", 1800, new String[]{"Goods"}, new String[]{"Iron", "Steel", "Aluminium"}, 200000, 20, IndustryType.MIXED),
    URANIUM_MINE("Uranium Mine", 1950, new String[]{"Uranium"}, new String[]{}, 600000, 35, IndustryType.PRIMARY),
    GEOCORE_PLANT("Geocore Plant", 1800, new String[]{"Waste"}, new String[]{"Chemicals"}, 0, 0, IndustryType.MIXED),
    TOWNHOUSE("Townhouse", 1800, new String[]{"Passengers", "Mail"}, new String[]{}, 0, 0, IndustryType.PRIMARY),
    COMMERCIAL("Commercial", 1800, new String[]{}, new String[]{}, 300000, 35, IndustryType.MIXED),
    RETAIL("Retail", 1800, new String[]{}, new String[]{}, 150000, 35, IndustryType.MIXED),
    DISTILLERY("Distillery", 1800, new String[]{"Alcohol"}, new String[]{"Grain", "Produce", "Sugar"}, 400000, 35, IndustryType.MIXED),
    BARRACKS("Barracks", 1800, new String[]{"Troops"}, new String[]{"Troops", "Food", "Alcohol"}, 0, 0, IndustryType.MIXED),
    MILITARY_DEPOT("Military Depot", 1800, new String[]{}, new String[]{"Weapons", "Munitions", "Troops"}, 0, 0, IndustryType.TRANSFORMING),
    MUNITIONS_FACTORY("Munitions Factory", 1800, new String[]{"Munitions"}, new String[]{"Chemicals", "Steel", "Aluminium"}, 700000, 35, IndustryType.MIXED),
    WEAPONS_FACTORY("Weapons Factory", 1800, new String[]{"Weapons"}, new String[]{"Rubber", "Steel", "Aluminium"}, 500000, 35, IndustryType.MIXED),
    AIRPORT_TERMINAL("Airport Terminal", 1800, new String[]{}, new String[]{}, 0, 0, IndustryType.MIXED),
    AIRPORT_TARMAC("Airport Tarmac", 1800, new String[]{}, new String[]{}, 0, 0, IndustryType.MIXED);

    private final String name;
    private final int startYear;
    private final List<String> suppliedCargos;
    private final List<String> demandedCargos;
    private final int cost;
    private final int profitability;
    private final IndustryType type;

    IndustryData(String name, int startYear, String[] supplied, String[] demanded,
                 int cost, int profitability, IndustryType type) {
        this.name = name;
        this.startYear = startYear;
        this.suppliedCargos = new ArrayList<>();
        for (String s : supplied) this.suppliedCargos.add(s);
        this.demandedCargos = new ArrayList<>();
        for (String d : demanded) this.demandedCargos.add(d);
        this.cost = cost;
        this.profitability = profitability;
        this.type = type;
    }

    public String getName() { return name; }
    public int getStartYear() { return startYear; }
    public List<String> getSuppliedCargos() { return suppliedCargos; }
    public List<String> getDemandedCargos() { return demandedCargos; }
    public int getCost() { return cost; }
    public int getProfitability() { return profitability; }
    public IndustryType getType() { return type; }

    public static IndustryData fromName(String name) {
        for (IndustryData data : IndustryData.values()) {
            if (data.getName().equalsIgnoreCase(name)) {
                return data;
            }
        }
        return null;
    }

}
