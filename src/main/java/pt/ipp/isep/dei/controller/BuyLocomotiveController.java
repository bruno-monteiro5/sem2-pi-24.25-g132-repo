package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.LocomotiveType;
import pt.ipp.isep.dei.domain.Player;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.repository.LocomotiveRepository;
import pt.ipp.isep.dei.repository.Repositories;

public class BuyLocomotiveController {

    private final LocomotiveRepository locomotiveRepository;

    public BuyLocomotiveController() {
        this.locomotiveRepository = Repositories.getInstance().getLocomotiveRepository();
    }

    /**
     * Creates and saves a new locomotive with a unique ID and selected type.
     *
     * @param type The selected locomotive type
     * @return The created Locomotive
     */
    public Locomotive buyLocomotive(LocomotiveType type) {
        Locomotive locomotive = new Locomotive(type);

        Player player = Repositories.getInstance().getPlayerRepository().getCurrentPlayer();
        Scenario scenario = Repositories.getInstance().getScenarioRepository().getActiveScenario();

        if (player == null || scenario == null) {
            throw new IllegalStateException("Missing active player or scenario");
        }

        if (!player.canAfford(type.getPrice())) {
            throw new IllegalStateException("Insufficient funds");
        }

        player.deductFunds(type.getPrice()); //is here where the budget is updated

        scenario.getLocomotives().add(locomotive);

        return locomotive;
    }

}
