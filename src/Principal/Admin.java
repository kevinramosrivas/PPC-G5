/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package Principal;

import Controller.LoginCtr;

/**
 *
 * @author mildr
 */
public class Admin {

    public static void main(String[] args) {
        LoginCtr adlog = new LoginCtr("admins");
        ServidorRMI1.main(args);
    }
}
