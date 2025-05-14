package MDISC.USs;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.*;
import guru.nidi.graphviz.model.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static guru.nidi.graphviz.model.Factory.*;

public class GraphVisualizer {

    public static void exportNetwork(RailwayNetwork network, boolean onlyElectrified, String outputFilePath) throws IOException {
        MutableGraph graph = mutGraph("Railway").setDirected(false);

        Set<String> addedEdges = new HashSet<>();

        for (Station station : network.getAllStations()) {
            String from = station.getName();
            graph.add(mutNode(from).add(Shape.CIRCLE, Style.FILLED, Color.rgb("DDDDDD")));

            for (Connection conn : station.getConnections()) {
                String to = conn.getDestination().getName();
                boolean electrified = conn.isElectrified();

                String edgeId = from + "--" + to;
                String reverseEdgeId = to + "--" + from;

                if (addedEdges.contains(edgeId) || addedEdges.contains(reverseEdgeId)) continue;
                if (onlyElectrified && !electrified) continue;

                graph.add(
                        mutNode(from).addLink(
                                to(mutNode(to)).with(electrified ? Color.BLUE : Color.GRAY)
                        )
                );

                addedEdges.add(edgeId);
            }
        }

        Graphviz.fromGraph(graph).render(Format.PNG).toFile(new File(outputFilePath));
    }
}
