package pt.ipp.isep.dei.domain;

public class Editor {

    private Map map;

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


