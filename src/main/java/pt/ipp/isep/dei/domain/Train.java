/**
package pt.ipp.isep.dei.domain;


import java.util.List;
import java.util.Random;

public class Train {

    private final int ID = 0;
    private double ide;
    private List<Cargo> cargo;
    private List <Train> trains;
    private Locomotive locomotive;

    public Train(double id, Locomotive locomotive) {
        this.ide = id;
        this.locomotive = locomotive;
    }

    public Train () {
        this.ide = ID;
        this.locomotive = null;

    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    public double getIde() {
        return ide;
    }

    public void setIde(double ide) {
        this.ide = ide;
    }

    public List<Cargo> getCargo() {
        return cargo;
    }

    public void setCargo(List<Cargo> cargo) {
        this.cargo = cargo;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public boolean assignTrain (int id) {
        if (train == null) return false;

        Random random = new Random();
        int uniqueId = id;

        boolean idExists;
        do {
            idExists = false;
            for (Train existingTrain : trains) {
                if (existingTrain.getId() == uniqueId) {
                    uniqueId = random.nextInt(100000) + 1;
                    idExists = true;
                    break;  // sai do for para recomeçar a verificação com o novo id
                }
            }
        } while (idExists);

        Train newTrain = new Train(uniqueId, train);
        return this.trains.add(newTrain);
    }

} */
