package FullWindow;

import BottomNavigation.BottomBar;
import TopBar.TopBar;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    MainFrame(){
        TopBar tb = new TopBar();
        this.add(tb, BorderLayout.NORTH);

        BottomBar bb = new BottomBar();
        this.add(bb, BorderLayout.SOUTH);


        this.setTitle("LearningMyFriend :শেখা ও শখা ");
        this.setSize(1000,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
