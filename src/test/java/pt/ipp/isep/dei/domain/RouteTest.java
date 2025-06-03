
package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Route} class.
 * Ensures 100% test coverage of all public methods.
 */
public class RouteTest {

    private Route route;
    private Train train;
    private PointOfRoute point;

    @BeforeEach
    public void setUp() {
        route = new Route();
        Locomotive loco = new Locomotive(1, LocomotiveType.DASH_9);
        train = new Train(1, loco);

        List<String> cargos = new ArrayList<>();
        cargos.add("COAL");
        Station station = new Station("StationA", new Position(1, 1), StationType.TERMINAL);
        point = new PointOfRoute(station, cargos, CargoMode.FULL);
    }

    /**
     * Test setting and getting the train assigned to the route.
     */
    @Test
    public void testSetAndGetAssignedTrain() {
        route.setAssignedTrain(train);
        assertEquals(train, route.getAssignedTrain());
    }

    /**
     * Test setting and getting the name of the route.
     */
    @Test
    public void testSetAndGetName() {
        route.setName("TestRoute");
        assertEquals("TestRoute", route.getName());
    }

    /**
     * Test adding and retrieving points in the route.
     */
    @Test
    public void testAddAndGetPoints() {
        route.addPoint(point);
        List<PointOfRoute> points = route.getPoints();
        assertEquals(1, points.size());
        assertEquals(point, points.get(0));
    }

    /**
     * Test the string representation of the route.
     */
    @Test
    public void testToString() {
        route.setName("TestRoute");
        route.setAssignedTrain(train);
        route.addPoint(point);
        String output = route.toString();
        assertTrue(output.contains("TestRoute"));
        assertTrue(output.contains("Station: StationA"));
        assertTrue(output.contains("COAL"));
        assertTrue(output.contains("FULL"));
    }
}
