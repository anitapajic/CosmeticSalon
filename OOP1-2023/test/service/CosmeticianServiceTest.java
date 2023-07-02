package service;

import model.Appointment;
import model.Cosmetician;
import model.Enum.TreatmentStatus;
import model.Salon;
import model.Treatment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CosmeticianServiceTest {
    private MainRepository mainRepository;
    private CosmeticianService cosmeticianService;

    @BeforeEach
    public void setup() {
        Salon salon = new Salon("Initial name", "00h-00h");

        this.mainRepository = new MainRepository(salon, new WorkerRepository(), new UserRepository(), new ClientRepository(), new CosmeticianRepository(), new RecepcionistRepository(),new MenagerRepository() , new TreatmentsRepository(), new AppointmentRepository());
        cosmeticianService = new CosmeticianService(mainRepository);
    }

    @Test
    public void testAddCosmetician() {
        Cosmetician cosmetician = new Cosmetician();
        cosmetician.setId(55);

        cosmeticianService.addCosmetician(cosmetician);

        ArrayList<Cosmetician> cosmeticians = mainRepository.getCosmeticianRepository().getCosmeticians();
        Assertions.assertTrue(cosmeticians.contains(cosmetician));
    }

    @Test
    public void testRemoveCosmetician() {
        Cosmetician cosmetician1 = new Cosmetician();
        cosmetician1.setUsername("username1");
        Cosmetician cosmetician2 = new Cosmetician();
        cosmetician2.setUsername("username2");

        ArrayList<Cosmetician> cosmeticians = new ArrayList<>();
        cosmeticians.add(cosmetician1);
        cosmeticians.add(cosmetician2);
        mainRepository.getCosmeticianRepository().setCosmeticians(cosmeticians);

        cosmeticianService.removeCosmetician("username1");

        ArrayList<Cosmetician> updatedCosmeticians = mainRepository.getCosmeticianRepository().getCosmeticians();

        Assertions.assertFalse(updatedCosmeticians.contains(cosmetician1));
        Assertions.assertEquals(1, updatedCosmeticians.size());
    }
    @Test
    public void testRemoveCosmetician_cosmeticianDoesNotExist() {
        // Arrange
        String username = "john_doe";
        Cosmetician cosmetician1 = new Cosmetician();
        cosmetician1.setUsername("jane_smith");
        Cosmetician cosmetician2 = new Cosmetician();
        cosmetician2.setUsername("peter_black");
        mainRepository.getCosmeticianRepository().getCosmeticians().add(cosmetician1);
        mainRepository.getCosmeticianRepository().getCosmeticians().add(cosmetician2);

        // Act
        cosmeticianService.removeCosmetician(username);

        // Assert
        List<Cosmetician> cosmeticians = mainRepository.getCosmeticianRepository().getCosmeticians();
        Assertions.assertTrue(cosmeticians.contains(cosmetician1));
        Assertions.assertTrue(cosmeticians.contains(cosmetician2));
    }
    
    @Test
    public void testGetCosmeticianSchedule() {
        Cosmetician cosmetician1 = new Cosmetician();
        cosmetician1.setUsername("username");
        cosmetician1.setId(55);

        ArrayList<Cosmetician> cosmeticians = new ArrayList<>();
        cosmeticians.add(cosmetician1);
        mainRepository.getCosmeticianRepository().setCosmeticians(cosmeticians);

        Appointment appointment1 = new Appointment();
        appointment1.setCosmeticianId(55);
        appointment1.setStatus(TreatmentStatus.SCHEDULED);
        Appointment appointment2 = new Appointment();
        appointment2.setCosmeticianId(56);
        appointment2.setStatus(TreatmentStatus.SCHEDULED);

        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        ArrayList<Appointment> result = cosmeticianService.getCosmeticianSchedule("username");

        Assertions.assertTrue(result.contains(appointment1));
        Assertions.assertEquals(1, result.size());
    }
    @Test
    public void testGetCosmeticianSchedule_noAppointmentsForCosmetician() {
        // Arrange
        Cosmetician cosmetician1 = new Cosmetician();
        cosmetician1.setUsername("username");
        cosmetician1.setId(55);

        ArrayList<Cosmetician> cosmeticians = new ArrayList<>();
        cosmeticians.add(cosmetician1);
        mainRepository.getCosmeticianRepository().setCosmeticians(cosmeticians);

        Appointment appointment1 = new Appointment();
        appointment1.setCosmeticianId(56);
        appointment1.setStatus(TreatmentStatus.SCHEDULED);
        Appointment appointment2 = new Appointment();
        appointment2.setCosmeticianId(57);
        appointment2.setStatus(TreatmentStatus.SCHEDULED);

        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        // Act
        ArrayList<Appointment> result = cosmeticianService.getCosmeticianSchedule("username");

        // Assert
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testIsCosmeticianFree() {
        // Create sample appointments
        Appointment appointment1 = new Appointment();
        appointment1.setStartTime(LocalDateTime.of(2023, 8, 1, 9, 0));
        appointment1.setEndTime(LocalDateTime.of(2023, 8, 1, 10, 0));
        appointment1.setCosmeticianId(3);
        appointment1.setStatus(TreatmentStatus.SCHEDULED);

        Appointment appointment2 = new Appointment();
        appointment2.setStartTime(LocalDateTime.of(2023, 8, 1, 11, 0));
        appointment2.setEndTime(LocalDateTime.of(2023, 8, 1, 12, 0));
        appointment2.setCosmeticianId(3);
        appointment2.setStatus(TreatmentStatus.SCHEDULED);

        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        mainRepository.getAppointmentRepository().setAppointments(appointments);


        boolean result = cosmeticianService.isCosmeticianFree("ana@cosmetician.com", "01.08.2023. 15:00", "Sportska masaza");


        Assertions.assertTrue(result);
    }
    @Test
    public void testIsCosmeticianFree_cosmeticianIsNotFree_returnsFalse() {
        // Create sample appointments
        Appointment appointment1 = new Appointment();
        appointment1.setStartTime(LocalDateTime.of(2023, 8, 1, 9, 0));
        appointment1.setEndTime(LocalDateTime.of(2023, 8, 1, 10, 0));
        appointment1.setCosmeticianId(3);
        appointment1.setStatus(TreatmentStatus.SCHEDULED);

        Appointment appointment2 = new Appointment();
        appointment2.setStartTime(LocalDateTime.of(2023, 8, 1, 11, 0));
        appointment2.setEndTime(LocalDateTime.of(2023, 8, 1, 12, 0));
        appointment2.setCosmeticianId(3);
        appointment2.setStatus(TreatmentStatus.SCHEDULED);

        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        // Try to schedule an appointment that overlaps with existing appointments
        boolean result = cosmeticianService.isCosmeticianFree("ana@cosmetician.com", "01.08.2023. 09:30", "Sportska masaza");

        Assertions.assertFalse(result);
    }


}
