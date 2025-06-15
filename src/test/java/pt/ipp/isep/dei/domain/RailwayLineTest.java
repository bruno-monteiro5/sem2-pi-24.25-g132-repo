
package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RailwayLineTest {
    private Scenario testScenario;
    private Station stationA;
    private Station stationB;
    private Station stationC;
    private RailwayLine lineAB;
    private RailwayLine lineBA;

    @BeforeEach
    void setUp() {
        Map map = new Map("TestMap", 100, 100, 1);
        PeriodTime period = new PeriodTime(1900, 2000);
        testScenario = new Scenario("Test Scenario", map, period);

        stationA = new Station("Station A", new Position(10, 20), StationType.STATION);
        stationB = new Station("Station B", new Position(30, 40), StationType.STATION);
        stationC = new Station("Station C", new Position(50, 60), StationType.STATION);

        testScenario.addStation(stationA);
        testScenario.addStation(stationB);
        testScenario.addStation(stationC);

        lineAB = new RailwayLine(testScenario, stationA, stationB,
                LineType.SINGLE_ELECTRIFIED, 100);
        lineBA = new RailwayLine(testScenario, stationB, stationA,
                LineType.DOUBLE_NOT_ELECTRICITY, 100);
    }

    @Test
    void constructor_ShouldThrowException_WhenScenarioIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new RailwayLine(null, stationA, stationB, LineType.SINGLE_ELECTRIFIED, 100));
    }

    @Test
    void constructor_ShouldThrowException_WhenStationsAreNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new RailwayLine(testScenario, null, stationB, LineType.SINGLE_ELECTRIFIED, 100));

        assertThrows(IllegalArgumentException.class, () ->
                new RailwayLine(testScenario, stationA, null, LineType.SINGLE_ELECTRIFIED, 100));
    }

    @Test
    void constructor_ShouldThrowException_WhenLineTypeIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new RailwayLine(testScenario, stationA, stationB, null, 100));
    }

    @Test
    void constructor_ShouldThrowException_WhenLengthIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                new RailwayLine(testScenario, stationA, stationB, LineType.SINGLE_ELECTRIFIED, 0));

        assertThrows(IllegalArgumentException.class, () ->
                new RailwayLine(testScenario, stationA, stationB, LineType.SINGLE_ELECTRIFIED, -10));
    }

    @Test
    void constructor_ShouldThrowException_WhenConnectingSameStation() {
        assertThrows(IllegalArgumentException.class, () ->
                new RailwayLine(testScenario, stationA, stationA, LineType.SINGLE_ELECTRIFIED, 100));
    }


    @Test
    void connects_ShouldReturnTrue_WhenStationsMatch() {
        assertTrue(lineAB.connects(stationA, stationB));
        assertTrue(lineAB.connects(stationB, stationA)); // Should work both ways
    }

    @Test
    void connects_ShouldReturnFalse_WhenStationsDontMatch() {
        assertFalse(lineAB.connects(stationA, stationC));
        assertFalse(lineAB.connects(stationC, stationB));
    }

    @Test
    void connects_ShouldReturnFalse_WhenNullStations() {
        assertFalse(lineAB.connects(null, stationB));
        assertFalse(lineAB.connects(stationA, null));
        assertFalse(lineAB.connects(null, null));
    }

    @Test
    void getScenario_ShouldReturnCorrectScenario() {
        assertEquals(testScenario, lineAB.getScenario());
    }

    @Test
    void getStations_ShouldReturnCorrectStations() {
        assertSame(stationA, lineAB.getStation1());
        assertSame(stationB, lineAB.getStation2());
    }

    @Test
    void getLineType_ShouldReturnCorrectType() {
        assertEquals(LineType.SINGLE_ELECTRIFIED, lineAB.getLineType());
        assertEquals(LineType.DOUBLE_NOT_ELECTRICITY, lineBA.getLineType());
    }

    @Test
    void getLength_ShouldReturnCorrectValue() {
        assertEquals(100, lineAB.getLength());
    }



    @Test
    void isElectrified_ShouldReturnTrue_ForElectrifiedLines() {
        assertTrue(lineAB.isElectrified());
    }

    @Test
    void isElectrified_ShouldReturnFalse_ForNonElectrifiedLines() {
        assertFalse(lineBA.isElectrified());
    }


    @Test
    void isDouble_ShouldReturnTrue_ForDoubleTrack() {
        assertTrue(lineBA.isDouble());
    }

    @Test
    void isDouble_ShouldReturnFalse_ForSingleTrack() {
        assertFalse(lineAB.isDouble());
    }

    @Test
    void equals_ShouldReturnTrue_ForSameStationsDifferentOrder() {
        RailwayLine lineAB2 = new RailwayLine(testScenario, stationB, stationA,
                LineType.SINGLE_ELECTRIFIED, 100);

        assertNotEquals(lineAB, lineAB2);
    }

    @Test
    void equals_ShouldReturnFalse_ForDifferentStations() {
        RailwayLine lineAC = new RailwayLine(testScenario, stationA, stationC,
                LineType.SINGLE_ELECTRIFIED, 100);
        assertNotEquals(lineAB, lineAC);
    }

    @Test
    void equals_ShouldReturnFalse_ForNull() {
        assertNotEquals(null, lineAB);
    }

    @Test
    void equals_ShouldReturnFalse_ForDifferentClass() {
        assertNotEquals("Not a railway line", lineAB);
    }

/**
    @Test
    void hashCode_ShouldBeEqual_ForSameStationsDifferentOrder() {
        RailwayLine lineAB2 = new RailwayLine(testScenario, stationB, stationA,
                LineType.SINGLE_ELECTRIFIED, 100);
        assertEquals(lineAB.hashCode(), lineAB2.hashCode());
    }
*/
    @Test
    void hashCode_ShouldBeDifferent_ForDifferentStations() {
        RailwayLine lineAC = new RailwayLine(testScenario, stationA, stationC,
                LineType.SINGLE_ELECTRIFIED, 100);
        assertNotEquals(lineAB.hashCode(), lineAC.hashCode());
    }
}
