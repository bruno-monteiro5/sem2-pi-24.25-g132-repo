package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;

public class Train {

    private final int id;
    private final List<Locomotive> locomotives = new ArrayList<>();
    private List<Cargo> cargo;

    public Train(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Locomotive> getLocomotives() {
        return new ArrayList<>(locomotives);
    }

    public void addLocomotive(Locomotive locomotive) {
        if (locomotive != null) {
            locomotives.add(locomotive);
        }
    }

    public List<Cargo> getCargo() {
        return cargo;
    }

    public void setCargo(List<Cargo> cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Train ID ").append(id).append(" - Locomotives: ");
        for (Locomotive loco : locomotives) {
            sb.append(loco.getType().getName()).append(", ");
        }
        if (!locomotives.isEmpty()) {
            sb.setLength(sb.length() - 2); // remove última vírgula
        }
        return sb.toString();
    }
}