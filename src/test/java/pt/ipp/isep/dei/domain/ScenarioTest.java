
package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ScenarioTest {

    @Test
    public void testScenarioCreation() {
        Map map = new Map("mapaTeste", 10, 10);
        Scenario scenario = new Scenario("cenarioTeste", map);

        assertEquals("cenarioTeste", scenario.getName());
        assertEquals(map, scenario.getMap());
        assertEquals(0, scenario.getPorts().size());
        assertEquals(0, scenario.getAvailableLocomotiveTypes().size());
        assertEquals(1.0, scenario.getIndustryGenerationFactor());
    }

    @Test
    public void testAddPort() {
        Scenario scenario = new Scenario("cenario", new Map("m", 5, 5));
        Port port = new Port("porto1");

        port.addImport("carvão");
        port.addExport("aço");
        port.addTransform("minério");

        scenario.addPort(port);

        ArrayList<Port> ports = scenario.getPorts();
        assertEquals(1, ports.size());
        assertEquals("porto1", ports.get(0).getName());
        assertTrue(ports.get(0).getImports().contains("carvão"));
        assertTrue(ports.get(0).getExports().contains("aço"));
        assertTrue(ports.get(0).getTransforms().contains("minério"));
    }

    @Test
    public void testAddLocomotiveType() {
        Scenario scenario = new Scenario("cenario", new Map("m", 5, 5));
        scenario.addLocomotiveType(LocomotiveType.STEAM);
        scenario.addLocomotiveType(LocomotiveType.DIESEL);

        assertEquals(2, scenario.getAvailableLocomotiveTypes().size());
        assertTrue(scenario.getAvailableLocomotiveTypes().contains(LocomotiveType.STEAM));
        assertTrue(scenario.getAvailableLocomotiveTypes().contains(LocomotiveType.DIESEL));
    }

    @Test
    public void testSetIndustryGenerationFactor() {
        Scenario scenario = new Scenario("cenario", new Map("m", 5, 5));
        scenario.setIndustryGenerationFactor(2.5);

        assertEquals(2.5, scenario.getIndustryGenerationFactor());
    }
}
