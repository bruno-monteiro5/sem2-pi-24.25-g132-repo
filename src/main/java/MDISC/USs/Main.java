
package MDISC.USs;

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
                                System.out.println("With" + trainType + "you CAN travel from a" + stationType + "to another of the same type");
                            } else {
                                System.out.println("with" + trainType + "you CAN'T travel from a " + stationType + "to another of the same type");
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

                        case 0:
                            System.out.println("A sair...");
                            break;

                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }

                } while (opcao != 0);

            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
}





