package comp3350.Innovator2.logic;

import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.objects.CriteriaSet;
import comp3350.Innovator2.objects.SearchResult;

public interface IStoreSearcher {
    //Must decide which search to run based on criteria.
    SearchResult getItems(CriteriaSet critSet) throws UIException;
}
