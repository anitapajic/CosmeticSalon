package repository;

import model.Enum.EduCoef;
import model.Enum.Gender;
import model.Enum.Role;
import model.Enum.TreatmentType;
import model.Treatment;
import model.Worker;

import java.io.File;
import java.util.ArrayList;

import static utils.ReadFromFile.read;

public class WorkerRepository {
    private ArrayList<Worker> workers;
    public WorkerRepository(){
        File treatmentsFile = new File("src/data/workers.csv");
        ArrayList<Worker> workerList = new ArrayList<>();
        ArrayList<String[]> x = new ArrayList<String[]>();

        try {
            read(treatmentsFile, x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String[] k : x) {
            Worker m = new Worker();
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

            workerList.add(m);

        }
        this.workers = workerList;
    }

    public ArrayList<Worker> getWorkers(){
        return this.workers;
    }
    public void setWorkers(ArrayList<Worker> workers){
        this.workers = workers;
    }
}
