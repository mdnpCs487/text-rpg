/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextRPG;

/**
 *
 * @author asus
 */
public class RNGclass {
    public static void main (String args []) {
        System.out.println("Critical Damage Generator");
        int i =0;
        double critchance = 0.75;
        double critRand = (double)(Math.random());
        for(i=1;100>i;i++) {
            System.out.println("Attempt Number : " +i);
            critRand = (double)(Math.random());
            if(critchance>=critRand) {
                System.out.println("CRITICAL !!!");
            } else {
                System.out.println("Normal");
            }
        }
        
        System.out.println("Math Random");
        
        for(i=1;100>i;i++) {
            double randomnum = Math.random();
            double randomizer = (randomnum * 0.75 + 1); //GET STATS UP TO 75% MORE
            int stat = (int)(10*randomizer);
            System.out.println("Attempt : " +i +"." +" Stat : " +stat +" Randomizer Number : " +randomizer +" | MATH RANDOM NUM : " +randomnum);
            System.out.println();
        }
        for(i=1;50>i;i++) {
            int statnum =77;
            double randomnum = Math.random();
            double randomizer = (randomnum * 0.75 +1);
            int stat = (int)((statnum*2.2)*randomizer);
            
            System.out.println("Attempt : " +i +" Stat : "+stat);
            stat = 0;
            
        }
    }
}
