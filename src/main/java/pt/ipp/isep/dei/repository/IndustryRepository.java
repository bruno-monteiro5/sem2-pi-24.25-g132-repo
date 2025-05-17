package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Industry;
import pt.ipp.isep.dei.domain.Position;

import java.util.ArrayList;
import java.util.List;

public class IndustryRepository {

    private List<Industry> industries = new ArrayList<>();
    public boolean industryExists(String name) {
        for (Industry i : industries) {
            if (i.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPositionInvalid(Position position) {
        for (Industry i : industries) {
            if (i.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public void save(Industry industry) {
        industries.add(industry);
    }
}
