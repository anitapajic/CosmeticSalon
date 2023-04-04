package repository;

public class MainRepository {
    private ClientRepository clientRepository;
    private CosmeticianRepository cosmeticianRepository;
    private RecepcionistRepository recepcionistRepository;
    private MenagerRepository menagerRepository;

    public MainRepository(ClientRepository clientRepository, CosmeticianRepository cosmeticianRepository, RecepcionistRepository recepcionistRepository, MenagerRepository menagerRepository){
        this.clientRepository = clientRepository;
        this.cosmeticianRepository = cosmeticianRepository;
        this.recepcionistRepository = recepcionistRepository;
        this.menagerRepository = menagerRepository;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public CosmeticianRepository getCosmeticianRepository() {
        return cosmeticianRepository;
    }

    public void setCosmeticianRepository(CosmeticianRepository cosmeticianRepository) {
        this.cosmeticianRepository = cosmeticianRepository;
    }

    public RecepcionistRepository getRecepcionistRepository() {
        return recepcionistRepository;
    }

    public void setRecepcionistRepository(RecepcionistRepository recepcionistRepository) {
        this.recepcionistRepository = recepcionistRepository;
    }

    public MenagerRepository getMenagerRepository() {
        return menagerRepository;
    }

    public void setMenagerRepository(MenagerRepository menagerRepository) {
        this.menagerRepository = menagerRepository;
    }
}
