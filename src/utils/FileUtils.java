package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtils{

    public static void write(String content, String filename) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            final File file = new File(filename);
            final File parent_directory = file.getParentFile();

            if (null == parent_directory) {
                throw new RuntimeException("Path does not exists.");
            }

            fw = new FileWriter(filename);
            bw = new BufferedWriter(fw);
            bw.write(content);

            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

    public static boolean isFileExists(String fullFilePath){
        final File file = new File(fullFilePath);
        if(file.exists() && !file.isDirectory()) {
            return true;
        }
        return false;
    }
}