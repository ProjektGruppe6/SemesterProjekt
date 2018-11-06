package worldofzuul;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class Dialog {

    private ArrayList<String> section;
    

    public Dialog() {

        

        }
   
public void showText(String path){
    
    
    try {

            FileInputStream in = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
            }

        } catch (Exception e) {
            System.out.println(e);
            
            
           

            }
    
}}

