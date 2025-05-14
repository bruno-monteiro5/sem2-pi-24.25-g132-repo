
package MDISC.USs;


public class Connection {
    private Station destination;
    private boolean electrified;

    public Connection(Station destination, boolean electrified) {
        this.destination = destination;
        this.electrified = electrified;
    }

    public Station getDestination() {
        return destination;
    }

    public boolean isElectrified() {
        return electrified;
    }
}

