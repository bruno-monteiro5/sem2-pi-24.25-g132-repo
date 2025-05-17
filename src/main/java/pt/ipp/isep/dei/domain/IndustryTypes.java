package pt.ipp.isep.dei.domain;

/**
 * Provides definitions for various types of industries.
 *
 * This class contains an enumeration {@link IndustryType} that categorizes
 * industries into distinct types, such as PRIMARY, TRANSFORMING, and MIXED.
 * It also provides utility methods for retrieving information about the
 * available industry types.
 */
public class IndustryTypes {

    /**
     * Enumeration representing different types of industries with descriptions.
     *
     * Each industry type has a description that explains the category
     * it represents (e.g., Primary Industry, Transforming Industry, etc.).
     */
    public enum IndustryType {
        /**
         * Represents a primary industry, typically involved in resource extraction
         * or raw material production.
         */
        PRIMARY("Primary Industry"),

        /**
         * Represents a transforming industry, focused on manufacturing or production
         * by transforming raw materials into finished goods.
         */
        TRANSFORMING("Transforming Industry"),

        /**
         * Represents a mixed industry, which combines aspects of both primary
         * and transforming industries.
         */
        MIXED("Mixed Industry");

        private final String description; // Detailed description of the industry type

        /**
         * Constructs an {@link IndustryType} with a specific description.
         *
         * @param description A {@link String} describing the industry type.
         */
        IndustryType(String description) {
            this.description = description;
        }

        /**
         * Retrieves the description of the industry type.
         *
         * @return A {@link String} representing the description of the industry type.
         */
        public String getDescription() {
            return description;
        }
    }

    /**
     * Retrieves the names of all defined industry types as an array of strings.
     *
     * This method iterates through all the values in the {@link IndustryType} enum
     * and returns their names as an array of strings.
     *
     * @return An array of strings containing the names of all industry types.
     */
    public static String[] getAllTypes() {
        IndustryType[] types = IndustryType.values(); // All available industry types
        String[] typeNames = new String[types.length]; // Array to store names
        for (int i = 0; i < types.length; i++) {
            typeNames[i] = types[i].name(); // Store the name of each industry type
        }
        return typeNames;
    }
}
