
package pt.ipp.isep.dei.domain;

public abstract class Industry {


    private String name;
    private Position position;

    public Industry (String name, Position position) {
        this.name = name;
        this.position = position;
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

    @Override
    public String toString() {
        return "Industry: " + name + " at position " + position;
    }


    public boolean isAtPosition(Position position) {
        return this.position.equals(position);
    }


    public abstract String getTypeDescription();
}
