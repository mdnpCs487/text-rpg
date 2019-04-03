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
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;

public class AudioPlay  {

    private Clip clip;
    private Clip clip1;
    JFrame f = new JFrame();
   // Constructor
   public AudioPlay() {
        try {
            this.clip = AudioSystem.getClip();
            this.clip1 = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlay.class.getName()).log(Level.SEVERE, null, ex);
        }



      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setTitle("Test Sound Clip");
      f.setSize(300, 200);
      f.setLayout(new FlowLayout());

      JButton button = new JButton("play");
        button.addActionListener(new  ActionListener() {
           public void actionPerformed(ActionEvent e) {                    
             playSound(0);                                     
           }
        });            



        f.add(button);

        //f.setVisible(true);

   }

   public void stopSoundloop() {
        clip.stop();   
        //clip.flush();
        clip.close();

    }
   
   public void stopSound() {
       clip1.stop();
       
       clip1.close();
   }

   public void playSoundloop(int a){

       // Open an audio input stream.
       String[] sounds = new String[10];
       sounds[0]= "BruhSoundEffect.wav";
       sounds[1]= "sn18b.wav";
       sounds[2]= "gtas8b.wav";//BROTHER FAN THEME
       sounds[3]= "sans8b.wav";//SANS THEME
       File f = new File(sounds[a]);
       try {
         URL url = f.toURI().toURL();
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();            
         clip.loop(Clip.LOOP_CONTINUOUSLY);

      } catch (Exception e) {
         e.printStackTrace();
         System.out.println(e + " " + f);
      }

}
   public void playSound(int a){

       // Open an audio input stream.
       String[] sounds = new String[30];
       sounds[0]= "/Users/asus/Documents/NetBeansProjects/DarkRPG/sfx/General Sounds/Buttons/sfx_sounds_button11.wav";
       sounds[1]= "/Users/asus/Documents/NetBeansProjects/DarkRPG/sfx/General Sounds/Buttons/sfx_sounds_button11.wav";//MAIN MENU BUTTON
       sounds[2]= "/Users/asus/Documents/NetBeansProjects/DarkRPG/sfx/General Sounds/Buttons/sfx_sounds_button3.wav";//MOST MENU BUTTONS
       sounds[3]= "/Users/asus/Documents/NetBeansProjects/DarkRPG/sfx/General Sounds/Simple Damage Sounds/sfx_damage_hit5.wav";//ATTACKS
       sounds[4]= "/Users/asus/Documents/NetBeansProjects/DarkRPG/sfx/Weapons/Grenade Whistles/sfx_wpn_missilelaunch.wav";//CHARGE ATTACK
       sounds[5]= "/Users/asus/Documents/NetBeansProjects/DarkRPG/sfx/Movement/Opening Doors/sfx_movement_dooropen1.wav";//RUN
       sounds[6]= "/Users/asus/Documents/NetBeansProjects/DarkRPG/sfx/General Sounds/Buttons/sfx_sounds_button4.wav";//SAVE BUTTON
       sounds[7]= "/Users/asus/Documents/NetBeansProjects/DarkRPG/sfx/General Sounds/Coins/sfx_coin_cluster5.wav";//SPECIAL BEARS
       sounds[8]= "/Users/asus/Documents/NetBeansProjects/DarkRPG/sfx/DeathScreams/Human/sfx_deathscream_human2.wav";//PLAYER DIES
       sounds[9]= "/Users/asus/Documents/NetBeansProjects/DarkRPG/sfx/General Sounds/Fanfares/sfx_sounds_fanfare3.wav";//DEFEAT MONSTER
       sounds[10]= "/Users/asus/Documents/NetBeansProjects/DarkRPG/sfx/General Sounds/Positive Sounds/sfx_sounds_powerup6.wav";//DRINK POTION
       sounds[11]= "/Users/asus/Documents/NetBeansProjects/DarkRPG/sfx/General Sounds/Fanfares/sfx_sounds_fanfare1.wav";//LEVEL UP
       //sounds[1]= "BruhSoundEffect.wav";
       File f = new File(sounds[a]);
       try {
         URL url = f.toURI().toURL();
         AudioInputStream audioIn1 = AudioSystem.getAudioInputStream(url);

         // Open audio clip and load samples from the audio input stream.
         clip1.open(audioIn1);
         clip1.start();            
         

      } catch (Exception e) {
         e.printStackTrace();
         System.out.println(e + " " + f);
      }

}

   public static void main(String[] args) {
       AudioPlay ap = new AudioPlay();
       Other oth = new Other(ap, ap.f);
       ap.shw();
   }

   public void shw(){
       f.setVisible(true);
   }
}
