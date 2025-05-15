
package MDISC.USs;

public class Main {
    public static void main(String[] args) {
        try {
            RailwayNetwork network = new RailwayNetwork();
            network.loadFromCSV("/Users/brunomac/IdeaProjects/sem2-pi-24.25-g132-repo/src/main/java/MDISC/USs/CSV/scenario1_lines.csv");

            GraphVisualizer.exportNetwork(network, true, "electrifiedNetwork.png");
            GraphVisualizer.exportNetwork(network, false, "fullNetwork.png");


            System.out.println("Graph exported successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
