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
        for (Receptionist p:this.mainRepository.getRecepcionistRepository().getRecepcionists()){
            if(username.equalsIgnoreCase(p.getUsername())){
                this.mainRepository.getRecepcionistRepository().getRecepcionists().remove(p);
                JOptionPane.showMessageDialog(null, "You successfully fired recepcionist.", "Information!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
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
