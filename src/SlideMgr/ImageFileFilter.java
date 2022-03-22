package SlideMgr;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**When you open up a file explorer window, this filters down the available items to jpeg and png formats.
 * Author: Robert
 */

public class ImageFileFilter extends FileFilter
{
    /**
     *
     * @param f - checks each file and only displays files ending in jpeg, jpg, or png in the file explorer window
     * @return - returns a bool to check if the file is among the filtered formats
     *
     *
     */
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