/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextRPG;

import static java.lang.System.in;
import java.util.Scanner;

/**
 *
 * @author asus
 */



public class loot {




DarkRPG darkrpg = new DarkRPG();
public int money;

public String sword1,sword2,sword3,sword4,sword5,sword6,sword7,sword8,sword9,sword10;
public String armor1,armor2,armor3,armor4,armor5,armor6,armor7,armor8,armor9,armor10;
loot() {
    this.sword1 = "Wooden Sword";
    this.sword2 = "Stone Sword";
    this.sword3 = "Gold Sword";
    this.sword4 = "Iron Sword";
    this.sword5 = "Diamond Sword";
    
    this.armor1 = "Wooden Armor";
    this.armor2 = "Stone Armor";
    this.armor3 = "Gold Armor";
    this.armor4 = "Iron Armor";
    this.armor5 = "Diamond Armor";
}

public static void itemDesc(){
    AudioPlay audioP = new AudioPlay();
    String sworda;
    sworda = "wood sword";
    int equipadddamage;
    int money = 10;
    Scanner input = new Scanner(System.in);
    boolean boughtwp1 = false;
    String yesno;
    System.out.println("---------------------------------");
    System.out.println("Wooden Sword\n\nA sword made out of wood...how is there even wood in a dungeon underground anyways?\n\n Increases min and max damage by 20.");
    System.out.println("---------------------------------");
    System.out.println("Buy This? Y/N");
    System.out.println("---------------------------------");
    yesno = input.nextLine();
    String yn = yesno.toUpperCase();
    if(yn.equals("Y")) {
        if(boughtwp1 == true) {
            System.out.println("You've bought this");
        }
        if(money>=1) {

                boughtwp1 = true;
                money = money - 1;
                equipadddamage = 20;
                System.out.println("you've bought the " +sworda+".");



        }
        else {
            System.out.println("**********************\nNot enough money.\n**********************");
        }
    }
    else if(yn.equals("N")) {

        audioP.stopSound();
    audioP.playSound(2);

        
    }System.out.println();
    }

}
