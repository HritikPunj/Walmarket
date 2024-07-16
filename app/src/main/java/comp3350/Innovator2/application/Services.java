package comp3350.Innovator2.application;

import comp3350.Innovator2.data.IItemPersistence;
import comp3350.Innovator2.data.IOrderPersistence;
import comp3350.Innovator2.data.IUserPersistence;
import comp3350.Innovator2.data.hsql.ItemPersistenceHSQLDB;
import comp3350.Innovator2.data.hsql.OrderPersistenceHSQLDB;
import comp3350.Innovator2.data.hsql.UserPersistenceHSQLDB;
import comp3350.Innovator2.data.stub.ItemPersistenceStub;
import comp3350.Innovator2.data.stub.OrderPersistenceStub;
import comp3350.Innovator2.data.stub.UserPersistenceStub;

public class Services
{
    //A variable to flag if we want to use the production DB or stub DB
    private static boolean isProd = true;
    private static IItemPersistence itemPersistence = null;
    private static IUserPersistence userPersistence = null;
    private static IOrderPersistence orderPersistence = null;

    public static synchronized IItemPersistence getItemPersistence()
    {
        if (itemPersistence == null)
        {
            if(isProd){
                itemPersistence = new ItemPersistenceHSQLDB(Main.getDBPathName());
            }
            else{
                itemPersistence = new ItemPersistenceStub();
            }
        }
        return itemPersistence;
    }

    public static synchronized IUserPersistence getUserPersistence()
    {
        if (userPersistence == null)
        {
            if(isProd){
                userPersistence = new UserPersistenceHSQLDB(Main.getDBPathName());
            }
            else{
                userPersistence = new UserPersistenceStub();
            }
        }
        return userPersistence;
    }

    public static synchronized IOrderPersistence getOrderPersistence()
    {
        if (orderPersistence == null)
        {
            if(isProd){
                orderPersistence = new OrderPersistenceHSQLDB(Main.getDBPathName());
            }
            else{
                orderPersistence = new OrderPersistenceStub();
            }
        }
        return orderPersistence;
    }
    public static synchronized void setFlag(boolean value){
        isProd = value;
    }

    public static synchronized void setMockUserDB(IUserPersistence userDB){
        userPersistence = userDB;
    }

    /**
     * clean
     *
     * Reset all services so to be reloaded from scratch next time they are referenced
     */
    public static synchronized void clean() {
        itemPersistence = null;
        userPersistence = null;
        orderPersistence = null;
    }

}
