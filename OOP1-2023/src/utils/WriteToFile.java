package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToFile {
    public static void write(File f, ArrayList lista) {

        try {
            FileWriter myWriter = new FileWriter(f, false);
            for (int i = 0; i<f.length(); i++){
                myWriter.write("");
            }
            for (Object o : lista){
                if (o instanceof String []){
                    String joined = String.join("|", (String []) o)+"\n";
                    myWriter.write(joined);
                }
                else {
                    myWriter.write(o.toString());
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("String se ne moze upisati u zadati fajl.");
            e.printStackTrace();
        }
    }
}
