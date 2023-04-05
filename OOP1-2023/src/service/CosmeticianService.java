package service;

import repository.CosmeticianRepository;
import repository.MainRepository;

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
}
