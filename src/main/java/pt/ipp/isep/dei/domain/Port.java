package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a port in the simulation scenario.
 * <p>
 * Ports can import, export, and transform specific types of cargo.
 */
public class Port implements Serializable {

    /**
     * The name of the port.
     */
    private String name;

    /**
     * List of cargo types that this port imports.
     */
    private ArrayList<String> imports;

    /**
     * List of cargo types that this port exports.
     */
    private ArrayList<String> exports;

    /**
     * List of cargo types that this port can transform.
     */
    private ArrayList<String> transforms;

    /**
     * Constructs a port with the given name.
     *
     * @param name the name of the port
     */
    public Port(String name) {
        this.name = name;
        this.imports = new ArrayList<>();
        this.exports = new ArrayList<>();
        this.transforms = new ArrayList<>();
    }

    /**
     * Gets the name of the port.
     *
     * @return the port name
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a cargo type to the list of imports.
     *
     * @param cargo the cargo type to import
     */
    public void addImport(String cargo) {
        imports.add(cargo);
    }

    /**
     * Adds a cargo type to the list of exports.
     *
     * @param cargo the cargo type to export
     */
    public void addExport(String cargo) {
        exports.add(cargo);
    }

    /**
     * Adds a cargo type to the list of transformations.
     *
     * @param cargo the cargo type to transform
     */
    public void addTransform(String cargo) {
        transforms.add(cargo);
    }

    /**
     * Gets the list of cargo types that this port imports.
     *
     * @return list of imported cargo types
     */
    public ArrayList<String> getImports() {
        return imports;
    }

    /**
     * Gets the list of cargo types that this port exports.
     *
     * @return list of exported cargo types
     */
    public ArrayList<String> getExports() {
        return exports;
    }

    /**
     * Gets the list of cargo types that this port transforms.
     *
     * @return list of transformed cargo types
     */
    public ArrayList<String> getTransforms() {
        return transforms;
    }

    /**
     * Returns a string representation of the port.
     *
     * @return formatted string with port name and cargo operations
     */
    @Override
    public String toString() {
        return String.format("Port{name='%s', imports=%s, exports=%s, transforms=%s}",
                name, imports, exports, transforms);
    }
}
