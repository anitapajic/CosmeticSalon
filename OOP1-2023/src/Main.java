import gui.LoginWindow;
import model.Salon;
import repository.*;
import service.*;


public class Main {
    public static void main(String[] args) {

        Salon salon = new Salon("Initial name", "00h-00h");

        MainRepository mainRepository = new MainRepository(salon, new WorkerRepository(), new UserRepository(), new ClientRepository(), new CosmeticianRepository(), new RecepcionistRepository(),new MenagerRepository() , new TreatmentsRepository(), new AppointmentRepository());
        MainService mainService = new MainService(new WorkerService(mainRepository), new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository));
        LoginWindow loginWindow = new LoginWindow(mainRepository, mainService);
        loginWindow.setVisible(true);
    }
}