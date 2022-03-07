import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 *
 * @author Josep Fayos
 */

public class MyTask extends JFrame {

    private Flame flame;
    private BufferedImage backgroundImage;

    public MyTask() throws HeadlessException {

        String path = "C:\\Users\\Josep\\Desktop\\puente.jpg";

        try {
            this.backgroundImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(backgroundImage.getWidth()+375,backgroundImage.getHeight()+37);
        flame = new Flame(backgroundImage);
        flame.setConvolution(false);
        Viewer v1 = new Viewer(flame);
        v1.setImage(backgroundImage);
        ControlPanel cpanel1 = new ControlPanel(flame, v1);

        this.add(cpanel1, BorderLayout.CENTER);
        this.add(v1, "Center");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        flame.stop();
//        flame = new Flame(backgroundImage);
    }
    
    public static void main(String[] args) {
        new MyTask();
    }
}
