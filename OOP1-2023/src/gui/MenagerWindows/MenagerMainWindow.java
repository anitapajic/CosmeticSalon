package gui.MenagerWindows;

import gui.ClientWindows.MyAppointmentsWindow;
import gui.LoginWindow;
import model.Menager;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenagerMainWindow extends JFrame {
    MainRepository mainRepository;
    Menager menager;
    private JButton btnAllWorkers = new JButton("All workers");
    private JButton btnNewWorker = new JButton("Add new worker");
    private JButton btnPriceList = new JButton("Price list");
    private JButton btnLogout = new JButton("Log Out");


    public MenagerMainWindow(){}

    public MenagerMainWindow(MainRepository mainRepository, Menager menager){
        this.mainRepository = mainRepository;
        this.menager = menager;
        setTitle("Welcome! " + menager.getName() + " " + menager.getLastname());
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
        this.btnAllWorkers.setPreferredSize(d);
        this.btnNewWorker.setPreferredSize(d);
        this.btnPriceList.setPreferredSize(d);
        this.btnLogout.setPreferredSize(d);
    }

    private void podesiZapad() {
        JPanel panelZapad = new JPanel(new MigLayout());
        panelZapad.setBackground(new Color(214, 179, 171 ));

        panelZapad.add(this.btnAllWorkers, "wrap");
        panelZapad.add(this.btnNewWorker, "wrap");
        panelZapad.add(this.btnPriceList, "wrap");
        panelZapad.add(this.btnLogout, "wrap");



        this.getContentPane().add(panelZapad, BorderLayout.WEST);

    }

    private void podesiCentar() {
        JPanel panelCentar = new JPanel(new MigLayout("wrap 2"));
        panelCentar.setBackground(Color.white);

        panelCentar.add(new JLabel(" "), "span 2");
        panelCentar.add(new JLabel("       Name::"));
        panelCentar.add(new JLabel(menager.getName()));
        panelCentar.add(new JLabel("       Last name:"));
        panelCentar.add(new JLabel(menager.getLastname()));
        panelCentar.add(new JLabel("       Username:"));
        panelCentar.add(new JLabel(menager.getUsername()));
        panelCentar.add(new JLabel("       Address:"));
        panelCentar.add(new JLabel(menager.getAddress()));
        panelCentar.add(new JLabel("       Phone number:"));
        panelCentar.add(new JLabel(String.valueOf(menager.getTelephone())));

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

        btnNewWorker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewWorkerWindow uuk = new AddNewWorkerWindow();
                uuk.setVisible(true);
            }
        });
        btnAllWorkers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllWorkersWindow uut = new AllWorkersWindow();
                uut.setVisible(true);
            }
        });
//        btnPriceList.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                PriceList priceList = new PriceList(userRepository, guest);
//                priceList.setVisible(true);
//            }
//        });
    }
}
