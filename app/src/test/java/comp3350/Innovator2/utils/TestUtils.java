package comp3350.Innovator2.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import comp3350.Innovator2.application.Main;
import comp3350.Innovator2.logic.exceptions.DataException;

public class TestUtils
{
    private static final File DB_SRC = new File("src/main/assets/db/WalmarketDB.script");

    public static File copyDB() throws IOException {
        // Create a temporary file for the database copy
        final File target = File.createTempFile("temp-db", ".script");

        // Set up stream for reading the source file
        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            // Initialize input and output streams
            inStream = Files.newInputStream(DB_SRC.toPath());
            outStream = Files.newOutputStream(target.toPath());

            // Buffer for transferring data
            byte[] buffer = new byte[1024];
            int length;

            // Read from source and write to target
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
        }
        catch(IOException e){
            throw new DataException("Unable to copy the script file. " + e);
        }
        finally{
            // Ensure streams are closed to avoid resource leaks
            if (inStream != null) {
                inStream.close();
            }
            if (outStream != null) {
                outStream.close();
            }
        }

        // Update the path name for the database to the new location
        Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));

        // Return the path to the copied database file
        return target;
    }
}
