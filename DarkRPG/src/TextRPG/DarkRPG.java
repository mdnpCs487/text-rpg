package TextRPG;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *

                                                                                   LAST UPDATED ON 18/01/20, WORKING ON :
                                                                                   - ADDING CRIT CHANCE RES AND CRIT DAMAGE RES
                                                                                   - RESET WAIT TIME WHEN USING CHARGE ATTACK
                                                                                   - ENEMY AND PLAYER STAT BALANCING IN EARLY AND LATER STAGES OF GAME.


//SEARCH KEYWORDS//NAVIGATION//TOP
GAME VARIABLES
. MAIN MENU
. GAME START
. POTIONS 
. FIGHT
. ANALYZE SITUATION
. DRINK HP POTION
. RUN
. EXPERIENCE FORMULAS
. CONTINUE VENTURES
. STOP GAME
. SHOP

/
package TextRPG;

/**
 *
 * @author asus
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.math.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.Random;
public class DarkRPG {

    
    private AudioPlay au;
    
    
    private static DecimalFormat df2 = new DecimalFormat(".##");
   
    /**
     * @param args the command line arguments
     */
//shopDialogue sdial = new shopDialogue();
    
    public static void tooMuchPoint() {
        System.out.println("You put in too much.");
    }
   
    public static void main(String[] args) {
        
        
        //Audio files
        //testSound2 audio = new testSound2();
        
        AudioPlay audioP = new AudioPlay();
        Other oth1 = new Other(audioP, audioP.f);
        //Properties related
        Properties prop = new Properties();
        InputStream inputS = null;
        
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        
            //--------------------------------------------------//GAME VARIABLES//
        String[] enemies = { "Skeleton", "Zombie", "Elf", "Goblin", "Slime", "Bird", "Turtle", "Cat", "Money Bear", "XP Bear"  };
        String[] bossEnemies = { "Brother Fan the Chad Elf", "SANS?!" };
        String[] lines = {"------------------------------------", "************************************"};
        
        String cDmgNum = "";
        String addedDamage = " +("+cDmgNum+")";
        // int health = 100;
        int attackDamage = 25;//DEBUG DAMAGE
        int mindamage = 1;
        int numHealthPots = 3;
        int money = 0;
        
        int numMagicBomb = 999;
        double MonXP = 0;
        
        int moneyDrop = 75;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; //Percentage
        //XP
        int plyLevel = 1;
        double XP = 0;
        double displayXP = 0;
        double nextlvXP = 50;
        
        double critRand = (double)(Math.random());
        boolean crit = false;
        double beforecritdmg = 0;
        double enebeforecritdmg = 0;
        //STAT ALLOCATION STUFF
        int pointsMax = 0;
        boolean inputProcess = true;  
        boolean statInputVit = true;      
        boolean statInputStr = true;
        boolean statInputAgi = true;
        boolean statInputIntel = true;
        boolean statInputEnd = true;
        boolean statInputLuck = true;   
        boolean statInputDex = true;
        int bonusGold = pointsMax * 30;
        
        
            //----------------------------------------------- ENEMY VARIABLES //
        int maxEnemyHealth = 80;
        int enemyAttackDamage = 30;
        int eneLevel = 1;
        int depth = 1;
        int floor = 1;
        
        double enemyHit = 61;
        double enemyEva = 74;
        
        int eneMindamage = 1;
        
        String enemy = "";
        String bossEnemy = "";
        
        //ENEMY ATTRIBUTES      
        int eneVit = 0;
        int eneStr = 0;
        int eneAgi = 0;
        int eneIntel = 0;
        int eneEndur = 0;
        int eneDex = 0;
        int eneLuck = 0;
        double eneCritchance = 0;
        double eneCritdmg = 1.3;
        
        // WAITTIME VARIABLES
        int plyWaitTime = 160;
        int eneWaitTime = 150;
        
        int plyAtkWait = 90;
        int eneAtkWait = 100;
        
        int eneMana = 30;
        
        
        
            // ETC
        
        boolean postmenu;
        
        int[] bossHealth = {1, 1000};
        
        int moneyGained = 0;
        
        int bonusMoney = 0;
        
        double bonusXP = 0;
        
        
        
        boolean chargebnsdmg = false;
        boolean showbnsdmg = false;
        
        
        
        //ASCII_ART CLASS LOAD
        ASCII_Art arts = new ASCII_Art();
        
        
        //OTHER CLASS ACCESS TEST
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
        
            // ----------------------------------SHOP AND EQUIPMENT VARIABLES //
        
        int weapondamage = 0;
        
        int equipaddhealth = 0;
        double equipaddhit = 0;
        double equipaddeva = 0;
        
        boolean wp1b, wp2b, wp3b, wp4b, wp5b = false;
        
        //DECIMAL LIMITER
        
        
            //EVASION
        
        
        
        
            // ----------------------------------------------PLAYER VARIABLES //
        String name;
        String myName = null;
        int maxHealth = 30;
        int health = maxHealth;
        int charge = 0;
        int chargeDmg = 0;
        
        int plyMana = 50;
        
        double plyEva = 57;
        double plyHit = 50;
        
        
        int vit = 0;
        int intel = 0;
        int agi = 0;
        int endur = 0;
        int luck = 0;
        int str = 0;
        int dex = 0;
        double critchance = 0;
        double critdmg = 1.3;
      
        boolean buffedDmg = false;
        double buffTime = 0;
        double buffDmg = 1.5;
        
        double goldMult = 1; //if luck is 10, then goldMult is 1.15 (per luck is 0.015 (1.5%) gold multiplier).
        
        //-------------------------------------------------------------DEFENSE//
        double defPoints = 0;
        double rawDef = (1000 - defPoints) / 1000; //if defPoints 700 then rawDef = 1000 - 700 = 300 --> 0.3, which is then multiplied to enemy damage, hence making the enemy only deal 0.3 of it's damage.
        double disDef = defPoints/10; //if defPoints 700 then disDef = 700/10=70(%)damage reduction -- for display purposes.
        
        double magiResPoints = 50;
        double rawMagiRes = (1000 - magiResPoints) / 1000;
        double disMagiRes = magiResPoints/10;
        
        double eneDefPoints = 0;
        double rawEneDef = (1000 - eneDefPoints) / 1000;
        double disEneDef = eneDefPoints/10;
        
        double eneMagiResPoints = 50;
        double eneRawMagiRes = (1000 - magiResPoints) / 1000;
        double eneDisMagiRes = magiResPoints/10;
        
        
        
        
        
        
        //------------------------------------------------------BOSS VARIABLES//
        int SansHealth = 1;
        int SansAtkDmg = 50;
        int SansStamina = 100;
        
        double SansEva = 3000;
        double SansHit = 1000;
        
        int ChadHealth = 1250;
        int ChadAtkDmg = 300;
        double ChadEva = plyHit;
        double ChadHit = plyEva;
        
        boolean running = true;
        boolean mainmenu = true;
        boolean mainmenu2 = true;
        
        
        
        
        
        
        boolean load = false;
        GAME:
        while(running) {                                                                            //GAME START !!!!!
           
            while(mainmenu) {                                                                       // MAIN MENU!!!!! 
        
            //
            //String filepath = "BruhSoundEffect.wav";
            //audio.playMusic(filepath);
            
            //
            //PUT THE BLOCK HERE AGAIN IF U WANT
            
        
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        //audioP.playSoundloop(randomNumberInRange(1,5));
        
        
        System.out.println("||===============================================||");
        System.out.println("\t Welcome to the dungeon.\n");
        System.out.println("\t1. Explore the dungeon");
        System.out.println("\t2. Exit");
        System.out.println("||===============================================||");
        
        MAINMENU: 
        while(mainmenu2) {
        
        String inputMenu = in.nextLine();
        
               
        if(inputMenu.equals("1")){                                              // CUE EXPLORE THE DUNGEON INTRO //
            System.out.println("\n\n\n\n");
            audioP.playSound(0);
            
            //au.stopSound();
            
            
            mainmenu = false;
            break;
            
            
        } 
        
        else if(inputMenu.equals("2")){
            audioP.stopSound();
            audioP.playSound(1);
            
            System.exit(0);
            
            load = true;
            running = false;
            mainmenu = false;
            break;
            
            
            
        } else {
            System.out.println("Invalid Command");
            continue MAINMENU;
        }
        }
        }
            
            
            if(load == false) {
            System.out.println("##################\nLOAD SAVE??? 1/0\n##################");
            System.out.println("DON'T ENTER 1 IF YOU'VE NOT SAVED.");
            String inpsave = in.nextLine();
            if(inpsave.equals("1")) {
                try {
                audioP.stopSound();
                audioP.playSound(1);    
                audioP.stopSoundloop();
                //
                
                //    
                  
                    
		inputS = new FileInputStream("DarkRPGsave.properties");

		// load a properties file
		prop.load(inputS);
                

		// get the property value and print it out
		System.out.println(prop.getProperty("savedmonvalue"));
                
                int moneyloaded = Integer.parseInt(prop.getProperty("savedmonvalue"));
                money = moneyloaded;
                
                int healthpotloaded = Integer.parseInt(prop.getProperty("savednumHealthPots"));
                numHealthPots = healthpotloaded;
                
                int plylvloaded = Integer.parseInt(prop.getProperty("savedplyLevel"));
                plyLevel = plylvloaded;
                
                int attackdamageloaded = Integer.parseInt(prop.getProperty("savedattackDamage"));
                attackDamage = attackdamageloaded;
                
               int wpdamageloaded = Integer.parseInt(prop.getProperty("savedWPdmg"));
               weapondamage = wpdamageloaded;
                
               int maxhealthloaded = Integer.parseInt(prop.getProperty("savedmaxHealth"));
               maxHealth = maxhealthloaded;
                
               double xploaded = Double.parseDouble(prop.getProperty("savedXP"));
               XP = xploaded;
               
               double displayxploaded = Double.parseDouble(prop.getProperty("saveddisplayXP"));
               displayXP = displayxploaded;
                
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
                System.out.println("\n\n\n\n\n\n\n\n\n\n");
                System.out.println("######################\nCONTINUED LAST SAVE\n######################");
                load = true;
            } else if(inpsave.equals("0")){
                audioP.stopSound();
                audioP.playSound(1); 
                audioP.stopSoundloop();
                System.out.println("");
                load = true;
                System.out.println("\n\n\n\n\n\n\n\n\n\n");
                
                System.out.println("\tWhat...is your name, traveller?");        // BEGINNING, INPUT NAME --- START
        myName = in.nextLine();
        
        audioP.stopSound();
        audioP.playSound(0);
        
        if(myName.equals("op")) {
            plyLevel = 9999;
            pointsMax = 9999*3;
            maxHealth = 100 + (2*plyLevel);
            mindamage = (int)1.25* plyLevel;
            attackDamage = 3 * plyLevel;
            plyEva = plyLevel;
            plyHit = plyLevel;
            
            
            System.out.println("\tAh");
            try{
    TimeUnit.MILLISECONDS.sleep((2250));
    }
    catch (Exception ex) {
        
    } 
            System.out.println("\tThe overpowered");
            
            try{
    TimeUnit.MILLISECONDS.sleep((1500));
    }
    catch (Exception ex) {
        
    }
            
            System.out.println("\tWell ok then unreasonable amount of stats and health for you");
            try{
    TimeUnit.MILLISECONDS.sleep((1750));
    System.out.println("\n\n\n\n\n\n\n\n\n\n");
    System.out.println("\n\n\n\n\n\n\n\n\n\n");
    }
    catch (Exception ex) {
        
    }
            
        } else {                                                                // WHEN NAME ISNT ISEKAI MC, START OF THE GAME INTRO SEQUENCE //
            System.out.println("Ah so that's your name.");
            
            try{
    TimeUnit.MILLISECONDS.sleep((1000));
    }
    catch (Exception ex) {
        
    }
            
            System.out.println("just so you know, you've been hit by a truck and you're now a hero that's aiming for the demon king in his dungeon.");
            
            try{
    TimeUnit.MILLISECONDS.sleep((1750));
    }
    catch (Exception ex) {
        
    }
        
            System.out.println("He's strong so you better grind lots. Oh and fill up this form, it's for your stats.");
            
            try{
    TimeUnit.MILLISECONDS.sleep((1250));
    }
    catch (Exception ex) {
        
    } //STAT ALLOCATION
      inputProcess = true;  
      statInputVit = true;      
      statInputStr = true;
      statInputAgi = true;
      statInputIntel = true;
      statInputEnd = true;
      statInputLuck = true;   
      statInputDex = true;
      bonusGold = pointsMax * 30;
     
     pointsMax = 50;
     
     
     System.out.println("\n\n\n");
     
     INPUT:
     while(inputProcess==true) {
         System.out.println("Points : " +pointsMax);
     restartStatInput:
     while(statInputVit==true) {
       
         System.out.println("Input your Vitality :");
         System.out.println("- Increases HP by 9/point.");
         String inputVit = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
         
         try {
         
         if(Integer.parseInt(inputVit)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputVit)<=pointsMax) {
             vit = vit+Integer.parseInt(inputVit);
             System.out.println("Your Vitality : " +vit);
             pointsMax = pointsMax - Integer.parseInt(inputVit);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputVit = false;
         break;
         }catch (NumberFormatException e) {
             System.out.println("Input a round number.");
         }
         
         maxHealth = 100 + (vit * 9);
         
     }    
         
     restartStatInput:
     while(statInputStr==true) {
       
         System.out.println("Input your Strength :");
         System.out.println("- Increases minimum damage by +1 every 2 points and max damage by +2.");
         String inputStr = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         try {
         
         if(Integer.parseInt(inputStr)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputStr)<=pointsMax) {
             str = str+Integer.parseInt(inputStr);
             System.out.println("Your Strength : " +str);
             pointsMax = pointsMax - Integer.parseInt(inputStr);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputStr = false;
         break;
         }catch (NumberFormatException e) {
             System.out.println("Input a round number.");
         }
         
         attackDamage = attackDamage + 2;
         mindamage = mindamage + 1;
         
     }
    
     
     restartStatInput:
     while(statInputAgi==true) {
       
         System.out.println("Input your Agility :");
         System.out.println("- Increases speed.");
         String inputAgi = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         try {
         
         if(Integer.parseInt(inputAgi)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputAgi)<=pointsMax) {
             agi = agi+Integer.parseInt(inputAgi);
             System.out.println("Your Agility : " +agi);
             pointsMax = pointsMax - Integer.parseInt(inputAgi);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputAgi = false;
         break;
         } catch(NumberFormatException e) {
             System.out.println("Input a round number.");
         }
     }
     
     restartStatInput:
     while(statInputIntel==true) {
       
         System.out.println("Input your Intelligence :");
         System.out.println("- Increases mana pool.\n- Increases magic resistance.");
         String inputIntel = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         try {
         
         if(Integer.parseInt(inputIntel)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputIntel)<=pointsMax) {
             intel = intel+Integer.parseInt(inputIntel);
             System.out.println("Your Intelligence : " +intel);
             pointsMax = pointsMax - Integer.parseInt(inputIntel);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputIntel = false;
         break;
         }catch(NumberFormatException e) {
             System.out.println("Input a round number.");
         }
     }
     
     restartStatInput:
     while(statInputDex==true) {
       
         System.out.println("Input your Dexterity :");
         System.out.println("- Decreases attack wait time by 1 every 3 dex points.\n- Increases weapon damage. \n - Increases crit damage.");
         String inputDex = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         try {
         
         if(Integer.parseInt(inputDex)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputDex)<=pointsMax) {
             dex = dex+Integer.parseInt(inputDex);
             System.out.println("Your Dexterity : " +dex);
             pointsMax = pointsMax - Integer.parseInt(inputDex);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputDex = false;
         break;
         }catch(NumberFormatException e) {
             System.out.println("Input a round number.");
         }
     }
     
     restartStatInput:
     while(statInputEnd==true) {
       
         System.out.println("Input your Endurance :");
         System.out.println("- Increases physical & ailment resistances.");
         String inputEnd = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         try {
         
         if(Integer.parseInt(inputEnd)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputEnd)<=pointsMax) {
             endur = endur+Integer.parseInt(inputEnd);
             System.out.println("Your Endurance : " +endur);
             pointsMax = pointsMax - Integer.parseInt(inputEnd);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputEnd = false;
         break;
         }catch(NumberFormatException e) {
             System.out.println("Input a round number.");
         }
     }
     
     restartStatInput:
     while(statInputLuck==true) {
       
         System.out.println("Input your Luck :");
         System.out.println("- Increases gold gain\n - Increases loot box rate.\n - Increases drop rate. \n - Increases crit chance.");
         String inputLuck = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         try {
         
         if(Integer.parseInt(inputLuck)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputLuck)<=pointsMax) {
             luck = luck+Integer.parseInt(inputLuck);
             System.out.println("Your Luck : " +luck);
             pointsMax = pointsMax - Integer.parseInt(inputLuck);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputLuck = false;
         break;
         }catch(NumberFormatException e) {
             System.out.println("Input a round number.");
         }
     }
    
     if(pointsMax>0) {
         bonusGold = pointsMax * 30;
         System.out.println("You still have " +pointsMax +" points left. use it or let it convert to " +bonusGold +" gold?" );
         System.out.println("Use(Y)/Bonus Gold(N)");
         String inputLeftover = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         if("Y".equals(inputLeftover)) {
                statInputVit = true;
                statInputStr = true;
                statInputAgi = true;
                statInputIntel = true;
                statInputEnd = true;
                statInputDex = true;
                statInputLuck = true;
             continue INPUT;
         } else if("N".equals(inputLeftover)) {
             bonusGold = pointsMax * 30;
             pointsMax = 0;
             System.out.println("You got " +bonusGold +" gold!");
             inputProcess = true;
             
         } else {
             System.out.println("what");
         }
     } else if(pointsMax<=0) {
         
     }
     //PLAYER ATTRIBUTE FORMULAS
            maxHealth = maxHealth + (vit*9);
            health = maxHealth;
            plyWaitTime = plyWaitTime - agi;
            plyEva = plyEva + (agi / 3);
            attackDamage = attackDamage + (str*2);
            mindamage = mindamage + (str/2);
            plyMana = intel * 4;
            magiResPoints = magiResPoints + intel;
            disMagiRes = disMagiRes;
            plyAtkWait = plyAtkWait - (dex / 3);
            plyHit = plyHit + (dex / 3);
            defPoints = endur * 1.75;
            disDef = disDef;
            goldMult = goldMult + (luck * 0.015);
            rawDef = (1000 - defPoints) / 1000; //if defPoints 700 then rawDef = 1000 - 700 = 300 --> 0.3, which is then multiplied to enemy damage, hence making the enemy only deal 0.3 of it's damage.
            disDef = defPoints/10; //if defPoints 700 then disDef = 700/10=70(%)damage reduction -- for display purposes
            critchance = 0.3 + (luck * 0.002);
            critdmg = 1.3 + (dex * 0.01);
        
            rawMagiRes = (1000 - magiResPoints) / 1000;
            disMagiRes = magiResPoints/10;
            
         inputProcess = false;
         System.out.println("===ATTRIBUTE OVERVIEW===");
         System.out.println("Vitality       : " +vit +" [ Max Health : " +maxHealth +" HP ]");
         System.out.println("Strength       : " +str +" [ Damage : " +mindamage +"-" +attackDamage +" Damage ]");
         System.out.println("Agility        : " +agi +" [ Base Wait Time : " +plyWaitTime +" Points ] " +"[ Evasion : " +plyEva +" Points ]");
         System.out.println("Intelligence   : " +intel +" [ Mana : "+plyMana +" Points ] [ " +"Magical Resistance : " +disMagiRes +"% ]");
         System.out.println("Endurance      : " +endur +" [ Damage Resistance : " +disDef +"% ]");
         System.out.println("Dexterity      : " +dex +" [ Attack Wait Time : " +plyAtkWait +" Points ]" +" [ Accuracy / Hit : " +plyHit +" ]" +" [ Critical Damage : " +critdmg*100 +"% ]");
         System.out.println("Luck           : " +luck +" [ Gold Multiplier : " +(goldMult*100) +"% ]" +" [ Crit Chance : " +critchance*100 +"% ]");
         System.out.println("========================");
         
         
         boolean inputAccept = true;
         INPUTACCEPT:
         while(inputAccept==true) {   
         System.out.println("\nACCEPT?");
         System.out.println("Okay(Y)/Not Yet(N)");    
         String inputAcceptStat = in.nextLine();  
         
         audioP.stopSound();
        audioP.playSound(0);
         
         if("Y".equals(inputAcceptStat)) {
             break;
     } else if("N".equals(inputAcceptStat)) {
                inputProcess = true;
                pointsMax = 100;
                statInputVit = true;
                statInputStr = true;
                statInputAgi = true;
                statInputIntel = true;
                statInputEnd = true;
                statInputDex = true;
                statInputLuck = true;
                inputAccept = false;
                vit=0;
                str=0;
                agi=0;
                intel=0;
                endur=0;
                dex=0;
                luck=0;
             continue INPUT;
     } else {
             System.out.println("wat");
             continue INPUTACCEPT;
     }
      
         }
            
    }
            
            
        }
                
            }
            
            else {
                
                continue;
            }
           }
        
            
            
            System.out.println("------------------------------------");
            
            
            int curenemyHealth = rand.nextInt(maxEnemyHealth);
            int enemyHealth = curenemyHealth;
            
           
            
            
            //BOOLEANS
            boolean Boss = false;
            boolean SansFight = false;
            boolean ChadFight = false;
            
            if(floor%5!=0 ) {
            enemy = enemies[rand.nextInt(enemies.length)];                      // NORMAL ENEMIES
                switch (enemy) {
                    case "Money Bear":
                        {
                            System.out.println("\t# " +enemy +" has appeared! #\n");
                            Boss = false;
                            enemy=enemy;
                            eneLevel = (int)(floor*1.2);
                            
                            
                            //ATTRIBUTES
                            eneVit = (int)(eneLevel*3.5*(Math.random()* 0.75 + 1));
                            eneStr = (int)(eneLevel*2*(Math.random()* 0.75 + 1));
                            eneAgi = (int)(eneLevel*2*(Math.random()* 0.75 + 1));
                            eneIntel = (int)(eneLevel*2*(Math.random()* 0.75 + 1));
                            eneEndur = (int)(eneLevel*2*(Math.random()* 0.75 + 1));
                            eneDex = (int)(eneLevel*2*(Math.random()* 0.75 + 1));
                            eneLuck = (int)(eneLevel*2*(Math.random()* 0.75 + 1));
                            
                            maxEnemyHealth = 100+(int)(depth*1.5)+(eneVit*9);
                            curenemyHealth = rand.nextInt(maxEnemyHealth);
                            enemyHealth = 1+curenemyHealth;
                            enemyAttackDamage = (eneStr*2)+rand.nextInt(1*(depth));
                            
                            eneDefPoints = (int)eneEndur*1.75+(floor*15);
                            rawEneDef = (1000 - eneDefPoints) / 1000;
                            disEneDef = eneDefPoints/10;
                            
                            eneMagiResPoints = 50 + eneIntel;
                            eneRawMagiRes = (1000 - magiResPoints) / 1000;
                            eneDisMagiRes = eneMagiResPoints/10;
                            eneMana = (eneIntel *3);
                            /*
                            maxEnemyHealth = 125+(int)(depth*1.5)+(eneLevel*6);
                            curenemyHealth = rand.nextInt(maxEnemyHealth);
                            enemyHealth = 1+curenemyHealth;
                            enemyAttackDamage = 25+(1*(depth/2)+eneLevel);
                            enemyAttackDamage = 1+(eneLevel+rand.nextInt(enemyAttackDamage));
                            eneDefPoints = (int)60+(floor*15);
                            disEneDef = eneDefPoints/10;
                            */
                            
                            enemyHit = 50+(int)(eneDex / 3);
                            enemyEva = 55+(int)(eneAgi / 3);
                            
                            eneCritchance = 0.3 + (eneLuck * 0.002);
                            eneCritdmg = 1.3 + (eneDex * 0.006);
                            
                            eneAtkWait = randomNumberInRange(eneAtkWait-10,eneAtkWait+10);//ENEMY ATTACK WAIT TIME POINT RANDOMIZER
                            
                            bonusMoney = maxEnemyHealth * 15;

                            
                            break;
                        }
                    case "XP Bear":
                        {
                            System.out.println("\t# " +enemy +" has appeared! #\n");
                            Boss = false;
                            enemy=enemy;
                            eneLevel = (int)(floor*1.2);
                            
                            
                            //ATTRIBUTES
                            eneVit = (int)(eneLevel*3.5*(Math.random()* 0.75 + 1));
                            eneStr = (int)(eneLevel*2*(Math.random()* 0.75 + 1));
                            eneAgi = (int)(eneLevel*2*(Math.random()* 0.75 + 1));
                            eneIntel = (int)(eneLevel*2*(Math.random()* 0.75 + 1));
                            eneEndur = (int)(eneLevel*2*(Math.random()* 0.75 + 1));
                            eneDex = (int)(eneLevel*2*(Math.random()* 0.75 + 1));
                            eneLuck = (int)(eneLevel*2*(Math.random()* 0.75 + 1));
                            
                            maxEnemyHealth = 100+(int)(depth*1.5)+(eneVit*9);
                            curenemyHealth = rand.nextInt(maxEnemyHealth);
                            enemyHealth = 1+curenemyHealth;
                            enemyAttackDamage = (eneStr*2)+rand.nextInt(1*(depth));
                            
                            eneDefPoints = (int)eneEndur*1.75+(floor*15);
                            disEneDef = eneDefPoints/10;
                            
                            /*
                            maxEnemyHealth = 125+(int)(depth*1.5)+(eneLevel*6);
                            curenemyHealth = rand.nextInt(maxEnemyHealth);
                            enemyHealth = 1+curenemyHealth;
                            enemyAttackDamage = 25+(1*(depth/2)+eneLevel);
                            enemyAttackDamage = 1+(eneLevel+rand.nextInt(enemyAttackDamage));
                            eneDefPoints = (int)60+(floor*15);
                            disEneDef = eneDefPoints/10;
                            */
                            
                            enemyHit = 50+(int)(eneDex / 3);
                            enemyEva = 55+(int)(eneAgi / 3);
                            
                            eneCritchance = 0.3 + (eneLuck * 0.005);
                            eneCritdmg = 1.3 + (eneDex * 0.01);
                            
                            eneAtkWait = randomNumberInRange(eneAtkWait-10,eneAtkWait+10);//ENEMY ATTACK WAIT TIME POINT RANDOMIZER
                            bonusMoney = 0;
                            bonusXP = maxEnemyHealth*5;
                            break;
                        }
                    default:                                                    //NORMAL ENEMY ATTRIBUTES
                        {
                            
                            System.out.println("\t# " +enemy +" has appeared! #\n");
                            //NORMAL ENEMIES DATA
                            Boss = false;
                            enemy=enemy;
                            
                            /*
                            eneLevel = floor;
                            maxEnemyHealth = 110+(int)(depth*1.25)+(eneLevel*4);
                            curenemyHealth = rand.nextInt(maxEnemyHealth);
                            enemyHealth = 1+curenemyHealth;
                            enemyAttackDamage = 30+(int)(1*(depth/1.5)+eneLevel);
                            enemyAttackDamage = 1+(eneLevel+rand.nextInt(enemyAttackDamage));
                            eneDefPoints = (int)40+(floor*9);
                            disEneDef = eneDefPoints/10;
                            
                            eneAtkWait = randomNumberInRange(eneAtkWait-10,eneAtkWait+10);//ENEMY ATTACK WAIT TIME POINT RANDOMIZER
                            
                            double dblplyLevel = plyLevel; //CONVERT INT TO DOUBLE
                            enemyHit = 50;
                            enemyEva = 55;
                            */
                            
                            //ATTRIBUTES
                            eneLevel = floor*300;
                            eneVit = (int)(eneLevel*2.2*(Math.random()* 0.75 + 1));
                            eneStr = (int)(eneLevel*1.8*(Math.random()* 0.75 + 1));
                            eneAgi = (int)(eneLevel*1.6*(Math.random()* 0.75 + 1));
                            eneIntel = (int)(eneLevel*1.4*(Math.random()* 0.75 + 1));
                            eneEndur = (int)(eneLevel*1.3*(Math.random()* 0.75 + 1));
                            eneDex = (int)(eneLevel*1.5*(Math.random()* 0.75 + 1));
                            eneLuck = (int)(eneLevel*1.2*(Math.random()* 0.75 + 1));
                            
                            switch(enemy) {
                                case "Turtle":
                                    eneAgi = (int)(eneLevel*0.7*(Math.random()* 0.75 + 1));
                                    if(eneAgi<1){
                                        eneAgi = 1;
                                    }
                                    eneEndur = (int)(eneLevel*2.1*(Math.random()* 0.75 + 1));
                            }
                            
                            maxEnemyHealth = 100+(int)(depth*1.5)+(eneVit*9);
                            curenemyHealth = rand.nextInt(maxEnemyHealth);
                            enemyHealth = 1+curenemyHealth;
                            enemyAttackDamage = (eneStr*2)+rand.nextInt(1*(depth));
                            eneMindamage = 1+(eneStr/2);
                                    
                            eneDefPoints = (int)eneEndur*1.25+(floor*8);
                            if(eneDefPoints>1000) {
                                eneDefPoints = 1000;
                            }
                            rawEneDef = (1000 - eneDefPoints) / 1000;
                            disEneDef = eneDefPoints/10;
                            
                            eneMagiResPoints = 50 + eneIntel;
                            eneRawMagiRes = (1000 - magiResPoints) / 1000;
                            eneDisMagiRes = eneMagiResPoints/10;
                            eneMana = (eneIntel *3);
                            
                            enemyHit = 50+(int)(eneDex / 3);
                            enemyEva = 55+(int)(eneAgi / 3);
                            
                            eneCritchance = 0.3 + (eneLuck * 0.005);
                            eneCritdmg = 1.3 + (eneDex * 0.01);
                            
                            eneAtkWait = randomNumberInRange(eneAtkWait-10,eneAtkWait+10);//ENEMY ATTACK WAIT TIME POINT RANDOMIZER
                            
                            bonusMoney = 0;
                            bonusXP = 0;
                            break;
                        }
                }
            } else if(floor%5==0 ) {
                
            bossEnemy = bossEnemies[rand.nextInt(bossEnemies.length)];
            System.out.println("\t# " +bossEnemy +" has appeared! #\n");
                
                //SANS?! ENCOUNTER DATA
            if(bossEnemy.equals("SANS?!")){
                SansFight = true;
                
                enemy = bossEnemy;
                
                audioP.stopSoundloop();
                audioP.playSoundloop(3);
                eneLevel = 1;

                /*                
                eneAtkWait = randomNumberInRange(eneAtkWait-10,eneAtkWait+10);//ENEMY ATTACK WAIT TIME POINT RANDOMIZER
                
                curenemyHealth = SansHealth;
                enemyHealth = SansHealth;  
                enemyAttackDamage = SansAtkDmg;
                enemyEva = SansEva;
                enemyHit = SansHit;
                eneDefPoints = 0;
                disEneDef = eneDefPoints/10;
                maxEnemyHealth = maxEnemyHealth - maxEnemyHealth + 1;
                */
                
                //ATTRIBUTES
                            eneVit = 1+0*(int)(eneLevel*2.2*(Math.random()* 0.75 + 1));
                            eneStr = 1+0*(int)(eneLevel*1.8*(Math.random()* 0.75 + 1));
                            eneAgi = 1+0*(int)(eneLevel*1.6*(Math.random()* 0.75 + 1));
                            eneIntel = 1+0*(int)(eneLevel*1.2*(Math.random()* 0.75 + 1));
                            eneEndur = 1+0*(int)(eneLevel*1.4*(Math.random()* 0.75 + 1));
                            eneDex = 1+0*(int)(eneLevel*1.5*(Math.random()* 0.75 + 1));
                            eneLuck = 1+0*(int)(eneLevel*1.2*(Math.random()* 0.75 + 1));
                            
                            SansAtkDmg = str*50;
                            
                            maxEnemyHealth = maxEnemyHealth - maxEnemyHealth + 1;
                            curenemyHealth = SansHealth;
                            enemyHealth = SansHealth;
                            enemyAttackDamage = SansAtkDmg;
                            
                            eneDefPoints = 0;
                            disEneDef = eneDefPoints/10;
                            
                            eneMagiResPoints = 50 + eneIntel;
                            eneRawMagiRes = (1000 - magiResPoints) / 1000;
                            eneDisMagiRes = eneMagiResPoints/10;
                            eneMana = (eneIntel *3);
                            
                            eneCritchance = 0.3 + (eneLuck * 0.005);
                            eneCritdmg = 1.3 + (eneDex * 0.01);
                            
                            enemyHit = SansHit;
                            enemyEva = SansEva;
                            
                            eneAtkWait = randomNumberInRange(eneAtkWait-10,eneAtkWait+10);//ENEMY ATTACK WAIT TIME POINT RANDOMIZER
                
                SansStamina = 100;
                if(SansStamina<=0){
                    SansStamina = 0;
                    enemyEva = 100;
                }
                
                
                bonusMoney = floor*1000;
                } 
            
            //SPECIAL CHAD ELF ENCOUNTER DATA
            else if(bossEnemy.equals("Brother Fan")){
                Boss = true;
                
                enemy = bossEnemy;
                
                audioP.stopSoundloop();
                audioP.playSoundloop(2);
                
                eneLevel = floor*3;

                
                //ATTRIBUTES
                            eneVit = (int)(eneLevel*15*(Math.random()* 0.75 + 1));
                            eneStr = (int)(eneLevel*10*(Math.random()* 0.75 + 1));
                            eneAgi = (int)(eneLevel*5*(Math.random()* 0.75 + 1));
                            eneIntel = (int)(eneLevel*5*(Math.random()* 0.75 + 1));
                            eneEndur = (int)(eneLevel*15*(Math.random()* 0.75 + 1));
                            eneDex = (int)(eneLevel*15*(Math.random()* 0.75 + 1));
                            eneLuck = (int)(eneLevel*15*(Math.random()* 0.75 + 1));
                            
                            maxEnemyHealth = 100+(int)(depth*1.5)+(eneVit*10);
                            curenemyHealth = rand.nextInt(maxEnemyHealth);
                            enemyHealth = 1+curenemyHealth;
                            enemyAttackDamage = (eneStr*2)+rand.nextInt(1*(depth));
                            
                            eneDefPoints = (int)eneEndur*1.8+(floor*20);
                            disEneDef = eneDefPoints/10;
                            
                            eneMagiResPoints = 50 + eneIntel;
                            eneRawMagiRes = (1000 - magiResPoints) / 1000;
                            eneDisMagiRes = eneMagiResPoints/10;
                            eneMana = (eneIntel *3);
                            
                            enemyHit = 50+(int)(eneDex / 3);
                            enemyEva = 55+(int)(eneAgi / 3);                                                        
                            
                            eneCritchance = 0.3 + (eneLuck * 0.005);
                            eneCritdmg = 1.3 + (eneDex * 0.01);
                            
                            eneAtkWait = randomNumberInRange(eneAtkWait-10,eneAtkWait+10);//ENEMY ATTACK WAIT TIME POINT RANDOMIZER
                            
                bonusMoney = floor* 1000;
                  
                }
            }
            
            
            
            while(enemyHealth > 0) {
        
        //EVASION FORMULAS        
        double plyDiffHit = plyEva - enemyHit; //difference of your eva and enemy hit / acc || 3
        double EnemyDiffHit = enemyEva - plyHit; //difference of enemy eva and your acc || 3
        double possPlyNotGetHit = plyDiffHit / plyEva; //possibility of enemy damage to MISS the player. || ex 3/55 = 0.054
        double possEnemyNotGetHit = EnemyDiffHit / enemyEva; //possibility of player damage to MISS the enemy. 
        //ex possEnemyNotGetHit = (74 - 50) / 74; --> possEnemyNotGetHit = 24 /74; --> possEnemyNotGetHit = 0.3243; soo possibility of player damage to MISS the enemy is 0.3243.
        double plyHitPercent = (1 - possEnemyNotGetHit) * 100; //0.054-->5.4%
        //ex plyHitPercent = (1 - 0.3243) * 100; plyHitPercent = (0.676) * 100; --> plyHitPercent = 67.6; OR 67.6%
        if(plyHitPercent>100){
            plyHitPercent=100;
        }
        double enemyHitPercent = (1 - possPlyNotGetHit) * 100; //
        if(enemyHitPercent>100){ 
            enemyHitPercent = 100;
        }
                                                 //SANS SPRITE ENCOUNTER IN LOOP
                if(bossEnemy.equals("SANS?!")){
                    System.out.println("");
                    String PrintSans = arts.printSans();                    
                    System.out.println("");

                } 
                                             //CHAD ELF SPRITE ENCOUNTER IN LOOP
                if(bossEnemy.equals("Brother Fan the Chad Elf")){
                    System.out.println("");
                    String PrintChad = arts.printChad(); //calls the method printChad() from ASCII_Art.    
                    System.out.println("");

                } 
                                                              //ENEMY ENCOUNTERS
                if(enemy.equals("Skeleton")) {
                    System.out.println("");
                    String PrintSkeleton = arts.printSkeleton();
                    System.out.println("");
                }
                if(enemy.equals("XP Bear") || enemy.equals("Money Bear")) {
                    System.out.println("");
                    String PrintBear = arts.printBear();
                    System.out.println("");
                }
                if(enemy.equals("Zombie")) {
                    System.out.println("");
                    String PrintZombie = arts.printZombie();
                    System.out.println("");
                }
                if(enemy.equals("Slime")) {
                    System.out.println("");
                    String PrintSlime = arts.printSlime();
                    System.out.println("");
                }
                if(enemy.equals("Bird")) {
                    System.out.println("");
                    String PrintBird = arts.printBird();
                    System.out.println("");
                }
                if(enemy.equals("Goblin")) {
                    System.out.println("");
                    String PrintGoblin = arts.printGoblin();
                    System.out.println("");
                }
                if(enemy.equals("Cat")) {
                    System.out.println("");
                    String PrintCat = arts.printCat();
                    System.out.println("");
                }
                if(enemy.equals("Elf")) {
                    System.out.println("");
                    String PrintElf = arts.printElf();
                    System.out.println("");
                }
                if(enemy.equals("Turtle")) {
                    System.out.println("");
                    String PrintTurtle = arts.printTurtle();
                    System.out.println("");
                }
                
                if(floor%5==0 ) {
                    enemy = bossEnemy;
                } else {
                    enemy = enemy;
                }
                                                                                // COMBAT MENU
                System.out.println("------------------------------------");
                System.out.println("\t"+myName +"'s HP: "+health);
                System.out.println("\t" +enemy +"'s HP: " +enemyHealth);
                if(bossEnemy=="SANS?!") {
                    System.out.println("\t" +enemy +"'s STAMINA: " +SansStamina);
                }
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Fight");
                System.out.println("\t1a. Analyze Situation");
                System.out.println("\t2. Inventory (WIP)");
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
                
                String MagicBombInv = null;                                        //POTIONS
                        
                    if(numMagicBomb > 0) {
                        MagicBombInv = "Magic Bomb" +" x ";
                       
                        
                    }
                    else {
                        //System.out.println("\t> You have no health potions left, defeat enemy for a chance to gain one");
                        MagicBombInv = "Empty";
                       
                    }
                
                    
                    
                    
                if (input.equals("1")) {                                        // FIGHT
                    
                    audioP.stopSound();
                    audioP.playSound(2);
                    
                    if(buffedDmg==true) {
                        buffTime = buffTime - 100;
                        if(buffTime==0) {
                            attackDamage = (int)(attackDamage / 1.5);
                            buffedDmg = false;
                        }
                    }
                            
                    System.out.println("[ Player Wait Time ] : " +plyWaitTime);
                    System.out.println("[ Player Buffed ] : " +buffedDmg);
                    if(buffedDmg==true){
                        System.out.println("[ Buff Timer } :" +buffTime);
                    } 
                    //System.out.println("[ Player Attack Wait Points ] : " +plyAtkWait);
                    System.out.println("");
                    System.out.println("[ Enemy Wait Time ] : " +eneWaitTime);
                    System.out.println("[ Enemy Attack Wait Points ] : " +eneAtkWait);
                    System.out.println("\n");
                    
                    if(plyWaitTime<eneWaitTime) {
                        System.out.println(" == YOUR TURN ==");
                    } else {
                        System.out.println(" == ENEMY TURN ==");
                    }

                    
                    //SCREEN JUMP TO CLEAN
                    //System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    //System.out.println("------------------------------------");
                    if(defPoints>1000){
                        defPoints = 1000;
                    }
                    
                    eneDefPoints = eneDefPoints / 1000;
                    
                    
                    
                    
                    
                    double damageDealt = (mindamage+weapondamage+rand.nextInt(attackDamage))*rawEneDef; //EX : 1+10+(randomized 1-50)*0.8)
                    double damageDealtCharged = (mindamage+weapondamage+rand.nextInt(attackDamage+chargeDmg))*rawEneDef;
                   // int damageTaken = rand.nextInt(enemyAttackDamage);
                    double damageTaken = ((rand.nextInt(enemyAttackDamage))*rawDef);
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
                    double plyRandEva = (double)(Math.random()) + plyMin; //double math random is going to roll random number from 0-1, but since it's multiplied by plyrange, which is -> 
                    
                    critRand = (double)(Math.random());
                    
                    double chargeAtkWaitTime = 0;
                    
                    chargeAtkWaitTime = plyAtkWait + (chargeDmg/2);
                    
                    System.out.println("1. Attack [ Wait Time : " +plyAtkWait +" ]");
                    System.out.println("2. Charge up Power [ Wait Time : " +(chargeDmg/2) +" ]");
                    System.out.println("3. Charge Attack [ Wait Time : " +chargeAtkWaitTime +" ]");
                    System.out.println("4. Buff +50% DMG [ Wait Time : 50] ");
                    System.out.println("------------------------------------");
                    
                    String inpAtk = in.nextLine();
                    
                    if(inpAtk.equals("1")){
                        
                        
                        
                        audioP.stopSound();
                        audioP.playSound(3);
                        
                        if(crit) {
                            damageDealt = damageDealt / critdmg;
                            crit = false;
                        }
                        
                        if(plyWaitTime<eneWaitTime) { //WAITTIME CALCULATION
                        
                        plyWaitTime = plyWaitTime + plyAtkWait;
                        eneWaitTime = eneWaitTime - plyAtkWait;    
                            
                        if(eneRandEva >= possEnemyNotGetHit) {
                            
                            //ex if(0.454 >= 0.676) { ENEMY TAKE DAMAGE AND STUFF 
                            //SOOO THE SMALLER POSSIBILITY OF ENEMY NOT GETTING HIT, EXAMPLE IF possEnemyNotGetHit = 0.1; then it would be easy to hit enemy since
                            //since eneRandEva could easily generate numbers bigger than 0.1. YESYES
                            
                        eneRandEva = possEnemyNotGetHit;
                        
                        if(critRand<=critchance) {
                            crit = true;
                            beforecritdmg = damageDealt; //temporary variable to store pre-crit dmg.
                            damageDealt = damageDealt * critdmg;                            
                            //System.out.println("\t > CRITICAL HIT ! <");
                        } 
                        
                        enemyHealth -= damageDealt;
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("\t> You hit the enemy!");       
                       // new DecimalFormat("$#.00").format(eneRandEva);
                        df2.setRoundingMode(RoundingMode.UP);
                        //System.out.println("(debug) chance : " +df2.format(eneRandEva));
                        if(crit) {
                            System.out.println("\t> Critical Hit !!! <");
                        }
                        System.out.println("\t> You strike the "+enemy +" for " +damageDealt +" damage!");
                        //You hit the enemy!
                        
                        
                    } else {
                        enemyHealth = enemyHealth + 0;
                        df2.setRoundingMode(RoundingMode.UP);
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("\t> Your attack missed the enemy!");
                        
                        if(bossEnemy=="SANS?!"){
                            SansStamina = SansStamina -5;
                        }
                        } 
                       // System.out.println("\t> The enemy evaded the attack!");System.out.println("(debug) chance : " +df2.format(eneRandEva));
                        } else if(plyWaitTime>eneWaitTime) {
                            
                            plyWaitTime = plyWaitTime - eneAtkWait;
                            eneWaitTime = eneWaitTime + eneAtkWait;
                            
                            plyRandEva = (double)(Math.random()) + plyMin;
                    if(plyRandEva >= possPlyNotGetHit) {
                        plyRandEva = possPlyNotGetHit;
                        
                        if(critRand<=eneCritchance) {
                            //crit = true;
                            enebeforecritdmg = damageTaken; //temporary variable to store pre-crit dmg.
                            damageTaken = damageTaken * eneCritdmg;                            
                            //System.out.println("\t > CRITICAL HIT ! <");
                        } 
                        
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
                       //Evasion randomizer or chance
                    
                       
                        
                  } else if(inpAtk.equals("2")){
                      
                        audioP.stopSound();
                        audioP.playSound(4);
                      
                      chargebnsdmg = true;
                      System.out.println("------------------------------------");
                      System.out.println("\t> You charge up power inside.");
                     
                      charge += 1;
                      System.out.println("\t> Charge Level : "+charge);
                      boolean charged = true;
                       
                        
                      chargeDmg =(int) ((attackDamage * charge))/2;
                      
                      
                      
                      cDmgNum = Integer.toString(chargeDmg);
                      
                      addedDamage = " +("+cDmgNum+")";
                      System.out.println("\t> You charged up +"+(chargeDmg)+" max damage!");
                      
                      plyWaitTime = plyWaitTime + (chargeDmg/2);
                      System.out.println("\t> Wait Time +" +(chargeDmg/2));
                      
                    try
                    {
                    TimeUnit.MILLISECONDS.sleep((100));
                    }
                    catch (Exception ex) {

                    }  
                      
                      //Evasion randomizer or chance
                    plyRandEva = (double)(Math.random()) + plyMin; //double math random is going to roll random number from 0-1, but since it's multiplied by plyrange, which is -> 
                    
                    if(plyWaitTime>eneWaitTime || plyWaitTime<eneWaitTime) { //ENEMY ATTACK NONETHELESS
                        
                        
                        eneWaitTime = eneWaitTime + eneAtkWait;

                        eneWaitTime = eneWaitTime - chargeDmg/2;
                        
                    if(plyRandEva >= possPlyNotGetHit) {
                        plyRandEva = possPlyNotGetHit;
                        
                        audioP.stopSound();
                        audioP.playSound(3);
                        
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
                    
                  } else if(inpAtk.equals("3")){
                      
                      
                      if(charge>=1) {
                          
                          
                          
                      audioP.stopSound();
                      audioP.playSound(3);
                                           
                      chargebnsdmg = false;
                      
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
                        charge = 0;
                    } else {
                        enemyHealth = enemyHealth + 0;
                        df2.setRoundingMode(RoundingMode.UP);
                        System.out.println("\t> Your attack missed the enemy!");
                       // System.out.println("\t> The enemy evaded the attack!");System.out.println("(debug) chance : " +df2.format(eneRandEva));
                       charge = 0;
                       
                       //Evasion randomizer or chance
                    plyRandEva = (double)(Math.random()) + plyMin; //double math random is going to roll random number from 0-1, but since it's multiplied by plyrange, which is -> 
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
                      
                    } else {
                          System.out.println("------------------------------------");
                          System.out.println("\t You haven't charged up power!.");
                      }   
                  }
                  else if(inpAtk.equals("4")) {                                     // BUFF DMG
                      
                      buffedDmg = true;
                      buffTime = 300;
                      plyWaitTime = plyWaitTime + 50;
                      System.out.println("\t> Wait Time +" + 50);                     
                      attackDamage = (int)(attackDamage * buffDmg);
                     
                      
                    if(plyWaitTime>eneWaitTime || plyWaitTime<eneWaitTime) {
                        
                        
                        eneWaitTime = eneWaitTime + eneAtkWait;

                        eneWaitTime = eneWaitTime - chargeDmg/2;
                        
                    if(plyRandEva >= possPlyNotGetHit) {
                        plyRandEva = possPlyNotGetHit;
                        
                        audioP.stopSound();
                        audioP.playSound(3);
                        
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
                        System.out.println("You have taken too much damage. You are too weak to go on.");
                        
                        audioP.stopSound();
                        audioP.playSound(8);
                        try{
                        TimeUnit.SECONDS.sleep(2);
                        } catch (Exception ex) {
                            
                        }
                        break;
                        
                        
                        
                    }
                }
                
                else if (input.equals("1a")) {                                  // ANALYZE SITUATION
                    //SCREEN JUMP TO CLEAN
                    
                    audioP.stopSound();
                    audioP.playSound(2);
                    //String addedDamage = (cDmgNum);
                    
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    
                   
                    System.out.println("############################################################");
                    System.out.println("                        ANALYZE                             ");
                    System.out.println("############################################################");
                    if(bossEnemy=="SANS?!"){
                        String[] SansDialogues = {"\t*You feel your sins crawling near your behind.", "\t*You are having a bad time", "\t*You felt your sins weighing on your neck, also your shoulders feel stiff."
                          ,"\t*The easiest enemy. Can only deal 1 damage. But dodges\n\t amost all your attacks so not really."};
                        String SansSystem = SansDialogues[rand.nextInt(SansDialogues.length)];
                        System.out.println(SansSystem);
                    } else if(bossEnemy=="Brother Fan the chad elf") {
                        String[] FanDialogues = {"\t*His posture feels intimidating.", "\t*His presence shooks your entire being."};
                        String FanSystem = FanDialogues[rand.nextInt(FanDialogues.length)];
                        System.out.println(FanSystem);
                    }
                    
                    if(chargebnsdmg!=true) {
                        showbnsdmg = false;
                    } else {
                        showbnsdmg = true;
                    }
                    
                    if(showbnsdmg!=true){
                        addedDamage = "";
                    }
                    
                    
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("                      FLOOR " +floor +", DEPTH " +depth);
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("Ply LV  : " +plyLevel);
                    System.out.println("Ply HP  : " +health +"/" +maxHealth);
                    System.out.println("Ply DMG : " +(mindamage+weapondamage)+"-"+(attackDamage+weapondamage)+addedDamage);
                   // new DecimalFormat("$#.00").format(plyHitPercent);
                    System.out.println("Ply DEF : " +defPoints+"("+disDef+"% Damage Reduction)");
                   df2.setRoundingMode(RoundingMode.UP);
                    System.out.println("Ply HIT : " +plyHit +"("+df2.format(plyHitPercent)+"% chance to hit enemy.)");
                    System.out.println("Ply EVA : " +plyEva);
                    System.out.println("===ATTRIBUTE OVERVIEW===");
                    System.out.println("Vitality       : " +vit +" [ Max Health : " +maxHealth +" HP ]");
                    System.out.println("Strength       : " +str +" [ Damage : " +mindamage +"-" +attackDamage +" Damage ]");
                    System.out.println("Agility        : " +agi +" [ Base Wait Time : " +plyWaitTime +" Points ] " +"[ Evasion : " +plyEva +" Points ]");
                    System.out.println("Intelligence   : " +intel +" [ Mana : "+plyMana +" Points ] [ " +"Magical Resistance : " +disMagiRes +"% ]");
                    System.out.println("Endurance      : " +endur +" [ Damage Resistance : " +disDef +"% ]");
                    System.out.println("Dexterity      : " +dex +" [ Attack Wait Time : " +plyAtkWait +" Points ]" +" [ Accuracy / Hit : " +plyHit +" ]" +" [ Critical Damage : " +critdmg*100 +"% ]");
                    System.out.println("Luck           : " +luck +" [ Gold Multiplier : " +(goldMult*100) +"% ]" +" [ Crit Chance : " +critchance*100 +"% ]");
                    System.out.println("Potions : " +"\n1. " +potionInv +"" +numHealthPots);
                    System.out.println("------------------------------------------------------------");
                    System.out.println(enemy +" LV  : " +eneLevel);
                    System.out.println(enemy +" HP  : " +enemyHealth +"/" +(curenemyHealth+1));
                    System.out.println(enemy +" DMG : " +eneMindamage +"-" +enemyAttackDamage);
                    System.out.println(enemy +" DEF : " +eneDefPoints+"("+disEneDef+"% Damage Reduction)");
                  //  new DecimalFormat("$#.00").format(enemyHitPercent);
                  df2.setRoundingMode(RoundingMode.UP);
                    System.out.println(enemy +" HIT : " +enemyHit +"("+df2.format(enemyHitPercent)+"% chance for " +enemy +" to hit you)");  
                    System.out.println(enemy +" EVA : " +enemyEva);
                    System.out.println("===ENEMY ATTRIBUTE OVERVIEW===");
                    System.out.println("Vitality       : " +eneVit +" [ Max Health : " +maxEnemyHealth +" HP ]");
                    System.out.println("Strength       : " +eneStr +" [ Damage : " +eneMindamage +"-" +enemyAttackDamage +" Damage ]");
                    System.out.println("Agility        : " +eneAgi +" [ Base Wait Time : " +eneWaitTime +" Points ] " +"[ Evasion : " +enemyEva +" Points ]");
                    System.out.println("Intelligence   : " +eneIntel +" [ Mana : "+eneMana +" Points ] [ " +"Magical Resistance : " +eneDisMagiRes +"% ]");
                    System.out.println("Endurance      : " +eneEndur +" [ Damage Resistance : " +disEneDef +"% ]");
                    System.out.println("Dexterity      : " +eneDex +" [ Attack Wait Time : " +eneAtkWait +" Points ]" +" [ Accuracy / Hit : " +enemyHit +" ]" +" [ Critical Damage : " +Math.round(eneCritdmg*100) +"% ]");
                    System.out.println("Luck           : " +eneLuck +" [ Crit Chance : " +Math.round(eneCritchance*100) +"% ]");
                    System.out.println("############################################################");
                    System.out.println("                                                            ");
                    
                }
                else if (input.equals("2")) {                                   // DRINK HP POTION --> TO BE INVENTORY
                    
                    audioP.stopSound();
                    audioP.playSound(2);
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
                    System.out.println("1."+potionInv +" " +numHealthPots);
                    System.out.println("2."+MagicBombInv +" " +numMagicBomb);
                    System.out.println("==============================");
                    System.out.println("Use :");
                    
                    String input2 = in.nextLine();
                    if(input2.equals("0")){
                        audioP.stopSound();
                        audioP.playSound(2);
                        invOpen = false;
                    }
                    
                    if(input2.equals("1")){
                        
                        audioP.stopSound();
                        audioP.playSound(2);
                        
                        while(health==maxHealth || numHealthPots < 1) {
                            numHealthPots = numHealthPots - 0;
                            System.out.println("\t> Can't use now.");
                            if(numHealthPots<1){
                                System.out.println("\t> Out of potions.");
                            }
                            if(health==maxHealth){
                                System.out.println("\t> Already at max health.");
                            }
                            
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
                        
                        audioP.stopSound();
                        audioP.playSound(10);
                        
                        System.out.println("\n\n\n\n\n\n\n\n\n\n"+"\t> You drink a health potion, healing yourself for " +healthPotionHealAmount 
                                            +"\n\t> You now have " +health + "HP"
                                            +"\n\t> You have " + numHealthPots +" health potions left.\n");
                        //System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("\n\n");
                        continue INVENTORY;
                    }
                    else if(input2.equals("2")) {
                        
                        audioP.stopSound();
                        audioP.playSound(2);
                        
                        while(numMagicBomb < 1) {
                            numMagicBomb = numMagicBomb - 0;
                            System.out.println("\t> Can't use now.");
                            if(numMagicBomb<1){
                                System.out.println("\t> Out of magic bombs.");
                            }
                            
                            
                            System.out.println("\n\n\n\n\n\n\n\n\n\n");
                            continue INVENTORY;
                        }
                        
                        
                        if(numMagicBomb >= 1) {
                            if(enemyHealth<=1){
                                System.out.println("You can't throw more bombs.");
                                break;
                                
                            }
                        enemyHealth = (int)(enemyHealth - maxEnemyHealth);
                        numMagicBomb = numMagicBomb - 1;
                        }
                        
                        audioP.stopSound();
                        audioP.playSound(10);
                        
                        System.out.println("\n\n\n\n\n\n\n\n\n\n"+"\t> You threw a magic bomb.");
                        //System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("\n\n");
                        continue INVENTORY;
                    }
                    
                    
                    }
                }
                else if (input.equals("3")) {                                   // RUN 
                    audioP.stopSound();
                    audioP.playSound(5);
                    //SCREEN JUMP TO CLEAN
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("------------------------------------");
                    
                    plyWaitTime = 160-agi;
                    eneWaitTime = 150;
                    
                    System.out.println("\t> You run away from the " +enemy +"!");
                    SansFight = false;
                    continue GAME;
                }   
                else {
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\tInvalid Command");
                }
            } //while(enemyhealth > 0) END
           
            plyWaitTime = 160-agi;
            eneWaitTime = 150;

            SansFight = false;
            //call art if depth 13
            
            
            if(health<1){
                
                
                
                System.out.println("");
                System.out.println("You limp out of the dungeon, weak from battle");
                break;
                
            }
            
            MonXP = ((eneLevel*100) + rand.nextInt(maxEnemyHealth) * 5)+bonusXP;                                // EXPERIENCE FORMULAS
            XP = XP + MonXP;
                                
            for(nextlvXP = nextlvXP;nextlvXP<=XP;plyLevel++){
                
                
                
                pointsMax = pointsMax + 2;
                
                maxHealth = maxHealth + 2;
                attackDamage = attackDamage +5;
                //plyLevel = plyLevel +1;
                nextlvXP = nextlvXP + (nextlvXP/5);                      //NEXT LEVEL XP 
               
                System.out.println("\n\n");                     
                System.out.println("************************");                 
                System.out.println("--> You leveled up to lv "+(plyLevel+1) +"! <--");
                System.out.println("--> HP +"+"2!");
                System.out.println("--> MAX DMG +"+"5!");
                System.out.println("--> ATTRIBUTE POINTS +2!" +"[" +pointsMax +" Points ]");
                System.out.println("************************");
                
                double roundoff1 = Math.round(nextlvXP*100)/100;
                System.out.println(+(int)nextlvXP +" XP left to level up!");
                
                
                XP = XP - nextlvXP;
                
                if(XP==nextlvXP) {
                    audioP.stopSound();
                    audioP.playSound(11);
                }
                
            } if(XP<=nextlvXP){
                
                audioP.stopSound();
                audioP.playSound(9);
                
                displayXP = displayXP+MonXP;
                double nextlvxp = nextlvXP - XP;
                System.out.println("\n");
            System.out.println("------------------------------------");
            System.out.println(" # " +enemy +" was defeated! # ");
            System.out.println("");
            System.out.println(" # You gained " +MonXP +" XP! #");
            System.out.println("");
            System.out.println(" # Total XP : "+displayXP +" XP #");
            System.out.println("");
           System.out.println(" # " +(int)nextlvxp +" XP left to level up!" +" #");
           System.out.println("");
            }                
              /*          
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
            */
            System.out.println(" # You have " +health +"/"+maxHealth +" HP # ");
            System.out.println("");
           
            moneyGained = eneLevel+rand.nextInt(moneyDrop+bonusMoney);            //MONEY DROP
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
           
            postmenu = true;
            POSTMENU:
            while(postmenu) {
            System.out.println("------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("1. Continue Fighting");
            System.out.println("2. Exit Dungeon?");
            System.out.println("3. Shop & Continue Fighting (WIP)");
            System.out.println("4. Save (WIP)");
            System.out.println("5. Allocate Stats");
            System.out.println("------------------------------------");
            
            String input = in.nextLine();
            
            while(!input.equals("1") && !input.equals("2") && !input.equals("3") &&!input.equals("4") &&!input.equals("5")) {
            System.out.println("Invalid Command!");
            input = in.nextLine();
        }
            
            if(input.equals("1")) {                                             //CONTINUE VENTURES
                
                postmenu = false;
                
                audioP.stopSound();
                audioP.playSound(2);
                
                depth = depth +1;                                             // DEPTH & FLOOR
                            System.out.println("\n\n\n\n\n\n\n\n\n\n");
                            System.out.println("\t*********************************");
                            System.out.println("\t You went further in the dungeon.");
                            System.out.println("\t*********************************");
                            System.out.println("");
                            
                            if(depth % 3 == 0){
                                floor = floor +1;
                            
                        } else if(depth==13 ) {                                 // ALPHA CONTENT END
                            System.out.println("You've killed the boss and reached the end content of the alpha demo version.\nEnter Y to end the demo, N to keep diving.");
                            System.out.println("End demo? Y/N");
                            String confirm = in.nextLine();
                            confirm = confirm.toUpperCase();
                            if(confirm.equals("Y")){
                                
                                String printThanks = arts.printThank();
                                running = false;
                             
                            } else if(confirm.equals("N")){
                                continue;
                            }
                            
                
            }
               continue;
            }
            else if(input.equals("2")) {                                        //STOP GAME
                
                postmenu = false;
                
                audioP.stopSound();
                audioP.playSound(2);
                
                System.out.println("You emerged from the dungeon.");
                break;
            }
            else if(input.equals("3")) {                                        //SHOP
                
                audioP.stopSound();
                audioP.playSound(2);
                
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
                        +"\n3. +25 Health, +1 DEF 85 coins" 
                        +"\n4. Weapons & Armor (HEAVY WIP)"
                        +"\n5. Continue Journey"
                        +"\n6. Back");
                
                System.out.println("---------------------------------");
                
             
                
                String inputShop = in.nextLine();
                while
                (          !inputShop.equals("1") 
                        && !inputShop.equals("2") 
                        && !inputShop.equals("3")
                        && !inputShop.equals("4")
                        && !inputShop.equals("5")
                        && !inputShop.equals("6"))
                {
                System.out.println("Invalid Command!");
                inputShop = in.nextLine();
        }
                if(inputShop.equals("1")) {
                    
                    if(money<=50){
                        System.out.println("You don't have enough money.");
                        continue;
                    } else {
                        
                        audioP.stopSound();
                audioP.playSound(2);
                        
                        attackDamage = attackDamage + 3;
                        mindamage = mindamage +1;
                        System.out.println("You upgraded your min damage by 1 & max damage by 3! now your damage is " +(mindamage+weapondamage)+"-"+(attackDamage+weapondamage) +"!" +"\nYou have " +money +" Coins left!");
                        money = money - 50;
                        
                    } 
                    
                    
                }
                
                if(inputShop.equals("2")) {
                    if(money<25) {
                        System.out.println("You don't have enough money.");
                        continue;
                    } else {
                        
                        audioP.stopSound();
                audioP.playSound(2);
                        
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
                        
                audioP.stopSound();
                audioP.playSound(2);
                        
                    maxHealth = maxHealth + 25;    
                    defPoints = defPoints +1;
                    System.out.println("You added 25 max health! and 25 DEF! (Max health : " +maxHealth +"DEF : "+defPoints+"("+disDef);
                    money = money - 85;
                    
                    }
                  }
                else if(inputShop.equals("4")) {
                    
                    audioP.stopSound();
                audioP.playSound(2);
                    
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
                    
                    audioP.stopSound();
                audioP.playSound(2);
                    
                    System.out.println("---------------------------------");
                    System.out.println("1."+sw1 + " 1 coins");
                    System.out.println("2."+sw2);
                    System.out.println("3."+sw3);
                    System.out.println("4."+sw4);
                    System.out.println("5."+sw5);
                    
                    String swDesc[] = {"Wooden Sword\nA sword made from 3 blocks of wood.", 
                    "Iron Sword\nA sword made from steel, crafted by the finest blacksmith in the village.",
                    "Gold Sword\nA golden sword which delivers weak strikes but brings fortune to the wielder upon slaying an enemy.",
                    "Diamond Sword\nThe legendary sword wielded by Steve from Minecraft.",
                    "Sword of Sparda\nA sword named after a powerful demon. Does not feature Dante from the Devil May Cry™ series."};
                    int swPrice[] = {300, 750, 1500, 3000, 8000};
                    String chsWep = in.nextLine();
                    switch(chsWep) {
                        case "1":
                        {
                            
                            System.out.println(lines[0]);
                            System.out.println(swDesc[0]);
                            System.out.println("Price : " + swPrice[0]);
                            
                            wp1b = true;
                            break;
                        }
                        case "2":
                        {
                            System.out.println(lines[0]);
                            System.out.println(swDesc[1]);
                            System.out.println("Price : " + swPrice[1]);
                            
                            wp2b = true;
                            break;
                        }
                        case "3":
                        {
                            System.out.println(lines[0]);
                            System.out.println(swDesc[2]);
                            System.out.println("Price : " + swPrice[2]);
                            
                            wp3b = true;
                            break;
                        }
                        case "4":
                        {
                            System.out.println(lines[0]);
                            System.out.println(swDesc[3]);
                            System.out.println("Price : " + swPrice[3]);
                            
                            wp4b = true;
                            break;
                        }
                        case "5":
                        {
                            System.out.println(lines[0]);
                            System.out.println(swDesc[4]);
                            System.out.println("Price : " + swPrice[4]);
                            
                            wp5b = true;
                            break;
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
                     
                     audioP.stopSound();
                audioP.playSound(2);
                     
                    continue GAME;
                }
                 else if(inputShop.equals("6")) {
                     audioP.stopSound();
                     audioP.playSound(2);
                     
                     continue POSTMENU;
                 }
            
            
            }
                
            }
            
            else if(input.equals("4")) {
                
                audioP.stopSound();
                audioP.playSound(6);
                
                System.out.println("\n\n\n\n\n\n\n\n\n\n");  
                System.out.println("##########################\nYou saved your progress!\n##########################");
                try {
           //cheat save system lul         
           //set properties values to prop
           String savedmoney = Integer.toString(money);
           prop.setProperty("savedmonvalue",savedmoney);
           
           String savedplyLevel = Integer.toString(plyLevel);
           prop.setProperty("savedplyLevel", savedplyLevel);
           
           String savedVit = Integer.toString(vit);
           prop.setProperty("savedVit", savedVit);          
           
           String savedStr = Integer.toString(str); //agi intel dex end luck
           prop.setProperty("savedStr", savedStr);
           
           String savedAgi = Integer.toString(agi);
           prop.setProperty("savedAgi", savedAgi);
           
           String savedIntel = Integer.toString(intel);
           prop.setProperty("savedIntel", savedIntel);
           
           String savedDex = Integer.toString(dex);
           prop.setProperty("savedDex", savedDex);
           
           String savedEnd = Integer.toString(endur);
           prop.setProperty("savedEnd", savedEnd);
           
           String savedLuck = Integer.toString(luck);
           prop.setProperty("savedLuck", savedLuck);
           
           String savedEAdmg = Integer.toString(weapondamage);
           prop.setProperty("savedEAdmg", savedEAdmg);                  
           
           String savedXP = Double.toString(XP);
           prop.setProperty("savedXP", savedXP);
           
           String savednextlvXP = Double.toString(nextlvXP);
           prop.setProperty("savednextlvXP", savednextlvXP);
           
           String savedmaxHealth = Integer.toString(maxHealth);
           prop.setProperty("savedmaxHealth", savedmaxHealth);
           
           String savednumHealthPots = Integer.toString(numHealthPots);
           prop.setProperty("savednumHealthPots", savednumHealthPots);                      
           
           String savedattackDamage = Integer.toString(attackDamage);
           prop.setProperty("savedattackDamage", savedattackDamage);
           
           String saveddisplayXP = Double.toString(displayXP);
           prop.setProperty("saveddisplayXP", saveddisplayXP);
           
           String saveddepth = Integer.toString(depth);
           prop.setProperty("saveddepth", saveddepth);
           
           String savedfloor = Integer.toString(floor);
           prop.setProperty("savedfloor", savedfloor);
           
           //String savednextlvXP = Double.toString(nextlvXP);
           //prop.setProperty("savednextlvXP", savednextlvXP);
           
           //String savedMonXP = Double.toString(MonXP);
           //prop.setProperty("savedMonXP", savedMonXP);
           
           //prop.setProperty("test","test2");
           
           //save properties to file
           prop.store(new FileOutputStream("DarkRPGsave.properties"), null);
            
          // running = false;
           
       } catch (IOException ex) {
           ex.printStackTrace();
       }  
            continue POSTMENU;    
            } else if(input.equals("5")) {
                //STAT ALLOCATION
      inputProcess = true;  
      statInputVit = true;      
      statInputStr = true;
      statInputAgi = true;
      statInputIntel = true;
      statInputEnd = true;
      statInputLuck = true;   
      statInputDex = true;
      bonusGold = pointsMax * 30;
     
     pointsMax = pointsMax;
     
     
     System.out.println("\n\n\n");
     
     INPUT2:
     while(inputProcess==true) {
         System.out.println("Points : " +pointsMax);
     restartStatInput:
     while(statInputVit==true) {
       
         System.out.println("Input your Vitality :");
         System.out.println("- Increases HP by 9/point.");
         String inputVit = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
         
         try {
         
         if(Integer.parseInt(inputVit)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputVit)<=pointsMax) {
             vit = vit+Integer.parseInt(inputVit);
             System.out.println("Your Vitality : " +vit);
             pointsMax = pointsMax - Integer.parseInt(inputVit);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputVit = false;
         break;
         }catch (NumberFormatException e) {
             System.out.println("Input a round number.");
         }
         
         maxHealth = 100 + (vit * 9);
         
     }    
         
     restartStatInput:
     while(statInputStr==true) {
       
         System.out.println("Input your Strength :");
         System.out.println("- Increases minimum damage by +1 every 2 points and max damage by +2.");
         String inputStr = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         try {
         
         if(Integer.parseInt(inputStr)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputStr)<=pointsMax) {
             str = str+Integer.parseInt(inputStr);
             System.out.println("Your Strength : " +str);
             pointsMax = pointsMax - Integer.parseInt(inputStr);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputStr = false;
         break;
         }catch (NumberFormatException e) {
             System.out.println("Input a round number.");
         }
         
         attackDamage = attackDamage + 2;
         mindamage = mindamage + 1;
         
     }
    
     
     restartStatInput:
     while(statInputAgi==true) {
       
         System.out.println("Input your Agility :");
         System.out.println("- Increases speed.");
         String inputAgi = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         try {
         
         if(Integer.parseInt(inputAgi)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputAgi)<=pointsMax) {
             agi = agi+Integer.parseInt(inputAgi);
             System.out.println("Your Agility : " +agi);
             pointsMax = pointsMax - Integer.parseInt(inputAgi);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputAgi = false;
         break;
         } catch(NumberFormatException e) {
             System.out.println("Input a round number.");
         }
     }
     
     restartStatInput:
     while(statInputIntel==true) {
       
         System.out.println("Input your Intelligence :");
         System.out.println("- Increases mana pool.\n- Increases magic resistance.");
         String inputIntel = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         try {
         
         if(Integer.parseInt(inputIntel)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputIntel)<=pointsMax) {
             intel = intel+Integer.parseInt(inputIntel);
             System.out.println("Your Intelligence : " +intel);
             pointsMax = pointsMax - Integer.parseInt(inputIntel);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputIntel = false;
         break;
         }catch(NumberFormatException e) {
             System.out.println("Input a round number.");
         }
     }
     
     restartStatInput:
     while(statInputDex==true) {
       
         System.out.println("Input your Dexterity :");
         System.out.println("- Decreases attack wait time by 1 every 3 dex points.\n- Increases weapon damage. \n - Increases crit damage.");
         String inputDex = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         try {
         
         if(Integer.parseInt(inputDex)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputDex)<=pointsMax) {
             dex = dex+Integer.parseInt(inputDex);
             System.out.println("Your Dexterity : " +dex);
             pointsMax = pointsMax - Integer.parseInt(inputDex);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputDex = false;
         break;
         }catch(NumberFormatException e) {
             System.out.println("Input a round number.");
         }
     }
     
     restartStatInput:
     while(statInputEnd==true) {
       
         System.out.println("Input your Endurance :");
         System.out.println("- Increases physical & ailment resistances.");
         String inputEnd = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         try {
         
         if(Integer.parseInt(inputEnd)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputEnd)<=pointsMax) {
             endur = endur+Integer.parseInt(inputEnd);
             System.out.println("Your Endurance : " +endur);
             pointsMax = pointsMax - Integer.parseInt(inputEnd);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputEnd = false;
         break;
         }catch(NumberFormatException e) {
             System.out.println("Input a round number.");
         }
     }
     
     restartStatInput:
     while(statInputLuck==true) {
       
         System.out.println("Input your Luck :");
         System.out.println("- Increases gold gain\n - Increases loot box rate.\n - Increases drop rate. \n - Increases crit chance.");
         String inputLuck = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         try {
         
         if(Integer.parseInt(inputLuck)>pointsMax) {
             
             continue restartStatInput;
         } else if(Integer.parseInt(inputLuck)<=pointsMax) {
             luck = luck+Integer.parseInt(inputLuck);
             System.out.println("Your Luck : " +luck);
             pointsMax = pointsMax - Integer.parseInt(inputLuck);
             
             System.out.println("Points left : " +pointsMax);System.out.println("\n");
         } 
         statInputLuck = false;
         break;
         }catch(NumberFormatException e) {
             System.out.println("Input a round number.");
         }
     }
    
     if(pointsMax>0) {
         bonusGold = pointsMax * 30;
         System.out.println("You still have " +pointsMax +" points left. use it or let it convert to " +bonusGold +" gold?" );
         System.out.println("Use(Y)/Bonus Gold(N)");
         String inputLeftover = in.nextLine();
         
         audioP.stopSound();
        audioP.playSound(0);
        
         if("Y".equals(inputLeftover)) {
                statInputVit = true;
                statInputStr = true;
                statInputAgi = true;
                statInputIntel = true;
                statInputEnd = true;
                statInputDex = true;
                statInputLuck = true;
             continue INPUT2;
         } else if("N".equals(inputLeftover)) {
             bonusGold = pointsMax * 30;
             pointsMax = 0;
             System.out.println("You got " +bonusGold +" gold!");
             inputProcess = true;
             
         } else {
             System.out.println("what");
         }
         
     } else if(pointsMax<=0) {
         
     }
     if(pointsMax>0) {
         bonusGold = pointsMax * 30;
         System.out.println("You still have " +pointsMax +" points left. use it or let it convert to " +bonusGold +" gold?" );
         System.out.println("Use(Y)/Bonus Gold(N)");
         String inputLeftover = in.nextLine();
         if("Y".equals(inputLeftover)) {
                statInputVit = true;
                statInputStr = true;
                statInputAgi = true;
                statInputIntel = true;
                statInputEnd = true;
                statInputDex = true;
                statInputLuck = true;
             continue INPUT2;
         } else if("N".equals(inputLeftover)) {
             bonusGold = pointsMax * 30;
             pointsMax = 0;
             System.out.println("You got " +bonusGold +" gold!");
             money = money + bonusGold;
             inputProcess = true;
             
         } else {
             System.out.println("what");
         }
     } else if(pointsMax<=0) {
         
     }
     //PLAYER ATTRIBUTE FORMULAS
            maxHealth = maxHealth + (vit*9);
            health = maxHealth;
            plyWaitTime = plyWaitTime - agi;
            plyEva = plyEva + (agi / 3);
            attackDamage = attackDamage + (str*2);
            mindamage = mindamage + (str/2);
            plyMana = intel * 4;
            magiResPoints = magiResPoints + intel;
            disMagiRes = disMagiRes;
            plyAtkWait = plyAtkWait - (dex / 3);
            plyHit = plyHit + (dex / 3);
            defPoints = endur * 1.75;
            disDef = disDef;
            goldMult = goldMult + (luck * 0.015);
            rawDef = (1000 - defPoints) / 1000; //if defPoints 700 then rawDef = 1000 - 700 = 300 --> 0.3, which is then multiplied to enemy damage, hence making the enemy only deal 0.3 of it's damage.
            disDef = defPoints/10; //if defPoints 700 then disDef = 700/10=70(%)damage reduction -- for display purposes
            critchance = 0.3 + (luck * 0.002);
            critdmg = 1.3 + (dex * 0.01);
        
            rawMagiRes = (1000 - magiResPoints) / 1000;
            disMagiRes = magiResPoints/10;
            
         inputProcess = false;
         System.out.println("===ATTRIBUTE OVERVIEW===");
         System.out.println("Vitality       : " +vit +" [ Max Health : " +maxHealth +" HP ]");
         System.out.println("Strength       : " +str +" [ Damage : " +mindamage +"-" +attackDamage +" Damage ]");
         System.out.println("Agility        : " +agi +" [ Base Wait Time : " +plyWaitTime +" Points ] " +"[ Evasion : " +plyEva +" Points ]");
         System.out.println("Intelligence   : " +intel +" [ Mana : "+plyMana +" Points ] [ " +"Magical Resistance : " +disMagiRes +"% ]");
         System.out.println("Endurance      : " +endur +" [ Damage Resistance : " +disDef +"% ]");
         System.out.println("Dexterity      : " +dex +" [ Attack Wait Time : " +plyAtkWait +" Points ]" +" [ Accuracy / Hit : " +plyHit +" ]" +" [ Critical Damage : " +critdmg*100 +"% ]");
         System.out.println("Luck           : " +luck +" [ Gold Multiplier : " +(goldMult*100) +"% ]" +" [ Crit Chance : " +critchance*100 +"% ]");
         System.out.println("========================");
         
         
         boolean inputAccept = true;
         INPUTACCEPT2:
         while(inputAccept==true) {   
         System.out.println("\nACCEPT?");
         System.out.println("Okay(Y)/Not Yet(N)");    
         String inputAcceptStat = in.nextLine();         
         
         if("Y".equals(inputAcceptStat)) {
             break;
     } else if("N".equals(inputAcceptStat)) {
                inputProcess = true;
                pointsMax = pointsMax;
                statInputVit = true;
                statInputStr = true;
                statInputAgi = true;
                statInputIntel = true;
                statInputEnd = true;
                statInputDex = true;
                statInputLuck = true;
                inputAccept = false;
             continue INPUT2;
     } else {
             System.out.println("wat");
             continue INPUTACCEPT2;
     }
      
         }
    }
            }
        }
       }
        
        
        System.out.println("######################");
        System.out.println("# THANKS FOR PLAYING #");
        System.out.println("######################");
        
        
        
       
    }

    public static int randomNumberInRange(int min, int max) {
    Random random = new Random();
    return random.nextInt((max - min)+1) +min;
    }
    
    class shopDialogue extends DarkRPG {
        void shDial() {
    System.out.println("---------------------------------");
    System.out.println("Wooden Sword\n\nA sword made out of wood...how is there even wood in a dungeon underground anyways?\n\n Increases min and max damage by 20.");
    System.out.println("---------------------------------");
    System.out.println("Buy This? Y/N");
    System.out.println("---------------------------------");
        }
    }
    
    static void shopDial() {
        DarkRPG yeet = new DarkRPG();
        
        
        System.out.println("");
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
    
  


