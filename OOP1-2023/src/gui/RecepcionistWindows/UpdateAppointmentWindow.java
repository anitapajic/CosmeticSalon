package gui.RecepcionistWindows;

import gui.MenagerWindows.AddNewWorkerWindow;
import gui.MenagerWindows.AllWorkersWindow;
import model.Appointment;
import model.Enum.*;
import model.Menager;
import model.Receptionist;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateAppointmentWindow extends JFrame {
    private MainRepository mainRepository;
    private MainService mainService;
    private Appointment appointment;


    private final JLabel id = new JLabel("Id:");
    private final JTextField txtId = new JTextField(20);

    private final JLabel name = new JLabel("Name:");
    private final JTextField txtName = new JTextField(20);

    private final JLabel type = new JLabel("Treatment Type:");
    private final JComboBox<TreatmentType> cbType = new JComboBox<TreatmentType>(TreatmentType.values());

    private final JLabel price = new JLabel("Price:");
    private final JTextField txtPrice = new JTextField(20);

    private final JLabel duration = new JLabel("Duration:");
    private final JTextField txtDuration = new JTextField(20);

    private final JLabel cosmetician = new JLabel("Cosmetician:");
    private String[] cosmeticians = {"ana@cosmetician.com", "marija@cosmetician.com"};
    private final JComboBox<String> cbCosmetician = new JComboBox<>(cosmeticians);

    private final JLabel client = new JLabel("Client:");
    private final JTextField txtClient = new JTextField(20);

    private final JLabel startTime = new JLabel("Start time:");
    private final JTextField txtStartTime = new JTextField(20);

    private final JLabel endTime = new JLabel("End time:");
    private final JTextField txtEndTime = new JTextField(20);

    private final JLabel status = new JLabel("Status");
    private final JComboBox<TreatmentStatus> cbStatus = new JComboBox<>(TreatmentStatus.values());

    private final JButton btnOk = new JButton("Submit");
    private final JButton btnCancel = new JButton("Quit");

    public UpdateAppointmentWindow(MainRepository mainRepository, MainService mainService, Appointment appointment){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        this.appointment = appointment;
        this.setTitle("Add appointment");
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

        if (!appointment.equals(null)){

            txtId.setText(String.valueOf(appointment.getId()));
            txtName.setText(appointment.getName());
            cbType.setSelectedItem(appointment.getType());
            txtPrice.setText(String.valueOf(appointment.getPrice()));
            txtDuration.setText(String.valueOf(appointment.getDuration()));
            cbCosmetician.setSelectedItem(appointment.getCosmetician());
            txtClient.setText(appointment.getClient());
            txtStartTime.setText(String.valueOf(appointment.getStartTime()));
            txtEndTime.setText(String.valueOf(appointment.getEndTime()));
            cbStatus.setSelectedItem(appointment.getStatus());
        }
        this.setLayout(ml);
        this.add(id);
        this.add(txtId);
        this.add(name);
        this.add(txtName);
        this.add(type);
        this.add(cbType);
        this.add(price);
        this.add(txtPrice);
        this.add(duration);
        this.add(txtDuration);
        this.add(client);
        this.add(txtClient);
        this.add(cosmetician);
        this.add(cbCosmetician);
        this.add(startTime);
        this.add(txtStartTime);
        this.add(endTime);
        this.add(txtEndTime);
        this.add(status);
        this.add(cbStatus);

        this.add(new JLabel());
        this.add(btnOk, "split 2");
        this.add(btnCancel);

    }

    public void appointment(){
        try{

            this.appointment.setId(Integer.valueOf(txtId.getText()));
            this.appointment.setName(txtName.getText());
            this.appointment.setType((TreatmentType) cbType.getSelectedItem());
            this.appointment.setPrice(Double.valueOf(txtPrice.getText()));
            this.appointment.setDuration(Integer.valueOf(txtDuration.getText()));
            this.appointment.setComestician0((String) cbCosmetician.getSelectedItem());
            this.appointment.setClient(txtClient.getText());
            this.appointment.setCosmetician((String) cbCosmetician.getSelectedItem());
            LocalDateTime startDate = null;
            try {
                startDate = LocalDateTime.parse(txtStartTime.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Wrong date format!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            this.appointment.setStartTime(startDate);

            LocalDateTime endTime = null;
            try {
                endTime = LocalDateTime.parse(txtEndTime.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Wrong date format!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            this.appointment.setEndTime(endTime);
            this.appointment.setStatus((TreatmentStatus) cbStatus.getSelectedItem());

            UpdateAppointmentWindow.this.mainService.getAppointmentService().addAppointment(appointment);
            this.setVisible(false);
            JOptionPane.showMessageDialog(null, "You have succesfully added appointment", "Succes", JOptionPane.INFORMATION_MESSAGE);
            AllAppointmentsWindow allWorkers = new AllAppointmentsWindow(mainRepository, mainService, new Receptionist());
            allWorkers.setVisible(true);


        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Wrong data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appointment();
                UpdateAppointmentWindow.this.dispose();
                UpdateAppointmentWindow.this.setVisible(false);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateAppointmentWindow.this.mainService.getAppointmentService().addAppointment(appointment);
                UpdateAppointmentWindow.this.dispose();
                UpdateAppointmentWindow.this.setVisible(false);

            }
        });
    }

}
