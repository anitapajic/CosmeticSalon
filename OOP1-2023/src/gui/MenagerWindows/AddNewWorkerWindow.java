package gui.MenagerWindows;

import model.Enum.EduCoef;
import model.Enum.Gender;
import model.Enum.Role;
import model.Menager;
import model.Worker;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;
import service.MainService;
import service.WorkerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddNewWorkerWindow extends JFrame {
    private MainRepository mainRepository;
    private MainService mainService;
    private Worker worker;

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

    private final JLabel role = new JLabel("Role:");
    private final JComboBox<Role> cbRole = new JComboBox<Role>(new Role[]{Role.COSMETICIAN, Role.RECEPCIONIST, Role.MENAGER});

    private final JLabel yearsOfService = new JLabel("Years of service:");
    private final JTextField txtYearsOfService = new JTextField(20);

    private final JLabel pqLevel = new JLabel("Proffesional qualification level:");
    private final JComboBox<EduCoef> cbPQL = new JComboBox<EduCoef>(EduCoef.values());

    private final JLabel salary = new JLabel("Salary:");
    private final JTextField txtSallary = new JTextField(20);

    private final JButton btnOk = new JButton("Submit");
    private final JButton btnCancel = new JButton("Quit");

    public AddNewWorkerWindow(MainRepository mainRepository, MainService mainService, Worker worker){
        this.mainService = mainService;
        this.mainRepository = mainRepository;
        this.worker = worker;
        this.setTitle("Add worker");
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

        if (!worker.equals(null)){

            txtFirstName.setText(worker.getName());
            txtLastName.setText(worker.getLastname());
            cbGender.setSelectedItem(worker.getGender());
            txtTelephoneNumber.setText(String.valueOf(worker.getTelephone()));
            txtAddress.setText(worker.getAddress());
            txtUserName.setText(worker.getUsername());
            txtPassword.setText(worker.getPassword());
            cbRole.setSelectedItem(worker.getRole());
            cbPQL.setSelectedItem(worker.getEducoef());
            txtYearsOfService.setText(String.valueOf(worker.getYearsOfService()));
            txtSallary.setText(String.valueOf(worker.getSalary()));
        }
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
        this.add(role);
        this.add(cbRole);
        this.add(pqLevel);
        this.add(cbPQL);
        this.add(yearsOfService);
        this.add(txtYearsOfService);
        this.add(salary);
        this.add(txtSallary);
        this.add(new JLabel());
        this.add(btnOk, "split 2");
        this.add(btnCancel);

    }


    public void worker(){
        try{

            this.worker.setName(txtFirstName.getText());
            this.worker.setLastname(txtLastName.getText());
            this.worker.setGender((Gender) cbGender.getSelectedItem());
            this.worker.setTelephone(txtTelephoneNumber.getText());
            this.worker.setAddress(txtAddress.getText());
            this.worker.setUsername(txtUserName.getText());
            this.worker.setPassword(txtPassword.getText());
            this.worker.setRole((Role) cbRole.getSelectedItem());
            this.worker.setEducoef((EduCoef) cbPQL.getSelectedItem());
            this.worker.setSalary(Double.parseDouble(txtSallary.getText()));
            this.worker.setYearsOfService(Double.parseDouble(txtYearsOfService.getText()));

            AddNewWorkerWindow.this.mainService.getMenagerService().addWorker(worker);
            this.setVisible(false);
            JOptionPane.showMessageDialog(null, "You have succesfully added new worker to system!", "Succes", JOptionPane.INFORMATION_MESSAGE);
            AllWorkersWindow allWorkers = new AllWorkersWindow(mainRepository, mainService, new Menager());
            allWorkers.setVisible(true);


        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Wrong data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worker();
                AddNewWorkerWindow.this.dispose();
                AddNewWorkerWindow.this.setVisible(false);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewWorkerWindow.this.dispose();
                AddNewWorkerWindow.this.setVisible(false);

            }
        });
    }

}
