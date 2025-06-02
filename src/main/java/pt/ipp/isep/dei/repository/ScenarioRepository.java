package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Scenario;

import java.io.*;
import java.util.ArrayList;

public class ScenarioRepository {

    private ArrayList<Scenario> scenarios = new ArrayList<>();
    private static final String FILE_NAME = "scenarios.ser";
    private Scenario scenarioBeingUsed;

    public ScenarioRepository() {
        loadFromFile();
    }

    public void add(Scenario scenario) {
        scenarios.add(scenario);
        saveToFile();
    }

    public ArrayList<Scenario> getAll() {
        return scenarios;
    }

    public void setActiveScenario(Scenario scenario) {
        scenarioBeingUsed = scenario;
        saveToFile();
    }

    public Scenario getActiveScenario() {
        return scenarioBeingUsed;
    }

    private void saveToFile() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(scenarios);
            out.writeObject(scenarioBeingUsed); // guardar o cenário ativo
            out.close();
        } catch (IOException e) {
            System.out.println("Erro ao guardar cenários: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            scenarios = (ArrayList<Scenario>) in.readObject();
            scenarioBeingUsed = (Scenario) in.readObject(); // restaurar o cenário ativo
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            scenarios = new ArrayList<>();
            scenarioBeingUsed = null;
        }
    }
}
