package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.LocomotiveType;
import pt.ipp.isep.dei.domain.Player;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.repository.LocomotiveRepository;
import pt.ipp.isep.dei.repository.Repositories;

/**
 * Controller responsável pela compra de locomotivas por parte do jogador.
 * <p>
 * Verifica se o jogador tem saldo suficiente, deduz o custo da locomotiva
 * e adiciona a locomotiva ao cenário ativo.
 */
public class BuyLocomotiveController {

    /** Repositório de locomotivas onde são guardadas as criadas. */
    private final LocomotiveRepository locomotiveRepository;

    /**
     * Construtor por defeito. Obtém o repositório a partir dos singletons.
     */
    public BuyLocomotiveController() {
        this.locomotiveRepository = Repositories.getInstance().getLocomotiveRepository();
    }

    /**
     * Cria e adiciona uma nova locomotiva do tipo selecionado ao cenário ativo.
     * <p>
     * Verifica se o jogador e o cenário estão disponíveis e se o jogador tem saldo suficiente.
     *
     * @param type o tipo de locomotiva selecionado
     * @return a nova instância de {@link Locomotive}
     * @throws IllegalStateException se não houver jogador/cenário ativos ou saldo insuficiente
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
