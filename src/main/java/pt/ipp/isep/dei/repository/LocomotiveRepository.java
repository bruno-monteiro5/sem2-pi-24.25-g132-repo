package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Locomotive;

import java.util.ArrayList;
import java.util.List;

public class LocomotiveRepository {

    private static final List<Locomotive> locomotives = new ArrayList<>();

    public List<Locomotive> getLocomotives() {
        return new ArrayList<>(locomotives); // devolve cópia para segurança
    }

    public boolean addLocomotive(Locomotive locomotive) {
        if (locomotive == null || locomotives.contains(locomotive)) {
            return false;
        }
        return locomotives.add(locomotive);
    }

    public void save(Locomotive locomotive) {
        addLocomotive(locomotive);
    }
}