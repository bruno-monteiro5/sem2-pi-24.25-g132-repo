package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Map;

import java.io.*;

public class MapFileHandler {

    public static void saveMapToFile(Map map, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(map);
        }
    }

    public static Map loadMapFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Map) ois.readObject();
        }
    }

}
