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
            Cosmetician cosmetician = (Cosmetician) newWorker;
            this.cosmeticianService.addCosmetician(cosmetician);

            File file1 = new File("src/data/cosmeticians.csv");
            utils.WriteToFile.write(file1, this.mainRepository.getCosmeticianRepository().getCosmeticians());
        }
        else if(newWorker.getRole().equals(Role.RECEPCIONIST)){
            Receptionist receptionist = (Receptionist) newWorker;
            this.recepcionistService.addRecepcionist(receptionist);

            File file1 = new File("src/data/receptionists.csv");
            utils.WriteToFile.write(file1, this.mainRepository.getRecepcionistRepository().getRecepcionists());
        }
        Person user = newWorker;
        this.mainRepository.getUserRepository().getUsers().add(user);
        File file1 = new File("src/data/persons.csv");
        utils.WriteToFile.write(file1, this.mainRepository.getUserRepository().getUsers());

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
        this.mainRepository.getTreatmentsRepository().getTreatmentsList().removeIf(t -> t.getId().equals(id));
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
