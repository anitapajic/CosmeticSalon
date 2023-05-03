package service;

import repository.MainRepository;

public class MainService {
    private UserService userService;
    private ClientService clientService;
    private MenagerService menagerService;
    private CosmeticianService cosmeticianService;
    private RecepcionistService recepcionistService;
    private AppointmentService appointmentService;
    private TreatmentService treatmentService;
    private WorkerService workerService;

    public MainService(WorkerService workerService, UserService userService,ClientService clientService, MenagerService menagerService,  CosmeticianService cosmeticianService, RecepcionistService recepcionistService, AppointmentService appointmentService, TreatmentService treatmentService){
        this.workerService = workerService;
        this.userService = userService;
        this.clientService = clientService;
        this.menagerService = menagerService;
        this.cosmeticianService = cosmeticianService;
        this.recepcionistService = recepcionistService;
        this.appointmentService = appointmentService;
        this.treatmentService = treatmentService;
    }
    public MainService(){}

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public MenagerService getMenagerService() {
        return menagerService;
    }

    public void setMenagerService(MenagerService menagerService) {
        this.menagerService = menagerService;
    }

    public CosmeticianService getCosmeticianService() {
        return cosmeticianService;
    }

    public void setCosmeticianService(CosmeticianService cosmeticianService) {
        this.cosmeticianService = cosmeticianService;
    }

    public RecepcionistService getRecepcionistService() {
        return recepcionistService;
    }

    public void setRecepcionistService(RecepcionistService recepcionistService) {
        this.recepcionistService = recepcionistService;
    }

    public AppointmentService getAppointmentService() {
        return appointmentService;
    }

    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public TreatmentService getTreatmentService() {
        return treatmentService;
    }

    public void setTreatmentService(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    public WorkerService getWorkerService() {
        return workerService;
    }

    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }
}
