
package pt.ipp.isep.dei.domain;

public class Industry {


    private String name;
    private Position position;
    private IndustryTypes.IndustryType type;

    public Industry (String name, Position position, IndustryTypes.IndustryType type) {
        this.name = name;
        this.position = position;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition (Position position) {
        this.position = position;
    }

    public IndustryTypes.IndustryType getType() {
        return type;
    }

    public void setType (IndustryTypes.IndustryType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Industry{" +
                "-Name='" + name + '\'' +
                "-Position=" + position +
                "-Type=" + type +
                '}';
    }


    public boolean isAtPosition(Position position) {
        return this.position.equals(position);
    }

}
