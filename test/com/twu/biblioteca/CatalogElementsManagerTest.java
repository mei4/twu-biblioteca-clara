package com.twu.biblioteca;

import com.twu.biblioteca.catalogElement.CatalogElement;
import com.twu.biblioteca.catalogElement.CatalogElementsManager;
import com.twu.biblioteca.catalogElement.book.Book;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatalogElementsManagerTest {

    private CatalogElement mockedBook1;
    private CatalogElement mockedBook2;
    private List<CatalogElement> mockedCatalogElements;
    private CatalogElementsManager catalogElementsManager;
    private String errorMessageInvalid = "Reference not valid";

    @Before
    public void initialize() {
        mockedBook1 = mock(Book.class);
        mockedBook2 = mock(Book.class);
        mockedCatalogElements = mock(List.class);
        catalogElementsManager = new CatalogElementsManager(mockedCatalogElements);
    }

    @Test
    public void checkThatAllAvailableCatalogElementsAreReturned() {
        when(mockedBook1.isCheckout()).thenReturn(false);
        when(mockedBook2.isCheckout()).thenReturn(true);
        when(mockedCatalogElements.get(0)).thenReturn(mockedBook1);
        when(mockedCatalogElements.get(1)).thenReturn(mockedBook2);
        when(mockedCatalogElements.size()).thenReturn(2);
        catalogElementsManager = new CatalogElementsManager(new ArrayList(Arrays.asList(mockedBook1, mockedBook2)));
        assertEquals(catalogElementsManager.getAllAvailable(), new ArrayList(Arrays.asList(mockedBook2)));
    }

    @Test
    public void checkThatTheCatalogElementIsReturnedWhenFindByReference() {
        when(mockedCatalogElements.get(0)).thenReturn(mockedBook1);
        when(mockedCatalogElements.size()).thenReturn(1);
        assertEquals(catalogElementsManager.getByReference("1"), mockedBook1);
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

    @Test
    public void checkThatOnlyCheckedOutCatalogElementsAreReturned() {
        when(mockedBook1.isCheckout()).thenReturn(false);
        when(mockedBook2.isCheckout()).thenReturn(true);
        when(mockedCatalogElements.get(0)).thenReturn(mockedBook1);
        when(mockedCatalogElements.get(1)).thenReturn(mockedBook2);
        when(mockedCatalogElements.size()).thenReturn(2);

        assertEquals(catalogElementsManager.getAllCheckedOut(),
                new HashMap<Integer,CatalogElement>() {{put(2, mockedBook2);}});
    }

}
