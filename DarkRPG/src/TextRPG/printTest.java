/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextRPG;
import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author asus
 */
public class printTest {
    public static void main(String[] args) {
 
        String myString;
        myString = "hello world!";
    
         
        for (char c : myString.toCharArray()) {
            
      
    
    System.out.print(c);
     try{
    TimeUnit.MILLISECONDS.sleep((300));
    }
    catch (Exception ex) {
        
    }  
    
}
     }
}
