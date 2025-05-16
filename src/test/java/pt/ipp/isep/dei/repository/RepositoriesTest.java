package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoriesTest {

    @Test
    void testGetInstance() {
        Repositories instance = pt.ipp.isep.dei.repository.Repositories.getInstance();
        assertNotNull(instance);
    }

    @Test
    void testGetOrganizationRepository() {
        Repositories instance = pt.ipp.isep.dei.repository.Repositories.getInstance();
        assertNotNull(instance.getOrganizationRepository());
    }

    @Test
    void testGetTaskCategoryRepository() {
        Repositories instance = pt.ipp.isep.dei.repository.Repositories.getInstance();
        assertNotNull(instance.getTaskCategoryRepository());
    }
}