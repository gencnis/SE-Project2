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
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TopBar extends JPanel implements java.io.Serializable
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
        UIManager.put("MenuBar.background", Color.ORANGE);



        JMenuItem newSlide = new JMenuItem("Add New Slide");
        newSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.newSlide("WHITE");
            }
        });
        Add.add(newSlide);

        JMenuItem paintedSlide = new JMenuItem("Add New Painted Slide");
        paintedSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] possibleValues = { "WHITE", "RED", "BLUE", "GREEN", "YELLOW"};

                Object selectedValue = JOptionPane.showInputDialog(null,
                        "Choose A Slide Color Please: ", "Input",
                        JOptionPane.INFORMATION_MESSAGE, null,
                        possibleValues, possibleValues[0]);

                System.out.println(selectedValue.toString());
                MainFrame.newSlide(selectedValue.toString());
            }
        });
        Add.add(paintedSlide);

        JMenuItem removed = new JMenuItem("Remove Current Slide");
        removed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.removed();
            }
        });
        Add.add(removed);



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

        JMenuItem toggleSlideNumber = new JMenuItem("Display/Hide Slide Number");
        toggleSlideNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                MainFrame.toggleSlideNumber();
                System.out.println("Slide Number Toggled");
            }
        });
        Insert.add(toggleSlideNumber);


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


        // Button
        JMenuItem templateLoader = new JMenuItem("As a Template");
        templateLoader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.loadTemplate(e);
            }
        });
        LoadProject.add(templateLoader);

        // Button
        JMenuItem saver = new JMenuItem("As a project");
        saver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.saveAsProject(e);
            }
        });
        Save.add(saver);

        // Button
        JMenuItem templateSaver = new JMenuItem("As a Template");
        templateSaver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.saveTemplate(e);
            }
        });

        Save.add(templateSaver);
        // Button
        Share.add(new JMenuItem("Via Email"));
        Share.add(new JMenuItem("Via Social Media"));


        // Button
        Print.add(new JMenuItem("Print As PDF"));




        // Button
        JMenuItem PRESENTER = new JMenuItem("Present Full Screen");
        PRESENTER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.fullScreen();
            }
        });
        Present.add(PRESENTER);




        aa.add(Add);aa.add(Insert);aa.add(Draw);aa.add(LoadProject);aa.add(Save);;aa.add(Share);aa.add(Print);aa.add(Present);
        this.add(aa);

    }

}
