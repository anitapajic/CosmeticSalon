package gui.MenagerWindows;

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

public class LoyalityCardValueWindow extends JFrame {
    private MainRepository mainRepository;
    private Menager menager;
    private MainService mainService;

    private final JLabel name = new JLabel("Value:");
    private final JTextField txtName = new JTextField(20);
    private final JButton btnOk = new JButton("OK");
    private final JButton btnCancel = new JButton("Cancel");

    public LoyalityCardValueWindow(MainRepository mainRepository, MainService mainService, Menager menager){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        this.menager = menager;
        this.setTitle("Loyality card value");
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(214, 179, 171 ));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnOk);
        GUI();
        pack();
        actions();
    }

    public void GUI(){
        MigLayout ml = new MigLayout("wrap 2");


        this.setLayout(ml);

        this.add(name);
        this.add(txtName);

        this.add(new JLabel());
        this.add(btnOk, "split 2");
        this.add(btnCancel);

    }


    public void actions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCard(Double.parseDouble(txtName.getText()));
                LoyalityCardValueWindow.this.dispose();
                LoyalityCardValueWindow.this.setVisible(false);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoyalityCardValueWindow.this.dispose();
                LoyalityCardValueWindow.this.setVisible(false);

            }
        });
    }

    private void setCard(double value){
        for(Client c : mainRepository.getClientRepository().getClients()){
            if(value <= mainService.getClientService().getSpentMoney(c.getUsername())){
                Client client = mainRepository.getClientRepository().getClientByUsername(c.getUsername());
                client.setCardStatus(LoyalityCardStatus.YES);
                client.setCardValue(mainService.getClientService().getSpentMoney(c.getUsername()));
            }
            else{
                Client client = mainRepository.getClientRepository().getClientByUsername(c.getUsername());
                client.setCardStatus(LoyalityCardStatus.NO);
                client.setCardValue(0.0);
            }
        }
    }
}
