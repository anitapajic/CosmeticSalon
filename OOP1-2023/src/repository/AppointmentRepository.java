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
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public AppointmentRepository(){
        File treatmentsFile = new File("src/data/appointments.csv");
        ArrayList<Appointment> appointmentList = new ArrayList<>();
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
            t.setClient(k[6]);
            t.setCosmeticianId(Integer.valueOf(k[7]));
            String[] ids = k[5].split(",");
            List<Integer> cosmeticianIds = new ArrayList<>();
            for (String id : ids) {
                cosmeticianIds.add(Integer.valueOf(id));
            }
            t.setComesticians(cosmeticianIds);


            LocalDateTime date;
            date = LocalDateTime.parse(k[8], DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));
            t.setStartTime(date);

            LocalDateTime date2;
            date2 = LocalDateTime.parse(k[9], DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));
            t.setEndTime(date2);

            t.setStatus(TreatmentStatus.valueOf(k[10]));
            appointmentList.add(t);

        }
        this.appointments = appointmentList;
    }

    public ArrayList<Appointment> getAppointments(){
        return this.appointments;
    }
    public void setAppointments(ArrayList<Appointment> appointments){
        this.appointments = appointments;
    }
}
