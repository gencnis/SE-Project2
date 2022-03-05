/**
 *
 * This is the full window that contains everything
 *
 * @Author: Fehmi Neffati
 *
 */

package FullWindow;

import BottomNavigation.BottomBar;
import TopBar.TopBar;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame
{
    //SAMPLE BUTTON CLICK LISTENER CODE:
    //needs to declare buttons Here
    private JButton load;


    MainFrame(){
        TopBar tb = new TopBar();
        this.add(tb, BorderLayout.NORTH);

        BottomBar bb = new BottomBar();
        this.add(bb, BorderLayout.SOUTH);

//BUTTON CLICK IMPLEMENTATION EXAMPLE:

        //make each button like so to hook up a click action
        //be sure to add the button to the list of events in ButtonClicker Class
        ButtonClicker click = new ButtonClicker();
        load = new JButton("Load Image");
        load.addActionListener(click);


        //sample button implementation
        JPanel top = new JPanel();
        top.add(load);
        add(top, BorderLayout.CENTER);



        this.setTitle("LearningMyFriend :শেখা ও শখা ");
        this.setSize(1000,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }


    private class ButtonClicker implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            //MAKE SURE YOU HAVE AN IF STATEMENT FOR EVERY BUTTON ON THE UI
            if (e.getSource() == load)
            {
                //put method call or implementation here
                load.setText("The button has been clicked");
            }
        }
    }
}
