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
    TimeUnit.MILLISECONDS.sleep((100));
    }
    catch (Exception ex) {
        
    }  
     
     
    
}
     
        //STAT ALLOCATION TESTING 
        
     Scanner in = new Scanner(System.in);   
     boolean inputProcess = true;   
     boolean statInputStr = true;
     boolean statInputAgi = true;
     boolean statInputIntel = true;
     int pointsMax = 0;
     int str;
     int agi;
     int intel;
     int bonusGold = pointsMax * 25;
     
     pointsMax = 30;
     
     
     System.out.println("\n\n\n");
     
     INPUT:
     while(inputProcess==true) {
         
         
         
         
     restartStatInput:
     while(statInputStr==true) {
       
         System.out.println("Input your str :");
         String inputStr = in.nextLine();
         try {
         str = Integer.parseInt(inputStr);
         if(str>pointsMax) {
             
             continue restartStatInput;
         } else if(str<=pointsMax) {
             System.out.println("Your str : " +str);
             pointsMax = pointsMax - str;
             System.out.println("Points left : " +pointsMax);
         } 
         statInputStr = false;
         break;
         }catch (NumberFormatException e) {
             System.out.println("Input a decimal number.");
         }
     }
    
     
     restartStatInput:
     while(statInputAgi==true) {
       
         System.out.println("Input your agi :");
         String inputAgi = in.nextLine();
         try {
         agi = Integer.parseInt(inputAgi);
         if(agi>pointsMax) {
             
             continue restartStatInput;
         } else if(agi<=pointsMax) {
             System.out.println("Your agi : " +agi);
             pointsMax = pointsMax - agi;
             System.out.println("Points left : " +pointsMax);
         } 
         statInputAgi = false;
         break;
         } catch(NumberFormatException e) {
             System.out.println("Input a decimal number.");
         }
     }
     
     restartStatInput:
     while(statInputIntel==true) {
       
         System.out.println("Input your Intel :");
         String inputIntel = in.nextLine();
         try {
         intel = Integer.parseInt(inputIntel);
         if(intel>pointsMax) {
             
             continue restartStatInput;
         } else if(intel<=pointsMax) {
             System.out.println("Your intel : " +intel);
             pointsMax = pointsMax - intel;
             System.out.println("Points left : " +pointsMax);
         } 
         statInputIntel = false;
         break;
         }catch(NumberFormatException e) {
             System.out.println("Input a decimal number.");
         }
     }
    
     if(pointsMax>0) {
         bonusGold = pointsMax * 25;
         System.out.println("You still have " +pointsMax +" points left. use it or let it convert to " +bonusGold +" gold?" );
         System.out.println("Y/N");
         String inputLeftover = in.nextLine();
         if("Y".equals(inputLeftover)) {
                statInputStr = true;
                statInputAgi = true;
                statInputIntel = true;
             continue INPUT;
         } else if("N".equals(inputLeftover)) {
             bonusGold = pointsMax * 25;
             pointsMax = 0;
             System.out.println("You got " +bonusGold +" gold!");
             inputProcess = false;
             break;
         } else {
             System.out.println("what");
         }
     } else if(pointsMax<=0) {
         inputProcess = false;
         break;
     }
        
     }
     
     }
}
