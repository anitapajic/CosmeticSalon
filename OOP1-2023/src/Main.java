import gui.LoginWindow;
import repository.*;
import service.*;
import service.UserService.*;

public class Main {
    public static void main(String[] args) {

        MainRepository mainRepository = new MainRepository(new WorkerRepository(), new UserRepository(), new ClientRepository(), new CosmeticianRepository(), new RecepcionistRepository(),new MenagerRepository() , new TreatmentsRepository(), new AppointmentRepository());
        MainService mainService = new MainService(new UserService(mainRepository), new ClientService(mainRepository), new MenagerService(mainRepository), new CosmeticianService(mainRepository), new RecepcionistService(mainRepository), new AppointmentService(mainRepository), new TreatmentService(mainRepository));
        LoginWindow loginWindow = new LoginWindow(mainRepository, mainService);
        loginWindow.setVisible(true);
    }
}