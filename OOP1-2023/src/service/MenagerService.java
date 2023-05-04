package service;

import model.*;
import model.Enum.Role;
import repository.MainRepository;
import repository.MenagerRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class MenagerService {
    private MainRepository mainRepository;
    private UserService userService;
    private ClientService clientService;
    private CosmeticianService cosmeticianService;
    private RecepcionistService recepcionistService;

    public MenagerService(MainRepository mainRepository){
        this.mainRepository = mainRepository;
        this.userService = new UserService(mainRepository);
        this.clientService = new ClientService(mainRepository);
        this.cosmeticianService = new CosmeticianService(mainRepository);
        this.recepcionistService = new RecepcionistService(mainRepository);
    }

    public ArrayList<Worker> getWorkers(){
        return this.mainRepository.getWorkerRepository().getWorkers();
    }

    public void addWorker(Worker worker){
        Worker newWorker;
        newWorker = worker;
        int randInt = new Random().nextInt();
        newWorker.setId(randInt);

        if(newWorker.getRole().equals(Role.COSMETICIAN)){
            Cosmetician cosmetician = new Cosmetician(newWorker);
            this.cosmeticianService.addCosmetician(cosmetician);

        }
        else if(newWorker.getRole().equals(Role.RECEPCIONIST)){
            Receptionist receptionist = new Receptionist(newWorker);
            this.recepcionistService.addRecepcionist(receptionist);

        }
        else if (newWorker.getRole().equals(Role.MENAGER)) {
            Menager menager  = new Menager(newWorker);
            mainRepository.getMenagerRepository().getMenagers().add(menager);
        }
        Person user = newWorker;
        this.mainRepository.getUserRepository().getUsers().add(user);

        this.mainRepository.getWorkerRepository().getWorkers().add(newWorker);

    }

    public void removeWorker(String username){
        this.userService.removeUser(username);
        this.cosmeticianService.removeCosmetician(username);
        this.recepcionistService.removeRecepcionist(username);

    }

    public void updateWorker(Worker worker){
        if (worker.getRole().equals(Role.RECEPCIONIST)) {
            for(Worker w : this.mainRepository.getRecepcionistRepository().getRecepcionists()){
                if(w.getUsername().equals(worker.getUsername())){
                    this.mainRepository.getRecepcionistRepository().getRecepcionists().remove(w);
                    this.mainRepository.getRecepcionistRepository().getRecepcionists().add((Receptionist) worker);
                }
            }
        }
        else{
            for(Worker w : this.mainRepository.getCosmeticianRepository().getCosmeticians()){
                if(w.getUsername().equals(worker.getUsername())){
                    this.mainRepository.getCosmeticianRepository().getCosmeticians().remove(w);
                    this.mainRepository.getCosmeticianRepository().getCosmeticians().add((Cosmetician) worker);
                }
            }
        }

    }

    public void deleteTreatment(Integer id){
        Treatment toBeDeleted = new Treatment();
        for(Treatment t: mainRepository.getTreatmentsRepository().getTreatmentsList()){
            if(t.getId().equals(id)){
                toBeDeleted = t;
            }
        }
        this.mainRepository.getTreatmentsRepository().getTreatmentsList().remove(toBeDeleted);
    }

    public void addTreatment(Treatment treatment){
        this.mainRepository.getTreatmentsRepository().getTreatmentsList().add(treatment);
    }
    public void updateTreatment(Treatment treatment){
        for(Treatment t: this.mainRepository.getTreatmentsRepository().getTreatmentsList()){
            if(t.getId().equals(treatment.getId())){
                this.mainRepository.getTreatmentsRepository().getTreatmentsList().remove(t);
                this.mainRepository.getTreatmentsRepository().getTreatmentsList().add(treatment);
            }
        }
    }
    public ArrayList<Treatment> getTreatments(){
        return this.mainRepository.getTreatmentsRepository().getTreatmentsList();
    }

    public MainRepository getMainRepository() {
        return mainRepository;
    }

    public void setMainRepository(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }


}
