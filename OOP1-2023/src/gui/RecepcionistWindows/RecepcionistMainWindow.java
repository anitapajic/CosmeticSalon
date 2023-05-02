package gui.RecepcionistWindows;

import gui.ClientWindows.MyAppointmentsWindow;
import gui.LoginWindow;
import model.Receptionist;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RecepcionistMainWindow extends JFrame {
    MainRepository mainRepository;
    Receptionist receptionist;
    private JButton btnMyAppointments = new JButton("Appointments");
    private JButton btnMakeAppointment = new JButton("Make new appointment");
    private JButton btnLogout = new JButton("Log Out");

    public RecepcionistMainWindow(){}

    public RecepcionistMainWindow(MainRepository mainRepository, Receptionist receptionist){
        this.mainRepository = mainRepository;
        this.receptionist = receptionist;
        setTitle("Welcome! " + receptionist.getName() + " " + receptionist.getLastname());
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
        this.btnMakeAppointment.setPreferredSize(d);
        this.btnLogout.setPreferredSize(d);
    }

    private void podesiZapad() {
        JPanel panelZapad = new JPanel(new MigLayout());
        panelZapad.setBackground(new Color(214, 179, 171 ));

        panelZapad.add(this.btnMyAppointments, "wrap");
        panelZapad.add(this.btnMakeAppointment, "wrap");
        panelZapad.add(this.btnLogout, "wrap");



        this.getContentPane().add(panelZapad, BorderLayout.WEST);

    }

    private void podesiCentar() {
        JPanel panelCentar = new JPanel(new MigLayout("wrap 2"));
        panelCentar.setBackground(Color.white);

        panelCentar.add(new JLabel(" "), "span 2");
        panelCentar.add(new JLabel("       Name::"));
        panelCentar.add(new JLabel(receptionist.getName()));
        panelCentar.add(new JLabel("       Last name:"));
        panelCentar.add(new JLabel(receptionist.getLastname()));
        panelCentar.add(new JLabel("       Username:"));
        panelCentar.add(new JLabel(receptionist.getUsername()));
        panelCentar.add(new JLabel("       Address:"));
        panelCentar.add(new JLabel(receptionist.getAddress()));
        panelCentar.add(new JLabel("       Phone number:"));
        panelCentar.add(new JLabel(String.valueOf(receptionist.getTelephone())));

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
                //userRepository.upisiPodatke();
            }
        });

//        btnMakeAppointment.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Rezervacija reservation = new Rezervacija();
//                GuestMakeReservation uuk = new GuestMakeReservation(userRepository, reservation);
//                uuk.setVisible(true);
//            }
//        });
        btnMyAppointments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllAppointmentsWindow uut = new AllAppointmentsWindow();
                uut.setVisible(true);
            }
        });

    }

}
