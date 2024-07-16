package comp3350.Innovator2.objects;

import comp3350.Innovator2.objects.utils.Category;

/**
 * #### class CriteriaSet
 * Holds data for a DB query.
 */
public class CriteriaSet
{
    //================================================== Variables

    private int pageNum;
    private int pageSize;
    private String itemName;
    private Category cat;

    //================================================== Construction

    public CriteriaSet(int pageNum, int pageSize, String itemName, Category cat)
    {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.itemName = itemName;
        this.cat = cat;
    }

    //================================================== Getters

    public int getPageNum()
    { return pageNum; }

    public int getPageSize()
    { return pageSize; }

    public String getItemName()
    { return itemName; }

    public Category getCategory()
    { return cat; }
}
