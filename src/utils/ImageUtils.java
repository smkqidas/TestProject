package utils;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ImageUtils {
     final static int DRESS_COLOR_R = 156;
     final static int DRESS_COLOR_G = 182;
     final static int DRESS_COLOR_B = 178;
     final static int DRESS_COLOR_A = 1;


    public static List<ImgMetaData> createCoordinatesFilePath(String imageId){
        System.out.println("Hello");
        BufferedImage img = null;
        File f;
        try{
            f = new File("/Users/sadiqmk/Documents/MyProject/images/skirts/" + imageId + ".png");
            img = ImageIO.read(f);
        }catch(IOException e){
            System.out.println(e);
        }

        //get image width and height
        int width = img.getWidth();
        int height = img.getHeight();


        System.out.println("H : " + height + " W : " + width);

        List<ImgMetaData> coordinatesList = new ArrayList();
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width-1; x++) {
//                System.out.println(x + "," + y);
                int currentColor = img.getRGB(x, y);
                int nextColor = img.getRGB(x + 1, y);
                if (!isColorEqual(currentColor,nextColor)) {
                    System.out.println("Current: " + currentColor + " NextColor: " + nextColor);
                    ImgMetaData imgMetaData = new ImgMetaData();
                    if (isColorEqualToRGBA(currentColor, DRESS_COLOR_R, DRESS_COLOR_G, DRESS_COLOR_B, DRESS_COLOR_A)) {
                        imgMetaData.setX(x);
                        x++;
                        System.out.println("in if");
                    }
                    else if (isColorEqualToRGBA(nextColor, DRESS_COLOR_R, DRESS_COLOR_G, DRESS_COLOR_B, DRESS_COLOR_A)) {
                        imgMetaData.setX(x + 1);
                        System.out.println("in else if");
                        x++;
                    }
                    else
                    {
                        continue;
                    }
                    imgMetaData.setY(y);
                    System.out.println("Setting x : " + imgMetaData.getX() + " y : " + imgMetaData.getY());
                    coordinatesList.add(imgMetaData);
                }
            }
        }
        System.out.println("Completed..");
        return coordinatesList;
    }

    public static boolean isColorEqual(int color1, int color2){
        //Get RGBA of color1
        int color1_a = (color1>>24) & 0xff;
        int color1_r = (color1>>16) & 0xff;
        int color1_g = (color1>>8) & 0xff;
        int color1_b = color1 & 0xff;

        //Get RGBA of color2
        int color2_a = (color2>>24) & 0xff;
        int color2_r = (color2>>16) & 0xff;
        int color2_g = (color2>>8) & 0xff;
        int color2_b = color2 & 0xff;

        if ((color1_a == color2_a) && (color1_b == color2_b) && (color1_g == color2_g) && (color1_r == color2_r))
            return true;
        return false;
    }

    public static boolean isColorEqualToRGBA(int color1, int r, int g, int b, int a){
        int color1_a = (color1>>24) & 0xff;
        int color1_r = (color1>>16) & 0xff;
        int color1_g = (color1>>8) & 0xff;
        int color1_b = color1 & 0xff;
        System.out.println("a : " + color1_a + " r : " + color1_r + " g : " + color1_g + " b : " + color1_b);
        if ((color1_b == b) && (color1_g == g) && (color1_r == r))
            return true;
        return false;

    }

}