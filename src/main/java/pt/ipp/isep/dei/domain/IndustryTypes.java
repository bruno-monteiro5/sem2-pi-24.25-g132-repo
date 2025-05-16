package pt.ipp.isep.dei.domain;

public class IndustryTypes {

    // Enum interno para representar os tipos de indústria
    public enum IndustryType {
        PRIMARY("Primary Industry"),
        TRANSFORMING("Transforming Industry"),
        MIXED("Mixed Industry");

        private final String description;

        // Construtor do enum
        IndustryType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    // Retorna uma lista com o nome de todos os tipos (para combobox, por exemplo)
    public static String[] getAllTypes() {
        IndustryType[] types = IndustryType.values();
        String[] typeNames = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            typeNames[i] = types[i].name();
        }
        return typeNames;
    }
}

