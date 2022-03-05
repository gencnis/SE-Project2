package SlideMgr;

import java.io.File;

import javax.swing.filechooser.FileFilter;

//when you open up a file explorer window, this filters down the available items to jpeg and png formats.
public class ImageFileFilter extends FileFilter
{
    public boolean accept(File f) {
        if (f.getName().toLowerCase().endsWith(".jpeg")) return true;
        if (f.getName().toLowerCase().endsWith(".jpg")) return true;
        if (f.getName().toLowerCase().endsWith(".png")) return true;

        if (f.isDirectory()) return true;
        return false;
    }

    public String getDescription() {
        return "JPEG files";
    }
}