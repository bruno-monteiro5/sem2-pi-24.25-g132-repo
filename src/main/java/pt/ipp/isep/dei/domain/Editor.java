package pt.ipp.isep.dei.domain;

/**
 * Represents an editor for managing elements in a {@link Map}.
 *
 * <p>This class provides methods to add industries to the map and list all
 * industries currently present. It acts as an intermediary tool for users
 * or systems to interact with a map's industries.</p>
 */
public class Editor {

    private Map map;

    /**
     * Constructs an {@code Editor} for a specific map.
     *
     * @param map The {@link Map} instance that this editor will manage. Must not be {@code null}.
     */
    public Editor(Map map) {
        this.map = map;
    }

    public void addIndustry(String name, Industry industry) {
        try {
            map.addIndustry(industry);
            System.out.println("Industry '" + name + "' added successfully!");
        } catch (IllegalArgumentException e) {
            System.err.println("Could not add industry: The industry is not valid or already exists in the map.");
        }
    }


    public void listIndustries() {
        System.out.println("All Industries in Map:");
        for (Industry industry : map.getIndustries()) {
            System.out.println(industry);
        }
    }

}


