package pt.ipp.isep.dei.domain;

public class MixedIndustry extends Industry {

    public MixedIndustry(String name, Position position) {
        super(name, position);
    }

    @Override
    public String getTypeDescription() {
        return "Mixed Industry";
    }
}
