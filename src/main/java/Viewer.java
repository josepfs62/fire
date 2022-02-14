import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Josep Fayos
 */

public class Viewer extends Canvas implements Runnable {

    private Flame flame;
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    private Boolean active = true;
    
    public Viewer(Flame flame) {
        super();
        this.flame = flame;
        Thread view = new Thread(this);
        view.start();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public void paint() {
        Image image = flame;
        BufferStrategy bs = this.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        if (bs == null || g == null) {
            return;
        }

        g.drawImage(getImage(), 249, 0, this);
        g.drawImage(image, 249, 0, this);
        //g.drawImage(flame, 0, 0, this);

        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        while (true){
            while (active) {
                try {
                    sleep(200);
                    createBufferStrategy(2);
                } catch
                    (InterruptedException ex) {
                }

                while (true) {
                    if (this.getBufferStrategy() != null) {
                        paint();
                    }
                    try {
                        sleep(33);
                    } catch (Exception e) {
                    }

                }
//                if (this.getGraphics() != null) {
//                    paint(this.getGraphics());
//                }
            }
        }
    }
}
