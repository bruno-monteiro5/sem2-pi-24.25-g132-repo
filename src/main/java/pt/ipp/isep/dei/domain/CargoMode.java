package pt.ipp.isep.dei.domain;

/**
 * Represents the cargo loading strategy for a train before departure.
 * <p>
 * Determines when a train is allowed to leave the station based on its cargo load status.
 */
public enum CargoMode {

    /**
     * The train departs only when all carriages are fully loaded.
     */
    FULL,

    /**
     * The train departs when at least half of the carriages are loaded.
     */
    HALF,

    /**
     * The train departs with the available cargo, regardless of how much is loaded.
     */
    AVAILABLE
}