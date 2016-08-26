/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exersices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Readers {
    
    String filepath;
    
    public Readers(String filepath){
        this.filepath = filepath;
    }

    public List<String> gettext() {

        BufferedReader br = null;
        List<String> list = new ArrayList();
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(filepath));

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                list.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
}
