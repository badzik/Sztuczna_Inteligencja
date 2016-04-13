/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sztucznainteligencja;

import java.awt.image.BufferedImage;

/**
 *
 * @author badzik
 */
public class MyImage {
    static BufferedImage[][] divide(BufferedImage img, int size) {
        int sizex, sizey;  //ilosc ramek
        int tempx = 0, tempy = 0; // lokalizacja w poszczególnych ramkach
        int bufx = 0, bufy = 0; // lokalizacaja ramki
        int rgb;
        sizey = (int) (img.getHeight() / size);
        sizex = (int) (img.getWidth() / size);
        BufferedImage[][] retframes = new BufferedImage[sizex][sizey];
        for (int i = 0; i < sizey; i++) {
            for (int j = 0; j < sizex; j++) {
                retframes[j][i] = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_GRAY);
            }
        }
        for (int i = 0; i < img.getHeight()-(img.getHeight()%size); i++) {
            for (int j = 0; j < img.getWidth()-(img.getWidth()%size); j++) {
                rgb = img.getRGB(j, i);
                retframes[bufx][bufy].setRGB(tempx, tempy, rgb);
                tempx++;
                if (tempx == size) {
                    tempx = 0;
                    bufx++;
                    if (bufx == sizex) {
                        bufx = 0;
                    }
                }
            }
            tempy++;
            if (tempy == size) {
                tempy = 0;
                bufy++;
                if (bufy == sizey) {
                    bufy = 0;
                }
            }
            
        }
        /*        File outputfile = new File("sav1.jpg");
        try {
        ImageIO.write(retframes[1][2], "jpg", outputfile);
        } catch (IOException ex) {
        Logger.getLogger(Compress.class.getName()).log(Level.SEVERE, null, ex);
        }
        File outputfile2 = new File("sav2.jpg");
        try {
        ImageIO.write(retframes[2][2], "jpg", outputfile2);
        } catch (IOException ex) {
        Logger.getLogger(Compress.class.getName()).log(Level.SEVERE, null, ex);
        }
        File outputfile3 = new File("sav3.jpg");
        try {
        ImageIO.write(retframes[1][3], "jpg", outputfile3);
        } catch (IOException ex) {
        Logger.getLogger(Compress.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return retframes;
    }
}
