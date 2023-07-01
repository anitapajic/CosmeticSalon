package service;

import model.Salon;
import model.Treatment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;

import java.util.ArrayList;

public class TreatmentServiceTest {
    private MainRepository mainRepository;
    private TreatmentService treatmentService;

    @BeforeEach
    public void setup() {
        Salon salon = new Salon("Initial name", "00h-00h");

        this.mainRepository = new MainRepository(salon, new WorkerRepository(), new UserRepository(), new ClientRepository(), new CosmeticianRepository(), new RecepcionistRepository(),new MenagerRepository() , new TreatmentsRepository(), new AppointmentRepository());
        this.treatmentService = new TreatmentService(mainRepository);
    }
    @Test
    public void testGetTreatmentById() {
        Treatment treatment = new Treatment();
        treatment.setId(55);
        treatment.setName("treatment");

        ArrayList<Treatment> treatments = new ArrayList<>();
        treatments.add(treatment);
        mainRepository.getTreatmentsRepository().setTreatmentsList(treatments);

        Treatment result = treatmentService.getTreatmentById(55);

        Assertions.assertEquals(treatment, result);
    }
}
