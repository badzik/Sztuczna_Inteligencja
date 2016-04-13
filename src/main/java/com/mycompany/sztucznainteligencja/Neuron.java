/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sztucznainteligencja;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author badzik
 */
public class Neuron {

    private Integer[] weights;
    private Integer size;

    public Neuron() {
    }

    public Integer[] getWeights() {
        return weights;
    }

    public void randomize(int numberofweights) {
        Random rand = new Random();
        weights=new Integer[numberofweights];
        int r;
        for (int j = 0; j < numberofweights; j++) {
            r = rand.nextInt(256) + 0;
            weights[j]=r;
        }

    }
}
