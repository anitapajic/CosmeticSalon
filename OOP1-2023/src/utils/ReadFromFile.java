package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFromFile {
    public static ArrayList<String []> read(File file, ArrayList<String []> s) throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            String[] line1 = st.split("\\|");
            s.add(line1);
        }
        return s;
    }
}
