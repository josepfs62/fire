import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author josep
 */

/**
 * arp, tcp, socket, puerto, protocolo de aplicacion
 * Kernel examples
 *
 * Vertical edge:
 {1, 0, -1},
 {1, 0, -1},
 {1, 0, -1}
 *
 * Horizontal edge:
 {1, 1, 1},
 {0, 0, 0},
 {-1, -1, -1}
 *
 * Scharr Vertical:
 {3, 0, -3},
 {10, 0, -10},
 {3, 0, -3}
 *
 * Scharr Horizontal:
 {3, 10, 3},
 {0, 0, 0},
 {-3, -10, -3}
 *
 * Outline:
 {-1, -1, -1},
 {-1, 8, -1},
 {-1, -1, -1}
 */


public class ImageFiltered extends BufferedImage{
    private int height;
    private int width;
    private ArrayList<ArrayList<Integer>> convolutionSparks = new ArrayList<ArrayList<Integer>>();;
    ArrayList<ArrayList<Integer>> pixels = new ArrayList<ArrayList<Integer>>();
    private int[][] kernel = { {0, 0, 0},
                            {-1, 2, -1},
                            {0, 0, 0}};

    private int totalKernel = 0;

    ImageFiltered(BufferedImage image) {
        super(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.width = image.getWidth();
        this.height = image.getHeight();
        for (int i = 0; i < image.getWidth(); i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < image.getHeight(); j++) {
                int clr = image.getRGB(i, j);
                list.add(clr);
            }
            this.pixels.add(list);

        }
        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel[0].length; j++) {
                totalKernel += kernel[i][j];
            }
        }
        if (totalKernel == 0){
            totalKernel = 1;
        }
        createFilteredImage();
    }
    
    private void createFilteredImage(){
        for (int i = 1; i < width-1; i++) {
            for (int j = 1; j < height-1; j++) {
                int [] pintar = calculatedPixel(i, j);
                Color rgb = new Color (pintar[0], pintar[1], pintar[2]);
                this.setRGB(i, j, rgb.getRGB());
                if ((pintar[0] + pintar[1] + pintar[2]) > 570){
                    ArrayList<Integer> position = new ArrayList<Integer>();
                    position.add(i, j);
                    convolutionSparks.add(position);
                }
            }
        }
    }
    
    private int[] calculatedPixel(int i, int j){
        int[] p1 = getColors(pixels.get(i-1).get(j-1));
        int[] p2 = getColors(pixels.get(i).get(j-1));
        int[] p3 = getColors(pixels.get(i+1).get(j-1));
        int[] p4 = getColors(pixels.get(i-1).get(j));
        int[] p5 = getColors(pixels.get(i).get(j));
        int[] p6 = getColors(pixels.get(i+1).get(j));
        int[] p7 = getColors(pixels.get(i-1).get(j+1));
        int[] p8 = getColors(pixels.get(i).get(j+1));
        int[] p9 = getColors(pixels.get(i+1).get(j+1));

        int[][][] kernelsPixels = {{p1, p2, p3},{p4, p5, p6},{p7, p8, p9}};

        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;

        for (int k = 0; k < kernelsPixels.length; k++) {
            for (int l = 0; l < kernelsPixels[0].length; l++) {
                totalRed += kernelsPixels[k][l][0] * kernel[k][l];
                totalGreen += kernelsPixels[k][l][1] * kernel[k][l];
                totalBlue += kernelsPixels[k][l][2] * kernel[k][l];
            }

        }

        totalRed = totalRed / totalKernel;
        if (totalRed < 0){
            totalRed = 0;
        }
        else if (totalRed > 255){
            totalRed = 255;
        }

        totalGreen = totalGreen / totalKernel;
        if (totalGreen < 0){
            totalGreen = 0;
        }
        else if (totalGreen > 255){
            totalGreen = 255;
        }

        totalBlue = totalBlue / totalKernel;
        if (totalBlue < 0){
            totalBlue = 0;
        }
        else if (totalBlue > 255){
            totalBlue = 255;
        }

        return new int[]{totalRed, totalGreen, totalBlue};
    }

    private int[] getColors(int color){
        int red = color >> 16 & 0xFF;
        int green = color >> 8 & 0xFF;
        int blue = color & 0xFF;
        return new int[]{red, green, blue};
    }

    public ArrayList<ArrayList<Integer>> getconvolutionSparks() {
        return convolutionSparks;
    }
}
