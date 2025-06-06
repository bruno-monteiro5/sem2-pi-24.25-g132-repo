package pt.ipp.isep.dei.domain;

/**
 * Enum that represents how a train should load cargo before departure.
 * <p>
 * Used to define when the train is allowed to leave the station.
 */
public enum CargoMode {

    /**
     * The train only departs when all carriages are fully loaded.
     */
    FULL,

    /**
     * The train departs when at least half of the carriages are loaded.
     */
    HALF,

    /**
     * The train departs with the available cargo, even if not full.
     */
    AVAILABLE
}