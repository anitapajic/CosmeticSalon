package gui.MenagerWindows;

import model.Enum.TreatmentType;
import model.Menager;
import model.Salon;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalonSetUpWindow extends JFrame {
    private Salon salon;
    private MainRepository mainRepository;
    private Menager menager;

    private final JLabel name = new JLabel("Name:");
    private final JTextField txtName = new JTextField(20);
    private final JLabel workHours = new JLabel("Mon-Sat:");
    private final JTextField txtWorkHours = new JTextField(20);
    private final JLabel sunday = new JLabel("Sun:");
    private final JLabel txtSun = new JLabel("Not working");
    private final JButton btnOk = new JButton("OK");
    private final JButton btnCancel = new JButton("Cancel");

    public SalonSetUpWindow(Salon salon, MainRepository mainRepository, Menager menager){
        this.salon = salon;
        this.mainRepository = mainRepository;
        this.menager = menager;
        this.setTitle("Salon data");
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

        if (!salon.equals(null)){

            txtName.setText(salon.getName());
            txtWorkHours.setText(salon.getWorkHours());

        }
        this.setLayout(ml);

        this.add(name);
        this.add(txtName);
        this.add(workHours);
        this.add(txtWorkHours);
        this.add(sunday);
        this.add(txtSun);

        this.add(new JLabel());
        this.add(btnOk, "split 2");
        this.add(btnCancel);

    }

    public void salon(){
        try{
            this.salon.setName(txtName.getText());
            this.salon.setWorkHours(txtWorkHours.getText());
            this.setVisible(false);
            JOptionPane.showMessageDialog(null, "You have changed data about salon", "Succes", JOptionPane.INFORMATION_MESSAGE);


        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Wrong data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salon();
                mainRepository.setSalon(salon);
                SalonSetUpWindow.this.dispose();
                SalonSetUpWindow.this.setVisible(false);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainRepository.setSalon(salon);
                SalonSetUpWindow.this.dispose();
                SalonSetUpWindow.this.setVisible(false);

            }
        });
    }

}
