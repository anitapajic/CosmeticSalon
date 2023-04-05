package repository;

public class MainRepository {
    private ClientRepository clientRepository;
    private CosmeticianRepository cosmeticianRepository;
    private RecepcionistRepository recepcionistRepository;
    private MenagerRepository menagerRepository;
    private TreatmentsRepository treatmentsRepository;
    private AppointmentRepository appointmentRepository;
    private UserRepository userRepository;

    public MainRepository(){}

    public MainRepository(UserRepository userRepository, ClientRepository clientRepository, CosmeticianRepository cosmeticianRepository, RecepcionistRepository recepcionistRepository, MenagerRepository menagerRepository, TreatmentsRepository treatmentsRepository, AppointmentRepository appointmentRepository){
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.cosmeticianRepository = cosmeticianRepository;
        this.recepcionistRepository = recepcionistRepository;
        this.menagerRepository = menagerRepository;
        this.treatmentsRepository = treatmentsRepository;
        this.appointmentRepository = appointmentRepository;
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

    public TreatmentsRepository getTreatmentsRepository() {
        return treatmentsRepository;
    }

    public void setTreatmentsRepository(TreatmentsRepository treatmentsRepository) {
        this.treatmentsRepository = treatmentsRepository;
    }

    public AppointmentRepository getAppointmentRepository() {
        return appointmentRepository;
    }

    public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
