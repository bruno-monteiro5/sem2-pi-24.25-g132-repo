package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.*;

public class AnnualFinancialResultsController {
    private final SimulatorController simulatorController;
    private final CargoDeliveryRepository cargoDeliveryRepo;
    private final TrainRepository trainRepo;
    private final RailwayLineRepository lineRepo;

    public AnnualFinancialResultsController() {
        Repositories repositories = Repositories.getInstance();
        this.simulatorController = new SimulatorController();
        this.cargoDeliveryRepo = repositories.getCargoDeliveryRepository();
        this.trainRepo = repositories.getTrainRepository();
        this.lineRepo = repositories.getRailwayLineRepository();
    }

    public AnnualFinancialResults generateAnnualReport() {
        int currentYear = simulatorController.getCurrentYear();

        double revenue = calculateTotalRevenue(currentYear);
        double trackCost = calculateTrackMaintenance();
        double trainCost = calculateTrainMaintenance();
        double fuelCost = calculateFuelCost(currentYear);

        resetAnnualData(); // Limpa dados para o próximo ano

        return new AnnualFinancialResults(currentYear, revenue, trackCost, trainCost, fuelCost);
    }

    private double calculateTotalRevenue(int year) {
        return cargoDeliveryRepo.getDeliveriesByYear(year).stream()
                .mapToDouble(delivery -> {
                    Station station = cargoDeliveryRepo.getStationForDelivery(delivery);
                    int demand = station.getDemandFor(delivery.getType().name());
                    double baseValue = getCargoBaseValue(delivery.getType());
                    return baseValue * (1 + 0.2 * (demand - 5)) * delivery.getCount();
                })
                .sum();
    }

    private double calculateTrackMaintenance() {
        return lineRepo.getAll().stream()
                .mapToDouble(line -> line.getLength() * getMaintenanceCostPerKm(line.getType()))
                .sum();
    }

    private double calculateTrainMaintenance() {
        return trainRepo.getAll().stream()
                .mapToDouble(train -> train.getLocomotive().getType().getMaintenance())
                .sum();
    }

    private double calculateFuelCost(int year) {
        return trainRepo.getAll().stream()
                .mapToDouble(train ->
                        train.getDistanceTraveled() * train.getLocomotive().getType().getFuelCost()
                )
                .sum();
    }

    private void resetAnnualData() {
        trainRepo.getAll().forEach(Train::resetAnnualDistance);
        cargoDeliveryRepo.clear();
    }

    private double getMaintenanceCostPerKm(LineType type) {
        switch (type) {
            case SINGLE_NOT_ELECTRICITY: return 6;
            case DOUBLE_NOT_ELECTRICITY: return 9;
            case SINGLE_ELECTRIFIED: return 11;
            case DOUBLE_ELECTRIFIED: return 14;
            default: return 0;
        }
    }

    private double getCargoBaseValue(CargoType type) {
        // Valores base conforme especificado no problema
        switch (type) {
            case PASSENGERS: return 100;
            case MAIL: return 60;
            case COAL: return 40;
            case IRON_ORE: return 50;
            case STEEL: return 70;
            default: return 30;
        }
    }
}