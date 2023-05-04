package service;

import model.Appointment;
import model.Client;
import model.Cosmetician;
import model.Receptionist;
import repository.MainRepository;
import repository.RecepcionistRepository;

import javax.swing.*;
import java.util.ArrayList;

public class RecepcionistService {
    private MainRepository mainRepository;

    public RecepcionistService(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }


    public MainRepository getMainRepository() {
        return mainRepository;
    }

    public void setMainRepository(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void addRecepcionist(Receptionist c){
        this.mainRepository.getRecepcionistRepository().getRecepcionists().add(c);
    }
    public void removeRecepcionist(String username){
        Receptionist toBeDeleted = new Receptionist();
        for (Receptionist p:this.mainRepository.getRecepcionistRepository().getRecepcionists()){
            if(username.equalsIgnoreCase(p.getUsername())){
                toBeDeleted = p;
            }
        }
        this.mainRepository.getRecepcionistRepository().getRecepcionists().remove(toBeDeleted);
    }

    public ArrayList<Appointment> getAllAppointments(){
        return this.mainRepository.getAppointmentRepository().getAppointments();
    }

    public void updateAppointment(Appointment appointment){
        for(Appointment a:this.mainRepository.getAppointmentRepository().getAppointments()){
            if(a.getId().equals(appointment.getId())){
                this.mainRepository.getAppointmentRepository().getAppointments().remove(a);
                this.mainRepository.getAppointmentRepository().getAppointments().add(appointment);
            }
        }
    }
}
