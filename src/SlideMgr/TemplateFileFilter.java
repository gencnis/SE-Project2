package SlideMgr;


import java.io.File;

import javax.swing.filechooser.FileFilter;

public class TemplateFileFilter extends FileFilter
{
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