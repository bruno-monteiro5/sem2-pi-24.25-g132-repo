package pt.ipp.isep.dei.simulator;

import pt.ipp.isep.dei.domain.*;

public class TrainArrivalEvent extends SimulationEvent {
    private final Train train;
    private final Station station;

    public TrainArrivalEvent(int tick, Train train, Station station) {
        super(tick);
        this.train = train;
        this.station = station;
    }

    @Override
    public void handle(SimulatorEngine engine) {

        Station from = train.getLastStationVisited();
        Station to = station;

        train.setCurrentStation(station);
        train.setLastStationVisited(station);

        System.out.printf("Tick %d: Train %s arrived at %s%n",
                engine.getCurrentTick(), train.getId(), station.getName());

        //  Recolher carga da estação
        for (Cargo supply : station.getSupplyCargo()) {
            if (train.acceptsCargo(supply)) {
                train.addCargo(new Cargo(supply));
                System.out.printf(" → Collected %s: %d units%n", supply.getType().name(), supply.getQuantity());
            }
        }

        station.getSupplyCargo().clear();

        //  Entregar carga se veio de outra estação
        if (from != null && !from.equals(to)) {
            double dx = to.getLocation().getX() - from.getLocation().getX();
            double dy = to.getLocation().getY() - from.getLocation().getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            for (Cargo cargo : train.getCargos()) {
                CargoType type = cargo.getType();
                double baseValue = type.getBaseValue();

                double revenue = baseValue * (distance / 100.0);
                int demand = station.getDemandFor(type.name());
                revenue *= 0.1 * (1 + demand);
                revenue *= station.getTotalRevenueMultiplier();

                engine.getPlayer().addToBudget(revenue);
                station.addRevenue(revenue);
                station.registerDelivery(type);

                System.out.printf(" → Delivered %s: +%.2f$ (demand=%d, bonus=%.2fx)\n",
                        type, revenue, demand, station.getTotalRevenueMultiplier());
            }

            train.clearCargos();
        }

        // 📍 Verificar fim da rota
        var route = train.getAssignedRoute();
        var points = route.getPointsOfRoute();
        int index = -1;

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getStation().equals(station)) {
                index = i;
                break;
            }
        }

        boolean endOfRoute = (index == points.size() - 1);
        if (endOfRoute) {
            System.out.printf("Tick %d: Train %s completed its route.%n",
                    engine.getCurrentTick(), train.getId());

            engine.scheduleEvent(new RouteCompletionEvent(
                    engine.getCurrentTick(), train, route, true
            ));
        }

        // Agendar próxima partida
        int departureTick = engine.getCurrentTick() + 1;
        engine.scheduleEvent(new TrainDepartureEvent(departureTick, train, station));

    }



    @Override
    public String getDescription() {
        return String.format("Tick %d: Train %s arrived at %s",
                getScheduledTick(), train.getId(),station.getName());
    }


}