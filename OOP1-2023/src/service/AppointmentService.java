package service;

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
}
