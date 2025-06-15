/*
package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RailwayLineRepositoryTest {
    private RailwayLineRepository repository;
    private RailwayLine line1, line2;
    private Station stationA, stationB, stationC;
    private Scenario scenario;

    @BeforeEach
    void setUp() {
        repository = new RailwayLineRepository();
        scenario = new Scenario("Test Scenario", 1900, 2000);

        // Create test stations
        stationA = new Station("Station A", new Position(10, 20), StationType.STATION);
        stationB = new Station("Station B", new Position(30, 40), StationType.STATION);
        stationC = new Station("Station C", new Position(50, 60), StationType.STATION);

        // Create test lines
        line1 = new RailwayLine(scenario, stationA, stationB, LineType.SINGLE_ELECTRIFIED, 100);
        line2 = new RailwayLine(scenario, stationB, stationC, LineType.DOUBLE_NOT_ELECTRICITY, 150);
    }

    @Test
    void addRailwayLine_ReturnTrue_WhenAddingNewLine() {
        assertTrue(repository.addRailwayLine(line1));
        assertEquals(1, repository.getAll().size());
    }

    @Test
    void addRailwayLine_ReturnFalse_WhenAddingDuplicateLine() {
        repository.addRailwayLine(line1);
        assertFalse(repository.addRailwayLine(line1));
        assertEquals(1, repository.getAll().size());
    }

    @Test
    void getAll_ReturnDefensiveCopy() {
        repository.addRailwayLine(line1);
        List<RailwayLine> lines = repository.getAll();
        lines.clear();

        assertEquals(1, repository.getAll().size());
    }

    @Test
    void removeRailwayLine_ReturnTrue_WhenLineExists() {
        repository.addRailwayLine(line1);
        assertTrue(repository.removeRailwayLine(line1));
        assertTrue(repository.getAll().isEmpty());
    }

    @Test
    void removeRailwayLine_ReturnFalse_WhenLineDoesNotExist() {
        repository.addRailwayLine(line1);
        assertFalse(repository.removeRailwayLine(line2));
        assertEquals(1, repository.getAll().size());
    }

    @Test
    void clear_RemoveAllLines() {
        repository.addRailwayLine(line1);
        repository.addRailwayLine(line2);
        repository.clear();

        assertTrue(repository.getAll().isEmpty());
    }
}

 */
