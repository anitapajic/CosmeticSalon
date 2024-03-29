package service;

import model.Appointment;
import model.Client;
import repository.ClientRepository;
import repository.MainRepository;

import java.util.ArrayList;

public class ClientService {
    private MainRepository mainRepository;

    public ClientService(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }


    public void addClient(Client client){
        this.mainRepository.getClientRepository().getClients().add(client);
    }

    public ArrayList<Appointment> getClientAppointments(String username){
        ArrayList<Appointment> appointments = new ArrayList<>();
        for(Appointment a:this.mainRepository.getAppointmentRepository().getAppointments()){
            if(a.getClient().equals(username)){
                appointments.add(a);
            }
        }
        return appointments;
    }

    public double getSpentMoney(String username){
        double spent = 0;
        for(Appointment app:mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getClient().equals(username)){
                spent += app.getPrice();
            }
        }
        return spent;
    }



}
