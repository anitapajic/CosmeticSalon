package gui.MenagerWindows;

import gui.ClientWindows.MakeNewAppointmentWindow;
import gui.MenagerWindows.Charts.IncomeOutcomeMonthly;
import model.Menager;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonthReportWindow extends JFrame {
    private MainRepository mainRepository;
    private MainService mainService;
    private Menager menager;
    private final JButton btnOk = new JButton("Submit");
    private final JButton btnCancel = new JButton("Quit");
    private final JLabel txtmonths = new JLabel("Choose month: ");
    String[] months = {
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };
    private final JComboBox<String> cbMonths = new JComboBox<>(months);

    public MonthReportWindow(MainRepository mainRepository, MainService mainService, Menager menager){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        this.menager = menager;

        setTitle("Choose month");
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

        this.add(txtmonths);
        this.add(cbMonths);
        this.add(new JLabel());
        this.add(btnOk, "split 2");
        this.add(btnCancel);

    }

    public void actions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = cbMonths.getSelectedIndex();
                double income = mainService.getMenagerService().getIncomeMonthly(selectedIndex);
                double outcome = mainService.getMenagerService().getOutcomeMonthly(selectedIndex);
                int month = selectedIndex + 1;
                IncomeOutcomeMonthly incomeOutcomeMonthly = new IncomeOutcomeMonthly(income, outcome, month);
                incomeOutcomeMonthly.setVisible(true);
                MonthReportWindow.this.dispose();

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonthReportWindow.this.dispose();
            }
        });
    }
}
