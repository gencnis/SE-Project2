package Utilities;

import SlideMgr.Slide;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtilities
{


    //resizes the image based on provided image and dimensions
    public static BufferedImage rescale(BufferedImage originalImage, int width, int height)
    {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    //primes image for use
    public static void setTargetImage(File reference, int width, int height, Slide currentSlide)
    {
        File targetFile;
        ImageIcon icon; //what you load your image into when you get it
        BufferedImage targetImg;
        try
        {
           targetFile = reference;

//TODO: not sure if wanna store this for saving
            targetImg = rescale(ImageIO.read(reference), width, height); //sets image to desired size
            icon=new ImageIcon(targetImg); //puts image into a use able format for JPanels
            currentSlide.addImageToSlide(icon); //adds image to the slide
        } catch (IOException ex)
        {
            System.out.println("File not found or invalid.");
            // Logger.getLogger(MainAppFrame.class.getName()).log(Level.SEVERE, null, ex);
        }





    }
}
