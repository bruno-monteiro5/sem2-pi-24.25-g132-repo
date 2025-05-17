package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Industry;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.domain.IndustryTypes;
import pt.ipp.isep.dei.repository.IndustryRepository;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.List;

public class AddIndustryController {

    private IndustryRepository repository;

    public AddIndustryController() {
        this.repository = Repositories.getInstance().getIndustryRepository();
    }

    public Industry addIndustry(String name, Position position, IndustryTypes.IndustryType type) {
        if (repository.industryExists(name)) {
            throw new IllegalArgumentException("An industry with that name already exists.");
        }

        if (repository.isPositionInvalid(position)) {
            throw new IllegalArgumentException("Position is already occupied by another industry.");
        }

        Industry industry = new Industry(name, position, type);

        repository.save(industry);

        return industry;
    }




}
