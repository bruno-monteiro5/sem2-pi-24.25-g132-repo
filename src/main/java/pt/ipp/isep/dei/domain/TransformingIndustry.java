package pt.ipp.isep.dei.domain;

public class TransformingIndustry extends Industry {

    public TransformingIndustry(String name, Position position) {
        super(name, position);
    }

    @Override
    public String getTypeDescription() {
        return "Transforming Industry";
    }
}

