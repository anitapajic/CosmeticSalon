package repository;

import model.Appointment;
import model.Client;
import model.Enum.EduCoef;
import model.Enum.Gender;
import model.Enum.LoyalityCardStatus;
import model.Enum.Role;
import model.Receptionist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static utils.ReadFromFile.read;

public class ClientRepository {
    private List<Client> clients = new ArrayList<>();

    public ClientRepository(){

        File treatmentsFile = new File("src/data/clients.csv");
        List<Client> clientList = new ArrayList<>();
        ArrayList<String[]> x = new ArrayList<String[]>();

        try {
            read(treatmentsFile, x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String[] k : x) {
            Client m = new Client();
            m.setId(Integer.valueOf(k[0]));
            m.setName(k[1]);
            m.setLastname(k[2]);
            m.setGender(Gender.valueOf(k[3]));
            m.setTelephone(k[4]);
            m.setAddress(k[5]);
            m.setUsername(k[5]);
            m.setPassword(k[6]);
            m.setRole(Role.CLIENT);
            m.setCardStatus(LoyalityCardStatus.valueOf(k[8]));
            m.setCardValue(Double.valueOf(k[9]));
            clientList.add(m);

        }
        this.clients = clientList;
    }

    public List<Client> getClients(){
        return this.clients;
    }

}
