package MDISC.USs;

import java.io.File;
import java.io.IOException;
import java.util.*;

class RailwayNetwork {
    private List<Station> stations = new ArrayList<>();

    public void loadFromCSV(String filePath) throws IOException {
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length < 4) continue;

            String nameA = parts[0].trim();
            String nameB = parts[1].trim();
            boolean electrified = parts[2].trim().equals("1");

            Station stationA = getOrCreateStation(nameA);
            Station stationB = getOrCreateStation(nameB);

            stationA.addConnection(new Connection(stationB, electrified));
            stationB.addConnection(new Connection(stationA, electrified));
        }
        scanner.close();
    }

    private Station getOrCreateStation(String name) {
        for (Station station : stations) {
            if (station.getName().equals(name)) {
                return station;
            }
        }
        Station newStation = new Station(name, getStationType(name));
        stations.add(newStation);
        return newStation;
    }

    private StationType getStationType(String name) {
        switch (name.charAt(0)) {
            case 'D': return StationType.DEPOT;
            case 'S': return StationType.STATION;
            case 'T': return StationType.TERMINAL;
            default: throw new IllegalArgumentException("Invalid station name: " + name);
        }
    }

    public Station getStation(String name) {
        for (Station station : stations) {
            if (station.getName().equals(name)) {
                return station;
            }
        }
        return null;
    }

    public List<Station> getAllStations() {
        return stations;
    }

    public boolean isConnected(Station from, Station to, boolean onlyElectrified) {
        Set<Station> visited = new HashSet<>();
        return dfs(from, to, onlyElectrified, visited);
    }

    private boolean dfs(Station current, Station target, boolean onlyElectrified, Set<Station> visited) {
        if (target != null && current.equals(target)) return true;
        visited.add(current);
        for (Connection conn : current.getConnections()) {
            Station next = conn.getDestination();
            if (!visited.contains(next) && (!onlyElectrified || conn.isElectrified())) {
                if (dfs(next, target, onlyElectrified, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Station> getStationsWithOddDegree(boolean onlyElectrified) {
        List<Station> result = new ArrayList<>();
        for (Station station : stations) {
            if (station.getDegree(onlyElectrified) % 2 != 0) {
                result.add(station);
            }
        }
        return result;
    }

    public boolean isEulerianTrailPossible(boolean onlyElectrified) {
        List<Station> oddDegreeStations = getStationsWithOddDegree(onlyElectrified);
        if (oddDegreeStations.size() != 0 && oddDegreeStations.size() != 2) return false;

        Station start = oddDegreeStations.isEmpty() ? stations.get(0) : oddDegreeStations.get(0);
        Set<Station> visited = new HashSet<>();
        dfs(start, null, onlyElectrified, visited);

        return visited.size() == stations.size();
    }

    public List<Station> getPossibleStartStations(boolean onlyElectrified) {
        List<Station> oddStations = getStationsWithOddDegree(onlyElectrified);
        return oddStations.isEmpty() ? new ArrayList<>(stations) : oddStations;
    }
}

