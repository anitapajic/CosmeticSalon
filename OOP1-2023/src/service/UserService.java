package service;

import gui.ClientWindows.ClientMainWindow;
import gui.CosmeticianWindows.CosmeticianMainWindow;
import gui.MenagerWindows.MenagerMainWindow;
import gui.RecepcionistWindows.RecepcionistMainWindow;
import model.*;
import model.Enum.LoyalityCardStatus;
import model.Enum.Role;
import repository.MainRepository;
import repository.UserRepository;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserService {
    private MainRepository mainRepository;
    private List<Client> clients = new ArrayList<>();
    private List<Person> users = new ArrayList<>();
    private ClientService clientService;

    public UserService(MainRepository mainRepository){
        this.mainRepository = mainRepository;
        this.clientService = new ClientService(this.mainRepository);
    }

    public void registration(Client client){
        this.clients = mainRepository.getClientRepository().getClients();
        for(Client c: clients){
            if(c.getUsername().equals(client.getUsername())){
                JOptionPane.showMessageDialog(null, "You are already registered. Please login.", "Notification",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        Client newClient;
        newClient = client;
        int randInt = new Random().nextInt();
        newClient.setId(randInt);
        newClient.setCardValue(0.0);
        newClient.setCardStatus(LoyalityCardStatus.NO);
        this.clientService.addClient(newClient);

        File file1 = new File("src/data/clients.csv");
        utils.WriteToFile.write(file1, this.mainRepository.getClientRepository().getClients());

        JOptionPane.showMessageDialog(null, "You are successfully registered!", "Success!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void login(String username, String password){
        this.users = mainRepository.getUserRepository().getUsers();
        for(Person u: users){
            if(username.equalsIgnoreCase(u.getUsername()) && password.equalsIgnoreCase(u.getPassword())){
                switch (u.getRole()) {
                    case CLIENT -> {
                        Client client = new Client(u);
                        ClientMainWindow clientMainWindow = new ClientMainWindow(mainRepository, client);
                        clientMainWindow.setVisible(true);
                    }
                    case COSMETICIAN -> {
                        Cosmetician cosmetician = new Cosmetician(u);
                        CosmeticianMainWindow cosmeticianMainWindow = new CosmeticianMainWindow(mainRepository, cosmetician);
                        cosmeticianMainWindow.setVisible(true);
                    }
                    case RECEPCIONIST -> {
                        Receptionist receptionist = new Receptionist(u);
                        RecepcionistMainWindow recepcionistMainWindow = new RecepcionistMainWindow(mainRepository, receptionist);
                        recepcionistMainWindow.setVisible(true);
                    }
                    case MENAGER -> {
                        Menager menager = new Menager(u);
                        MenagerMainWindow menagerMainWindow = new MenagerMainWindow(mainRepository, menager);
                        menagerMainWindow.setVisible(true);
                    }
                    default -> {
                        JOptionPane.showMessageDialog(null, "You don't have an account. Please register first.", "Error!",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

    }
    public void removeUser(String username){
        Person toBeDeleted = new Person();
        for (Person p:this.mainRepository.getUserRepository().getUsers()){
            if(username.equalsIgnoreCase(p.getUsername())){
                toBeDeleted = p;
            }
        }
        this.mainRepository.getUserRepository().getUsers().remove(toBeDeleted);
    }
    public void addUser(Person user){
        this.mainRepository.getUserRepository().getUsers().add(user);
        File file1 = new File("src/data/persons.csv");
        utils.WriteToFile.write(file1, this.mainRepository.getUserRepository().getUsers());
    }
}
