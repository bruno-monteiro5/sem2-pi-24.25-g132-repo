package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a building that can be used to upgrade a station.
 * Each building has a name, price, unlock time, and logical relationships
 * such as mutual exclusivity or replacement of other buildings.
 */
public class Building {

    // Attributes
    public int id; // Unique identifier for the building
    public ArrayList<Building> mutuallyExclusiveBuildingList; // Buildings that conflict with this one
    public boolean isReplaced; // Indicates if this building is replaced by another
    public int replacedById; // ID of the building replacing this one
    public int unlockYear; // Year when the building becomes available
    public float buildingPrice; // Cost to purchase/upgrade the building
    public String name; // Name of the building

    // Static attributes
    public static final String[] NAMES = {
            "Telegraph", "Telephone", "Small Hotel", "Grand Hotel",
            "Small Cafe", "Large Cafe", "Customs", "Post Office",
            "Silo", "Liquid Storage"
    };
    public static final int DEFAULT_ID = -1;
    public static final int DEFAULT_UNLOCK_YEAR = 0; // Represents buildings available at the start
    public static final float DEFAULT_BUILDING_PRICE = 10000f;

    /**
     * Returns the name of the building based on its unique identifier (ID).
     *
     * @return The name of the building as a string.
     */
    public String getName() {
        return NAMES[this.id - 1];
    }

    // === Constructors ===

    /**
     * Default constructor initializing the building with default parameters.
     */
    public Building() {
        this.id = DEFAULT_ID;
        this.mutuallyExclusiveBuildingList = new ArrayList<>();
        this.isReplaced = isReplacedBool();
        this.replacedById = determineReplacementBuilding();
        this.unlockYear = calculateUnlockYear();
        this.buildingPrice = DEFAULT_BUILDING_PRICE;
        initializeMutuallyExclusiveList();
    }

    /**
     * Constructs a building with a specific ID and price.
     *
     * @param id           The unique identifier of the building.
     * @param buildingPrice The cost of the building.
     */
    public Building(int id, float buildingPrice) {
        this.id = id;
        this.mutuallyExclusiveBuildingList = new ArrayList<>();
        this.isReplaced = isReplacedBool();
        this.replacedById = determineReplacementBuilding();
        this.unlockYear = calculateUnlockYear();
        this.buildingPrice = buildingPrice;
        initializeMutuallyExclusiveList();
    }

    /**
     * Constructs a building with a specific ID and the ID of a building that replaces it.
     *
     * @param id          The unique identifier of the building.
     * @param replacedById The ID of the building that replaces this one.
     */
    public Building(int id, int replacedById) {
        this.id = id;
        this.mutuallyExclusiveBuildingList = new ArrayList<>();
        this.isReplaced = isReplacedBool();
        this.replacedById = replacedById;
        this.unlockYear = calculateUnlockYear();
        this.buildingPrice = DEFAULT_BUILDING_PRICE;
        initializeMutuallyExclusiveList();
    }

    // === Private Helper Methods ===

    /**
     * Initializes the list of mutually exclusive buildings based on the building's ID.
     */
    private void initializeMutuallyExclusiveList() {
        switch (id) {
            case 3: // Small Hotel
                mutuallyExclusiveBuildingList.add(new Building(4, false)); // Grand Hotel
                break;
            case 4: // Grand Hotel
                mutuallyExclusiveBuildingList.add(new Building(3, false)); // Small Hotel
                break;
            case 5: // Small Cafe
                mutuallyExclusiveBuildingList.add(new Building(6, false)); // Large Cafe
                break;
            case 6: // Large Cafe
                mutuallyExclusiveBuildingList.add(new Building(5, false)); // Small Cafe
                break;
        }
    }

    /**
     * Private constructor to create dummy buildings during initialization of the conflict list.
     *
     * @param id    The unique identifier.
     * @param dummy A dummy flag to differentiate this constructor.
     */
    private Building(int id, boolean dummy) {
        this.id = id;
        this.mutuallyExclusiveBuildingList = new ArrayList<>();
        this.isReplaced = false;
        this.replacedById = DEFAULT_ID;
        this.unlockYear = DEFAULT_UNLOCK_YEAR;
        this.buildingPrice = DEFAULT_BUILDING_PRICE;
    }

    /**
     * Determines if the building is replaced based on its identifier.
     *
     * @return True if the building is replaced, false otherwise.
     */
    private boolean isReplacedBool() {
        return id == 1 || id == 3 || id == 5; // Telegraph, Small Hotel, Small Cafe
    }

    /**
     * Determines the ID of a building replacing the current one.
     *
     * @return The ID of the replacement building, or -1 if none exists.
     */
    private int determineReplacementBuilding() {
        switch (id) {
            case 1: // Telegraph
                return 2; // Replaced by Telephone
            case 3: // Small Hotel
                return 4; // Replaced by Grand Hotel
            case 5: // Small Cafe
                return 6; // Replaced by Large Cafe
            default:
                return DEFAULT_ID;
        }
    }

    /**
     * Calculates the year when a building is unlocked based on its ID.
     *
     * @return The unlock year of the building.
     */
    private int calculateUnlockYear() {
        switch (id) {
            case 2: // Telephone
                return 1880;
            case 4: // Grand Hotel
                return 1900;
            case 6: // Large Cafe
                return 1920;
            default:
                return DEFAULT_UNLOCK_YEAR;
        }
    }

    // === Public Methods ===

    /**
     * Gets the list of mutually exclusive buildings for this building.
     *
     * @return A list of conflict buildings.
     */
    public ArrayList<Building> getMutuallyExclusiveBuildingList() {
        return new ArrayList<>(mutuallyExclusiveBuildingList);
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d, Name: %s, Unlock Year: %d, Price: %.2f, Replaced By: %d",
                id, getName(), unlockYear, buildingPrice, replacedById
        );
    }

    // === Public Getters ===

    public int getId() {
        return id;
    }

    public boolean isReplaced() {
        return isReplaced;
    }

    public int getReplacedById() {
        return replacedById;
    }

    public int getUnlockYear() {
        return unlockYear;
    }

    public float getBuildingPrice() {
        return buildingPrice;
    }
}
