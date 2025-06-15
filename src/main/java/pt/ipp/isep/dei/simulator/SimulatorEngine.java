package pt.ipp.isep.dei.simulator;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.ui.ApplicationSession;

import java.util.*;
import java.util.function.Consumer;

public class SimulatorEngine {
    private int currentTick = 0;
    private int currentYear;
    private int currentMonth;
    private boolean isRunning = false;
    private boolean isPaused = false;
    private final List<SimulationEvent> futureEventsSnapshot = new ArrayList<>();
    private final List<SimulationEvent> pastEvents = new ArrayList<>();
    private final Scenario scenario;
    private final Player player;
    private final List<LocomotiveType> announcedLocomotives = new ArrayList<>();
    private Consumer<SimulationEvent> eventListener;


    public Player getPlayer() {
        return player;
    }

    public SimulatorEngine(Scenario scenario, Player player) {
        this.scenario = scenario;
        this.player = player;
    }


    private final PriorityQueue<SimulationEvent> eventQueue = new PriorityQueue<>();

    public void scheduleEvent(SimulationEvent event) {
        eventQueue.add(event);
    }

    public void runNextEvent() {
        if (!eventQueue.isEmpty()) {
            SimulationEvent next = eventQueue.poll();
            currentTick = next.getScheduledTick();
            next.handle(this);

            pastEvents.add(next);
            futureEventsSnapshot.clear();
            futureEventsSnapshot.addAll(eventQueue);

            // 👉 Notificar o controlador de UI
            if (eventListener != null) {
                eventListener.accept(next);
            }
        }
    }


    public boolean hasMoreEvents() {
        return !eventQueue.isEmpty();
    }

    public int getCurrentTick() {
        return currentTick;
    }

    public void setSimulationStart(int year, int month) {
        this.currentYear = year;
        this.currentMonth = month;
    }

    public int getCurrentSimulatedYear() {
        return currentYear;
    }

    public int getCurrentSimulatedMonth() {
        return currentMonth;
    }

    public void setEventListener() {
        if (this.eventListener != null) {
            return;
        }

        ObservableList<String> list = ApplicationSession.getInstance().getSimulatorEventMessages();

        this.eventListener = event -> {
            Platform.runLater(() -> {
                String msg = event.getDescription(); // usa getDescription()
                System.out.println("🟢 Listener ativado → a adicionar: " + msg);
                list.add(msg); // ← essencial para aparecer na ListView
            });
        };
    }





    public boolean start() {
        if (isRunning) return false;

        isRunning = true;
        isPaused = false;
        currentTick = 0;
        pastEvents.clear();
        futureEventsSnapshot.clear();
        eventQueue.clear();

        // Agendar eventos de partida dos comboios com rota atribuída

        System.out.println("🎬 STARTING SIMULATION...");
        System.out.println(">> Número de comboios no cenário: " + scenario.getTrains().size());

        for (Train train : scenario.getTrains()) {
            System.out.println("   - Train " + train.getId() + ", rota atribuída: " + (train.getAssignedRoute() != null));
        }


        for (Train train : scenario.getTrains()) {
            System.out.println(">> Verifying train " + train.getId());

            if (train.getAssignedRoute() == null) {
                System.out.println("⚠️ Train " + train.getId() + " does not have route atributted!");
                continue;
            }

            List<PointOfRoute> points = train.getAssignedRoute().getPointsOfRoute();
            System.out.println(">> Atributted route has " + points.size() + " points");

            if (!points.isEmpty()) {
                Station initialStation = points.get(0).getStation();
                System.out.println(">> First Station: " + initialStation.getName());

                train.setLastStationVisited(initialStation);
                scheduleEvent(new TrainDepartureEvent(currentTick, train, initialStation));
            }
        }



        // Agendar o primeiro evento mensal
        int anoInicio = scenario.getPeriodTime().getStartYear();
        scheduleEvent(new MonthProgressionEvent(
                currentTick,
                1,
                anoInicio,
                scenario
        ));

        return true;
    }



    public void pause() {
        isPaused = true;
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public List<SimulationEvent> getFutureEvents() {
        return new ArrayList<>(futureEventsSnapshot);
    }

    public List<SimulationEvent> getPastEvents() {
        return new ArrayList<>(pastEvents);
    }

    public List<LocomotiveType> getAnnouncedLocomotives() {
        return announcedLocomotives;
    }

    public void setEventListener(Consumer<SimulationEvent> listener) {
        this.eventListener = listener;
    }

    public Consumer<SimulationEvent> getEventListener() {
        return eventListener;
    }

    public void generateMonthlyCargo() {
        List<Station> stations = Repositories.getInstance().getStationRepository().getAll();
        Scenario scenario = ApplicationSession.getInstance().getCurrentScenario();
        double factor = scenario.getIndustryGenerationFactor(); // e.g., 1.0 = 100%

        for (Station station : stations) {
            List<Industry> industries = station.getIndustriesInRange();

            for (Industry industry : industries) {
                String industryName = industry.getName();
                IndustryData data = IndustryData.fromName(industryName);

                if (data == null) {
                    System.out.printf("⚠️ Industry '%s' not found in IndustryData. Skipping.%n", industryName);
                    continue;
                }

                // Generate supplied cargo
                for (String cargoName : data.getSuppliedCargos()) {
                    try {
                        CargoType type = CargoType.valueOf(cargoName.toUpperCase());
                        int quantity = (int) (10 * factor);
                        Cargo cargo = new Cargo(type, quantity, true, type.getBaseValue());
                        station.addSupplyCargo(cargo);

                        System.out.printf("🟢 Generated SUPPLY cargo: %s x%d for station %s (Industry: %s)%n",
                                type.name(), quantity, station.getName(), industryName);
                    } catch (IllegalArgumentException e) {
                        System.out.printf("❌ Unknown cargo type (supply): '%s' from industry '%s'%n",
                                cargoName, industryName);
                    }
                }

                // Generate demanded cargo
                for (String cargoName : data.getDemandedCargos()) {
                    try {
                        CargoType type = CargoType.valueOf(cargoName.toUpperCase());
                        int quantity = (int) (10 * factor);
                        Cargo cargo = new Cargo(type, quantity, false, type.getBaseValue());
                        station.addDemandCargo(cargo);

                        System.out.printf("🟡 Generated DEMAND cargo: %s x%d for station %s (Industry: %s)%n",
                                type.name(), quantity, station.getName(), industryName);
                    } catch (IllegalArgumentException e) {
                        System.out.printf("❌ Unknown cargo type (demand): '%s' from industry '%s'%n",
                                cargoName, industryName);
                    }
                }
            }
        }
    }





}