package comp3350.Innovator2.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * #### class SearchResult
 * Holds data for query result.
 */
public class SearchResult
{
    //================================================== Variables

    private List<Item> itemList;
    private boolean hasNext;//There are items on next page.

    //================================================== Construction

    public SearchResult(List<Item> itemList, boolean hasNext)
    {
        this.itemList = itemList;
        this.hasNext = hasNext;
    }

    public SearchResult() {
        this.itemList = new ArrayList<Item>();
        this.hasNext = false;
    }


    //================================================== Getters

    public List<Item> getItemList()
    { return itemList; }

    public boolean hasNextPage()
    { return hasNext; }
}
