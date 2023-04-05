package service;

import repository.MainRepository;
import repository.MenagerRepository;

public class MenagerService {
    private MainRepository mainRepository;

    public MenagerService(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }


    public MainRepository getMainRepository() {
        return mainRepository;
    }

    public void setMainRepository(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }
}
