package repository;

import model.Appointment;
import model.Enum.TreatmentStatus;
import model.Enum.TreatmentType;
import model.Treatment;

import javax.swing.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static utils.ReadFromFile.read;

public class AppointmentRepository {
    private List<Appointment> appointments = new ArrayList<>();

    public AppointmentRepository(){
        File treatmentsFile = new File("src/data/appointments.csv");
        List<Appointment> appointmentList = new ArrayList<>();
        ArrayList<String[]> x = new ArrayList<String[]>();

        try {
            read(treatmentsFile, x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String[] k : x) {
            Appointment t = new Appointment();
            t.setId(Integer.valueOf(k[0]));
            t.setName(k[1]);
            t.setType(TreatmentType.valueOf(k[2]));
            t.setPrice(Double.valueOf(k[3]));
            t.setDuration(Integer.valueOf(k[4]));
            t.setClient(k[5]);
            t.setCosmetician(k[6]);
            LocalDateTime date = null;
            try {
                date = LocalDateTime.parse(k[7], DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid date!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            t.setStartTime(date);
            LocalDateTime date2 = null;
            try {
                date2 = LocalDateTime.parse(k[8], DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid date!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            t.setStartTime(date2);
            t.setStatus(TreatmentStatus.valueOf(k[9]));
            appointmentList.add(t);

        }
        this.appointments = appointmentList;
    }

    public List<Appointment> getAppointments(){
        return this.appointments;
    }
}
