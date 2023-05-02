package gui.CosmeticianWindows;

import gui.ClientWindows.MyAppointmentsWindow;
import gui.LoginWindow;
import model.Cosmetician;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;
import service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CosmeticianMainWindow extends JFrame {

    MainRepository mainRepository;
    Cosmetician cosmetician;
    private JButton btnMyAppointments = new JButton("Schedule");
    private JButton btnMyTreatments = new JButton("My treatment list");
    private JButton btnLogout = new JButton("Log Out");

    public CosmeticianMainWindow(){}

    public CosmeticianMainWindow(MainRepository mainRepository, Cosmetician cosmetician){
        this.cosmetician = cosmetician;
        this.mainRepository = mainRepository;
        setTitle("Welcome! " + cosmetician.getName() + " " + cosmetician.getLastname());
//        setPreferredSize(new Dimension(600,300));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        GUI();
        pack();
        setLocationRelativeTo(null);
        akcije();
    }


    private void GUI() {
        dugmad();
        podesiZapad();
        podesiCentar();

    }

    private void dugmad() {
        Dimension d = new Dimension(250,30);
        this.btnMyAppointments.setPreferredSize(d);
        this.btnMyTreatments.setPreferredSize(d);
        this.btnLogout.setPreferredSize(d);
    }

    private void podesiZapad() {
        JPanel panelZapad = new JPanel(new MigLayout());
        panelZapad.setBackground(new Color(214, 179, 171 ));

        panelZapad.add(this.btnMyAppointments, "wrap");
        panelZapad.add(this.btnMyTreatments, "wrap");
        panelZapad.add(this.btnLogout, "wrap");



        this.getContentPane().add(panelZapad, BorderLayout.WEST);

    }

    private void podesiCentar() {
        JPanel panelCentar = new JPanel(new MigLayout("wrap 2"));
        panelCentar.setBackground(Color.white);

        panelCentar.add(new JLabel(" "), "span 2");
        panelCentar.add(new JLabel("       Name::"));
        panelCentar.add(new JLabel(cosmetician.getName()));
        panelCentar.add(new JLabel("       Last name:"));
        panelCentar.add(new JLabel(cosmetician.getLastname()));
        panelCentar.add(new JLabel("       Username:"));
        panelCentar.add(new JLabel(cosmetician.getUsername()));
        panelCentar.add(new JLabel("       Address:"));
        panelCentar.add(new JLabel(cosmetician.getAddress()));
        panelCentar.add(new JLabel("       Phone number:"));
        panelCentar.add(new JLabel(String.valueOf(cosmetician.getTelephone())));

        this.getContentPane().add(panelCentar, BorderLayout.CENTER);
    }

    private void akcije() {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                userRepository.upisiPodatke();
                setVisible(false);
                dispose();
                LoginWindow login = new LoginWindow();
                login.setVisible(true);
            }
        });


        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                LoginWindow login = new LoginWindow();
                login.setVisible(true);
                mainRepository.saveAllData();
            }
        });

        btnMyTreatments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreatmentsWindow uuk = new TreatmentsWindow(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)), cosmetician);

                uuk.setVisible(true);
            }
        });
        btnMyAppointments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScheduleWindow uut = new ScheduleWindow(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)), cosmetician);
                uut.setVisible(true);
            }
        });

    }

}
