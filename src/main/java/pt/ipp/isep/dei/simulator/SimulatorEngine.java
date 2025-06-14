package pt.ipp.isep.dei.simulator;

import pt.ipp.isep.dei.domain.*;

import java.util.*;

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

            pastEvents.add(next); // guardar no histórico
            futureEventsSnapshot.clear();
            futureEventsSnapshot.addAll(eventQueue); // atualizar snapshot
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
            System.out.println(">> Verificando train " + train.getId());

            if (train.getAssignedRoute() == null) {
                System.out.println("⚠️ Train " + train.getId() + " não tem rota atribuída!");
                continue;
            }

            List<PointOfRoute> points = train.getAssignedRoute().getPointsOfRoute();
            System.out.println(">> Rota atribuída tem " + points.size() + " pontos");

            if (!points.isEmpty()) {
                Station initialStation = points.get(0).getStation();
                System.out.println(">> Primeira estação: " + initialStation.getName());

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



}