import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;

/**
 *
 * @author Josep Fayos
 */

public class Viewer extends Canvas implements Runnable {

    private Flame flame;
    private BufferedImage image;

    private Boolean active = true;

    public Viewer(Flame flame) {
        super();
        this.flame = flame;
        Thread view = new Thread(this);
        view.start();
    }

    public void paint() {
        Image imageFlame = flame;
        BufferStrategy bs = this.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        if (bs == null || g == null) {
            return;
        }

        g.drawImage(getImage(), 375, 0, this);
        g.drawImage(imageFlame, 375, 0, this);
        g.drawImage(flame, 375, 0, this);

        bs.show();
        g.dispose();
    }

    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }


    //public void
//    if((pintar[0] + pintar[1] + pintar[2]) > 450){
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        this.convolutionSparks.add(list);
//        list.add(i);
//        list.add(j);
//    }

//
    @Override
    public void run() {
        boolean createBufferedStrat = false;

        while (true){
            while (active) {

                while (!createBufferedStrat) {
                    try {
                        sleep(3000);
                        createBufferStrategy(2);
                        createBufferedStrat = true;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
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
