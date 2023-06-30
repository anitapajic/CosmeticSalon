package gui.MenagerWindows;

import gui.MenagerWindows.Charts.CosmeticianNumOfApp;
import gui.MenagerWindows.Charts.IncomeFromTreatmentTypes;
import gui.MenagerWindows.Charts.IncomeOutcomeThisYear;
import gui.MenagerWindows.Charts.NumOfAppStatus;
import model.Menager;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;
import service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsWindow extends JFrame {
    private final JButton btnForMonth = new JButton("Chart report for specific month");
    private final JButton btnYear = new JButton("Chart report for whole year");
    private final JButton btnReport1 = new JButton("Report about cosmeticians");
    private final JButton btnReport2 = new JButton("Report about statuses of appointments");
    private final JButton btnReport3 = new JButton("Report about treatments");
    private final JButton btnReport4 = new JButton("Report about clients with loyality card");
    private final JButton btnChart1 = new JButton("Chart for income from treatment types");
    private final JButton btnChart2 = new JButton("Chart for cosmeticians");
    private final JButton btnChart3 = new JButton("Chart for appointment statuses");
    private MainRepository mainRepository;
    private MainService mainService;
    private Menager menager;

    public ReportsWindow(MainRepository mainRepository, MainService mainService, Menager menager){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        this.menager = menager;

        setTitle("Type of reports");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GUI();
        pack();
        setLocationRelativeTo(null);
        akcije();
    }

    private void GUI() {
        dugmad();
        podesiZapad();

    }

    private void dugmad() {
        Dimension d = new Dimension(250,30);
        this.btnForMonth.setPreferredSize(d);
        this.btnYear.setPreferredSize(d);
        this.btnReport1.setPreferredSize(d);
        this.btnReport2.setPreferredSize(d);
        this.btnReport3.setPreferredSize(d);
        this.btnReport4.setPreferredSize(d);
        this.btnChart1.setPreferredSize(d);
        this.btnChart2.setPreferredSize(d);
        this.btnChart3.setPreferredSize(d);
    }

    private void podesiZapad() {
        JPanel panelZapad = new JPanel(new MigLayout());
        panelZapad.setBackground(new Color(214, 179, 171 ));
        panelZapad.add(this.btnForMonth, "wrap");
        panelZapad.add(this.btnYear, "wrap");
        panelZapad.add(this.btnReport1, "wrap");
        panelZapad.add(this.btnReport2, "wrap");
        panelZapad.add(this.btnReport3, "wrap");
        panelZapad.add(this.btnReport4, "wrap");
        panelZapad.add(this.btnChart1, "wrap");
        panelZapad.add(this.btnChart2, "wrap");
        panelZapad.add(this.btnChart3, "wrap");
        this.getContentPane().add(panelZapad, BorderLayout.WEST);
    }

    private void akcije() {
        this.btnForMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonthReportWindow monthReportWindow = new MonthReportWindow(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)), menager);
                monthReportWindow.setVisible(true);
            }
        });

        this.btnYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IncomeOutcomeThisYear monthReportWindow = new IncomeOutcomeThisYear(mainService.getMenagerService().getIncomeThisYear(), mainService.getMenagerService().getOutcomeThisYear());
                monthReportWindow.setVisible(true);
            }
        });
        this.btnReport1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Report1Window monthReportWindow = new Report1Window(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)), menager);
                monthReportWindow.setVisible(true);
            }
        });
        this.btnReport2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Report2Window monthReportWindow = new Report2Window(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)), menager);
                monthReportWindow.setVisible(true);
            }
        });
        this.btnReport3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Report3Window monthReportWindow = new Report3Window(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)), menager);
                monthReportWindow.setVisible(true);
            }
        });
        this.btnReport4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Report4Window monthReportWindow = new Report4Window(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)), menager);
                monthReportWindow.setVisible(true);
            }
        });
        this.btnChart1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IncomeFromTreatmentTypes cosmeticianNumOfApp = new IncomeFromTreatmentTypes(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)));
                cosmeticianNumOfApp.setVisible(true);
            }
        });
        this.btnChart2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CosmeticianNumOfApp monthReportWindow = new CosmeticianNumOfApp(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)));
                monthReportWindow.setVisible(true);
            }
        });
        this.btnChart3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NumOfAppStatus monthReportWindow = new NumOfAppStatus(mainRepository, new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository)));
                monthReportWindow.setVisible(true);
            }
        });
    }

}
