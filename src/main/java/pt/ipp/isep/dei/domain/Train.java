package pt.ipp.isep.dei.domain;

import java.util.List;

public class Train {

    private final int id;
    private Locomotive locomotive;
    private List<Cargo> cargo;

    public Train(int id, Locomotive locomotive) {
        this.id = id;
        this.locomotive = locomotive;
    }

    public int getId() {
        return id;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    public List<Cargo> getCargo() {
        return cargo;
    }

    public void setCargo(List<Cargo> cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return String.format("Train ID %d - Locomotive: %s", id, locomotive.getType().getName());
    }
}
