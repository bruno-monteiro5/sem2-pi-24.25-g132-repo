package pt.ipp.isep.dei.ui.console.menu;

import pt.ipp.isep.dei.domain.Player;
import pt.ipp.isep.dei.repository.PlayerRepository;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.ui.console.*;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class PlayerUI implements Runnable {

    public PlayerUI() {}

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Build Station", new BuildStationUI()));
        options.add(new MenuItem("Upgrade Station", new UpgradeStationUI()));
        options.add(new MenuItem("See the details of a station", new StationDetailsUI()));
        options.add(new MenuItem("Build a railway line between two stations", new BuildRailwayLineUI()));
        options.add(new MenuItem("Buy a locomotive", new BuyLocomotiveUI()));
        options.add(new MenuItem("Create a train", new CreateTrainUI()));
        options.add(new MenuItem("Assign a train to a route", new AssignTrainToRouteUI()));
        options.add(new MenuItem("List all trains", new ListTrainUI()));
        options.add(new MenuItem("Simulator", new SimulatorUI()));
        //options.add(new MenuItem("See Year Financial Results", new AnnualFinancialResultsUI()));

        int option = 0;
        do {

            Player player = Repositories.getInstance().getPlayerRepository().getCurrentPlayer();
            System.out.print("Budget: " + player.getBalance());

            option = Utils.showAndSelectIndex(options, "\n\n--- PLAYER MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}