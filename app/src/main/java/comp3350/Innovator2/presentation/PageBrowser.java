package comp3350.Innovator2.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp3350.Innovator2.R;
import comp3350.Innovator2.data.utils.DBHelper;
import comp3350.Innovator2.objects.SearchResult;
import comp3350.Innovator2.objects.utils.Category;

/**
 * #### PageBrowser
 * Receives primary input from item browser.
 * Controls display of items inside browser.
 */
public class PageBrowser extends AppCompatActivity {

    //================================================== Variables

    //UI Elements
    private RecyclerView browserRV;
    private TextView pageNumText;
    private Button nextButton;
    private Button prevButton;
    private EditText searchBar;
    private Spinner categories;

    private boolean usingStub = false;

    //Class Links
    private HelperCriteria criteria;
    private HelperUser helperUser;

    //Page Data
    private static final int pageSize = 10;
    private static int currPage = 0;
    private static SearchResult items;

    //================================================== Creation

    public void setUsingStub(boolean newStub) { usingStub = newStub; }

    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        //Display the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_browser);

        //Set up database
        Bundle extras = this.getIntent().getExtras();
        //STUB
        if (extras != null && extras.getString("USING_STUB").equals("TRUE")) DBHelper.setUpStub(this, getResources());

        //HSQL
        else DBHelper.copyDatabaseToDevice(this, getResources());

        //UI Elements
        browserRV = findViewById(R.id.rvBrowser);
        pageNumText = findViewById(R.id.pageTextView);
        nextButton = findViewById(R.id.nextPageButton);
        prevButton = findViewById(R.id.lastPageButton);
        searchBar = findViewById(R.id.searchBar);
        categories = findViewById(R.id.categoriesDropDown);

        searchBar.setOnEditorActionListener(searchBarListener);
        categories.setOnItemSelectedListener(categoryListener);

        //Setup Categories
        Category[] catVals = Category.values();
        CategoriesEnumAdapter adapter = new CategoriesEnumAdapter(this, android.R.layout.simple_spinner_dropdown_item, catVals);
        categories.setAdapter(adapter);

        //Class Links
        criteria = new HelperCriteria(this);
        helperUser = new HelperUser(this);

        //Set up user buttons
        helperUser.updateButtons();

        //Load a set of items
        onClickSearchButton(null);
    }

    @Override
    public void onResume ()
    {
        //Refresh
        super.onResume();
        helperUser.updateButtons();
        updateBrowser();
    }

    //================================================== UI Input

    //---------------------------------------- PageBrowser Pages

    /**
     * #### public void onClickNextButton(View)
     * Loads next page of items into browser.
     */
    public void onClickNextButton (View view)
    {
        currPage++;
        updateBrowser();
        checkPageBounds();
    }

    /**
     * #### public void onClickPrevButton(View)
     * Loads prev page of items into browser.
     */
    public void onClickPrevButton (View view)
    {
        currPage--;
        updateBrowser();
        checkPageBounds();
    }

    /**
     * #### public void onClickSearchButton(View)
     * Loads browser with set of filtered items.
     */
    public void onClickSearchButton (View view)
    {
        criteria.readNewCriteria();
        currPage = 0;
        updateBrowser();
        checkPageBounds();
    }

    //---------------------------------------- Listeners

    //Search if enter is pressed.
    private final TextView.OnEditorActionListener searchBarListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                onClickSearchButton(searchBar);
            }
            return false;
        }
    };

    //Search when new category selected.
    private final AdapterView.OnItemSelectedListener categoryListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            onClickSearchButton(null);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    //---------------------------------------- Other

    //Clear the search bar.
    public void onClearSearch(View view) {
        searchBar.setText("");
        onClickSearchButton(searchBar);
    }

    //Change page to cart.
    public void onGoToCartPage(View view) {
        Intent intent = new Intent(PageBrowser.this, PageCart.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    //================================================== Interface

    /**
     * #### public int getPageNum()
     * Returns the current page number.
     */
    public static int getPageNum()
    {
        return currPage;
    }

    /**
     * #### public int getPageSize()
     * Returns the current page size.
     */
    public static int getPageSize()
    {
        return pageSize;
    }

    //================================================== Internals

    //Enable or disable page buttons.
    private void checkPageBounds()
    {
        //Enable or disable prevButton.
        if (currPage != 0) {
            prevButton.setEnabled(true);
            prevButton.setVisibility(View.VISIBLE);
        } else {
            prevButton.setEnabled(false);
            prevButton.setVisibility(View.INVISIBLE);
        }
        //Enable or disable nextButton.
        if (items.hasNextPage()) {
            nextButton.setEnabled(true);
            nextButton.setVisibility(View.VISIBLE);
        } else {
            nextButton.setEnabled(false);
            nextButton.setVisibility(View.INVISIBLE);
        }
    }

    //Display new page of items.
    private void updateBrowser()
    {
        //Grab list of items.
        items = criteria.requestSearch();

        //Fill browser with items.
        ItemRVAdapter browserRVA = new ItemRVAdapter(this, items.getItemList(), 0);
        browserRV.setAdapter(browserRVA);
        browserRV.setLayoutManager(new LinearLayoutManager(this));

        //Update page text.
        String pageText = "Page " + (currPage + 1);
        pageNumText.setText(pageText);
    }
}
