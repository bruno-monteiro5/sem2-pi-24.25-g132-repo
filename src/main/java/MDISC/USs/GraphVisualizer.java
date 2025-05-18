package MDISC.USs;

import guru.nidi.graphviz.attribute.Attributes;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkSource;

import java.io.File;
import java.io.IOException;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

public class GraphVisualizer {

    public static void visualize(RailwayNetwork network, boolean onlyElectrified, String outputFilePath) {
        Graph g = graph("railway"); // sem RankDir

        for (Line line : network.getLines()) {
            if (onlyElectrified && !line.isElectrified()) continue;

            String from = line.getStationA().getName();
            String to = line.getStationB().getName();
            String label = line.getDistance() + "km";
            Color color = line.isElectrified() ? Color.CYAN3 : Color.GRAY60;

            LinkSource edge = node(from).link(
                    to(node(to)).with(
                            Label.of(label),
                            color,
                            Attributes.attr("penwidth", "5")
                    )
            );
            g = g.with(edge);
        }

        try {
            Graphviz.fromGraph(g).render(Format.SVG).toFile(new File(outputFilePath));
            System.out.println("Graph saved to " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error saving graph: " + e.getMessage());
        }
    }
}
