
package MDISC.USs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            try {
                RailwayNetwork net = new RailwayNetwork();
                net.loadStations("/Users/brunomac/IdeaProjects/sem2-pi-24.25-g132-repo/src/main/java/MDISC/USs/CSV/scenario1_stations.csv");
                net.loadLines("/Users/brunomac/IdeaProjects/sem2-pi-24.25-g132-repo/src/main/java/MDISC/USs/CSV/scenario1_lines.csv");
                Scanner scanner = new Scanner(System.in);
                int opcao;

                do {
                    System.out.println("\n--- MENU MDISC ---");
                    System.out.println("1 - Verify if a train can travel between two stations (US13)");
                    System.out.println("2 - Verify if a train can travel from a station to another of the same type (US13)");
                    System.out.println("3 - Maintenance of the lines(general/electric) (US14)");
                    System.out.println("4 - Generate graph (Graphviz)");
                    System.out.println("5 - Find shortest path (US27)");
                    System.out.println("0 - Leave");
                    System.out.print("Choose an option: ");
                    opcao = scanner.nextInt();
                    scanner.nextLine(); // consumir \n

                    switch (opcao) {
                        case 1:
                            System.out.println("Available stations:");
                            for (Station s : net.getStations()) {
                                System.out.println("- " + s.getName());
                            }
                            System.out.print("Origin Station: ");
                            String from = scanner.nextLine();
                            System.out.print("Destination Station: ");
                            String to = scanner.nextLine();
                            System.out.print("Type of train (steam/diesel/electric): ");
                            String tipo = scanner.nextLine();

                            boolean result = net.canTravel(from, to, tipo);
                            System.out.println("Result: " + (result ? "Can travel" : "Can't travel"));
                            break;

                        case 2:
                            System.out.print("Type of train (steam/diesel/electric): ");
                            String trainType = scanner.nextLine();

                            System.out.print("Type of station (Depot/Station/Terminal): ");
                            String stationType = scanner.nextLine(); // Ex: "Station"

                            boolean connected = net.isTypeFullyConnected(trainType, stationType);

                            if (connected) {
                                System.out.println("With " + trainType + " you CAN travel from a " + stationType + " to another of the same type");
                            } else {
                                System.out.println("With " + trainType + " you CAN'T travel from a " + stationType + " to another of the same type");
                            }
                            break;

                        case 3:
                            System.out.print("Type of maintenance?\n1 - General\n2 - Electric\nOption: ");
                            boolean onlyElectrified = false;
                            int lineType = scanner.nextInt();
                            if (lineType != 1 && lineType != 2) {
                                System.out.print("Enter a valid option (1 or 2):");
                                lineType = scanner.nextInt();
                            }else if (lineType == 1){
                                onlyElectrified = false;
                            }else if (lineType == 2){
                                onlyElectrified = true;
                            }

                            net.findEulerianPath(onlyElectrified);
                            break;


                        case 4:
                            GraphVisualizer.visualize(net, false, "grafo.svg");
                            break;

                        case 5:
                            List<Station> route = null;
                            Station start = null;
                            Station end = null;
                            List<Station> checkpoints = new ArrayList<>();

                            System.out.print("Insert path manually (M) or use CSV file (F)? ");
                            String method = scanner.nextLine().trim().toUpperCase();

                            if (method.equals("F")) {
                                System.out.print("Enter CSV file path: ");
                                String filePath = scanner.nextLine().trim();

                                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                                    String line = reader.readLine();
                                    if (line != null) {
                                        String[] stationNames = line.split(";");
                                        if (stationNames.length >= 2) {
                                            start = net.findStationByName(stationNames[0].trim());
                                            end = net.findStationByName(stationNames[stationNames.length - 1].trim());

                                            for (int i = 1; i < stationNames.length - 1; i++) {
                                                Station s = net.findStationByName(stationNames[i].trim());
                                                if (s != null) {
                                                    checkpoints.add(s);
                                                } else {
                                                    System.out.println("Warning: checkpoint '" + stationNames[i] + "' not found and will be ignored.");
                                                }
                                            }
                                        } else {
                                            System.out.println("Error: the CSV must contain at least origin and destination.");
                                        }
                                    }
                                } catch (IOException e) {
                                    System.out.println("Error reading file: " + e.getMessage());
                                }

                            } else {
                                System.out.print("Type the origin station: ");
                                String originStation = scanner.nextLine().trim();
                                start = net.findStationByName(originStation);
                                while (start == null) {
                                    System.out.println("Invalid station. Try again:");
                                    originStation = scanner.nextLine().trim();
                                    start = net.findStationByName(originStation);
                                }

                                System.out.print("Type the final station: ");
                                String finalStation = scanner.nextLine().trim();
                                end = net.findStationByName(finalStation);
                                while (end == null) {
                                    System.out.println("Invalid station. Try again:");
                                    finalStation = scanner.nextLine().trim();
                                    end = net.findStationByName(finalStation);
                                }

                                System.out.println("Type the intermediary stations (press ENTER to finish):");
                                while (true) {
                                    String station = scanner.nextLine().trim();
                                    if (station.isEmpty()) break;

                                    Station s = net.findStationByName(station);
                                    while (s == null) {
                                        System.out.println("Invalid station. Try again:");
                                        station = scanner.nextLine().trim();
                                        s = net.findStationByName(station);
                                    }
                                    checkpoints.add(s);
                                }
                            }

                            if (start != null && end != null) {
                                route = net.getShortestRouteThrough(start, checkpoints, end);
                            }

                            if (route == null) {
                                System.out.println("No route found with the given checkpoints.");
                            } else {
                                System.out.println("Shortest route found:");
                                int i = 1;
                                for (Station s : route) {
                                    System.out.println(i + " - " + s.getName());
                                    i++;
                                }

                                int totalDistance = 0;
                                for (int j = 0; j < route.size() - 1; j++) {
                                    Station current = route.get(j);
                                    Station next = route.get(j + 1);

                                    for (Line l : net.getLines()) {
                                        boolean match = (l.getStationA().equals(current) && l.getStationB().equals(next)) ||
                                                (l.getStationA().equals(next) && l.getStationB().equals(current));
                                        if (match) {
                                            totalDistance += l.getLength();
                                            break;
                                        }
                                    }
                                }

                                System.out.println("Total distance: " + totalDistance + " km");

                                System.out.println("Generating graph with route...");
                                GraphVisualizer.visualizeWithRoute(net, route, "grafo_route.svg");
                            }
                            break;



                        case 0:
                            System.out.println("Leaving...");
                            break;

                        default:
                            System.out.println("Invalid option");
                            break;
                    }

                } while (opcao != 0);

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
}





