/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sztucznainteligencja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author badzik
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> r=Arrays.asList(1,2,3,4);
        Neuron n=new Neuron();
        n.randomize(r);
        r=new ArrayList<>();
        r.add(33);
        
        System.out.println(n.getWeights());
    }
    
    
}
