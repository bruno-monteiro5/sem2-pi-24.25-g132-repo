package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Position;

import java.util.ArrayList;
import java.util.List;

public class CityRepository {
    private List<City> cities = new ArrayList<>();

    public List<City> getCities() {
        return cities;
    }

    public boolean cityExists(String name) {
        for(City c : cities){
            if(c.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public void save(City city) {
        cities.add(city);
    }

    public boolean positionInvalid(Position position) {
        for(City c : cities){
            if(c.getY() == position.getY() && c.getX() == position.getX())
                return true;
        }
        return false;
    }
}
