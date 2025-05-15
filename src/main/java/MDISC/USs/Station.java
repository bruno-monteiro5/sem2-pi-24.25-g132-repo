package MDISC.USs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Station {
    private final String name;
    private final char type; // 'D' = Depot, 'S' = Station, 'T' = Terminal

    public Station(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Station name cannot be null or empty.");
        }

        char initial = Character.toUpperCase(name.charAt(0));
        if (initial != 'D' && initial != 'S' && initial != 'T') {
            throw new IllegalArgumentException("Invalid station type. Name must start with D, S or T.");
        }

        this.name = name;
        this.type = initial;
    }

    public String getName() {
        return name;
    }

    public char getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
