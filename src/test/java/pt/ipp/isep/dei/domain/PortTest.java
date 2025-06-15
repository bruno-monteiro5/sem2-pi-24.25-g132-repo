package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Port} class.
 *
 * These tests validate correct creation, name retrieval,
 * and management of imported, exported, and transformed cargo types.
 */
public class PortTest {

    /**
     * Tests the constructor and getName method.
     */
    @Test
    public void testConstructorAndGetName() {
        Port port = new Port("Lisbon");

        assertEquals("Lisbon", port.getName());
    }

    /**
     * Tests adding imports and retrieving them.
     */
    @Test
    public void testAddImport() {
        Port port = new Port("ImportPort");
        port.addImport("Coal");
        port.addImport("Iron");

        ArrayList<String> imports = port.getImports();
        assertEquals(2, imports.size());
        assertTrue(imports.contains("Coal"));
        assertTrue(imports.contains("Iron"));
    }

    /**
     * Tests adding exports and retrieving them.
     */
    @Test
    public void testAddExport() {
        Port port = new Port("ExportPort");
        port.addExport("Steel");
        port.addExport("Food");

        ArrayList<String> exports = port.getExports();
        assertEquals(2, exports.size());
        assertTrue(exports.contains("Steel"));
        assertTrue(exports.contains("Food"));
    }

    /**
     * Tests adding transformed cargo and retrieving them.
     */
    @Test
    public void testAddTransform() {
        Port port = new Port("TransformPort");
        port.addTransform("Grain");
        port.addTransform("Sugar");

        ArrayList<String> transforms = port.getTransforms();
        assertEquals(2, transforms.size());
        assertTrue(transforms.contains("Grain"));
        assertTrue(transforms.contains("Sugar"));
    }

    /**
     * Tests that the import/export/transform lists are initialized empty.
     */
    @Test
    public void testInitialListsAreEmpty() {
        Port port = new Port("EmptyPort");

        assertTrue(port.getImports().isEmpty());
        assertTrue(port.getExports().isEmpty());
        assertTrue(port.getTransforms().isEmpty());
    }
}
