package repository;

import model.Enum.TreatmentType;
import model.Treatment;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static utils.ReadFromFile.read;

public class TreatmentsRepository {
    public ArrayList<Treatment> treatments;

    public TreatmentsRepository() {

        File treatmentsFile = new File("src/data/treatments.csv");
        ArrayList<Treatment> treatmentList = new ArrayList<>();
        ArrayList<String[]> x = new ArrayList<String[]>();

        try {
            read(treatmentsFile, x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String[] k : x) {
            Treatment t = new Treatment();
            t.setId(Integer.valueOf(k[0]));
            t.setName(k[1]);
            t.setType(TreatmentType.valueOf(k[2]));
            t.setPrice(Double.valueOf(k[3]));
            t.setDuration(Integer.valueOf(k[4]));
            String[] ids = k[5].split(",");
            List<Integer> cosmeticianIds = new ArrayList<>();
            for (String id : ids) {
                cosmeticianIds.add(Integer.valueOf(id));
            }
            t.setComesticians(cosmeticianIds);

            treatmentList.add(t);

        }
        this.treatments = treatmentList;
    }

    public ArrayList<Treatment> getTreatmentsList(){
        return this.treatments;
    }
    public Treatment getTreatmentByName(String name){
        Treatment treatment = new Treatment();
        for(Treatment t: treatments){
            if(t.getName().equals(name)){
                treatment = t;
            }
        }
        return treatment;
    }

}
