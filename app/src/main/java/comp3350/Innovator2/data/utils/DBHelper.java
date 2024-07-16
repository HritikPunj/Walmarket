package comp3350.Innovator2.data.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import comp3350.Innovator2.application.Main;
import comp3350.Innovator2.application.Services;
import comp3350.Innovator2.logic.exceptions.DataException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class DBHelper {

    //Static variable to store the name of directory where images are stored
    private static String imageDir = "ItemImages";
    private static int NUM_IMAGES = 58; //Number of images stored in the folder

    public static void copyDatabaseToDevice(Context context, Resources res) {
        final String DB_PATH = "db";

        //Set the production flag in services to true
        Services.setFlag(true);

        String[] assetNames;
        String[] imageNames;
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = context.getAssets();

        try {

            //Copy script to Device
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }
            copyAssetsToDirectory(context, assetNames, dataDirectory);
            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());
            Main.setContext(context);
            Main.setResources(res);

            //Now the DB is created, add the images to it
            //Store images as binary in DB
            addImages(res, context);

        } catch (final IOException ioe) {
            System.out.println("Unable to access application data: " + ioe.getMessage());
        }
    }

    private static void addImages(Resources res, Context context){

        //Add image for all items
        for(int i = 1; i <= NUM_IMAGES; i ++)
        {
            //Name of image in resources
            String imageName = "id_" + i;

            String sql = "UPDATE ITEMS SET image = ? WHERE itemID = ?";

            try (final Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:" + Main.getDBPathName() + ";shutdown=true", "SA", "")) {
                //Form prepared statement
                final PreparedStatement pst = conn.prepareStatement(sql);

                //Get the bitmap of image using id
                Bitmap bitmap = getBitmap(res, context, imageName);

                //If a non null bitmap is returned then add it to DB
                if(bitmap != null)
                {
                    //Create a byte array output stream and convert the bitmap into bytearray
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    //Compress the bitmap into the byte array
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);

                    byte[] bitmapData = byteArray.toByteArray();

                    //Set parameters
                    pst.setBytes(1, bitmapData);
                    pst.setInt(2, i);

                    pst.executeUpdate();
                }

                pst.close();
            } catch (SQLException e) {
                throw new DataException(e);
            }
        }
    }

    public static Bitmap getBitmap(Resources res, Context context, String imageName)
    {
        //If either one of context or resource are null, then return null
        if((context != null)  && (res != null))
        {
            return BitmapFactory.decodeResource(res, getDrawableIdByName(imageName, context));
        }
        else{
            return null;
        }
    }


    private static int getDrawableIdByName(String name, Context context) {
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "drawable", context.getPackageName());
    }

    private static void copyAssetsToDirectory(Context context, String[] assets, File directory) throws IOException {
        AssetManager assetManager = context.getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

    public static Bitmap convertByteArrayToBitmap(byte[] imageData)
    {
        Bitmap image = null;

        if(imageData != null){
            // Create BitmapFactory.Options object
            BitmapFactory.Options options = new BitmapFactory.Options();

            // Set preferred color configuration to ARGB_888
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            image = BitmapFactory.decodeByteArray(imageData, 0, imageData.length, options);
        }

        return image;
    }

    public static void setUpStub(Context context, Resources res) {
        //Set flag for stub DB
        Services.setFlag(false);

        //Set resources and context
        Main.setContext(context);
        Main.setResources(res);
    }
}
