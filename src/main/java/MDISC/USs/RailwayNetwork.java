package MDISC.USs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RailwayNetwork {
    private final ArrayList<Station> stations = new ArrayList<>();
    private final ArrayList<Line> lines = new ArrayList<>();

    public void loadStations(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String header = reader.readLine();
        if (header == null) throw new IOException("Station CSV file is empty.");
        String[] stationNames = header.split(";");

        for (String name : stationNames) {
            Station station = new Station(name.trim());
            stations.add(station);
        }

        reader.close();
    }

    public void loadLines(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            if (parts.length < 4) continue;

            String nameA = parts[0].trim();
            String nameB = parts[1].trim();
            boolean electrified = parts[2].trim().equals("1");
            int distance = Integer.parseInt(parts[3].trim());

            Station stationA = findStationByName(nameA);
            Station stationB = findStationByName(nameB);
            if (stationA == null || stationB == null) continue;

            Line railLine = new Line(stationA, stationB, electrified, distance);
            lines.add(railLine);
        }

        reader.close();
    }

    private Station findStationByName(String name) {
        for (Station s : stations) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public boolean canTravel(String fromName, String toName, String trainType) {
        int n = stations.size();
        int[][] adjacency = new int[n][n];

        for (Line line : lines) {
            Station a = line.getStationA();
            Station b = line.getStationB();
            boolean useLine = trainType.equalsIgnoreCase("electric") ? line.isElectrified() :
                    trainType.equalsIgnoreCase("diesel") || trainType.equalsIgnoreCase("steam");
            if (useLine) {
                int i = stations.indexOf(a);
                int j = stations.indexOf(b);
                if (i >= 0 && j >= 0) {
                    adjacency[i][j] = 1;
                    adjacency[j][i] = 1;
                }
            }
        }

        int fromIndex = findStationIndex(fromName);
        int toIndex = findStationIndex(toName);
        if (fromIndex == -1 || toIndex == -1) return false;

        boolean[] visited = new boolean[n];
        visited[fromIndex] = true;
        boolean changed;

        do {
            changed = false;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    for (int j = 0; j < n; j++) {
                        if (adjacency[i][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            changed = true;
                        }
                    }
                }
            }
        } while (changed);

        return visited[toIndex];
    }

    private int findStationIndex(String name) {
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).getName().equalsIgnoreCase(name.trim())) return i;
        }
        return -1;
    }

    public boolean isTypeFullyConnected(String trainType, String stationTypeStr) {
        char stationType = stationTypeStr.toUpperCase().charAt(0);
        ArrayList<Station> filtered = new ArrayList<>();

        for (Station s : stations) {
            if (s.getType() == stationType) filtered.add(s);
        }

        int n = filtered.size();
        if (n <= 1) return true;

        int[][] adj = new int[n][n];

        for (Line l : lines) {
            Station a = l.getStationA();
            Station b = l.getStationB();
            if (a.getType() != stationType || b.getType() != stationType) continue;

            boolean useLine = trainType.equalsIgnoreCase("electric") ? l.isElectrified() :
                    trainType.equalsIgnoreCase("diesel") || trainType.equalsIgnoreCase("steam");
            if (!useLine) continue;

            int i = filtered.indexOf(a);
            int j = filtered.indexOf(b);
            if (i >= 0 && j >= 0) {
                adj[i][j] = 1;
                adj[j][i] = 1;
            }
        }

        boolean[] visited = new boolean[n];
        dfs(adj, 0, visited);

        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    public void findEulerianPath(boolean onlyElectrified) {
        ArrayList<Station> subStations = new ArrayList<>();
        ArrayList<Line> subLines = new ArrayList<>();

        for (Line l : lines) {
            if (onlyElectrified && !l.isElectrified()) continue;
            subLines.add(l);
            if (!subStations.contains(l.getStationA())) subStations.add(l.getStationA());
            if (!subStations.contains(l.getStationB())) subStations.add(l.getStationB());
        }

        int n = subStations.size();
        int[][] adj = new int[n][n];

        for (Line l : subLines) {
            int i = subStations.indexOf(l.getStationA());
            int j = subStations.indexOf(l.getStationB());
            adj[i][j]++;
            adj[j][i]++;
        }

        boolean[] visited = new boolean[n];
        dfs(adj, 0, visited);
        for (boolean v : visited) {
            if (!v) {
                System.out.println("O grafo não está conectado.");
                return;
            }
        }

        int oddCount = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            int degree = 0;
            for (int j = 0; j < n; j++) degree += adj[i][j];
            if (degree % 2 != 0) {
                oddCount++;
                start = i;
            }
        }

        if (oddCount != 0 && oddCount != 2) {
            System.out.println("O grafo não possui um caminho Euleriano.");
            return;
        }

        System.out.println("Caminho Euleriano possível. Calculando rota com Fleury:");
        List<String> route = new ArrayList<>();
        fleury(adj, subStations, start, route);
        for (int i = 0; i < route.size(); i++) {
            System.out.print(route.get(i));
            if (i != route.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }

    private void dfs(int[][] adj, int i, boolean[] visited) {
        visited[i] = true;
        for (int j = 0; j < adj.length; j++) {
            if (adj[i][j] > 0 && !visited[j]) dfs(adj, j, visited);
        }
    }

    private void fleury(int[][] adj, List<Station> stations, int u, List<String> route) {
        for (int v = 0; v < adj.length; v++) {
            if (adj[u][v] > 0) {
                adj[u][v]--;
                adj[v][u]--;
                fleury(adj, stations, v, route);
            }
        }
        route.add(0, stations.get(u).getName());
    }
}
