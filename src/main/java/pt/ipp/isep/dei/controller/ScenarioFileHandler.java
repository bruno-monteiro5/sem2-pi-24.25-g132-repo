package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Scenario;

import java.io.*;

public class ScenarioFileHandler {

    public static void saveScenarioToFile(Scenario scenario, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(scenario);
        }
    }

    public static Scenario loadScenarioFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Scenario) ois.readObject();
        }
    }

}
