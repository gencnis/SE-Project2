package SlideMgr;


import java.io.File;

import javax.swing.filechooser.FileFilter;

//when you open up a file explorer window, this filters down the available items to .ser formats.
public class ProjectFileFilter extends FileFilter
{
    public boolean accept(File f)
    {
        if (f.getName().toLowerCase().endsWith(".ser")) return true;


        if (f.isDirectory()) return true;
        return false;
    }

    public String getDescription() {
        return "SER files";
    }

}