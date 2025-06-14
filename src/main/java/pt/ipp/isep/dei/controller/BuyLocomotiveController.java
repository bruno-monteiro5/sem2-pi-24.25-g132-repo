package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.LocomotiveType;
import pt.ipp.isep.dei.domain.Player;
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
        Player player = Repositories.getInstance().getPlayerRepository().getCurrentPlayer(); // ou via Repositories se corrigido

        if (!player.canAfford(type.getPrice())) {
            throw new IllegalArgumentException("Player does not have enough funds to buy this locomotive.");
        }

        player.deductFunds(type.getPrice()); // ← aqui é onde o budget é atualizado

        Locomotive locomotive = new Locomotive(type);
        locomotiveRepository.save(locomotive);
        return locomotive;
    }

}
