package service;

import model.*;
import model.Enum.TreatmentStatus;
import repository.MainRepository;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CosmeticianService {

    private MainRepository mainRepository;

    public CosmeticianService(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }

    public void addCosmetician(Cosmetician c){
        this.mainRepository.getCosmeticianRepository().getCosmeticians().add(c);
    }
    public void removeCosmetician(String username){
        Cosmetician toBeDeleted = new Cosmetician();
        for (Cosmetician p:this.mainRepository.getCosmeticianRepository().getCosmeticians()){
            if(username.equalsIgnoreCase(p.getUsername())){
                toBeDeleted = p;
            }
        }
        this.mainRepository.getCosmeticianRepository().getCosmeticians().remove(toBeDeleted);
    }

    public ArrayList<Appointment> getCosmeticianSchedule(String username){
        ArrayList<Appointment> appointments = new ArrayList<>();
        Cosmetician cosmetician = mainRepository.getCosmeticianRepository().GetCosmeticianByUsername(username);
        if(cosmetician != null) {
            ArrayList<Appointment> allAppointments = this.mainRepository.getAppointmentRepository().getAppointments();
            if(allAppointments != null) {
                for(Appointment a: allAppointments){
                    if(a.getCosmeticianId().equals(cosmetician.getId()) && a.getStatus().equals(TreatmentStatus.SCHEDULED)){
                        appointments.add(a);
                    }
                }
            }
        }
        return appointments;
    }

    public ArrayList<Treatment> getCosmeticianTreatments(String username){
        ArrayList<Treatment> treatments = new ArrayList<>();
        ArrayList<Cosmetician> cosmeticians = new ArrayList<>();
        for(Treatment a:this.mainRepository.getTreatmentsRepository().getTreatmentsList()){
            for(int i= 0; i < a.getComesticians().size(); i++){
                cosmeticians.add(mainRepository.getCosmeticianRepository().GetCosmeticianById(i));
            }
            for(Cosmetician c : cosmeticians){
                if(c.getUsername().equals(username)){
                    treatments.add(a);
                }
            }
        }
        return treatments;
    }
    public Cosmetician assignFreeCosmetician(String treatmentName, String startTime){
        ArrayList<Cosmetician> cosmeticians = mainRepository.getCosmeticianRepository().getCosmeticians();
        Cosmetician freeCosmetician = null;
        for(Cosmetician cosmetician : cosmeticians){
            if(isCosmeticianFree(cosmetician.getUsername(), startTime, treatmentName)){
                freeCosmetician = cosmetician;
                break;
            }
        }
        return freeCosmetician;
    }
    public Boolean isCosmeticianFree(String cosmeticianUsername, String startTime, String treatmentName){
        Boolean free = false;
        ArrayList<Appointment> scheduledApp;
        Treatment treatment = mainRepository.getTreatmentsRepository().getTreatmentByName(treatmentName);
        LocalDateTime startDate;
        try {
            startDate = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));
            LocalDateTime endDate = startDate.plusMinutes(treatment.getDuration());
            scheduledApp = getCosmeticianSchedule(cosmeticianUsername);
            int overlapCount = 0;
            for(Appointment appointment : scheduledApp){
                if((startDate.isEqual(appointment.getStartTime()) || startDate.isAfter(appointment.getStartTime())) && startDate.isBefore(appointment.getEndTime())
                        || (endDate.isAfter(appointment.getStartTime()) && (endDate.isBefore(appointment.getEndTime()) || endDate.isEqual(appointment.getEndTime())))){
                    overlapCount++;
                }
            }
            if (overlapCount == 0) {
                free = true;
            }
        }
            catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Wrong date format!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
        }

        return free;
    }
}
