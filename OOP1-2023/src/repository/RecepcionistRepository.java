package repository;

import model.Enum.EduCoef;
import model.Enum.Gender;
import model.Enum.Role;
import model.Receptionist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static utils.ReadFromFile.read;

public class RecepcionistRepository {

    private List<Receptionist> recepcionists = new ArrayList<>();
    public RecepcionistRepository(){
        File treatmentsFile = new File("src/data/recepcionists.csv");
        List<Receptionist> recepcionistList = new ArrayList<>();
        ArrayList<String[]> x = new ArrayList<String[]>();

        try {
            read(treatmentsFile, x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String[] k : x) {
            Receptionist m = new Receptionist();
            m.setId(Integer.valueOf(k[0]));
            m.setName(k[1]);
            m.setLastname(k[2]);
            m.setGender(Gender.valueOf(k[3]));
            m.setTelephone(k[4]);
            m.setAddress(k[5]);
            m.setUsername(k[6]);
            m.setPassword(k[7]);
            m.setRole(Role.RECEPCIONIST);
            m.setEducoef(EduCoef.valueOf(k[9]));
            m.setYearsOfService(Double.valueOf(k[10]));
            m.setBonus(Double.valueOf(k[11]));
            m.setSalary(Double.valueOf(k[8]));
            recepcionistList.add(m);

        }
        this.recepcionists = recepcionistList;
    }

    public List<Receptionist> getRecepcionists(){
        return this.recepcionists;
    }
}


