package service;

import model.Salon;
import model.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;

import java.util.ArrayList;

public class WorkerServiceTest {
    private MainRepository mainRepository;
    private WorkerService workerService;

    @BeforeEach
    public void setup() {
        Salon salon = new Salon("Initial name", "00h-00h");

        this.mainRepository = new MainRepository(salon, new WorkerRepository(), new UserRepository(), new ClientRepository(), new CosmeticianRepository(), new RecepcionistRepository(),new MenagerRepository() , new TreatmentsRepository(), new AppointmentRepository());
        this.workerService = new WorkerService(mainRepository);
    }
    @Test
    public void testGetWorkerByUsername() {
        Worker worker = new Worker();
        worker.setUsername("username");

        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(worker);
        mainRepository.getWorkerRepository().setWorkers(workers);
        Worker result = workerService.getWorkerByUsername("username");

        Assertions.assertEquals(worker, result);
    }
    @Test
    public void testGetWorkerByUsername_workerDoesNotExist_returnsNull() {
        // Arrange
        Worker worker = new Worker();
        worker.setUsername("username");

        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(worker);
        mainRepository.getWorkerRepository().setWorkers(workers);

        // Act
        Worker result = workerService.getWorkerByUsername("nonexistent");

        // Assert
        Assertions.assertNull(result.getUsername());
    }
}
