/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sztucznainteligencja;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author badzik
 */
public class Neuron {
    private List<Integer> weights=new ArrayList<>();
    private Integer size;

    public Neuron() {
    }
    
    public List<Integer> getWeights(){
        return weights;
    }
    
    public void randomize(List<Integer> random){
        weights=random;
    }
}
