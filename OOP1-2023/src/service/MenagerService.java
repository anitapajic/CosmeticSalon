package service;

import repository.MenagerRepository;

public class MenagerService {
    private MenagerRepository menagerRepository;


    public MenagerService(MenagerRepository menagerRepository){
        this.menagerRepository = menagerRepository;
    }



    public MenagerRepository getMenagerRepository() {
        return menagerRepository;
    }

    public void setMenagerRepository(MenagerRepository menagerRepository) {
        this.menagerRepository = menagerRepository;
    }
}
