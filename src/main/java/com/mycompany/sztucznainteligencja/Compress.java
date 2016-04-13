/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sztucznainteligencja;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author badzik
 */
public class Compress {

    public static void learningPhase(BufferedImage[][] dividedimage, Neuron[] neurons, int numberofcycles, int sizeofframe) {
        Random rand = new Random();
        BufferedImage tempimg;
        Integer x, y;
        Integer pos = 0;
        Integer actualwinner;
        Double tempsum[] = new Double[neurons.length];
        Double n = 0.3; //współczynnik syzbkości uczenia
        Integer[] learningvalues = new Integer[sizeofframe * sizeofframe];
        int rgb;

        //losowanie danych uczących[ramek]
        for (int i = 0; i < numberofcycles; i++) {
            x = rand.nextInt(dividedimage.length) + 0;
            y = rand.nextInt(dividedimage[0].length) + 0;
            tempimg = dividedimage[x][y];
            for (int j = 0; j < sizeofframe; j++) {
                for (int k = 0; k < sizeofframe; k++) {
                    rgb = tempimg.getRGB(j, k);
                    learningvalues[pos] = ((rgb >> 16 & 0xFF) + (rgb >> 8 & 0xFF) + (rgb & 0xFF)) / 3;
                    pos++;
                }
            }
            pos = 0;

            //szukanie zwycieskiego neuronu
            for (int k = 0; k < neurons.length; k++) {
                for (int j = 0; j < learningvalues.length; j++) {
                    tempsum[k] = Math.pow(learningvalues[j] - neurons[k].weights[j], 2);
                }
                tempsum[k] = Math.sqrt(tempsum[k]);
            }
            actualwinner = minValue(tempsum);
            
            n = n - (0.1 * n);
            for (int j = 0; j < sizeofframe * sizeofframe; j++) {
                neurons[actualwinner].weights[j] = neurons[actualwinner].weights[j] + n * (learningvalues[j] - neurons[actualwinner].weights[j]);
            }
        }

    }
    
    public static void conlearningPhase(BufferedImage[][] dividedimage, Neuron[] neurons, int numberofcycles, int sizeofframe) {
        for(Neuron n: neurons){
            n.L=0;
        }
        Random rand = new Random();
        BufferedImage tempimg;
        Integer x, y;
        Integer pos = 0;
        Integer actualwinner;
        Double tempsum[] = new Double[neurons.length];
        Double n = 0.3; //współczynnik syzbkości uczenia
        Double A=0.2;//współczynnik sumienia
        Integer[] learningvalues = new Integer[sizeofframe * sizeofframe];
        int rgb;

        //losowanie danych uczących[ramek]
        for (int i = 0; i < numberofcycles; i++) {
            x = rand.nextInt(dividedimage.length) + 0;
            y = rand.nextInt(dividedimage[0].length) + 0;
            tempimg = dividedimage[x][y];
            for (int j = 0; j < sizeofframe; j++) {
                for (int k = 0; k < sizeofframe; k++) {
                    rgb = tempimg.getRGB(j, k);
                    learningvalues[pos] = ((rgb >> 16 & 0xFF) + (rgb >> 8 & 0xFF) + (rgb & 0xFF)) / 3;
                    pos++;
                }
            }
            pos = 0;

            //szukanie zwycieskiego neuronu
            for (int k = 0; k < neurons.length; k++) {
                for (int j = 0; j < learningvalues.length; j++) {
                    tempsum[k] = Math.pow(learningvalues[j] - neurons[k].weights[j], 2);
                }
                tempsum[k] = Math.sqrt(tempsum[k]);
                tempsum[k]=tempsum[k]+A*neurons[k].L;
            }
            actualwinner = minValue(tempsum);
            neurons[actualwinner].L++;
            
            n = n - (0.1 * n);
            for (int j = 0; j < sizeofframe * sizeofframe; j++) {
                neurons[actualwinner].weights[j] = neurons[actualwinner].weights[j] + n * (learningvalues[j] - neurons[actualwinner].weights[j]);
            }
        }

    }

    public static Integer[][] compress(BufferedImage[][] dividedimage, Neuron[] neurons, int sizeofframe) {
        Integer[][] result = new Integer[dividedimage.length][dividedimage[0].length];
        Integer actualwinner;
        Integer[] actualvalues = new Integer[sizeofframe * sizeofframe];
        Double tempsum[] = new Double[neurons.length];
        int rgb;
        int pos = 0;
        BufferedImage tempimg;
        for (int x = 0; x < dividedimage.length; x++) {
            for (int y = 0; y < dividedimage[0].length; y++) {
                tempimg = dividedimage[x][y];
                for (int j = 0; j < sizeofframe; j++) {
                    for (int k = 0; k < sizeofframe; k++) {
                        rgb = tempimg.getRGB(j, k);
                        actualvalues[pos] = ((rgb >> 16 & 0xFF) + (rgb >> 8 & 0xFF) + (rgb & 0xFF)) / 3;
                        pos++;
                    }
                }
                pos = 0;

                for (int k = 0; k < neurons.length; k++) {
                    for (int j = 0; j < actualvalues.length; j++) {
                        tempsum[k] = Math.pow(actualvalues[j] - neurons[k].weights[j], 2);
                    }
                    tempsum[k] = Math.sqrt(tempsum[k]);
                }
                actualwinner = minValue(tempsum);
                result[x][y]=actualwinner;
            }
        }
        return result;

    }

    private static int minValue(Double[] numbers) {
        int min = 0;
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[min] > numbers[i]) {
                min = i;
            }
        }
        return min;
    }
}
