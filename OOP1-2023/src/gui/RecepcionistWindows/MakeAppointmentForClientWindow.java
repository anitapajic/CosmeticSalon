package gui.RecepcionistWindows;

import gui.ClientWindows.MakeNewAppointmentWindow;
import model.*;
import model.Enum.TreatmentStatus;
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
import java.util.Random;

public class MakeAppointmentForClientWindow extends JFrame {
    Receptionist recepcionist;
    MainRepository mainRepository;
    MainService mainService;
    ArrayList<Treatment> treatments;
    ArrayList<Cosmetician> cosmeticians;
    private final JLabel type;
    private final JLabel cosm;
    private final JComboBox<String> cbType;
    private final JComboBox<String> cbCosmetician;
    private final JLabel startTime;
    private final JTextField txtStartTime;
    private final JLabel client;
    private final JTextField txtClient;
    private final JButton btnOk = new JButton("Submit");
    private final JButton btnCancel = new JButton("Quit");

    public MakeAppointmentForClientWindow(MainRepository mainRepository, MainService mainService, Receptionist receptionist){
        this.mainService = mainService;
        this.mainRepository = mainRepository;
        this.recepcionist = receptionist;
        treatments = mainRepository.getTreatmentsRepository().getTreatmentsList();
        cosmeticians = mainRepository.getCosmeticianRepository().getCosmeticians();
        String[] treatmentNames = new String[treatments.size()];

        client = new JLabel("Client's username:");
        txtClient = new JTextField(20);

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
        this.add(client);
        this.add(txtClient);
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
                int selectedIndex = cbCosmetician.getSelectedIndex();
                if(selectedIndex == -1){
                    setAppointmentWithoutCosm();
                    JOptionPane.showMessageDialog(null, "You successfully made new appointment!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    MakeAppointmentForClientWindow.this.dispose();
                }
                else{
                    setAppointmentWithCosm((String) cbCosmetician.getSelectedItem());
                    MakeAppointmentForClientWindow.this.dispose();
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MakeAppointmentForClientWindow.this.dispose();
            }
        });
    }

    public void setAppointmentWithoutCosm(){
        Appointment appointment = new Appointment();
        Cosmetician cosmetician = mainService.getCosmeticianService().assignFreeCosmetician(cbType.getSelectedItem().toString(), txtStartTime.getText());
        Treatment t = mainRepository.getTreatmentsRepository().getTreatmentByName((String) cbType.getSelectedItem());
        try{
            LocalDateTime startTime;
            startTime = LocalDateTime.parse(txtStartTime.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));
            appointment.setStartTime(startTime);
            LocalDateTime endDate = startTime.plusMinutes(t.getDuration());
            appointment.setEndTime(endDate);
            appointment.setCosmeticianId(cosmetician.getId());
            appointment.setClient(txtClient.getText());
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
                appointment.setClient(txtClient.getText());
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
