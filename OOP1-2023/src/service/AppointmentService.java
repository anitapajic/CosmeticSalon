package service;

import model.Appointment;
import repository.MainRepository;

import java.util.ArrayList;

public class AppointmentService {
    private MainRepository mainRepository;
    public AppointmentService(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }


    public Appointment getAppointmentById(Integer id){
        Appointment appointment = new Appointment();
        for(Appointment app : mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getId().equals(id)){
                appointment = app;
            }
        }
        return appointment;
    }
    public void deleteAppointment(Appointment appointment){
        mainRepository.getAppointmentRepository().getAppointments().remove(appointment);
    }
    public void updateAppointment(Appointment appointment){
        Appointment appointment1 = new Appointment();
        for(Appointment app : mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getId().equals(appointment.getId())){
                appointment1 = app;
            }
        }
        mainRepository.getAppointmentRepository().getAppointments().remove(appointment1);
        mainRepository.getAppointmentRepository().getAppointments().add(appointment);

    }
    public void addAppointment(Appointment appointment){
        mainRepository.getAppointmentRepository().getAppointments().add(appointment);
    }
    public ArrayList<Appointment> getAppointments(){
        return mainRepository.getAppointmentRepository().getAppointments();
    }

}
