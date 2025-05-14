package pt.ipp.isep.dei.domain;

public class Industry {

    private String name;
    private IndustryType type;
    private Position position;

    public Industry (String name, IndustryType type, Position position) {
        this.name = name;
        this.type = type;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public IndustryType getType() {
        return type;
    }

    public void setType (IndustryType type) {
        this.type = type;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition (Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("Industry{name='%s', type=%s, position=%s}", name, type, position);
    }


}
