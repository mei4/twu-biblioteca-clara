package com.twu.biblioteca.catalogElement;

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
    private List<CatalogElement> catalogElements;
    private CatalogElementsManager catalogElementsManager;
    private String errorMessageInvalid = "Reference not valid";

    @Before
    public void initialize() {
        mockedBook1 = mock(Book.class);
        mockedBook2 = mock(Book.class);
        catalogElements = new ArrayList(Arrays.asList(mockedBook1, mockedBook2));
        catalogElementsManager = new CatalogElementsManager(catalogElements);
    }

    @Test
    public void checkThatAllAvailableCatalogElementsAreReturned() {
        when(mockedBook1.isCheckout()).thenReturn(false);
        when(mockedBook2.isCheckout()).thenReturn(true);

        assertEquals(catalogElementsManager.getAllAvailable(),
                new HashMap<Integer,CatalogElement>() {{put(1, mockedBook1);}});
    }

    @Test
    public void checkThatTheCatalogElementIsReturnedWhenFindByReference() {
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

        assertEquals(catalogElementsManager.getAllCheckedOut(),
                new HashMap<Integer,CatalogElement>() {{put(2, mockedBook2);}});
    }

}
