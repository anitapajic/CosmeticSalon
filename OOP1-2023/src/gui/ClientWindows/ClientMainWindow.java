package gui.ClientWindows;

import model.Client;
import repository.MainRepository;

import javax.swing.*;

public class ClientMainWindow extends JFrame {
    MainRepository mainRepository;
    Client client;
    public ClientMainWindow(){}

    public ClientMainWindow(MainRepository mainRepository, Client client){
        this.client = client;
        this.mainRepository = mainRepository;
    }
}
