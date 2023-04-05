package service;

import repository.MainRepository;

public class WorkerService {
    private MainRepository mainRepository;

    public WorkerService(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }

    public MainRepository getMainRepository() {
        return mainRepository;
    }

    public void setMainRepository(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }
}
