
package MDISC.USs;

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
                    System.out.println("1 - Verificar se um comboio pode viajar entre duas estações");
                    System.out.println("2 - Verificar Conectividade");
                    System.out.println("3 - Fazer manutenção das linhas (geral/elétrica)");
                    System.out.println("4 - Gerar imagem do grafo (Graphviz)");
                    System.out.println("0 - Sair");
                    System.out.print("Escolha a opção: ");
                    opcao = scanner.nextInt();
                    scanner.nextLine(); // consumir \n

                    switch (opcao) {
                        case 1:
                            System.out.print("Estação origem: ");
                            String from = scanner.nextLine();
                            System.out.print("Estação destino: ");
                            String to = scanner.nextLine();
                            System.out.print("Tipo de comboio (steam/diesel/electric): ");
                            String tipo = scanner.nextLine();

                            boolean result = net.canTravel(from, to, tipo);
                            System.out.println("Resultado: " + (result ? "Pode viajar" : "Não pode viajar"));
                            break;

                        case 2:
                            System.out.print("Tipo de comboio (steam/diesel/electric): ");
                            String trainType = scanner.nextLine();

                            System.out.print("Tipo de estação (Depot/Station/Terminal): ");
                            String stationType = scanner.nextLine(); // Ex: "Station"

                            boolean connected = net.isTypeFullyConnected(trainType, stationType);

                            if (connected) {
                                System.out.println("Com o comboio " + trainType + ", todas as estações do tipo " + stationType + " estão conectadas entre si!");
                            } else {
                                System.out.println("Com o comboio " + trainType + ", nem todas as estações do tipo " + stationType + " estão conectadas entre si.");
                            }
                            break;

                        case 3:
                            System.out.print("Apenas eletrificadas? (s/n): ");
                            String resp = scanner.nextLine();
                            boolean eletricas = resp.equalsIgnoreCase("s");
                            net.checkMaintenanceRoute(eletricas);
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





