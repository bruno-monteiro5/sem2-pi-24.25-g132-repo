package pt.ipp.isep.dei.domain;

/**
 * Represents a block of houses within a city on the map.
 * <p>
 * Each {@code HouseBlock} has a position on the map, represented as a {@link Position}.
 * Positions must adhere to map boundaries to ensure proper placement.</p>
 */
    public class HouseBlock {

        private Position position; // Position of the house block on the map

        /**
         * Constructs a HouseBlock with the specified position.
         *
         * @param position The position of the house block. Must not be null.
         * @throws IllegalArgumentException if position is null.
         */
        public HouseBlock(Position position) {
            if (position == null) {
                throw new IllegalArgumentException("Position cannot be null.");
            }
            this.position = position;
        }

        public Position getPosition() { return position; }

        /**
         * Sets the position of the house block.
         *
         * @param position The new position of the house block. Must not be null.
         * @throws IllegalArgumentException if the position is null.
         */
        public void setPosition(Position position) {
            if (position == null) {
                throw new IllegalArgumentException("Position cannot be null.");
            }
            this.position = position;
        }

        /**
         * Returns a string representation of the house block.
         *
         * @return A string representation of the house block, including its position.
         */
        @Override
        public String toString() {
            return "HouseBlock{" +
                    "position=" + position +
                    '}';
        }
    }