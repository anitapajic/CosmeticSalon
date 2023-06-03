package service;

import model.*;
import model.Enum.TreatmentStatus;
import repository.CosmeticianRepository;
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


    public MainRepository getMainRepository() {
        return mainRepository;
    }

    public void setMainRepository(MainRepository mainRepository) {
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
        for(Appointment a:this.mainRepository.getAppointmentRepository().getAppointments()){
            if(mainRepository.getCosmeticianRepository().GetCosmeticianById(a.getCosmeticianId()).getUsername().equals(username)&&a.getStatus().equals(TreatmentStatus.SCHEDULED)){
                appointments.add(a);
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
        LocalDateTime startDate;
        try{
            startDate = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));
            Treatment treatment = mainRepository.getTreatmentsRepository().getTreatmentByName(treatmentName);
            LocalDateTime endDate = startDate.plusMinutes(treatment.getDuration());
            for(Cosmetician cosmetician : cosmeticians){
                if(isCosmeticianFree(cosmetician.getUsername(), startTime, treatmentName)){
                    freeCosmetician = cosmetician;
                    break;
                }
            }
        }
        catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return freeCosmetician;
    }
    public Boolean isCosmeticianFree(String cosmeticianUsername, String startTime, String treatmentName){
        Boolean free = false;
        ArrayList<Appointment> scheduledApp = new ArrayList<>();
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
