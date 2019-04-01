/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextRPG;
import java.io.*;
import java.util.*;
/**
 *
 * @author asus
 */
public class PropertiesTest {
   public static void main(String[] args) {
       Properties prop = new Properties();
       
       String a = "1";
       if(a == "1") {
       
       try {
           //set properties values to prop
           prop.setProperty("database","test1");
           //save properties to file
           prop.store(new FileOutputStream("asdasddatabasePropDemo.properties"), null);
           
       } catch (IOException ex) {
           ex.printStackTrace();
       }
   } else {
           System.out.println("no");
       }
   }
}
