package repository;

import model.Cosmetician;
import model.Enum.EduCoef;
import model.Enum.Gender;
import model.Enum.Role;
import model.Menager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static utils.ReadFromFile.read;

public class CosmeticianRepository {
    private ArrayList<Cosmetician> cosmeticians;

    public CosmeticianRepository(){
        File treatmentsFile = new File("src/data/cosmeticians.csv");
        ArrayList<Cosmetician> cosmeticianList = new ArrayList<>();
        ArrayList<String[]> x = new ArrayList<String[]>();

        try {
            read(treatmentsFile, x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String[] k : x) {
            Cosmetician m = new Cosmetician();
            m.setId(Integer.valueOf(k[0]));
            m.setName(k[1]);
            m.setLastname(k[2]);
            m.setGender(Gender.valueOf(k[3]));
            m.setTelephone(k[4]);
            m.setAddress(k[5]);
            m.setUsername(k[6]);
            m.setPassword(k[7]);
            m.setRole(Role.valueOf(k[8]));
            m.setEducoef(EduCoef.valueOf(k[10]));
            m.setYearsOfService(Double.valueOf(k[11]));
            m.setBonus(Double.valueOf(k[12]));
            m.setSalary(Double.valueOf(k[9]));
            cosmeticianList.add(m);

        }
        this.cosmeticians = cosmeticianList;
    }

    public ArrayList<Cosmetician> getCosmeticians(){
        return this.cosmeticians;
    }
}
