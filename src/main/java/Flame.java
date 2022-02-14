import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Josep Fayos
 */

public class Flame extends BufferedImage implements Runnable {

    //i = x (alto)
    //j = y (ancho)
    private boolean active = true;
    private int width;
    private int height;
    private double[][] temp;
    private double[] createdSparks;
    private int sparksAmount = 20;
    private int speed = 1;
    private int color = 1;
    private int clear = 0 << 24 | 0 << 16 | 0 << 8 | 0;
    //private int rojo = new Color(255, 0, 0, 255).getRGB();
    //private int clear = new Color(0, 0, 0, 255).getRGB();

    public Flame(int height, int width, int imageType) {
        super(height, width, imageType);
        this.height = height;
        this.width = width;
        temp = new double[width][height];
        
        Thread t1 = new Thread(this); 
        t1.start();
        
    }

    private void createFlameImage(){
        
        for (int x = 0; x < temp.length-1; x++){
            for (int y = 1; y < temp[x].length-1; y++){
                int temp1 = (int) temp[x][y];
                int g = temp1 * (255 * 12) / 255;
                int g1 = temp1 * (255 * 20) / 255;
                int rojo = 255 << 24 | 255 << 16 | g << 8 | 0;
                int verde = 255 << 24 | g1 << 16 | 255 << 8 | 0;
                int azul = 255 << 24 | 0 << 16 | g1 << 8 | 255;
                int pintar = rojo;
                if (color == 1){
                    pintar = rojo;
                } else if (color == 2){
                    pintar = verde;
                } else if (color == 3){
                    pintar = azul;
                }
                if (temp[x][y] > 0){
                    this.setRGB(y, x, pintar);
                } else {
                    this.setRGB(y, x, clear);
                }
            }
        }
    }
    
    private void temperatureEvolve(){
        temp[temp.length-1] = createdSparks;
        
        for (int x = height - 2; x > 0 ; x--){
            for (int y = width - 2; y > 0 ; y--){
                double var0 = temp[y][x];
                double var1 = temp[y + 1][x];
                double var2 = temp[y + 1][x - 1];
                double var3 = temp[y + 1][x + 1];
                temp[y][x] = (((var0 + var1 + var2 + var3 ) / 4 - 0.02));
            }
        }
    }
    
    private void createSparks() {
        createdSparks = new double[width];
        for (int y = 0; y < createdSparks.length; y++){
                createdSparks[y] = 0;
            }
        for (int i = 0; i < sparksAmount; i++){
            createdSparks[(int) Math.round(Math.random() *  (width - 1))] = 255;
        }
    }

    public boolean getActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
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

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void run() {
        while(true){
            while (active) {
                createSparks();
                try {
                    temperatureEvolve();
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