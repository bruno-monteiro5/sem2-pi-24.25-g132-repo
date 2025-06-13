package pt.ipp.isep.dei.domain;

/**
 * Represents an editor for managing elements in a {@link Map}.
 * <p>
 * This class provides methods to manipulate a map's elements,
 * such as adding cities or industries (to be implemented).
 */
public class Editor {

    /**
     * The map managed by this editor.
     */
    private Map map;

    /**
     * Constructs an {@code Editor} for a specific map.
     *
     * @param map the {@link Map} instance to be edited; must not be {@code null}
     */
    public Editor(Map map) {
        this.map = map;
    }

    /**
     * Returns a string representation of the editor.
     *
     * @return the class name and the associated map
     */
    @Override
    public String toString() {
        return String.format("Editor{map=%s}", map);
    }
}


