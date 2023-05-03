package service;

import model.Appointment;
import model.Treatment;
import repository.MainRepository;
import repository.TreatmentsRepository;

public class TreatmentService {

    private MainRepository mainRepository;

    public TreatmentService(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }


    public MainRepository getMainRepository() {
        return mainRepository;
    }

    public void setMainRepository(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public Treatment getTreatmentById(Integer id){
        Treatment appointment = new Treatment();
        for(Treatment app : mainRepository.getTreatmentsRepository().getTreatmentsList()){
            if(app.getId().equals(id)){
                appointment = app;
            }
        }
        return appointment;
    }
}
