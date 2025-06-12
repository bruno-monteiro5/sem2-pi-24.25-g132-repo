package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.FuelType;
import pt.ipp.isep.dei.domain.LocomotiveType;
import pt.ipp.isep.dei.domain.PeriodTime;

import java.util.ArrayList;
import java.util.List;

public class LocomotiveTypeRepository {

    public List<LocomotiveType> getLocomotiveTypes(PeriodTime period, List<FuelType> fuelTypes) {
        int finalYear = period.getEndYear();
        List<LocomotiveType> locomotiveTypes = new ArrayList<>();

        for (LocomotiveType locomotiveType : LocomotiveType.values()) {
            if (locomotiveType.getStartYear() <= finalYear && finalYear <= locomotiveType.getEndYear()) {
                for (FuelType fuelType : fuelTypes) {
                    if (locomotiveType.getFuelType().getClass().equals(fuelType.getClass())) {
                        locomotiveTypes.add(locomotiveType);
                        break;
                    }
                }
            }
        }
        return locomotiveTypes;
    }
}
