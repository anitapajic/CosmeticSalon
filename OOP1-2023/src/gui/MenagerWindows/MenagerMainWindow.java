package gui.MenagerWindows;

import gui.ClientWindows.MyAppointmentsWindow;
import gui.LoginWindow;
import model.Menager;
import model.Salon;
import model.Worker;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;
import service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenagerMainWindow extends JFrame {
    MainRepository mainRepository;
    Menager menager;
    private final JButton btnAllWorkers = new JButton("All workers");
    private final JButton btnNewWorker = new JButton("Add new worker");
    private final JButton btnTreatments = new JButton("Treatments");
    private final JButton btnSalon = new JButton("Salon");
    private final JButton btnReports = new JButton("Reports");
    private final JButton btnLogout = new JButton("Log Out");


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
        this.btnTreatments.setPreferredSize(d);
        this.btnSalon.setPreferredSize(d);
        this.btnReports.setPreferredSize(d);
        this.btnLogout.setPreferredSize(d);
    }

    private void podesiZapad() {
        JPanel panelZapad = new JPanel(new MigLayout());
        panelZapad.setBackground(new Color(214, 179, 171 ));

        panelZapad.add(this.btnAllWorkers, "wrap");
        panelZapad.add(this.btnNewWorker, "wrap");
        panelZapad.add(this.btnTreatments, "wrap");
        panelZapad.add(this.btnSalon, "wrap");
        panelZapad.add(this.btnReports, "wrap");
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
                mainRepository.saveAllData();
            }
        });

        btnNewWorker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewWorkerWindow uuk = new AddNewWorkerWindow(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)), new Worker());
                uuk.setVisible(true);
            }
        });
        btnAllWorkers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllWorkersWindow uut = new AllWorkersWindow(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)), menager);

                uut.setVisible(true);
            }
        });

        btnTreatments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDTreatmentsWindow uut = new CRUDTreatmentsWindow(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)), menager);

                uut.setVisible(true);
            }
        });

        btnSalon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalonSetUpWindow salonSetUpWindow = new SalonSetUpWindow(mainRepository.getSalon(), mainRepository, menager);
                salonSetUpWindow.setVisible(true);
            }
        });

        btnReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportsWindow reportsWindow = new ReportsWindow(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)), menager);
                reportsWindow.setVisible(true);
            }
        });
    }

}
