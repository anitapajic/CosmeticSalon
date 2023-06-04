package gui.MenagerWindows;

import model.Menager;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;
import service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsWindow extends JFrame {
    private final JButton btnForMonth = new JButton("Report for specific month");
    private final JButton btnYear = new JButton("Report for whole year");
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
    }

    private void podesiZapad() {
        JPanel panelZapad = new JPanel(new MigLayout());
        panelZapad.setBackground(new Color(214, 179, 171 ));
        panelZapad.add(this.btnForMonth, "wrap");
        panelZapad.add(this.btnYear, "wrap");
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
    }

}
