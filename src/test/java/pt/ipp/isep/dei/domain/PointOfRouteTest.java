
package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link PointOfRoute} class.
 * Ensures 100% test coverage of all public methods.
 */
public class PointOfRouteTest {

    private Station station;
    private List<String> cargoes;
    private CargoMode mode;
    private PointOfRoute point;

    @BeforeEach
    public void setUp() {
        station = new Station("Porto", new Position(10, 20), StationType.STATION);
        cargoes = new ArrayList<>();
        cargoes.add("COAL");
        cargoes.add("MAIL");
        mode = CargoMode.HALF;
        point = new PointOfRoute(station, cargoes, mode);
    }

    /**
     * Test constructor and getters.
     */
    @Test
    public void testConstructorAndGetters() {
        assertEquals(station, point.getStation());
        assertEquals(cargoes, point.getCargoesToPick());
        assertEquals(mode, point.getCargoMode());
    }

    /**
     * Test toString() output.
     */
    @Test
    public void testToString() {
        String str = point.toString();
        assertTrue(str.contains("station=Porto"));
        assertTrue(str.contains("cargoesToPick=[COAL, MAIL]"));
        assertTrue(str.contains("cargoMode=HALF"));
    }
}
