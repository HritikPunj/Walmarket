package comp3350.Innovator2.application;

import android.content.Context;
import android.content.res.Resources;

public class Main {

    private static String dbName = "WalmarketDB";

    private static Resources res;

    private static Context context;


    public static void main(String[] args)
    {
        System.out.println("All done");
    }

    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }

    public static Resources getResources() {
        return res;
    }

    public static void setResources(final Resources resources)
    {
        res = resources;
    }

    public static void setContext(final Context con)
    {
        context = con;
    }

    public static Context getContext()
    {
        return context;
    }
}
