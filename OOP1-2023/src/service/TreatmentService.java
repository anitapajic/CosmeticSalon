package service;

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
}
