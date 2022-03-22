package SlideMgr;


import java.io.File;

import javax.swing.filechooser.FileFilter;

/**When you open up a file explorer window, this filters down the available items to .template formats.
 * Author: Robert
 */
public class TemplateFileFilter extends FileFilter
{
    /**
     *
     * @param f - checks each file and only displays files ending in .template in the file explorer window
     * @return - returns a bool to check if the file is among the filtered formats
     *
     *
     */
    public boolean accept(File f)
    {
        if (f.getName().toLowerCase().endsWith(".template")) return true;


        if (f.isDirectory()) return true;
        return false;
    }

    public String getDescription() {
        return "Template files";
    }

}