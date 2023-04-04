package service;

import repository.CosmeticianRepository;

public class CosmeticianService {

    private CosmeticianRepository cosmeticianRepository;

    public CosmeticianService(CosmeticianRepository cosmeticianRepository){
        this.cosmeticianRepository = cosmeticianRepository;
    }


    public CosmeticianRepository getCosmeticianRepository() {
        return cosmeticianRepository;
    }

    public void setCosmeticianRepository(CosmeticianRepository cosmeticianRepository) {
        this.cosmeticianRepository = cosmeticianRepository;
    }
}
