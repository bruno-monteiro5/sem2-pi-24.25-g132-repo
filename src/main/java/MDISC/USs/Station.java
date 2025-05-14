package MDISC.USs;

public class Station {
    private String Name;
    private String Type;

    public Station(String name) {
        this.Name = name;
    }

    private String determinateType(String type) {
        if (name == null)
            return "Invalid station";

        char firstLetter = Character.toUpperCase(name.charAt(0));
        switch (firstLetter) {
            case 'D':
                return "Depot";
            case 'S':
                return "Station";
            case 'T':
                return "Terminal";
            default:
                return "Unknown";
        }
    }

}
