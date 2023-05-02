package service;

import model.Appointment;
import repository.MainRepository;

public class AppointmentService {
    private MainRepository mainRepository;
    public AppointmentService(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }
    public AppointmentService(){}

    public MainRepository getMainRepository() {
        return mainRepository;
    }

    public void setMainRepository(MainRepository mainRepository) {
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
}
