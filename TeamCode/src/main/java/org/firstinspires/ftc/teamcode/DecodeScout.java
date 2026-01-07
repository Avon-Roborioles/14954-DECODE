package org.firstinspires.ftc.teamcode;

import android.nfc.Tag;
import android.util.Log;

import com.sun.tools.javac.tree.JCTree;

import org.jetbrains.annotations.TestOnly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;

public class DecodeScout extends ConsoleHandler {
    public DecodeScout(){}
    public String[] stringToArray(String string){
        String[] match = string.split(";");
        return match;
    }
    public String[] readLines() throws IOException {
        FileReader fileReader = new FileReader("MatchData.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }
@TestOnly
    public static void main(String[] args) throws IOException {
        DecodeScout scout = new DecodeScout();
        ArrayList<String[]> data = new ArrayList<>();
        //creates list of string array
        for(String string: scout.readLines()){
            data.add(scout.stringToArray(string));
        }
        // prints list of string arrays
        for(int i = 0; i<data.size(); i++){
            for(String string: data.get(i)){
                Log.i("array", string + " ");
            }
            Log.i("array", "\n");
        }
    }


}
