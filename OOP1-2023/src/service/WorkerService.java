package service;

import model.Worker;
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

    public Worker getWorkerByUsername(String username){
        Worker worker = new Worker();
        for(Worker w:mainRepository.getWorkerRepository().getWorkers()){
            if(w.getUsername().equals(username)){
                worker = w;
            }
        }
        return worker;
    }
}
