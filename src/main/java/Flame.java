import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josep Fayos
 */

public class Flame extends BufferedImage implements Runnable {
    Thread t1;
    //i = x (alto)
    //j = y (ancho)
    private boolean active = true;
    private boolean convolution = false;
    private int width;
    private int height;
    private double[][] temp;
    private double[] createdSparks;
    private Boolean Convolution;
    ArrayList<ArrayList<Integer>> convolutionSparks;
    private int sparksAmount = 20;
    private int speed = 0;
    private int color = 1;
    private int clear = 0 << 24 | 0 << 16 | 0 << 8 | 0;
    //private int rojo = new Color(255, 0, 0, 255).getRGB();
    //private int clear = new Color(0, 0, 0, 255).getRGB();

    public Flame(BufferedImage image) {
        super(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.t1 = new Thread(this);
        this.height = image.getHeight();
        this.width = image.getWidth();
        temp = new double[width][height];
        t1.start();
    }

    private void createFlameImage(){
        temperatureEvolve();
        for (int x = 1; x < temp.length-1; x++){
            for (int y = 1; y < temp[x].length-1; y++){
                int temp1 = (int) temp[x][y];
                int g = temp1 * (255 * 12) / 255;
                int g1 = temp1 * (255 * 20) / 255;
                int rojo = 255 << 24 | 255 << 16 | g << 8;
                int verde = 255 << 24 | g1 << 16 | 255 << 8;
                int azul = 255 << 24 | g1 << 8 | 255;
                int pintar = rojo;
                if (color == 1){
                    pintar = rojo;
                } else if (color == 2){
                    pintar = verde;
                } else if (color == 3){
                    pintar = azul;
                }
                if (temp[x][y] > 0){
                    this.setRGB(x, y, pintar);
                } else {
                    this.setRGB(x, y, clear);
                }
            }
        }
    }
    
    private void temperatureEvolve(){

        for (int x = 1; x < width-1 ; x++){
            for (int y = 1; y < height-1 ; y++){
                double var0 = temp[x][y];
                double var1 = temp[x][y + 1];
                double var2 = temp[x - 1][y + 1];
                double var3 = temp[x + 1][y + 1];
                temp[x][y] = (((var0 + var1 + var2 + var3 ) / 4 - 0.03));
            }
        }
    }
    
    private void createSparks() {
        if (convolution){
            //clear base
            createdSparks = new double[width];
            for (int y = 0; y < createdSparks.length; y++){
                createdSparks[y] = 0;
            }
            for (int i = 0; i < width-1; i++) {
                temp[i][height-1] = createdSparks[i];
            }
            for (int i = 0; i < convolutionSparks.size()-1; i++) {
                temp[convolutionSparks.get(i).get(0)][convolutionSparks.get(i).get(1)] = 0;
            }
            //añadir sparks del imageFiltered
            for (int i = 0; i < sparksAmount; i++) {
                int x = ((int) Math.round(Math.random() * (convolutionSparks.size() - 1)));
                temp[convolutionSparks.get(x).get(0)][convolutionSparks.get(x).get(1)] = 255;
                temp[convolutionSparks.get(x).get(0) - 1][convolutionSparks.get(x).get(1)] = 255;
                temp[convolutionSparks.get(x).get(0) + 1][convolutionSparks.get(x).get(1)] = 255;
            }
            //refrescar sparks
        } else {
            createdSparks = new double[width];
            for (int y = 0; y < createdSparks.length; y++){
                createdSparks[y] = 0;
            }
            for (int i = 0; i < sparksAmount; i++){
                createdSparks[(int) Math.round(Math.random() *  (width - 1))] = 255;
            }
            for (int i = 0; i < width-1; i++) {
                temp[i][height-1] = createdSparks[i];
            }
        }
    }

    public boolean getActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean getConvolution() {
        return convolution;
    }
    public void setConvolution(boolean convolution) {
        this.convolution = convolution;
    }
    public int getSparksAmount() {
        return sparksAmount;
    }
    public void setSparksAmount(int sparksAmount) {
        this.sparksAmount = sparksAmount;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setConvolutionSparks(ArrayList<ArrayList<Integer>> convolutionSparks) {
        this.convolutionSparks = convolutionSparks;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    @Override
    public int getWidth() {
        return width;
    }
    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void run() {
        while(true){
            while (active) {
                createSparks();
                try {
                    createFlameImage();
                    Thread.sleep(speed);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Flame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Flame.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }

}