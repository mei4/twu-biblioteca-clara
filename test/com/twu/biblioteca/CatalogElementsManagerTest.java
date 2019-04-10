package com.twu.biblioteca;

import com.twu.biblioteca.catalogElement.CatalogElement;
import com.twu.biblioteca.catalogElement.CatalogElementsManager;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class CatalogElementsManagerTest {

    private List<CatalogElement> mockedCatalogElements;
    private CatalogElementsManager catalogElementsManager;

    @Before
    public void initialize() {
        mockedCatalogElements = mock(List.class);
        catalogElementsManager = new CatalogElementsManager(mockedCatalogElements);
    }

    @Test
    public void checkThatAllTheCatalogElementsAreReturned() {
        assertEquals(catalogElementsManager.getAll(), mockedCatalogElements);
    }

}
