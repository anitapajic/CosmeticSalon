package repository;

import model.Client;
import model.Enum.Gender;
import model.Enum.LoyalityCardStatus;
import model.Enum.Role;
import model.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static utils.ReadFromFile.read;

public class UserRepository {

    private ArrayList<Person> users = new ArrayList<>();

    public UserRepository(){
        File treatmentsFile = new File("src/data/persons.csv");
        ArrayList<Person> userList = new ArrayList<>();
        ArrayList<String[]> x = new ArrayList<String[]>();

        try {
            read(treatmentsFile, x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String[] k : x) {
            Person m = new Person();
            m.setId(Integer.valueOf(k[0]));
            m.setName(k[1]);
            m.setLastname(k[2]);
            m.setGender(Gender.valueOf(k[3]));
            m.setTelephone(k[4]);
            m.setAddress(k[5]);
            m.setUsername(k[6]);
            m.setPassword(k[7]);
            m.setRole(Role.valueOf(k[8]));
            userList.add(m);

        }
        this.users = userList;
    }

    public ArrayList<Person> getUsers(){
        return this.users;
    }
}
