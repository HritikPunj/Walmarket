package comp3350.Innovator2.presentation;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import comp3350.Innovator2.R;
import comp3350.Innovator2.logic.IStoreSearcher;
import comp3350.Innovator2.logic.StoreHelper;
import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.objects.utils.Category;
import comp3350.Innovator2.objects.CriteriaSet;
import comp3350.Innovator2.objects.SearchResult;

/**
 * #### HelperCriteria
 * Service class for the Browser Page.
 * Reads and packs criteria into objects.
 */
public class HelperCriteria
{
    //================================================== Variables

    //UI Elements
    private final EditText searchBar;
    private final Spinner categories;

    //Class Links
    private final IStoreSearcher searcher;

    //Criteria
    private String title;
    private Category cat;

    //================================================== Creation

    public HelperCriteria(PageBrowser browser)
    {
        //UI Elements
        searchBar = browser.findViewById(R.id.searchBar);
        categories = browser.findViewById(R.id.categoriesDropDown);

        //Class Links
        this.searcher = StoreHelper.getStoreSearcher();

        //Get Criteria
        readNewCriteria();
    }

    //================================================== Interface

    /**
     * #### public List<Item> requestSearch()
     * Makes a request for a database query.
     */
    public SearchResult requestSearch()
    {
        try {
            return searcher.getItems(makeCriteriaSet());
        }
        catch (UIException e) {
            Toast.makeText(searchBar.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            return new SearchResult();
        }
    }

    /**
     * #### public void readNewCriteria()
     * Reads search criteria from the UI.
     */
    public void readNewCriteria()
    {
        title = searchBar.getText().toString().trim();
        cat = Category.values()[categories.getSelectedItemPosition()];
    }

    //================================================== Internals

    //Creates a CriteriaSet object.
    private CriteriaSet makeCriteriaSet()
    {
        return new CriteriaSet
                (
                        PageBrowser.getPageNum(),
                        PageBrowser.getPageSize(),
                        title,
                        cat
                );
    }
}
