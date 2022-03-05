/**
 *
 * top bar that contains all of the drop down menus and main functions
 *
 *
 * @Author: Fehmi Neffati
 *
 */

package TopBar;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    JMenu Add = new JMenu("Add|যোগ করুন");

    JMenu Insert = new JMenu("Insert|ঢোকান");
    JMenu Draw = new JMenu("Draw|আঁকা");
    JMenu LoadProject = new JMenu("Open Project|লোড প্রকল্প");
    JMenu Save = new JMenu("Save|রক্ষা");
    JMenu Share = new JMenu("Share|ভাগ");
    JMenu Print = new JMenu("Print|ছাপা");
    JMenu Present = new JMenu("Present|বর্তমান");



    public TopBar(){

        this.setBackground(Color.decode("#B2D5DB"));
        JMenuBar aa = new JMenuBar();
        aa.setBackground(Color.decode("#B2D5DB"));

        Add.add(new JMenuItem("Add New Slide"));
        Add.add(new JMenuItem("Add Slides Numbers"));

        Insert.add(new JMenuItem("Image"));
        Insert.add(new JMenuItem("Text"));
        Insert.add(new JMenuItem("Equation"));
        Insert.add(new JMenuItem("Link"));
        Insert.add(new JMenuItem("Code"));
        Insert.add(new JMenuItem("Bullet List"));

        Draw.add(new JMenuItem("Free Draw"));

        LoadProject.add(new JMenuItem("Open project from Device"));
        LoadProject.add(new JMenuItem("Load Template"));

        Save.add(new JMenuItem("As Project"));
        Save.add(new JMenuItem("As Template"));

        Share.add(new JMenuItem("Via Email"));
        Share.add(new JMenuItem("Via Social Media"));

        Print.add(new JMenuItem("Print As PDF"));

        Present.add(new JMenuItem("Present Full Screen"));



        aa.add(Add);aa.add(Insert);aa.add(Draw);aa.add(LoadProject);aa.add(Save);;aa.add(Share);aa.add(Print);aa.add(Present);
        this.add(aa);

    }

}
