package com.twu.biblioteca;

import com.twu.biblioteca.catalogElement.CatalogElement;
import com.twu.biblioteca.catalogElement.CatalogElementsManager;
import com.twu.biblioteca.catalogElement.book.Book;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatalogElementsManagerTest {

    private CatalogElement mockedBook;
    private List<CatalogElement> mockedCatalogElements;
    private CatalogElementsManager catalogElementsManager;
    private String errorMessageInvalid = "Reference not valid";

    @Before
    public void initialize() {
        mockedBook = mock(Book.class);
        mockedCatalogElements = mock(List.class);
        catalogElementsManager = new CatalogElementsManager(mockedCatalogElements);
    }

    @Test
    public void checkThatAllTheCatalogElementsAreReturned() {
        assertEquals(catalogElementsManager.getAll(), mockedCatalogElements);
    }

    @Test
    public void checkThatTheCatalogElementIsReturnedWhenFindByReference() {
        when(mockedCatalogElements.get(0)).thenReturn(mockedBook);
        when(mockedCatalogElements.size()).thenReturn(1);
        assertEquals(catalogElementsManager.getByReference("1"), mockedBook);
    }

    @Test
    public void checkThatAnErrorMessageIsDisplayedWhenReferenceDoesNotExists() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        catalogElementsManager.getByReference("11");

        assertEquals(errorMessageInvalid + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsDisplayedWhenReferenceHasNumberFormatException() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        catalogElementsManager.getByReference("a");

        assertEquals(errorMessageInvalid + "\n", out.toString());
    }

}
