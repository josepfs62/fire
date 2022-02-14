import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.String.valueOf;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Josep Fayos
 */

public class MyTask extends JFrame {

    private BufferedImage backgroundImage;

    public MyTask() throws HeadlessException {

        try {
            this.backgroundImage = ImageIO.read(new File("/home/josep/Documentos/puente.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setSize(backgroundImage.getWidth()+249,backgroundImage.getHeight()+37);
        Flame flame = new Flame(backgroundImage.getHeight(),backgroundImage.getWidth(),BufferedImage.TYPE_INT_ARGB/*, flamePalete*/);
        JButton pause = new JButton("Pause");
        JButton less = new JButton("-");
        JLabel sparks = new JLabel("");
        JButton plus = new JButton("+");
        JButton red = new JButton(" RED ");
        JButton green = new JButton(" GREEN ");
        JButton blue = new JButton(" BLUE ");
        javax.swing.JSlider scrollSpeed = new javax.swing.JSlider(JSlider.HORIZONTAL, 0, 20, flame.getSpeed());
        scrollSpeed.setMajorTickSpacing(5);
        scrollSpeed.setMinorTickSpacing((int) 1);
        scrollSpeed.setInverted(true);
        scrollSpeed.setPaintTicks(true);
        JButton convolution = new JButton("Convolution");
        sparks.setText(valueOf(flame.getSparksAmount()));
        ControlPanel cpanel1 = new ControlPanel(pause, less, sparks, plus, scrollSpeed, red, green, blue, convolution, backgroundImage.getHeight());
        Viewer v1 = new Viewer(flame);
        pause.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (v1.getActive() == true){
                    v1.setActive(false);
                } else {
                    v1.setActive(true);
                }
                if (flame.getActive() == true){
                    flame.setActive(false);
                } else {
                    flame.setActive(true);
                }
            }
        });
        less.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flame.setSparksAmount(flame.getSparksAmount() - 1);
                sparks.setText(valueOf(flame.getSparksAmount()));
            }
        });
        plus.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flame.setSparksAmount(flame.getSparksAmount() + 1);
                sparks.setText(valueOf(flame.getSparksAmount()));
            }
        });
        red.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flame.setColor(1);
            }
        });
        green.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flame.setColor(2);
            }
        });
        blue.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flame.setColor(3);
            }
        });
        scrollSpeed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                flame.setSpeed((int)source.getValue());
            }
        });

        //BackgroundImage
        v1.setImage(backgroundImage);

        this.add(cpanel1, BorderLayout.CENTER);
        this.add(v1, "Center");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    
    public static void main(String[] args) {
        new MyTask();
    }
}
