package comp3350.Innovator2.logic;

import java.util.ArrayList;

import comp3350.Innovator2.application.Services;
import comp3350.Innovator2.logic.exceptions.DataException;
import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.logic.exceptions.DataUIException;
import comp3350.Innovator2.objects.utils.Category;
import comp3350.Innovator2.objects.CriteriaSet;
import comp3350.Innovator2.objects.Item;
import comp3350.Innovator2.objects.SearchResult;

public class StoreSearcher implements IStoreSearcher {

    //================================================== Interface

    /**
     * #### public searchResult getItems()
     * Returns items based on criteria.
     * Decides which query is needed.
     */
    @Override
    public SearchResult getItems(CriteriaSet crit) throws UIException
    {
        try {
            //Use bitmap to decide search type.
            int searchType = 0;
            if (!crit.getItemName().trim().equals(""))
                searchType += 1;
            if (crit.getCategory() != Category.Default)
                searchType += 10;

            //Run the search and return results.
            switch (searchType) {
                case 1: //Search only by name.
                    return packResult(crit, searchItemsByTitle(crit));
                case 10: //Search only by category.
                    return packResult(crit, searchItemsByCategory(crit));
                case 11: //Search by title & category.
                    return packResult(crit, searchByTitleCategory(crit));
                default: //No search criteria.
                    return packResult(crit, getPaginatedItems(
                            crit,
                            (ArrayList<Item>) Services.getItemPersistence().getAllItems())
                    );
            }
        }
        catch (DataException e) {
            throw new DataUIException(e.getMessage());
        }
    }

    //================================================== Internals

    //Pack data into SearchResult object.
    private SearchResult packResult(CriteriaSet crit, ArrayList<Item> list)
    {
        //Check for next page.
        boolean hasNextPage = false;
        if (list.size() > crit.getPageSize())
        {
            hasNextPage = true;
            list.remove(crit.getPageSize());
        }
        return new SearchResult(list, hasNextPage);
    }

    private <T> ArrayList<T> GetSublist(ArrayList<T> initialList, int startInc, int endExc) {
        ArrayList<T> arr = new ArrayList<T>();
        for (int i = startInc; i < endExc; i++)
        {
            arr.add(initialList.get(i));
        }
        return arr;
    }

    /**
     * Takes in a CriteriaSet and ArrayList and returns the subset corresponding to that page number
     * @param crit
     * @return
     */
    private ArrayList<Item> getPaginatedItems(CriteriaSet crit, ArrayList<Item> items) throws DataException {
        int pageSize = crit.getPageSize();
        int pageNum = crit.getPageNum();

        if (items.size() >= ((pageNum + 1) * pageSize) + 1) return GetSublist(items, pageNum * pageSize, ((pageNum + 1) * pageSize) + 1);
        else if (items.size() <= pageNum * pageSize) return new ArrayList<Item>();
        else return GetSublist(items, pageNum * pageSize, items.size());
    }

    //Get a list of items filtered by name.
    private ArrayList<Item> searchItemsByTitle(CriteriaSet crit) throws DataException {
        return getPaginatedItems(
                crit,
                (ArrayList<Item>)Services.getItemPersistence().getSelectedItems(crit.getItemName())
        );
    }

    //Get a list of items filtered by category.
    private ArrayList<Item> searchItemsByCategory(CriteriaSet crit) throws DataException {
        return getPaginatedItems(
                crit,
                (ArrayList<Item>)Services.getItemPersistence().getSelectedItems(crit.getCategory())
        );
    }

    private ArrayList<Item> searchByTitleCategory(CriteriaSet crit) throws DataException {
        return getPaginatedItems(
                crit,
                (ArrayList<Item>)Services.getItemPersistence().getSelectedItems(
                        crit.getItemName(),
                        crit.getCategory()
                )
        );
    }
}
