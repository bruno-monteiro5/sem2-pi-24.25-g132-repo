package pt.ipp.isep.dei.domain;

import java.io.Serializable;


public class Simulator implements Serializable {

    private final Scenario scenario;
    private int currentYear;

    public Simulator(Scenario scenario) {
        this.scenario = scenario;
        this.currentYear = scenario.getPeriodTime().getStartYear();
    }

    public void simulateNextEvent() {
        System.out.println("\nSimulando ano: " + currentYear);

        generateCargoes();
        updateDepartures();
        updateArrivals();
        updateDemand();
        checkNewLocomotives();

        currentYear++;
    }

    private void generateCargoes() {
        System.out.println("Gerando cargas nas estações...");
        // Simula a criação de cargas com base nas estações e portos
    }

    private void updateDepartures() {
        System.out.println("Atualizando partidas de comboios...");
        // Simula comboios a saírem da estação
    }

    private void updateArrivals() {
        System.out.println("Atualizando chegadas de comboios...");
        // Simula comboios a chegarem e calcular receitas
    }

    private void updateDemand() {
        System.out.println("Atualizando procura nas estações...");
        // Simula aumento ou redução de procura
    }

    private void checkNewLocomotives() {
        System.out.println("Verificando desbloqueio de novas locomotivas...");
        // Ativa novas locomotivas de acordo com o ano atual
    }

    public int getCurrentYear() {
        return currentYear;
    }
}