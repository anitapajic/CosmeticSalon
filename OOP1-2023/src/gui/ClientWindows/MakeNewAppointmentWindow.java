package gui.ClientWindows;

import gui.RecepcionistWindows.UpdateAppointmentWindow;
import model.Appointment;
import model.Client;
import model.Cosmetician;
import model.Enum.TreatmentStatus;
import model.Enum.TreatmentType;
import model.Treatment;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MakeNewAppointmentWindow extends JFrame {
    Client client;
    MainRepository mainRepository;
    MainService mainService;
    ArrayList<Treatment> treatments;
    ArrayList<Cosmetician> cosmeticians;
    private JLabel type;
    private JLabel cosm;
    private JComboBox<String> cbType;
    private JComboBox<String> cbCosmetician;
    private JLabel startTime;
    private JTextField txtStartTime;
    private final JButton btnOk = new JButton("Submit");
    private final JButton btnCancel = new JButton("Quit");

    public MakeNewAppointmentWindow(MainRepository mainRepository, MainService mainService, Client client){
        this.mainService = mainService;
        this.mainRepository = mainRepository;
        this.client = client;
        treatments = mainRepository.getTreatmentsRepository().getTreatmentsList();
        cosmeticians = mainRepository.getCosmeticianRepository().getCosmeticians();
        String[] treatmentNames = new String[treatments.size()];

        for (int i = 0; i < treatments.size(); i++) {
            Treatment treatment = treatments.get(i);
            treatmentNames[i] = treatment.getName();
        }
        type = new JLabel("Treatment: ");
        cbType = new JComboBox<String>(treatmentNames);

        cosm = new JLabel("Cosmetician: ");
        String[] cosmeticianNames = new String[cosmeticians.size()];
        for (int i = 0; i < cosmeticians.size(); i++) {
            Cosmetician cosmetician = cosmeticians.get(i);
            cosmeticianNames[i] = cosmetician.getUsername();
        }
        cbCosmetician = new JComboBox<String>(cosmeticianNames);
        cbCosmetician.setSelectedIndex(-1);

        startTime = new JLabel("Start time: ");
        txtStartTime = new JTextField(20);
        this.setTitle("Make new appointment");
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
        this.add(type);
        this.add(cbType);
        this.add(startTime);
        this.add(txtStartTime);
        this.add(cosm);
        this.add(cbCosmetician);
        this.add(new JLabel());
        this.add(btnOk, "split 2");
        this.add(btnCancel);

    }

    public void actions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///TODO : Add validation
                int selectedIndex = cbCosmetician.getSelectedIndex();
               if(selectedIndex == -1){
                   setAppointmentWithoutCosm();
                   JOptionPane.showMessageDialog(null, "You successfully made new appointment!", "Information", JOptionPane.INFORMATION_MESSAGE);
                   MakeNewAppointmentWindow.this.dispose();
               }
               else{
                   setAppointmentWithCosm((String) cbCosmetician.getSelectedItem());
                   MakeNewAppointmentWindow.this.dispose();
               }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MakeNewAppointmentWindow.this.dispose();
            }
        });
    }

    public void setAppointmentWithoutCosm(){
        Appointment appointment = new Appointment();
        Cosmetician cosmetician = mainService.getCosmeticianService().assignFreeCosmetician((String) cbType.getSelectedItem(), txtStartTime.getText());
        Treatment t = mainRepository.getTreatmentsRepository().getTreatmentByName((String) cbType.getSelectedItem());
        try{
            LocalDateTime startTime;
            startTime = LocalDateTime.parse(txtStartTime.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));
            appointment.setStartTime(startTime);
            LocalDateTime endDate = startTime.plusMinutes(t.getDuration());
            appointment.setEndTime(endDate);
            appointment.setCosmeticianId(cosmetician.getId());
            appointment.setClient(client.getUsername());
            appointment.setType(t.getType());
            appointment.setPrice(t.getPrice());
            appointment.setName(t.getName());
            appointment.setDuration(t.getDuration());
            appointment.setComesticians(t.getComesticians());
            Random rand = new Random();
            appointment.setId(rand.nextInt(10000));
            appointment.setStatus(TreatmentStatus.SCHEDULED);
            mainService.getAppointmentService().addAppointment(appointment);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Wrong data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setAppointmentWithCosm(String username){
        Appointment appointment = new Appointment();
        Cosmetician cosmetician = mainRepository.getCosmeticianRepository().GetCosmeticianByUsername(username);
        if(mainService.getCosmeticianService().isCosmeticianFree(username, txtStartTime.getText(),(String) cbType.getSelectedItem())){
            Treatment t = mainRepository.getTreatmentsRepository().getTreatmentByName((String) cbType.getSelectedItem());
            try{
                LocalDateTime startTime;
                startTime = LocalDateTime.parse(txtStartTime.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));
                appointment.setStartTime(startTime);
                LocalDateTime endDate = startTime.plusMinutes(t.getDuration());
                appointment.setEndTime(endDate);

                appointment.setCosmeticianId(cosmetician.getId());
                appointment.setClient(client.getUsername());
                appointment.setType(t.getType());
                appointment.setPrice(t.getPrice());
                appointment.setName(t.getName());
                appointment.setDuration(t.getDuration());
                appointment.setComesticians(t.getComesticians());

                Random rand = new Random();
                appointment.setId(rand.nextInt(10000));
                appointment.setStatus(TreatmentStatus.SCHEDULED);

                mainService.getAppointmentService().addAppointment(appointment);
                JOptionPane.showMessageDialog(null, "You successfully made new appointment!", "Information", JOptionPane.INFORMATION_MESSAGE);

            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Wrong data!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Chosen cosmetician is unavailable!", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
