
package MDISC.USs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            try {
                RailwayNetwork net = new RailwayNetwork();
                net.loadStations("src/main/java/MDISC/USs/CSV/scenario1_stations.csv");
                net.loadLines("src/main/java/MDISC/USs/CSV/scenario1_lines.csv");
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
                                System.out.printf("Enter a valid option (1 or 2):");
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
                            System.out.print("Type the origin station: ");
                            String originStation = scanner.nextLine();
                            Station start = net.findStationByName(originStation);
                            while (start == null) {
                                System.out.println("Invalid Station");
                                System.out.print("Type the origin station: ");
                                originStation = scanner.nextLine();
                                start = net.findStationByName(originStation);
                            }

                            System.out.print("Type the final station: ");
                            String finalStation = scanner.nextLine();
                            Station end = net.findStationByName(finalStation);
                            while (end == null) {
                                System.out.println("Invalid Station");
                                System.out.print("Type the final station: ");
                                finalStation = scanner.nextLine();
                                end = net.findStationByName(finalStation);
                            }

                            List<Station> checkpoints = new ArrayList<>();
                            System.out.print("Choose intermediary stations (enter null to end):");
                            while (true){
                                String station = scanner.nextLine();

                                if (station.isEmpty()) {
                                    break;
                                }

                                Station s = net.findStationByName(station);
                                while (s == null) {
                                    System.out.println("Invalid Station");
                                    station = scanner.nextLine();
                                    s = net.findStationByName(station);
                                }

                                checkpoints.add(s);
                            }

                            List<Station> route = net.getShortestRouteThrough(start, checkpoints, end);
                            if (route == null) {
                                System.out.println("No route found");
                            } else {
                                System.out.println("Shortest route found:");
                                int i = 1;
                                for (Station s : route) {
                                    System.out.println( i + "-" + s.getName());
                                    i ++;
                                }
                                System.out.println("Generating graph with route...");
                                GraphVisualizer.visualizeWithRoute(net, route, "grafo_route.svg");
                            }


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





