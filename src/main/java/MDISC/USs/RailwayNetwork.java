package MDISC.USs;

import java.io.File;
import java.io.IOException;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RailwayNetwork {
    private final ArrayList<Station> stations = new ArrayList<>();
    private final ArrayList<Line> lines = new ArrayList<>();
    private final Graph graph = new Graph();

    public void loadStations(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String header = reader.readLine();
        if (header == null) {
            throw new IOException("Station CSV file is empty.");
        }

        String[] stationNames = header.split(";");
        for (String name : stationNames) {
            name = name.trim();
            Station station = new Station(name);
            stations.add(station);
            graph.addStation(station);
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
            if (stationA == null || stationB == null) {
                System.out.println("Warning: station not found for line: " + nameA + " - " + nameB);
                continue;
            }

            Line railLine = new Line(stationA, stationB, electrified, distance);
            lines.add(railLine);
            graph.addLine(railLine);
        }

        reader.close();
    }

    private Station findStationByName(String name) {
        for (Station s : stations) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    public Graph getGraph() {
        return graph;
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

        // Construir matriz de adjacência com base no tipo de comboio
        for (Line line : lines) {
            Station a = line.getStationA();
            Station b = line.getStationB();

            boolean useLine;
            String type = trainType.toLowerCase();

            if (type.equals("electric")) {
                useLine = line.isElectrified();
            } else if (type.equals("diesel") || type.equals("steam")) {
                useLine = true;
            } else {
                useLine = false;
            }

            if (useLine) {
                int i = stations.indexOf(a);
                int j = stations.indexOf(b);
                if (i >= 0 && j >= 0) {
                    adjacency[i][j] = 1;
                    adjacency[j][i] = 1;
                }
            }
        }

        // Obter índices das estações de origem e destino
        int fromIndex = findStationIndex(fromName);
        int toIndex = findStationIndex(toName);

        if (fromIndex == -1 || toIndex == -1) return false;

        // Algoritmo de propagação simples (BFS-like)
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

    private int findStationIndex(String stationName) {
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).getName().equalsIgnoreCase(stationName.trim())) {
                return i;
            }
        }
        return -1;
    }

    public void checkMaintenanceRoute(boolean onlyElectrified) {
        ArrayList<Station> nodes = new ArrayList<>();
        ArrayList<Line> edges = new ArrayList<>();

        // Gerar subconjunto do grafo
        for (Line l : lines) {
            if (onlyElectrified && !l.isElectrified()) continue;
            edges.add(l);
            if (!nodes.contains(l.getStationA())) nodes.add(l.getStationA());
            if (!nodes.contains(l.getStationB())) nodes.add(l.getStationB());
        }

        // Construir matriz de adjacência
        int n = nodes.size();
        int[][] adj = new int[n][n];
        int[] degrees = new int[n];

        for (Line l : edges) {
            int i = nodes.indexOf(l.getStationA());
            int j = nodes.indexOf(l.getStationB());
            adj[i][j] = 1;
            adj[j][i] = 1;
            degrees[i]++;
            degrees[j]++;
        }

        // Verificar graus ímpares
        ArrayList<Integer> oddVertices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] % 2 != 0) oddVertices.add(i);
        }

        // Verificar conectividade por DFS
        boolean[] visited = new boolean[n];
        dfsForMaintenance(adj, 0, visited);

        boolean connected = true;
        for (boolean v : visited) {
            if (!v) {
                connected = false;
                break;
            }
        }

        if (!connected) {
            System.out.println("A rede não é conexa.");
            return;
        }

        if (oddVertices.size() == 0 || oddVertices.size() == 2) {
            System.out.println("Existe um passeio de manutenção possível.");
            if (oddVertices.size() == 2) {
                System.out.println("Pode começar em: " + nodes.get(oddVertices.get(0)) + " ou " + nodes.get(oddVertices.get(1)));
            } else {
                System.out.println("Pode começar em qualquer estação.");
            }
        } else {
            System.out.println("Não é possível fazer um passeio de manutenção (grau ímpar em mais de 2 vértices).");
        }
    }

    private void dfsForMaintenance(int[][] adj, int i, boolean[] visited) {
        visited[i] = true;
        for (int j = 0; j < adj.length; j++) {
            if (adj[i][j] == 1 && !visited[j]) {
                dfsForMaintenance(adj, j, visited);
            }
        }
    }

    public boolean isTypeFullyConnected(String trainType, String stationTypeString) {
        char stationType = stationTypeString.toUpperCase().charAt(0); // 'D', 'S', 'T'
        ArrayList<Station> filtered = new ArrayList<>();

        for (Station s : stations) {
            if (s.getType() == stationType) {
                filtered.add(s);
            }
        }

        int n = filtered.size();
        if (n <= 1) return true; // Trivial

        int[][] adj = new int[n][n];

        for (Line l : lines) {
            Station a = l.getStationA();
            Station b = l.getStationB();
            if (a.getType() != stationType || b.getType() != stationType) continue;

            boolean useLine;
            String type = trainType.toLowerCase();
            if (type.equals("electric")) {
                useLine = l.isElectrified();
            } else if (type.equals("diesel") || type.equals("steam")) {
                useLine = true;
            } else {
                useLine = false;
            }

            if (!useLine) continue;

            int i = filtered.indexOf(a);
            int j = filtered.indexOf(b);
            if (i >= 0 && j >= 0) {
                adj[i][j] = 1;
                adj[j][i] = 1;
            }
        }

        // DFS para verificar conectividade
        boolean[] visited = new boolean[n];
        dfsType(adj, 0, visited);

        for (boolean v : visited) {
            if (!v) return false;
        }

        return true;
    }

    private void dfsType(int[][] matrix, int i, boolean[] visited) {
        visited[i] = true;
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[i][j] == 1 && !visited[j]) {
                dfsType(matrix, j, visited);
            }
        }
    }

}

