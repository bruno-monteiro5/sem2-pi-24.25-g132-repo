package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Port implements Serializable {

    private String name;
    private ArrayList<String> imports;
    private ArrayList<String> exports;
    private ArrayList<String> transforms;

    public Port(String name) {
        this.name = name;
        this.imports = new ArrayList<>();
        this.exports = new ArrayList<>();
        this.transforms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addImport(String cargo) {
        imports.add(cargo);
    }

    public void addExport(String cargo) {
        exports.add(cargo);
    }

    public void addTransform(String cargo) {
        transforms.add(cargo);
    }

    public ArrayList<String> getImports() {
        return imports;
    }

    public ArrayList<String> getExports() {
        return exports;
    }

    public ArrayList<String> getTransforms() {
        return transforms;
    }
}