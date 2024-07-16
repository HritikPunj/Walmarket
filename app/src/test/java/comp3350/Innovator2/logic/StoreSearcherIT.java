package comp3350.Innovator2.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import comp3350.Innovator2.application.Services;
import comp3350.Innovator2.objects.*;
import comp3350.Innovator2.objects.utils.Category;
import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.utils.TestUtils;

/**
 * Class which implements tests on the StoreSearcher class
 */
public class StoreSearcherIT {

    private IStoreSearcher storeSearcher;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        System.out.println("Starting integration test for Store searcher");

        //Creating a temp DB for testing
        this.tempDB = TestUtils.copyDB();

        //Create an instance of class to be tested
        this.storeSearcher = StoreHelper.getStoreSearcher();

        assertNotNull(this.storeSearcher);
    }

    @Test
    public void testPaginatedSearch() {
        System.out.println("\nStarting testPaginatedSearch");

        CriteriaSet criteria = new CriteriaSet(0, 10, "", Category.Default);
        SearchResult searchedItems = null;
        try {
            searchedItems = storeSearcher.getItems(criteria);
        }
        catch(UIException e) {
            assertEquals(0, 1);
        }
        if (searchedItems == null) assertEquals(0, 1);

        assertEquals(10, searchedItems.getItemList().size()); // check the size matches

        System.out.println("Finished testPaginatedSearch");
    }

    @Test
    public void testTitleSearch() {

        System.out.println("\nStarting testTitleSearch");

        CriteriaSet criteria = new CriteriaSet(0, 60, "Blackberries", Category.Default);
        SearchResult searchedItems = null;
        try {
            searchedItems = storeSearcher.getItems(criteria);
        }
        catch(UIException e) {
            assertEquals(0, 1);
        }
        if (searchedItems == null) assertEquals(0, 1);

        // test title search
        for(int i=0; i<searchedItems.getItemList().size(); i++) {
            assertEquals("Blackberries", searchedItems.getItemList().get(i).getTitle());
        }

        System.out.println("Finished testTitleSearch");
    }

    @Test
    public void testCategorySearch()
    {
        System.out.println("\nStarting testCategorySearch");

        CriteriaSet criteria = new CriteriaSet(0, 60, "", Category.Meat);
        SearchResult searchedItems = null;
        try {
            searchedItems = storeSearcher.getItems(criteria);
        }
        catch(UIException e) {
            assertEquals(0, 1);
        }
        if (searchedItems == null) assertEquals(0, 1);

        for(int i=0; i<searchedItems.getItemList().size(); i++) {
            assertEquals(Category.Meat, searchedItems.getItemList().get(i).getCategory());
        }

        System.out.println("Finished testCategorySearch");
    }

    @After
    public void tearDown() {
        System.out.println("Reset database.");
        // reset DB
        this.tempDB.delete();

        //clear Services
        Services.clean();

        System.out.println("Finished integration test for Store searcher.");
    }
}