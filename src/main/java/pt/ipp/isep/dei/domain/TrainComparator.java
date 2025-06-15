package pt.ipp.isep.dei.domain;

import java.util.Comparator;

public class TrainComparator implements Comparator<Train> {
    @Override
    public int compare(Train t1, Train t2) {

        //Group the locomotives by type (steam -> diesel -> electric)

        int typeComparison = t1.getLocomotive().getType().getFuelType().compareTo(t2.getLocomotive().getType().getFuelType());

        if (typeComparison != 0) return typeComparison;

        //Order the locomotives by name

        return t1.getLocomotive().getType().getName()
                .compareTo(t2.getLocomotive().getType().getName());
    }
}