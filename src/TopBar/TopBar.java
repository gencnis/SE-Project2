/**
 *
 * top bar that contains all of the drop down menus and main functions
 *
 *
 * @Author: Fehmi Neffati
 *
 */

package TopBar;

import FullWindow.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TopBar extends JPanel
{


    JMenu Add = new JMenu("Add|যোগ করুন");
    //TODO: Format stuff like this so I can access all your buttons
    ArrayList<JMenuItem> addItems;

    JMenu Insert = new JMenu("Insert|ঢোকান");
    JMenu Draw = new JMenu("Draw|আঁকা");
    JMenu LoadProject = new JMenu("Open Project|লোড প্রকল্প");
    JMenu Save = new JMenu("Save|রক্ষা");
    JMenu Share = new JMenu("Share|ভাগ");
    JMenu Print = new JMenu("Print|ছাপা");
    JMenu Present = new JMenu("Present|বর্তমান");



    public TopBar(MainFrame mainFrame)
    {

        this.setBackground(Color.decode("#B2D5DB"));
        JMenuBar aa = new JMenuBar();
        aa.setBackground(Color.decode("#B2D5DB"));
        aa.setPreferredSize(new Dimension(860,50));
        aa.setBorder(BorderFactory.createEtchedBorder(0));

        // Button
        JMenuItem newSlide = new JMenuItem("Add New Slide");
        newSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.newSlide();
            }
        });
        Add.add(newSlide);

        // Button
        Add.add(new JMenuItem("Add Slides Numbers"));

        // Button
        JMenuItem image = new JMenuItem("Image");
        image.addActionListener(new ActionListener()
        {
            //put in whatever dimensions you want
            public void actionPerformed(ActionEvent e)
            {
                //TODO: specify your dimensions somehow before putting them here
                MainFrame.loadImage(e, 100, 100);
            }
        });
        Insert.add(image);

        // Button
        JMenuItem addText = new JMenuItem("Text");
        addText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.insertText();
            }
        });
        Insert.add(addText);

        // Button
        Insert.add(new JMenuItem("Equation"));
        Insert.add(new JMenuItem("Link"));
        Insert.add(new JMenuItem("Code"));
        Insert.add(new JMenuItem("Bullet List"));


        // Button
        JMenuItem draw = new JMenuItem("Free Draw");
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.draw();
            }
        });
        Draw.add(draw);

        // Button
        JMenuItem stopDraw = new JMenuItem("Cursor : Stop Drawing");
        stopDraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.stopDrawing();
            }
        });
        Draw.add(stopDraw);

        // Button
        JMenuItem eraser = new JMenuItem("Eraser");
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.eraser();
            }
        });
        Draw.add(eraser);

        // Button
        JMenuItem loader = new JMenuItem("As a project");
        loader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.loadFromComputer();
            }
        });
        LoadProject.add(loader);
        LoadProject.add(new JMenuItem("Open Template"));

        // Button
        JMenuItem saver = new JMenuItem("As a project");
        saver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.saveAsProject();
            }
        });
        Save.add(saver);

        // Button
        Save.add(new JMenuItem("As Template"));
        // Button
        Share.add(new JMenuItem("Via Email"));
        Share.add(new JMenuItem("Via Social Media"));


        // Button
        Print.add(new JMenuItem("Print As PDF"));


        // Button
        Present.add(new JMenuItem("Present Full Screen"));



        aa.add(Add);aa.add(Insert);aa.add(Draw);aa.add(LoadProject);aa.add(Save);;aa.add(Share);aa.add(Print);aa.add(Present);
        this.add(aa);

    }

}
