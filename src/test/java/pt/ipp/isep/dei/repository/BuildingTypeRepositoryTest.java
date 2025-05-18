package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.BuildingType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for BuildingTypeRepository.
 */
class BuildingTypeRepositoryTest {

    /**
     * Testa se o repositório inicializa corretamente com todos os BuildingTypes disponíveis.
     */
    @Test
    void testRepositoryInitialization() {
        // Act
        BuildingTypeRepository repository = new BuildingTypeRepository();
        List<BuildingType> buildingTypes = repository.getAllBuildingTypes();

        // Assert
        assertEquals(BuildingType.values().length, buildingTypes.size(),
                "O número de tipos de construção deveria ser igual ao número de valores no enum BuildingType.");
        assertTrue(buildingTypes.containsAll(List.of(BuildingType.values())),
                "O repositório deveria conter todos os valores do enum BuildingType.");
    }

    /**
     * Testa se o método getAllBuildingTypes retorna uma nova lista que não é mutável.
     */
    @Test
    void testGetAllBuildingTypesImmutable() {
        // Arrange
        BuildingTypeRepository repository = new BuildingTypeRepository();

        // Act
        List<BuildingType> buildingTypes = repository.getAllBuildingTypes();

        // Assert
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            // Tentativa de modificar a lista
            buildingTypes.add(BuildingType.POST_OFFICE);
        }, "A lista retornada não deveria ser mutável.");
    }

    /**
     * Testa se o método getBuildingTypeDetails retorna corretamente o tipo de construção fornecido.
     */
    @Test
    void testGetBuildingTypeDetails() {
        // Arrange
        BuildingTypeRepository repository = new BuildingTypeRepository();
        BuildingType type = BuildingType.TELEGRAPH;

        // Act
        BuildingType result = repository.getBuildingTypeDetails(type);

        // Assert
        assertEquals(type, result,
                "O método getBuildingTypeDetails deveria retornar o tipo de construção fornecido.");
    }

    /**
     * Testa se o método getBuildingTypeDetails lança exceção ao passar um valor nulo.
     */
    @Test
    void testGetBuildingTypeDetailsWithNull() {
        // Arrange
        BuildingTypeRepository repository = new BuildingTypeRepository();

        // Act & Assert
        assertThrows(NullPointerException.class, () ->
                repository.getBuildingTypeDetails(null), "O método deveria lançar uma exceção para parâmetros nulos.");
    }
}

