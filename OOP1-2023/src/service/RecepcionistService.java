package service;

import repository.RecepcionistRepository;

public class RecepcionistService {
    private RecepcionistRepository recepcionistRepository;

    public RecepcionistService(RecepcionistRepository recepcionistRepository){
        this.recepcionistRepository = recepcionistRepository;
    }

    public RecepcionistRepository getRecepcionistRepository() {
        return recepcionistRepository;
    }

    public void setRecepcionistRepository(RecepcionistRepository recepcionistRepository) {
        this.recepcionistRepository = recepcionistRepository;
    }
}
