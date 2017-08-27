package com.codecamel.file;
import java.io.*;

/**
 * Created by Yifan on 08/27/17.
 */
public class CopyFiles {

    public static void main(String[] args) throws IOException {

        //Step 1 - Create a file Object for the the directories to read and write.
        File inputDirectory = new File("/Users/yifan/Desktop/input");
        File outputDirectory = new File("/Users/yifan/Desktop/output");

        outputDirectory.mkdir();

        //Step 2 -  Read the input files and Iterate the files
        File[] files = inputDirectory.listFiles();

        for (File eachfile : files) {
            if (eachfile.isFile()) {
                File dest = new File(
                        outputDirectory.getPath()
                                + File.separator
                                + eachfile.getName());
                //Step 3 : Create a Output Stream to write the files.

                OutputStream oStream = new FileOutputStream(dest);
                byte[] buffer = new byte[(int) eachfile.length()];
                FileInputStream iStream = new FileInputStream(eachfile);
                iStream.read(buffer);
                try {
                    oStream.write(buffer);
                } finally {
                    oStream.close();
                    iStream.close();
                }
            }
        }

    }

}