package handlers;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.FileUtils;
import utils.ImageUtils;
import utils.ImgMetaData;

public class DesignToolHandler {
    final static String DEFAULT_IMG_PATH = "/Users/sadiqmk/Documents/MyProject/images/jsonFiles/";

    public static void main(String[] args) {
        System.out.println(getCoordinatesFilePath("a1001"));
    }

    public static String getCoordinatesFilePath(String imgId){
        //Check if ImagesJson exists?
        // If yes return file path.
        //If No then call
        String jsonCoordinatesPath = DEFAULT_IMG_PATH + imgId + ".json";

//        if (FileUtils.isFileExists(jsonCoordinatesPath)){
//            return jsonCoordinatesPath;
//        }
//        else {
            ImageUtils imageUtils = new ImageUtils();
            List<ImgMetaData> coordinateList = imageUtils.createCoordinatesFilePath(imgId);
            String coordinatesJsonString = convertToString(coordinateList);
            FileUtils.write(coordinatesJsonString, jsonCoordinatesPath);
            return jsonCoordinatesPath;
//        }
    }

    private static String convertToString(List<ImgMetaData> list){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException("Exception caught while parsng json obj");
        }
    }
}
