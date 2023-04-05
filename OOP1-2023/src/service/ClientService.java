package service;

import model.Client;
import repository.ClientRepository;
import repository.MainRepository;

public class ClientService {
    private MainRepository mainRepository;

    public ClientService(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }


    public MainRepository getMainRepository() {
        return mainRepository;
    }


    public void setMainRepository(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void addClient(Client client){
        this.mainRepository.getClientRepository().getClients().add(client);
    }



}
