package service;

import repository.MainRepository;
import repository.RecepcionistRepository;

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
}
