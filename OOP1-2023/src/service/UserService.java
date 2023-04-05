package service;

import model.Client;
import model.Enum.LoyalityCardStatus;
import model.Enum.Role;
import model.Person;
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
    }

    public void registration(Client client){
        this.clients = mainRepository.getClientRepository().getClients();
        for(Client c: clients){
            if(c.getUsername().equals(client.getUsername())){
                JOptionPane.showMessageDialog(null, "You are already registered. Please login.", "Notification",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        this.clientService = new ClientService(this.mainRepository);
        Client newClient;
        newClient = client;
        int randInt = new Random().nextInt();
        newClient.setId(randInt);
        newClient.setCardValue(0.0);
        newClient.setCardStatus(LoyalityCardStatus.NO);
        this.clientService.addClient(newClient);

        File file1 = new File("src/data/persons.csv");
        utils.WriteToFile.write(file1, this.mainRepository.getClientRepository().getClients());

        JOptionPane.showMessageDialog(null, "You are successfully registered!", "Success!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void login(String username, String password){
        this.users = mainRepository.getUserRepository().getUsers();
        for(Person u: users){
            if(username.equalsIgnoreCase(u.getUsername()) && password.equalsIgnoreCase(u.getPassword())){
                if(u.getRole().equals(Role.CLIENT)){
                    System.out.println("CLIENT");
                }
                else if(u.getRole().equals(Role.COSMETICIAN)){
                    System.out.println("COSMETICIAN");
                }
                else if(u.getRole().equals(Role.RECEPCIONIST)){
                    System.out.println("RECEPCIONIST");

                } else if (u.getRole().equals(Role.MENAGER)) {
                    System.out.println("MENAGER");
                }
            }
        JOptionPane.showMessageDialog(null, "You don't have an account. Please register first.", "Error!",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    public void removeUser(String username){
        for (Person p:this.mainRepository.getUserRepository().getUsers()){
            if(username.equalsIgnoreCase(p.getUsername())){
                this.mainRepository.getUserRepository().getUsers().remove(p);
            }
        }
    }
}
