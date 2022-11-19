/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Interface.admin_menu;
import Interface.user_menu;
import static java.lang.Thread.sleep;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramos
 */
public class watchDemonUser extends Thread{
    public int hours;
    public int minutes;
    public int seconds;
 
    public user_menu userMenu;

    public watchDemonUser(user_menu menu){
        this.userMenu = menu;
    }
    public void run(){
        while(true){
            LocalDateTime locaDate = LocalDateTime.now();
            this.hours  = locaDate.getHour();
            this.minutes = locaDate.getMinute();
            this.seconds = locaDate.getSecond();
            
            try {
                userMenu.relojLabel.setText(hours  + ":"+ minutes +":"+seconds);
                sleep(10);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
          
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    
}
