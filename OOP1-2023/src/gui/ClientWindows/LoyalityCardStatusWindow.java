package gui.ClientWindows;

import gui.MenagerWindows.LoyalityCardValueWindow;
import model.Client;
import model.Enum.LoyalityCardStatus;
import model.Menager;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ref.Cleaner;

public class LoyalityCardStatusWindow extends JFrame {
    private MainRepository mainRepository;
    private Client client;
    private MainService mainService;

    private final JLabel name = new JLabel("Value:");
    private final JTextField txtName = new JTextField();

    public LoyalityCardStatusWindow(MainRepository mainRepository, MainService mainService, Client client){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        this.client = mainRepository.getClientRepository().getClientByUsername(client.getUsername());
        this.setTitle("Loyality card value");
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(214, 179, 171 ));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GUI();
        pack();
    }

    public void GUI(){
        MigLayout ml = new MigLayout("wrap 2");

        if(client.getCardStatus().equals(LoyalityCardStatus.YES)){
            txtName.setText(String.valueOf(client.getCardValue()));
        }
        else{
            txtName.setText("You don't have enough spent money for loyality card");
        }

        this.setLayout(ml);

        this.add(name);
        this.add(txtName);

        this.add(new JLabel());

    }



}
