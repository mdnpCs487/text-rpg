package TextRPG;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
#SEARCH KEYWORDS#
1. MAIN MENU
2. GAME START
3. POTIONS 
4. FIGHT
5. ANALYZE SITUATION
6. DRINK HP POTION
7. RUN
8. EXPERIENCE FORMULAS
9. CONTINUE VENTURES
10. STOP GAME
11. SHOP

/
package TextRPG;

/**
 *
 * @author asus
 */
import java.text.DecimalFormat;
import java.math.*;
import java.io.*;
import java.util.*;
//import java.util.Random
public class DarkRPG {


    
private static DecimalFormat df2 = new DecimalFormat(".##");
   
    /**
     * @param args the command line arguments
     */

    
   
    public static void main(String[] args) {
        //Properties related
        Properties prop = new Properties();
        InputStream inputS = null;
        
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        
        //GAME VARIABLES
        String[] enemies = { "Skeleton", "Zombie", "Elf", "Brother Fan the Chad Elf","SANS?!" };
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;
        int eneLevel = 1;
        int depth = 0;
        int floor = 1;
        
        
        
        //ASCII_ART CLASS LOAD
        ASCII_Art arts = new ASCII_Art();
        
        
        //OTHER CLASS ACCESS
        loot lootlist = new loot();
        String sw1 = lootlist.sword1;
        String sw2 = lootlist.sword2;
        String sw3 = lootlist.sword3;
        String sw4 = lootlist.sword4;
        String sw5 = lootlist.sword5;
        
        String arm1 = lootlist.armor1;
        String arm2 = lootlist.armor2;
        String arm3 = lootlist.armor3;
        String arm4 = lootlist.armor4;
        String arm5 = lootlist.armor5;
        
        int equipadddamage = 0;
        int equipaddhealth = 0;
        double equipaddhit = 0;
        double equipaddeva = 0;
        
        
        //DECIMAL LIMITER
        
        
        //Evasion
        double plyEva = 57;
        double plyHit = 50;
        double enemyHit = 61;
        double enemyEva = 74;
        
        
        //Player Variables
        int maxHealth = 100;
        int health = maxHealth;
        int charge = 1;
        int chargeDmg = 0;
        
        // int health = 100;
        int attackDamage = 50+5000;//DEBUG DAMAGE
        int mindamage = 1;
        int numHealthPots = 3;
        int money = 0;
        int moneyDrop = 50;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; //Percentage
        //XP
        int plyLevel = 0;
        double XP = 0;
        double nextlvXP = 50;
        //BOSS VARIABLES
        int SansHealth = 1;
        int SansAtkDmg = 1;
        double SansEva = 1000;
        double SansHit = 1000;
        
        int ChadHealth = 1000;
        int ChadAtkDmg = 200;
        double ChadEva = plyHit;
        double ChadHit = plyEva;
        
        
        boolean mainmenu = true;
        boolean running = true;
        
        
        
        while(mainmenu) {                                                                       // MAIN MENU!!!!! 
        System.out.println("=========================");
        System.out.println("\tWelcome to the dungeon.\n");
        System.out.println("1. Explore the dungeon");
        System.out.println("2. Exit");
        System.out.println("=========================");
        String inputMenu = in.nextLine();
        if(inputMenu.equals("1")){
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
            
            mainmenu = false;
            break;
            
            
        } 
        
        else if(inputMenu.equals("2")){
            running = false;
            break;
            
        }
        }
        
        boolean load = false;
        GAME:
        while(running) {                                                                            //GAME START !!!!!
           if(load == false) {
            System.out.println("###\nLOAD SAVE??? 1/0\n###");
            String inpsave = in.nextLine();
            if(inpsave.equals("1")) {
                try {

		inputS = new FileInputStream("DarkRPGsave.properties");

		// load a properties file
		prop.load(inputS);
                

		// get the property value and print it out
		System.out.println(prop.getProperty("savedmonvalue"));
                int moneyloaded = Integer.parseInt(prop.getProperty("savedmonvalue"));
                money = moneyloaded;
                
		//System.out.println(prop.getProperty("dbuser"));
		//System.out.println(prop.getProperty("dbpassword"));

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (inputS != null) {
			try {
				inputS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
                System.out.println("###########\nCONTINUED LAST SAVE\n###########");
                load = true;
            } else {
                continue;
            }
           }
        
            
            
            System.out.println("------------------------------------");
            
            
            int curenemyHealth = rand.nextInt(maxEnemyHealth);
            int enemyHealth = curenemyHealth;
            String enemy = enemies[rand.nextInt(enemies.length)];
            
            System.out.println("\t# " +enemy +" has appeared! #\n");
            //BOOLEANS
            boolean Boss = false;
            boolean SansFight = false;
            boolean ChadFight = false;
            
            //SANS?! ENCOUNTER DATA
            if(enemy.equals("SANS?!")){
                Boss = true;
                  eneLevel = 1;
                  curenemyHealth = SansHealth;
                  enemyHealth = enemyHealth - enemyHealth + SansHealth;  
                  enemyAttackDamage = enemyAttackDamage + SansAtkDmg;
                  enemyEva = enemyEva + SansEva;
                  enemyHit = enemyHit + SansHit;
                  
                  maxEnemyHealth = maxEnemyHealth - maxEnemyHealth + 1;
                  
                } 
            
            //SPECIAL CHAD ELF ENCOUNTER DATA
            else if(enemy.equals("Brother Fan the Chad Elf")){
                Boss = true;
                  eneLevel = 70;
                  curenemyHealth = ChadHealth;
                  enemyHealth = enemyHealth - enemyHealth + ChadHealth;  
                  enemyAttackDamage = enemyAttackDamage + ChadAtkDmg;
                  enemyEva = enemyEva + ChadEva;
                  enemyHit = enemyHit + ChadHit;
                  
                  maxEnemyHealth = maxEnemyHealth - maxEnemyHealth + 1000;
                  
                  //NORMAL ENEMIES DATA
                } else {
                Boss = false;
                eneLevel = 1+floor/2;
                maxEnemyHealth = 100+(plyLevel)+(eneLevel*2);
                curenemyHealth = rand.nextInt(maxEnemyHealth);
                enemyHealth = curenemyHealth;
                enemyAttackDamage = 25+(1*(plyLevel/2));
                enemyAttackDamage = 1+(eneLevel+rand.nextInt(enemyAttackDamage));
                double dblplyLevel = plyLevel; //CONVERT INT TO DOUBLE
                enemyHit = 50;
                enemyEva = 55;
                
                
            }
            
            
            while(enemyHealth > 0) {
        
        //EVASION FORMULAS        
        double plyDiffHit = plyEva - enemyHit; //difference of your eva and enemy hit / acc || 3
        double EnemyDiffHit = enemyEva - plyHit; //difference of enemy eva and your acc || 3
        double possPlyNotGetHit = plyDiffHit / plyEva; //possibility of enemy damage to MISS the player. || ex 3/55 = 0.054
        double possEnemyNotGetHit = EnemyDiffHit / enemyEva; //possibility of player damage to MISS the enemy. ex
        double plyHitPercent = (1 - possEnemyNotGetHit) * 100; //0.054-->5.4%
        if(plyHitPercent>100){
            plyHitPercent=100;
        }
        double enemyHitPercent = (1 - possPlyNotGetHit) * 100; //
        if(enemyHitPercent>100){
            enemyHitPercent = 100;
        }
                //SANS SPRITE ENCOUNTER IN LOOP
                if(enemy.equals("SANS?!")){
                    System.out.println("");
                    String PrintSans = arts.printSans();                    
                    System.out.println("");

                } 
                //CHAD ELF SPRITE ENCOUNTER IN LOOP
                if(enemy.equals("Brother Fan the Chad Elf")){
                    System.out.println("");
                    String PrintChad = arts.printChad(); //calls the method printChad() from ASCII_Art.    
                    System.out.println("");

                } 
                
                
              
                System.out.println("------------------------------------");
                System.out.println("\tYour HP: " +health);
                System.out.println("\t" +enemy +"'s HP: " +enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Fight");
                System.out.println("\t1a. Analyze Situation");
                System.out.println("\t2. Inventory");
                System.out.println("\t3. Run");
                System.out.println("------------------------------------");
                
                String input = in.nextLine();
                System.out.println("------------------------------------");
                
                
                String potionInv = null;                                        //POTIONS
                        
                    if(numHealthPots > 0) {
                        potionInv = "Potion" +" x ";
                       
                        
                    }
                    else {
                        //System.out.println("\t> You have no health potions left, defeat enemy for a chance to gain one");
                        potionInv = "Empty";
                       
                    }
                
                
                if (input.equals("1")) {                                        // FIGHT
                    //SCREEN JUMP TO CLEAN
                    
                    
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("------------------------------------");
                    
                    int damageDealt = mindamage+equipadddamage+rand.nextInt(attackDamage);
                    int damageDealtCharged = mindamage+equipadddamage+rand.nextInt(attackDamage+chargeDmg);
                   // int damageTaken = rand.nextInt(enemyAttackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);
                    //Evasion range for enemy.
                    double eneMax = possPlyNotGetHit;
                    double eneMin = 0; //0.05 minimal of player not getting hit.
                    double enerange = eneMax - eneMin + 0.01;
                    //Evasion range for player.
                    double plyMax = possPlyNotGetHit;
                    double plyMin = 0.01; //0.05 minimal of player not getting hit.
                    double plyrange = plyMax - plyMin + 0.01;
                    //Evasion randomizer or chance
                    double eneRandEva = (double)(Math.random()) + eneMin;
                    
                    System.out.println("1. Attack");
                    System.out.println("2. Charge up Power");
                    System.out.println("3. Charge Attack");
                    String inpAtk = in.nextLine();
                    if(inpAtk.equals("1")){
                         
                        if(eneRandEva >= possEnemyNotGetHit) {
                        eneRandEva = possEnemyNotGetHit;
                        
                        enemyHealth -= damageDealt;
                        System.out.println("\t> You hit the enemy!");       
                       // new DecimalFormat("$#.00").format(eneRandEva);
                        df2.setRoundingMode(RoundingMode.UP);
                        //System.out.println("(debug) chance : " +df2.format(eneRandEva));
                        System.out.println("\t> You strike the "+enemy +" for " +damageDealt +" damage!");
                        //You hit the enemy!
                    } else {
                        enemyHealth = enemyHealth + 0;
                        df2.setRoundingMode(RoundingMode.UP);
                        System.out.println("\t> Your attack missed the enemy!");
                       // System.out.println("\t> The enemy evaded the attack!");System.out.println("(debug) chance : " +df2.format(eneRandEva));
                       
                       //Evasion randomizer or chance
                    double plyRandEva = (double)(Math.random()) + plyMin; //double math random is going to roll random number from 0-1, but since it's multiplied by plyrange, which is -> 
                    if(plyRandEva >= possPlyNotGetHit) {
                        plyRandEva = possPlyNotGetHit;
                        
                        health -= damageTaken;
                        System.out.println("\t> The " +enemy +" hits you!");
                        new DecimalFormat("$#.00").format(plyRandEva);
                        df2.setRoundingMode(RoundingMode.UP);
                       // System.out.println("(debug) chance : "+df2.format(plyRandEva));
                        System.out.println("\t> You receive " +damageTaken +" damage!");
                        //the monster hit you!
                    } else {
                        health = health + 0;
                        df2.setRoundingMode(RoundingMode.UP);
                        System.out.println("\t> The " +enemy +" missed it's attack!");
                       // System.out.println("\t> You evaded the "+enemy +"'s attack!"); System.out.println("(debug) chance : "+df2.format(plyRandEva));
                        //You evaded!
                    }
                   }    
                        
                  } else if(inpAtk.equals("2")){
                      System.out.println("\t> You charge up power inside.");
                     
                      charge += 1;
                      System.out.println("\t> Charge Level : "+charge);
                      boolean charged = true;
                       
                      chargeDmg = attackDamage * charge;
                      
                      String cDmgNum = Integer.toString(chargeDmg);
                      System.out.println("\t> You charged up +"+(chargeDmg-attackDamage)+" max damage!");
                      
                  } else if(inpAtk.equals("3")){
                      charge = 1;
                      boolean charged = false;
                      if(eneRandEva >= possEnemyNotGetHit) {
                        eneRandEva = possEnemyNotGetHit;
                        
                        enemyHealth -= damageDealtCharged;
                        System.out.println("\t> You hit the enemy!");       
                       // new DecimalFormat("$#.00").format(eneRandEva);
                        df2.setRoundingMode(RoundingMode.UP);
                        //System.out.println("(debug) chance : " +df2.format(eneRandEva));
                        System.out.println("\t> You strike the "+enemy +" for " +damageDealtCharged +" damage!");
                        //You hit the enemy!
                    } else {
                        enemyHealth = enemyHealth + 0;
                        df2.setRoundingMode(RoundingMode.UP);
                        System.out.println("\t> Your attack missed the enemy!");
                       // System.out.println("\t> The enemy evaded the attack!");System.out.println("(debug) chance : " +df2.format(eneRandEva));
                       
                       //Evasion randomizer or chance
                    double plyRandEva = (double)(Math.random()) + plyMin; //double math random is going to roll random number from 0-1, but since it's multiplied by plyrange, which is -> 
                    if(plyRandEva >= possPlyNotGetHit) {
                        plyRandEva = possPlyNotGetHit;
                        
                        health -= damageTaken;
                        System.out.println("\t> The " +enemy +" hits you!");
                        new DecimalFormat("$#.00").format(plyRandEva);
                        df2.setRoundingMode(RoundingMode.UP);
                       // System.out.println("(debug) chance : "+df2.format(plyRandEva));
                        System.out.println("\t> You receive " +damageTaken +" damage!");
                        //the monster hit you!
                    } else {
                        health = health + 0;
                        df2.setRoundingMode(RoundingMode.UP);
                        System.out.println("\t> The " +enemy +" missed it's attack!");
                       // System.out.println("\t> You evaded the "+enemy +"'s attack!"); System.out.println("(debug) chance : "+df2.format(plyRandEva));
                        //You evaded!
                    }
                   } 
                      
                  }
                    
                    
                    
                    
                    
                    
                     
                    
                    
                      
                    
                    System.out.println("");
                  
                    if(health<1) {
                        System.out.println("\t You have taken too much damage. You are too weak to go on.");
                        
                        
                    }
                }
                else if (input.equals("1a")) {                                  // ANALYZE SITUATION
                    //SCREEN JUMP TO CLEAN
                    
                    //String addedDamage = (cDmgNum);
                    
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    
                   
                    System.out.println("############################################################");
                    System.out.println("                        ANALYZE                             ");
                    System.out.println("############################################################");
                    if(SansFight==true){
                        String[] SansDialogues = {"\t*You feel your sins crawling behind you", "\t*You feel like you're going to have a bad time", "\t*You felt your sins weighing on your neck"
                          ,"\t*The easiest enemy. Can only deal 1 damage."};
                        String SansSystem = SansDialogues[rand.nextInt(SansDialogues.length)];
                        System.out.println(SansSystem);
                    }
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("                      FLOOR " +floor +", DEPTH " +depth);
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("Ply LV  : " +plyLevel);
                    System.out.println("Ply HP  : " +health +"/" +maxHealth);
                    System.out.println("Ply DMG : " +(mindamage+equipadddamage)+"-"+(attackDamage+equipadddamage));
                   // new DecimalFormat("$#.00").format(plyHitPercent);
                   df2.setRoundingMode(RoundingMode.UP);
                    System.out.println("Ply HIT : " +plyHit +"("+df2.format(plyHitPercent)+"% chance to hit enemy.)");
                    System.out.println("Ply EVA : " +plyEva);
                    System.out.println("Inventory : " +"\n1. " +potionInv +"" +numHealthPots);
                    System.out.println("------------------------------------------------------------");
                    System.out.println(enemy +" LV  : " +eneLevel);
                    System.out.println(enemy +" HP  : " +enemyHealth +"/" +curenemyHealth);
                    System.out.println(enemy +" DMG : " +"1-"+enemyAttackDamage);
                  //  new DecimalFormat("$#.00").format(enemyHitPercent);
                  df2.setRoundingMode(RoundingMode.UP);
                    System.out.println(enemy +" HIT : " +enemyHit +"("+df2.format(enemyHitPercent)+"% chance for " +enemy +" to hit you)");  
                    System.out.println(enemy +" EVA : " +enemyEva);
                    System.out.println("############################################################");
                    System.out.println("                                                            ");
                    
                }
                else if (input.equals("2")) {                                   // DRINK HP POTION --> TO BE INVENTORY
                    //SCREEN JUMP TO CLEAN
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("------------------------------------");
                    boolean invOpen = true;
                    
                    INVENTORY:
                    while(invOpen){
                        System.out.println("##############################");
                        System.out.println("INVENTORY");
                        System.out.println("##############################");
                        System.out.println("(press 0 to get back into the fight)");
                        
                    
                    System.out.println("0. Back");
                    System.out.println("1."+potionInv +"" +numHealthPots);
                    System.out.println("2.");
                    System.out.println("==============================");
                    System.out.println("Use :");
                    String input2 = in.nextLine();
                    if(input2.equals("0")){
                        invOpen = false;
                    }
                    
                    if(input2.equals("1")){
                        while(health==maxHealth) {
                            numHealthPots = numHealthPots - 0;
                            System.out.println("\t> Your health is already full!");
                            System.out.println("\n\n\n\n\n\n\n\n\n\n");
                            continue INVENTORY;
                        }
                        if(health<maxHealth) {
                            numHealthPots--;
                            health += healthPotionHealAmount;
                        }
                        
                        if(health >= maxHealth) {
                            health = maxHealth;
                         }
                        
                        
                        System.out.println("\n\n\n\n\n\n\n\n\n\n"+"\t> You drink a health potion, healing yourself for " +healthPotionHealAmount 
                                            +"\n\t> You now have " +health + "HP"
                                            +"\n\t> You have " + numHealthPots +" health potions left.\n");
                        //System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        continue INVENTORY;
                    }
                    
                    
                    }
                }
                else if (input.equals("3")) {                                   // RUN 
                    //SCREEN JUMP TO CLEAN
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("------------------------------------");
                    
                    System.out.println("\t> You run away from the " +enemy +"!");
                    continue GAME;
                }   
                else {
                    System.out.println("\tInvalid Command");
                }
            } //while(enemyhealth > 0) END
           
            if(health<1){
                System.out.println("");
                System.out.println("You limp out of the dungeon, weak from battle");
                break;
                
            }
            
            double MonXP = (eneLevel*3) + rand.nextInt(maxEnemyHealth) * 0.15;                                // EXPERIENCE FORMULAS
            XP = XP + MonXP;
                                
                            
                        
            if(XP >= nextlvXP) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n");                     
                System.out.println("************************");                 
                System.out.println("--> You leveled up! <--");
                System.out.println("--> HP +"+"2!");
                System.out.println("--> MAX DMG +"+"5!");
                System.out.println("--> You are now level "+plyLevel+"! <--");
                System.out.println("************************");
                maxHealth = maxHealth + 2;
                attackDamage = attackDamage +5;
                plyLevel = plyLevel +1;
                nextlvXP = nextlvXP + nextlvXP * 1.15;
                System.out.println(+nextlvXP +" to level up!");
               
            } else {
            double nextlvxp = nextlvXP - XP;
            
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
            System.out.println("------------------------------------");
            System.out.println(" # " +enemy +" was defeated! # ");
            System.out.println("");
            System.out.println(" # You gained " +MonXP +" XP! #");
            System.out.println("");
            System.out.println(" # Total XP : "+XP +" XP #");
            System.out.println("");
           System.out.println(" # " +nextlvxp +" XP left to level up!" +" #");
           System.out.println("");
            
            }
            
            System.out.println(" # You have " +health +"/"+maxHealth +" HP # ");
            System.out.println("");
           
            int moneyGained = rand.nextInt(moneyDrop);            //MONEY DROP
                money = money + moneyGained;
                System.out.println(" # The " +enemy  +" dropped " +moneyGained +" Coins! #");
                System.out.println("");
                System.out.println(" # You have " +money + " coins! #");
                System.out.println("");
            if(rand.nextInt(100) < healthPotionDropChance) {
                numHealthPots++;               
                System.out.println(" # The " +enemy +" dropped a health potion! # ");
                System.out.println("");
                System.out.println(" # You have " +numHealthPots + " health potions! #");
                                
            }
            
           
            System.out.println("------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("1. Continue Fighting");
            System.out.println("2. Exit Dungeon?");
            System.out.println("3. Shop");
            System.out.println("4. Save");
            System.out.println("------------------------------------");
            
            String input = in.nextLine();
            
            while(!input.equals("1") && !input.equals("2") && !input.equals("3") &&!input.equals("4")) {
            System.out.println("Invalid Command!");
            input = in.nextLine();
        }
            
            if(input.equals("1")) {                                             //CONTINUE VENTURES
                depth = depth +1;                                             // DEPTH & FLOOR
                            System.out.println("\t*********************************");
                            System.out.println("\t You went further in the dungeon.");
                            System.out.println("\t*********************************");
                            System.out.println("");
                            
                            if(depth % 3 == 0){
                                floor = floor +1;
                            
                        }
               continue;
            }
            else if(input.equals("2")) {                                        //STOP GAME
                System.out.println("You emerged from the dungeon.");
                break;
            }
            else if(input.equals("3")) {                                        //SHOP
                boolean shop = true;
                SHOP:
                while(shop) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n");    
                System.out.println("---------------------------------");
                System.out.println("SHOP");
                System.out.println("What do you want to buy?");
                System.out.println("Money :" +money +" Coins");
                System.out.println("1. +1 Min damage & +3 Max Damage, 50 coins "
                        +"\n2. +1 Potion, 25 coins" 
                        +"\n3. +100 Health, 75 coins" 
                        +"\n4. Weapons & Armor"
                        +"\n5. Continue Journey");
                
                System.out.println("---------------------------------");
                
             
                
                String inputShop = in.nextLine();
                while
                (          !inputShop.equals("1") 
                        && !inputShop.equals("2") 
                        && !inputShop.equals("3")
                        && !inputShop.equals("4")
                        && !inputShop.equals("5"))
                {
                System.out.println("Invalid Command!");
                inputShop = in.nextLine();
        }
                if(inputShop.equals("1")) {
                    
                    if(money<=50){
                        System.out.println("You don't have enough money.");
                        continue;
                    } else {
                        attackDamage = attackDamage + 3;
                        mindamage = mindamage +1;
                        System.out.println("You upgraded your min damage by 1 & max damage by 3! now your damage is " +(mindamage+equipadddamage)+"-"+(attackDamage+equipadddamage) +"!" +"\nYou have " +money +" Coins left!");
                        money = money - 50;
                        
                    } 
                    
                    
                }
                
                if(inputShop.equals("2")) {
                    if(money<25) {
                        System.out.println("You don't have enough money.");
                        continue;
                    } else {
                    numHealthPots = numHealthPots + 1;
                    System.out.println("You bought 1 potion! now you carry " +numHealthPots +" potions in your inventory!");
                    money = money - 25;
                    
                    }
                }
                
                else if(inputShop.equals("3")) {
                    if(money<75) {
                        System.out.println("You don't have enough money.");
                        continue;
                    } else {
                    maxHealth = maxHealth + 100;    
                    System.out.println("You added 100 max health! now your health is " +health +" points!");
                    money = money - 80;
                    
                    }
                  }
                else if(inputShop.equals("4")) {
                    boolean armory = true;
                    System.out.println("---------------------------------\n1. Weaponry "
                        +"\n2. Armors "
                        +"\n---------------------------------");
                    
                    String inputArmory = in.nextLine();
                while
                (          !inputArmory.equals("1") 
                        && !inputArmory.equals("2"))
                {
                System.out.println("Invalid Command!");
                inputShop = in.nextLine();
                }
                
                boolean sw1b = false;
                boolean sw2b = false;
                boolean sw3b = false;
                boolean sw4b = false;
                boolean sw5b = false;
                
                if(inputArmory.equals("1")) {
                    System.out.println("---------------------------------");
                    System.out.println("1."+sw1 + " 1 coins");
                    System.out.println("2."+sw2);
                    System.out.println("3."+sw3);
                    System.out.println("4."+sw4);
                    System.out.println("5."+sw5);
                    
                    String chsWep = in.nextLine();
                    if(chsWep.equals("1"))
                    {
                        System.out.println("---------------------------------");
                        System.out.println("Wooden Sword\n\nA sword made out of wood...how is there even wood in a dungeon underground anyways?\n\n Increases min and max damage by 20.");
                        System.out.println("---------------------------------");
                        System.out.println("Buy This? Y/N");
                        System.out.println("---------------------------------");
                        String yesno = in.nextLine();
                        String yn = yesno.toUpperCase();
                        if(yn.equals("Y")) {
                            if(sw1b == true) {
                                System.out.println("You've bought this");
                            }
                            if(money>=1) {
                                
                                    sw1b = true;
                                    money = money - 1;
                                    equipadddamage = 20;
                                    System.out.println("you've bought the " +sw1+".");
                                
                                          
                            
                            }
                            else {
                                System.out.println("**********************\nNot enough money.\n**********************");
                            }
                        }
                        else if(yn.equals("N")) {
                            continue;
                        }
                    }
                    
                
                if(inputArmory.equals("2")) {
                    System.out.println("---------------------------------");
                    System.out.println(arm1);
                    System.out.println(arm2);
                    System.out.println(arm3);
                    System.out.println(arm4);
                    System.out.println(arm5);
                }
             
                }
            
            
               
                
               }   
                 else if(inputShop.equals("5")) {
                    continue GAME;
                }
            
            
            }
                
            }           
            else if(input.equals("4")) {
                System.out.println("###########\nYou saved your progress!\n###########");
                try {
                    
           //set properties values to prop
           String savedmoney = Integer.toString(money);
           
           prop.setProperty("savedmonvalue",savedmoney);
           prop.setProperty("test","test2");
           
           //save properties to file
           prop.store(new FileOutputStream("DarkRPGsave.properties"), null);
            
          // running = false;
           
       } catch (IOException ex) {
           ex.printStackTrace();
       }  
                
            }
            
       }
        
        
        System.out.println("######################");
        System.out.println("# THANKS FOR PLAYING #");
        System.out.println("######################");
        
        
        
       
    }

    
    
  /* 
                                                                                    // SAVE SYSTEM TEST
    
    int maxHealth2 = 100;
   int health2 = maxHealth2;
   int plyLevel2 = 1;
    int[] SaveInformation = {maxHealth2, health2, plyLevel2};
        int pmHLoc = 0;
        int phLoc = 1;
        int pLvLoc = 2;
   
q    public DarkRPG(){
        //ReadPlayer("Save File.txt");  
       
        SavePlayer("Save File.txt");
    }
   
   
   private void ReadPlayer(String filePath) {                                    //READFILE
        File inputFile;
        BufferedReader inputReader;
        
        try{
            inputFile = new File(filePath);
            inputReader = new BufferedReader(new FileReader(inputFile));
            String fileText = inputReader.readLine();
            System.out.println(fileText);
            inputReader.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    } 
   private void SavePlayer(String filePath) {              //WRITEFILE
       File outputFile;
       BufferedWriter outputWriter;
       
       try{
           outputFile = new File(filePath);
           
           outputWriter = new BufferedWriter(new FileWriter(outputFile));
           
           for (int i = 0; i<SaveInformation.length; i++) {
               outputWriter.write(Integer.toString(SaveInformation[i]));
           }
           
           outputWriter.close();
       }catch (Exception e) {
           e.printStackTrace();
       }
   }*/
}
    
  


