package MDISC.USs;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

public class tester {

    public static void main(String[] args) {
        List<Integer> sizes = Arrays.asList(50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 1050, 1100, 1150, 1200, 1250, 1300, 1350, 1400, 1450, 1500);

        try (FileWriter writer = new FileWriter("src/MATCP/data_us29.csv")) {
            writer.write("input_size,tempo_us13,tempo_us14\n");

            for (int size : sizes) {
                RailwayNetwork net = new RailwayNetwork();
                String prefix = "src/main/java/MDISC/USs/Datasets for US29-20250602/Scenario_" + size;
                try {
                    net.loadStations(prefix + "_stations.csv");
                    net.loadLines(prefix + "_lines.csv");
                } catch (IOException e) {
                    System.out.println("Erro a carregar cenário " + size + ": " + e.getMessage());
                    continue;
                }

                // US13 - verificar conectividade entre 1ª e última estação
                String startName = net.getStations().get(0).getName();
                String endName = net.getStations().get(net.getStations().size() - 1).getName();

                long t1 = System.nanoTime();
                net.isTypeFullyConnected("steam", "Station");
                long t2 = System.nanoTime();
                double tempoUS13 = (t2 - t1) / 1_000_000_000.0;

                // US14 - rota de manutenção
                long t3 = System.nanoTime();
                net.findEulerianPath(false);  // false → inclui todas as linhas
                long t4 = System.nanoTime();
                double tempoUS14 = (t4 - t3) / 1_000_000_000.0;

                // Escrever no ficheiro
                writer.write(size + "," + tempoUS13 + "," + tempoUS14 + "\n");
                System.out.printf("n = %d | US13: %.6fs | US14: %.6fs%n", size, tempoUS13, tempoUS14);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
