package comp3350.Innovator2.data;

import java.util.List;

import comp3350.Innovator2.objects.utils.Category;
import comp3350.Innovator2.objects.Item;
import comp3350.Innovator2.logic.exceptions.DataException;

public interface IItemPersistence {
    List<Item> getAllItems() throws DataException;

    List<Item> getSelectedItems(String searchString) throws DataException;

    List<Item> getSelectedItems(Category searchCategory) throws DataException;

    List<Item> getSelectedItems(String searchString, Category searchCategory) throws DataException;

    Item getItem(int itemID) throws DataException;

    void updateStock(int itemID, int amount) throws DataException;

   }
