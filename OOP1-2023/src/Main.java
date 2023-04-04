import repository.*;

public class Main {
    public static void main(String[] args) {
        TreatmentsRepository treatmentsRepository = new TreatmentsRepository();
        ClientRepository clientRepository = new ClientRepository();
        MenagerRepository menagerRepository = new MenagerRepository();
        RecepcionistRepository recepcionistRepository = new RecepcionistRepository();
        CosmeticianRepository cosmeticianRepository = new CosmeticianRepository();
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        MainRepository mainRepository = new MainRepository(clientRepository, cosmeticianRepository, recepcionistRepository, menagerRepository, treatmentsRepository, appointmentRepository);
    }
}