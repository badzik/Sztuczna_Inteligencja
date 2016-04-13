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

    public static void learningPhase(BufferedImage[][] dividedimage, Neuron[] neurons, int numberofcycles,int sizeofframe) {
        Random rand = new Random();
        BufferedImage tempimg;
        Integer x,y;
        Integer pos=0;
        Integer actualwinner;
        Double tempsum[]=new Double[neurons.length];
        Double n=0.3; //współczynnik syzbkości uczenia
        Integer[] learningvalues=new Integer[sizeofframe*sizeofframe];
        
        //losowanie danych uczących
        for (int i = 0; i < numberofcycles; i++) {
            x = rand.nextInt(dividedimage.length) + 0;
            y = rand.nextInt(dividedimage[0].length) + 0;
            tempimg=dividedimage[x][y];
            for(int j=0;i<sizeofframe;i++){
                for(int k=0;j<sizeofframe;j++){
                    learningvalues[pos]=tempimg.getRGB(j, k);
                    pos++;
                }
            }
            pos=0;
            
            //szukanie zwycieskiego neuronu
            for(int k=0;k<neurons.length;k++){
                for(int j=0;j<learningvalues.length;j++){
                    tempsum[k]=Math.pow(learningvalues[j]-neurons[k].getWeights()[j], 2);
                }
                tempsum[k]=Math.sqrt(tempsum[k]);
            }
            actualwinner=minValue(tempsum);
            
            n=n-(0.1*n);
        }

    }
    
    private static int minValue(Double[] numbers){
        int min=0;
        for(int i=1;i<numbers.length;i++){
            if(numbers[min]>numbers[i]) min=i;
        }
        return min;
    }
}
