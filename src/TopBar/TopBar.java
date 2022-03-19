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
import Item.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

<<<<<<< Updated upstream
public class TopBar extends JPanel
=======
/**
 * The Main GUI For the program
 * Has options to add/remove slides, add objects, and save projects and templates
 */
public class TopBar extends JPanel implements java.io.Serializable
>>>>>>> Stashed changes
{


    //each JMenu is a drop down menu for each option
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


    //initializes all buttons
    public TopBar(MainFrame mainFrame)
    {
<<<<<<< Updated upstream
=======
        //adds all submenus to list
        addItems.add(Add);addItems.add(Insert);addItems.add(Remove);addItems.add(Draw);addItems.add(LoadProject);addItems.add(Save);
        //addItems.add(Share);
        // addItems.add(Print);
        addItems.add(Present);

>>>>>>> Stashed changes

        //sets background and layout of top bar
        this.setBackground(Color.decode("#B2D5DB"));
        JMenuBar aa = new JMenuBar();
        aa.setBackground(Color.decode("#B2D5DB"));
        aa.setPreferredSize(new Dimension(860,50));
        aa.setBorder(BorderFactory.createEtchedBorder(0));
<<<<<<< Updated upstream
=======
        UIManager.put("MenuBar.background", Color.ORANGE);



        //new slide button
        JMenuItem newSlide = new JMenuItem("Add New Slide");
        newSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.newSlide("WHITE");
            }
        });
        Add.add(newSlide);

        //painted slide button
        JMenuItem paintedSlide = new JMenuItem("Add New Painted Slide");
        paintedSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //askes which value you wish to paint the slide background

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

        //adds remove current slide
        JMenuItem removed = new JMenuItem("Remove Current Slide");
        removed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.removed();
            }
        });
        Remove.add(removed);

>>>>>>> Stashed changes

        Add.add(new JMenuItem("Add New Slide"));


<<<<<<< Updated upstream
        //TODO: DO all your menu items like this. Then just call the action out of mainfram that you need done.
=======
        //adds Insert image button
>>>>>>> Stashed changes
        JMenuItem image = new JMenuItem("Image");
        image.addActionListener(new ActionListener()
                                    {
                                    //put in whatever dimensions you want
                                    public void actionPerformed(ActionEvent e)
                                    {
//          TODO:  //specify your dimensions somehow before putting them here
                                        MainFrame.loadImage(e, 100, 100);
                                    }
                                });

        Insert.add(image);

<<<<<<< Updated upstream
=======
        // adds insert text button
>>>>>>> Stashed changes
        JMenuItem addText = new JMenuItem("Text");
        addText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Before");
                MainFrame.insertText();
                System.out.println("After");
            }
        });
        Insert.add(addText);

        //adds Hide/Show slide number button
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
<<<<<<< Updated upstream
        Insert.add(new JMenuItem("Code"));
        Insert.add(new JMenuItem("Bullet List"));
=======
        Insert.add(new JMenuItem("Code"));*/

        // adds bullet list button
        JMenuItem bullet = new JMenuItem("Bullet List");
        bullet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.bullet();
            }
        });
        Insert.add(bullet);
>>>>>>> Stashed changes

        Draw.add(new JMenuItem("Free Draw"));

        LoadProject.add(new JMenuItem("Open project from Device"));
        LoadProject.add(new JMenuItem("Open Template"));

<<<<<<< Updated upstream
=======
        // adds free draw button
        JMenuItem draw = new JMenuItem("Free Draw");
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.draw();
            }
        });
        Draw.add(draw);

        // adds cursor button
        JMenuItem stopDraw = new JMenuItem("Cursor : Stop Drawing");
        stopDraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.stopDrawing();
            }
        });
        Draw.add(stopDraw);

        // adds eraser button
        JMenuItem eraser = new JMenuItem("Eraser");
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.eraser();
            }
        });
        Draw.add(eraser);

        // adds load project button
        JMenuItem loader = new JMenuItem("As a project");
        loader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.loadFromComputer();
            }
        });
        LoadProject.add(loader);


        // adds load template button
        JMenuItem templateLoader = new JMenuItem("As a Template");
        templateLoader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.loadTemplate(e);
            }
        });
        LoadProject.add(templateLoader);

        // adds save project button
>>>>>>> Stashed changes
        JMenuItem saver = new JMenuItem("As a project");
        saver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.saveAsProject();
            }
        });
        Save.add(saver);
        Save.add(new JMenuItem("As Template"));

<<<<<<< Updated upstream
        Share.add(new JMenuItem("Via Email"));
        Share.add(new JMenuItem("Via Social Media"));
=======
        // adds save template button
        JMenuItem templateSaver = new JMenuItem("As a Template");
        templateSaver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.saveTemplate(e);
            }
        });

        Save.add(templateSaver);
        // Button
      /*  Share.add(new JMenuItem("Via Email"));
        Share.add(new JMenuItem("Via Social Media"));*/


        // Button
       // Print.add(new JMenuItem("Print As PDF"));




        // adds present button
        JMenuItem PRESENTER = new JMenuItem("Present Full Screen");
        PRESENTER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.fullScreen();
            }
        });
        Present.add(PRESENTER);

        /*for(int i = 0; i < addItems.size(); i ++){
            JMenu nM = new JMenu();
            nM.setBackground(Color.decode("#B2D5DB"));
            addItems.add(i + 1, nM);
        }
        */
>>>>>>> Stashed changes

        Print.add(new JMenuItem("Print As PDF"));

        Present.add(new JMenuItem("Present Full Screen"));


<<<<<<< Updated upstream
=======
        //addds all dropdowns
        JMenu nM1 = new JMenu();
        JMenu nM2 = new JMenu();
        JMenu nM3 = new JMenu();
        JMenu nM4 = new JMenu();
        JMenu nM5 = new JMenu();
        JMenu nM6 = new JMenu();
        JMenu nM7 = new JMenu();
        JMenu nM8 = new JMenu();
>>>>>>> Stashed changes

        aa.add(Add);aa.add(Insert);aa.add(Draw);aa.add(LoadProject);aa.add(Save);;aa.add(Share);aa.add(Print);aa.add(Present);
        this.add(aa);

    }

}
