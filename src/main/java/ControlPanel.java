
import java.awt.*;
import javax.swing.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author josep
 */
public class ControlPanel extends JPanel{

    private javax.swing.JSlider scrollSpeed = new javax.swing.JSlider();
    
    GridBagConstraints c = new GridBagConstraints();
    JButton pause = new JButton("");
    JButton less = new JButton("");
    JButton plus = new JButton("");
    JButton red = new JButton("");
    JButton green = new JButton("");
    JButton blue = new JButton("");
    
    public ControlPanel(JButton pause, JButton less, JLabel sparks, JButton plus, JSlider scrollSpeed, JButton red, JButton green, JButton blue, JButton convolution, int imageHeight) {
        super();
        this.setSize(250,imageHeight);
        this.setLayout(new GridBagLayout());
        this.pause = pause;
        this.less = less;
        this.plus = plus;
        this.scrollSpeed = scrollSpeed;
        this.red = red;
        this.green = green;
        this.blue = blue;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.anchor = GridBagConstraints.NORTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        this.add(pause, c);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        this.add(new JLabel("Sparks"), c);
        c.gridx = 0;
        c.gridy = 3;
        this.add(less, c);
        c.gridx = 1;
        this.add(sparks, c);
        c.gridx = 2;
        this.add(plus, c);
        c.gridx = 1;
        c.gridy = 4;
        this.add(new JLabel("FPS"), c);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        this.add(scrollSpeed, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 6;
        this.add(red, c);
        c.gridx = 1;
        this.add(green, c);
        c.gridx = 2;
        this.add(blue, c);
        c.gridx = 1;
        c.gridy = 7;
        this.add(convolution, c);
        this.setBackground(Color.gray);
        this.setVisible(true);
    }
    
    
    
}
