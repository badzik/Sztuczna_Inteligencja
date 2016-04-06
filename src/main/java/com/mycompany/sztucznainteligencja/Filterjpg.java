/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sztucznainteligencja;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author badzik
 */
public class Filterjpg extends FileFilter{
    private String format= "JPG";
    
    @Override
    public boolean accept(File f) {
        if(f.isDirectory()){
            return true;
        }
        if(extension(f).equalsIgnoreCase(format)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "Pliki jpg";
    }
    
    public static String extension(File f){
        String filename = f.getName();
        int indexfile= filename.lastIndexOf('.');
        if(indexfile>0 && indexfile<filename.length()-1){
            return filename.substring(indexfile+1);
        }else {
            return "";
        }
    }
}
