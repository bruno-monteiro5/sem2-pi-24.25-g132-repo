package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.LocomotiveType;
import pt.ipp.isep.dei.domain.PeriodTime;
import pt.ipp.isep.dei.domain.fuelType;

import java.util.ArrayList;
import java.util.List;

public class LocomotiveTypeRepository {
    public List<LocomotiveType> getLocomotiveTypes(PeriodTime period , List<fuelType> fuelTypes) {
        int finalYear = period.getEndYear();
        List<LocomotiveType> locomotiveTypes = new ArrayList<>();

        for(LocomotiveType locomotiveType : LocomotiveType.values()) {
            if (locomotiveType.getStartYear() <= finalYear && finalYear <= locomotiveType.getEndYear()) {
                for (fuelType fuelType : fuelTypes) {
                    if (locomotiveType.getFuelType().equals(fuelType.name())) {
                        locomotiveTypes.add(locomotiveType);
                        break;
                    }
                }
            }

        }
        return locomotiveTypes;
    }
}
