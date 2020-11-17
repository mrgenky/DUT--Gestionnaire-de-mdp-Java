package fr.passwordmanager.controller;

import com.google.common.hash.Hashing;
import fr.passwordmanager.view.DialogMessage;

import javax.crypto.Cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class LoginController {

    //Fonction qui lance la procédure de réinitialisation de mot de passe
    public static void passwordReset() {
        int reponse = DialogMessage.confirmDialog("Si vous réinitialisez le mot de passe global, toutes les données seront perdues. Voulez-vous poursuivre ?", "Réinitialiser le mot de passe");

        if(reponse == 0)
        {
            try{
                File f = new File("../general/src/hashed.dat");
                f.delete();
            }
            catch(Exception e)
            {
                System.err.println();
            }
        }
    }

    //Fonction qui compare le mot de passe enregistré avec le mot de passe saisi
    public static boolean passwordComparison(char[] pwdWritten) {
        String pwdSaved = null;

        //Lit le mot de passe enregistré
        try{
            Path file = Path.of("../general/src/hashed.dat");
            pwdSaved = Files.readString(file);
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }

        final String pwdWrittenHashed = Hashing.sha256()
                .hashString(String.valueOf(String.valueOf(pwdSaved)), StandardCharsets.UTF_8)
                .toString();
        //Compare le mot de passe saisi avec le mot de passe enregistré
        if(pwdWrittenHashed.equals(pwdSaved)){
            return true;
        }
        else if(pwdWritten.length<=0){
            DialogMessage.messageDialog("Aucun mot de passe n'a été saisi");//Pop-up
            return false;
        }
        else{
            DialogMessage.messageDialog("Mot de passe inconnu");//Pop-up
            return false;
        }
    }
}
