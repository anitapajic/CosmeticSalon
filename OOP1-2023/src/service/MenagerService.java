package service;

import model.*;
import model.Enum.Role;
import model.Enum.TreatmentStatus;
import repository.MainRepository;
import repository.MenagerRepository;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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

    public ArrayList<Treatment> getTreatments(){
        return this.mainRepository.getTreatmentsRepository().getTreatmentsList();
    }

    public double getIncomeMonthly(int index){
        int month = index + 1;
        double income = 0;
        for(Appointment appointment : mainRepository.getAppointmentRepository().getAppointments()){
            if(appointment.getStatus().equals(TreatmentStatus.ACCOMPLISHED) && appointment.getStartTime().getMonthValue() == month){
                income += appointment.getPrice();
            }
        }
        return income;
    }

    public double getOutcomeMonthly(int index){
        double outcome = 0;
        for(Worker w : mainRepository.getWorkerRepository().getWorkers()){
            outcome += w.getSalary();
        }
        return outcome;
    }

    public double getIncomeThisYear(){
        double income = 0;
        for(Appointment appointment : mainRepository.getAppointmentRepository().getAppointments()){
            if(appointment.getStatus().equals(TreatmentStatus.ACCOMPLISHED)){
                income += appointment.getPrice();
            }
        }
        return income;
    }

    public double getOutcomeThisYear(){
        double outcome = 0;
        for(Worker w : mainRepository.getWorkerRepository().getWorkers()){
            outcome += w.getSalary();
        }
        return outcome*(LocalDateTime.now().getMonthValue());
    }

    public int getNumOfAppForCosm(Cosmetician cosmetician){
        int num = 0;
        for(Appointment appointment : mainRepository.getAppointmentRepository().getAppointments()){
            if(appointment.getStatus().equals(TreatmentStatus.ACCOMPLISHED) && appointment.getCosmeticianId().equals(cosmetician.getId())){
                num += 1;
            }
        }
        return num;
    }

    public int getIncomeForCosm(Cosmetician cosmetician){
        int income = 0;
        for(Appointment appointment : mainRepository.getAppointmentRepository().getAppointments()){
            if(appointment.getStatus().equals(TreatmentStatus.ACCOMPLISHED) && appointment.getCosmeticianId().equals(cosmetician.getId())){
                income += appointment.getPrice();
            }
        }
        return income;
    }

    public int getNumOfAppByTreatment(Treatment treatment){
        int num = 0;
        for(Appointment app: mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getName().equals(treatment.getName())){
                num += 1;
            }
        }
        return num;
    }

    public int getIncomeByTreatment(Treatment treatment){
        int income = 0;
        for(Appointment app: mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getName().equals(treatment.getName())){
                income += app.getPrice();
            }
        }
        return income;
    }


}
