package fr.passwordmanager.view;

import javax.swing.*;
import java.io.File;

public class Principal {

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        File data = new File("../general/src/hashed.dat");

        if(data.exists())
            new LoginWindow();
        else
            new SignUpWindow();
    }
}