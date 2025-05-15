package pt.ipp.isep.dei.domain;

public class PrimaryIndustry extends Industry {

    public PrimaryIndustry(String name, Position position) {
        super(name, position);
    }

    @Override
    public String getTypeDescription() {
        return "Primary Industry";
    }
}

