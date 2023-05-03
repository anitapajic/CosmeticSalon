package repository;

import java.io.File;

public class MainRepository {
    private ClientRepository clientRepository;
    private CosmeticianRepository cosmeticianRepository;
    private RecepcionistRepository recepcionistRepository;
    private MenagerRepository menagerRepository;
    private TreatmentsRepository treatmentsRepository;
    private AppointmentRepository appointmentRepository;
    private UserRepository userRepository;
    private WorkerRepository workerRepository;

    public MainRepository(){}

    public MainRepository(WorkerRepository workerRepository, UserRepository userRepository, ClientRepository clientRepository, CosmeticianRepository cosmeticianRepository, RecepcionistRepository recepcionistRepository, MenagerRepository menagerRepository, TreatmentsRepository treatmentsRepository, AppointmentRepository appointmentRepository){
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.cosmeticianRepository = cosmeticianRepository;
        this.recepcionistRepository = recepcionistRepository;
        this.menagerRepository = menagerRepository;
        this.treatmentsRepository = treatmentsRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public void saveAllData(){
        File file1 = new File("src/data/clients.csv");
        utils.WriteToFile.write(file1, this.getClientRepository().getClients());

        File file2 = new File("src/data/cosmeticians.csv");
        utils.WriteToFile.write(file2, this.getCosmeticianRepository().getCosmeticians());

        File file3 = new File("src/data/menagers.csv");
        utils.WriteToFile.write(file3, this.getMenagerRepository().getMenagers());

        File file4 = new File("src/data/persons.csv");
        utils.WriteToFile.write(file4, this.getUserRepository().getUsers());

        File file5 = new File("src/data/recepcionists.csv");
        utils.WriteToFile.write(file5, this.getRecepcionistRepository().getRecepcionists());

        File file6 = new File("src/data/treatments.csv");
        utils.WriteToFile.write(file6, this.getTreatmentsRepository().getTreatmentsList());

        File file7 = new File("src/data/workers.csv");
        utils.WriteToFile.write(file7, this.getWorkerRepository().getWorkers());

        File file8 = new File("src/data/appointments.csv");
        utils.WriteToFile.write(file8, this.getAppointmentRepository().getAppointments());
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public WorkerRepository getWorkerRepository() {
        return workerRepository;
    }

    public void setWorkerRepository(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
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
