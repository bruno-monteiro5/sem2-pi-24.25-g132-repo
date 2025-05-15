package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.StationType;

import java.util.Arrays;
import java.util.List;

/**
 * Repository for retrieving the list of available station types.
 */
public class StationTypeRepository {

    /**
     * Returns a list of all predefined StationType values.
     * @return list of StationType
     */
    public List<StationType> getAllStationTypes() {
        return Arrays.asList(StationType.values());
    }
}
