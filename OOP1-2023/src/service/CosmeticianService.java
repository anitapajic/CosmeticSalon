package service;

import model.*;
import model.Enum.TreatmentStatus;
import repository.CosmeticianRepository;
import repository.MainRepository;

import javax.swing.*;
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
}
