import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import static java.lang.String.valueOf;

/**
 *
 * @author josep
 */
public class ControlPanel extends JPanel{

    private javax.swing.JSlider scrollSpeed;
    private Flame flame;
    private Viewer v1;
    private Utils utils = new Utils();
    private BufferedImage backgroundImage;
    private ImageFiltered imageFiltered;
    JFileChooser fileChooser = new JFileChooser("/home/josep/Documentos");
    BufferedImage resizedBackground = null;
    Boolean changedBack = false;
    private GridBagConstraints c = new GridBagConstraints();
    private JLabel sparks = new JLabel("");
    private JButton plus = new JButton("+");
    private JButton red = new JButton(" RED ");
    private JButton green = new JButton(" GREEN ");
    private JButton blue = new JButton(" BLUE ");
    private JButton convolutionImage = new JButton("Convolution Image");
    private JButton convolution = new JButton("Convolution Fire");

    public ControlPanel(Flame flame, Viewer v1) {
        super();
        this.flame = flame;
        this.v1 = v1;
        this.backgroundImage = v1.getImage();
        this.imageFiltered = new ImageFiltered(backgroundImage);
        sparks.setText(String.valueOf(flame.getSparksAmount()));
        this.setSize(375,backgroundImage.getHeight());
        this.setLayout(new GridBagLayout());
        scrollSpeed = new JSlider(JSlider.HORIZONTAL, 0, 20, flame.getSpeed());
        scrollSpeed.setMajorTickSpacing(5);
        scrollSpeed.setMinorTickSpacing((int) 1);
        scrollSpeed.setInverted(true);
        scrollSpeed.setPaintTicks(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.anchor = GridBagConstraints.NORTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        JButton pause = new JButton("Pause");
        this.add(pause, c);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        this.add(new JLabel("Sparks"), c);
        c.gridx = 0;
        c.gridy = 3;
        JButton less = new JButton("-");
        this.add(less, c);
        c.gridx = 1;
        this.add(sparks, c);
        c.gridx = 2;
        this.add(plus, c);
        c.gridx = 1;
        c.gridy = 4;
        this.add(new JLabel("Speed"), c);
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
        this.add(convolutionImage, c);
        c.gridy = 8;
        this.add(convolution, c);
        c.gridy = 9;
        this.setBackground(Color.gray);
        this.setVisible(true);

        pause.addActionListener(e -> {
            v1.setActive(!v1.getActive());
            flame.setActive(!flame.getActive());
        });
        less.addActionListener(e -> {
            flame.setSparksAmount(flame.getSparksAmount() - 1);
            sparks.setText(valueOf(flame.getSparksAmount()));
        });
        plus.addActionListener(e -> {
            flame.setSparksAmount(flame.getSparksAmount() + 1);
            sparks.setText(valueOf(flame.getSparksAmount()));
        });
        red.addActionListener(e -> flame.setColor(1));
        green.addActionListener(e -> flame.setColor(2));
        blue.addActionListener(e -> flame.setColor(3));
        scrollSpeed.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            flame.setSpeed((int)source.getValue());
        });
        convolutionImage.addActionListener(e -> {
            if (v1.getImage() == this.backgroundImage) {
                v1.setImage(imageFiltered);
            } else {
                v1.setImage(this.backgroundImage);
            }
        });
        convolution.addActionListener(e -> {
            flame.setConvolution(!flame.getConvolution());
        });
        
    }
}