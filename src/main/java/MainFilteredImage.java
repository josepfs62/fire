/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 *
 * @author josep
 */
public class MainFilteredImage{

    static BufferedImage image;
    static BufferedImage imageFiltered;
    static ArrayList<ArrayList<Integer>> pixels = new ArrayList<ArrayList<Integer>>();

//    public static void main(String[] args) throws IOException {
//        File file = new File("/home/josep/Documentos/moto.jpg");
//        image = ImageIO.read(file);
//
//        for (int i = 0; i < image.getWidth(); i++) {
//            ArrayList<Integer> list = new ArrayList<Integer>();
//            pixels.add(list);
//            for (int j = 0; j < image.getHeight(); j++) {
//                int clr = image.getRGB(i, j);
//                list.add(clr);
//            }
//        }
//
//        MainFilteredImage.imageFiltered = new ImageFiltered(image.getHeight(), image.getWidth(), BufferedImage.TYPE_INT_ARGB, pixels);
//
//        JFrame frame = new JFrame();
//        frame.add(new CustomPaintComponent());
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(image.getWidth()+image.getWidth(), image.getHeight());
//        frame.setVisible(true);
//    }

    static class CustomPaintComponent extends Component {
        public void paint(Graphics g){
            g.drawImage(image, 0, 0, this);
            g.drawImage(imageFiltered, image.getWidth(), 0, this);
        }
    }

}
