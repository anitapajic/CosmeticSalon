package gui;

import model.Client;
import model.Enum.Gender;
import model.Enum.LoyalityCardStatus;
import model.Enum.Role;
import model.Person;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow extends JFrame {

    private final JLabel firstName = new JLabel("First name:");
    private final JTextField txtFirstName = new JTextField(20);

    private final JLabel lastName = new JLabel("Last name:");
    private final JTextField txtLastName = new JTextField(20);

    private final JLabel gender = new JLabel("Gender:");
    private final JComboBox<Gender> cbGender = new JComboBox<Gender>(Gender.values());

    private final JLabel telephoneNumber = new JLabel("Telephone number:");
    private final JTextField txtTelephoneNumber = new JTextField(20);

    private final JLabel address = new JLabel("Address:");
    private final JTextField txtAddress = new JTextField(20);

    private final JLabel userName = new JLabel("Username:");
    private final JTextField txtUserName = new JTextField(20);

    private final JLabel password = new JLabel("Password:");
    private final JTextField txtPassword = new JTextField(20);

    private final JButton btnOk = new JButton("Submit");
    private final JButton btnCancel = new JButton("Quit");

    MainRepository mainRepository;
    MainService mainService;

    public RegisterWindow(MainRepository mainRepository, MainService mainService){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        this.setTitle("Registration");
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
        this.add(firstName);
        this.add(txtFirstName);
        this.add(lastName);
        this.add(txtLastName);
        this.add(gender);
        this.add(cbGender);
        this.add(telephoneNumber);
        this.add(txtTelephoneNumber);
        this.add(address);
        this.add(txtAddress);
        this.add(userName);
        this.add(txtUserName);
        this.add(password);
        this.add(txtPassword);


        this.add(new JLabel());
        this.add(btnOk, "split 2");
        this.add(btnCancel);


    }

    public void actions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client forFile;
                    forFile = newClient();
                    Client g = forFile;
                    Person person = new Person(g.getId(), g.getName(), g.getLastname(), g.getGender(), g.getTelephone(), g.getAddress(), g.getUsername(), g.getPassword(), g.getRole());

                    RegisterWindow.this.mainService.getUserService().registration(g);
                    RegisterWindow.this.mainService.getUserService().addUser(person);
                    RegisterWindow.this.dispose();
                    RegisterWindow.this.setVisible(false);


                }
                catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterWindow.this.dispose();
                RegisterWindow.this.setVisible(false);

            }
        });
    }

    public Client newClient(){
        try{
            Client client = new Client();
            client.setName(txtFirstName.getText());
            client.setLastname(txtLastName.getText());
            client.setGender((Gender) cbGender.getSelectedItem());
            client.setPassword(txtPassword.getText());
            client.setUsername(txtUserName.getText());
            client.setTelephone(txtTelephoneNumber.getText());
            client.setAddress(txtAddress.getText());
            client.setRole(Role.CLIENT);

            this.setVisible(false);
            return client;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Wrong data!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
