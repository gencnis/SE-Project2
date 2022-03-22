package Utilities;

import SlideMgr.Slide;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

/**Contains methods for processing images for use with ImgLabel Class.
 * Includes Resizing, converting bufferedImage to Icon, and changing Alpha value for background images
 *
 * Author: Robert
 */
public class ImageUtilities
{




    /**Resizes the image based on provided image and dimensions
     *
     * @param originalImage - the image you wish to resize
     * @param width - desired width of resized image
     * @param height - desired height of resized image
     * @return - returns the image resized to provided height and width
     */
    public static BufferedImage rescale(BufferedImage originalImage, int width, int height)
    {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    //primes image for use

    /**Resizes image and converts it to an icon to use with ImgLabel on a slide
     * Calls addImageToSlide() to add the processed image to the specified slide
     *
     * @param reference - the reference to the image file
     * @param width - desired width of image on slide
     * @param height - desired height of image on slide
     * @param slide - the target slide that will have an image added on it
     */
    public static void setTargetImage(File reference, int width, int height, Slide slide)
    {
        File targetFile;
        ImageIcon icon; //what you load your image into when you get it
        BufferedImage targetImg;
        try
        {
           targetFile = reference;

//TODO: not sure if we should store this for saving
            targetImg = rescale(ImageIO.read(reference), width, height); //sets image to desired size
            icon=new ImageIcon(targetImg); //puts image into a use able format for JPanels
            slide.addImageToSlide(icon); //adds image to the slide
        } catch (IOException ex)
        {
            System.out.println("File not found or invalid.");
            // Logger.getLogger(MainAppFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /** Converts image to an icon to use with ImgLabel on a slide. Uses image's original size.
     * Calls addImageToSlide() to add the processed image to the specified slide
     *
     * @param reference - the reference to the image file
     * @param slide - the target slide that will have an image added on it
     */
    public static void setTargetImage(File reference,  Slide slide)
    {
        File targetFile;
        ImageIcon icon; //what you load your image into when you get it
        BufferedImage targetImg;
        try
        {
            targetFile = reference;

//TODO: not sure if wanna store this for saving
            targetImg = ImageIO.read(reference); //sets image to desired size
            icon=new ImageIcon(targetImg); //puts image into a use able format for JPanels
            slide.addImageToSlide(icon); //adds image to the slide
        } catch (IOException ex)
        {
            System.out.println("File not found or invalid.");
            // Logger.getLogger(MainAppFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    /**UNUSED
     * esizes an image to the target slide's dimensions and converts image to an icon to use with ImgLabel.
     * Reduces alpha of image to make it more transparent to not stand out so much.
     * Calls loadBackgroundImage() to add the processed image to the specified slide's background
     *
     * @param reference - the reference to the image file
     * @param slide - the target slide that will have an image added on it
     */
    public static void setTargetBackground(File reference, Slide slide)
    {
        File targetFile;
        ImageIcon icon; //what you load your image into when you get it
        BufferedImage targetImg;
        try
        {
            targetFile = reference;

//TODO: not sure if wanna store this for saving
            slide.setBackGround(ImageIO.read(reference));
            targetImg = rescale(ImageIO.read(reference), slide.getWidth(), slide.getHeight()); //sets image to desired size
            BufferedImage newImage = new BufferedImage(targetImg.getWidth(), targetImg.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = newImage.createGraphics();
            g.drawImage(targetImg, 0, 0, targetImg.getWidth(), targetImg.getHeight(), null);
            g.dispose();
            newImage = setAlpha(.5, newImage);
            icon=new ImageIcon(newImage); //puts image into a use able format for JPanels

            slide.loadBackgroundImage(icon); //adds image to the slide
        } catch (IOException ex)
        {
            System.out.println("File not found or invalid.");
            // Logger.getLogger(MainAppFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //UNUSED - Supposed to scale background to full screen
    public static void presentTargetBackground()
    {

    }

    /**UNUSED
     * Resizes an image to the target slide's dimensions and converts image to an icon to use with ImgLabel.
     * Reduces alpha of image to make it more transparent to not stand out so much.
     * Calls loadBackgroundImage() to add the processed image to the specified slide's background
     *
     * @param bi -  the image we want to set as a background
     * @param slide - the target slide that will have an image added on it
     */
    public static void setTargetBackground(BufferedImage bi, Slide slide)
    {

        ImageIcon icon; //what you load your image into when you get it
        BufferedImage targetImg;


//TODO: not sure if wanna store this for saving
            targetImg = rescale(bi, slide.getWidth(), slide.getHeight()); //sets image to desired size
            BufferedImage newImage = new BufferedImage(targetImg.getWidth(), targetImg.getHeight(), BufferedImage.TYPE_INT_ARGB);

            //changes image typ to something with an alpha value
           Graphics2D g = newImage.createGraphics();
            g.drawImage(targetImg, 0, 0, targetImg.getWidth(), targetImg.getHeight(), null);
            g.dispose();
            newImage = setAlpha(.5, newImage);
            //slide.setBackGround(newImage);
            icon=new ImageIcon(newImage); //puts image into a use able format for JPanels
            slide.loadBackgroundImage(icon); //adds image to the slide

    }

    /**UNUSED
     * Resizes an image to the specified dimensions and converts image to an icon to use with ImgLabel.
     * Reduces alpha of image to make it more transparent to not stand out so much.
     * Calls loadBackground() to add the processed image to the specified slide's background
     *
     * Purpose was to use for scaling slide backgrounds for full screen
     *
     * @param bi - the  image we want to set as a background
     * @param slide - the target slide that will have an image added on it
     */
    public static void setTargetBackground(BufferedImage bi, int width, int height, Slide slide)
    {

        ImageIcon icon; //what you load your image into when you get it
        BufferedImage targetImg;


//TODO: not sure if wanna store this for saving
        targetImg = rescale(bi, width, height); //sets image to desired size
        BufferedImage newImage = new BufferedImage(targetImg.getWidth(), targetImg.getHeight(), BufferedImage.TYPE_INT_ARGB);

        //changes image typ to something with an alpha value
        Graphics2D g = newImage.createGraphics();
        g.drawImage(targetImg, 0, 0, targetImg.getWidth(), targetImg.getHeight(), null);
        g.dispose();
        newImage = setAlpha(.5, newImage);
        slide.setBackGround(newImage);
        icon=new ImageIcon(newImage); //puts image into a use able format for JPanels
        slide.loadBackgroundImage(icon); //adds image to the slide

    }


    /**Reduces alpha value of an image to specified proportion making it more or less transparent.
     *
     * @param changeAmt - from 0 - 1 percent of alpha retained in image
     * @param bi - target image to edit
     * @return
     */
    public static BufferedImage setAlpha(double changeAmt, BufferedImage bi)
    {
        System.out.print("working");
        for (int cx=0;cx<bi.getWidth();cx++) {
            for (int cy=0;cy<bi.getHeight();cy++)
            {
                int argb = bi.getRGB(cx, cy); //always returns TYPE_INT_ARGB
                int alpha = (argb >> 24) & 0xff;  //isolate alpha

                alpha *= changeAmt; //similar distortion to tape saturation (has scrunching effect, eliminates clipping)
                alpha &= 0xff;      //keeps alpha in 0-255 range

                argb &= 0x00ffffff; //remove old alpha info
                argb |= (alpha << 24);  //add new alpha info
                bi.setRGB(cx, cy, argb);

            }

        }

        return bi;
    }
}
