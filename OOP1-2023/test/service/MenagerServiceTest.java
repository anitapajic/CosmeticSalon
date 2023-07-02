package service;

import model.*;
import model.Enum.EduCoef;
import model.Enum.Gender;
import model.Enum.Role;
import model.Enum.TreatmentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MenagerServiceTest {
    private MainRepository mainRepository;
    private MenagerService menagerService;

    @BeforeEach
    public void setup() {
        Salon salon = new Salon("Initial name", "00h-00h");

        this.mainRepository = new MainRepository(salon, new WorkerRepository(), new UserRepository(), new ClientRepository(), new CosmeticianRepository(), new RecepcionistRepository(),new MenagerRepository() , new TreatmentsRepository(), new AppointmentRepository());
        menagerService = new MenagerService(mainRepository);

    }

    @Test
    public void testGetWorkers() {
        // Create sample workers
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();

        // Add the workers to the repository
        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(worker1);
        workers.add(worker2);
        mainRepository.getWorkerRepository().setWorkers(workers);

        // Call the method under test
        ArrayList<Worker> result = menagerService.getWorkers();

        // Assert that the returned list contains the workers
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(worker1));
        Assertions.assertTrue(result.contains(worker2));
    }

    @Test
    public void testAddWorker_Cosmetician() {
        Cosmetician cosmetician = new Cosmetician();
        cosmetician.setUsername("username");
        cosmetician.setPassword("123");
        cosmetician.setName("new");
        cosmetician.setLastname("user");
        cosmetician.setAddress("address");
        cosmetician.setTelephone("251654+5+");
        cosmetician.setRole(Role.COSMETICIAN);
        cosmetician.setBonus(0.0);
        cosmetician.setYearsOfService(1.0);
        cosmetician.setEducoef(EduCoef.SIXTH);
        cosmetician.setSalary(15000.0);
        cosmetician.setGender(Gender.OTHER);


        ArrayList<Cosmetician> cosmeticians = new ArrayList<>();
        cosmeticians.add(cosmetician);
        mainRepository.getCosmeticianRepository().setCosmeticians(cosmeticians);

        menagerService.addWorker(cosmetician);

        ArrayList<Cosmetician> cosmeticians2 = mainRepository.getCosmeticianRepository().getCosmeticians();

        Assertions.assertTrue(cosmeticians2.contains(cosmetician));
    }
    @Test
    public void testAddWorker_Receptionist() {
        // Create a sample receptionist
        Receptionist cosmetician = new Receptionist();
        cosmetician.setUsername("username");
        cosmetician.setPassword("123");
        cosmetician.setName("new");
        cosmetician.setLastname("user");
        cosmetician.setAddress("address");
        cosmetician.setTelephone("251654+5+");
        cosmetician.setRole(Role.RECEPCIONIST);
        cosmetician.setBonus(0.0);
        cosmetician.setYearsOfService(1.0);
        cosmetician.setEducoef(EduCoef.SIXTH);
        cosmetician.setSalary(15000.0);
        cosmetician.setGender(Gender.OTHER);

        ArrayList<Receptionist> cosmeticians = new ArrayList<>();
        cosmeticians.add(cosmetician);
        mainRepository.getRecepcionistRepository().setRecepcionists(cosmeticians);

        // Call the method under test
        menagerService.addWorker(cosmetician);

        // Get the receptionists from the repository
        ArrayList<Receptionist> receptionists = mainRepository.getRecepcionistRepository().getRecepcionists();

        // Assert that the receptionist has been added
        Assertions.assertTrue(receptionists.contains(cosmetician));
    }

    @Test
    public void testAddWorker_Manager() {
        // Create a sample manager
        Menager cosmetician = new Menager();
        cosmetician.setUsername("username");
        cosmetician.setPassword("123");
        cosmetician.setName("new");
        cosmetician.setLastname("user");
        cosmetician.setAddress("address");
        cosmetician.setTelephone("251654+5+");
        cosmetician.setRole(Role.MENAGER);
        cosmetician.setBonus(0.0);
        cosmetician.setYearsOfService(1.0);
        cosmetician.setEducoef(EduCoef.SIXTH);
        cosmetician.setSalary(15000.0);
        cosmetician.setGender(Gender.OTHER);

        ArrayList<Menager> cosmeticians = new ArrayList<>();
        cosmeticians.add(cosmetician);
        mainRepository.getMenagerRepository().setMenagers(cosmeticians);

        // Call the method under test
        menagerService.addWorker(cosmetician);

        // Get the managers from the repository
        ArrayList<Menager> managers = mainRepository.getMenagerRepository().getMenagers();

        // Assert that the manager has been added
        Assertions.assertTrue(managers.contains(cosmetician));
    }

    @Test
    public void testRemoveWorker() {
        // Create sample workers
        Person worker1 = new Person();
        worker1.setUsername("worker1");
        Person worker2 = new Person();
        worker2.setUsername("worker2");

        // Add the workers to the repository
        ArrayList<Person> workers = new ArrayList<>();
        workers.add(worker1);
        workers.add(worker2);
        mainRepository.getUserRepository().setUsers(workers);

        // Call the method under test
        menagerService.removeWorker("worker1");

        // Get the workers from the repository
        ArrayList<Person> updatedWorkers = mainRepository.getUserRepository().getUsers();

        // Assert that the worker has been removed
        Assertions.assertFalse(updatedWorkers.contains(worker1));
        Assertions.assertEquals(1, updatedWorkers.size());
    }
    @Test
    public void testDeleteTreatment() {
        // Create sample treatments
        Treatment treatment1 = new Treatment();
        treatment1.setId(1);
        Treatment treatment2 = new Treatment();
        treatment2.setId(2);

        // Add the treatments to the repository
        ArrayList<Treatment> treatments = new ArrayList<>();
        treatments.add(treatment1);
        treatments.add(treatment2);
        mainRepository.getTreatmentsRepository().setTreatmentsList(treatments);

        // Call the method under test
        menagerService.deleteTreatment(1);

        // Get the treatments from the repository
        ArrayList<Treatment> updatedTreatments = mainRepository.getTreatmentsRepository().getTreatmentsList();

        // Assert that the treatment has been removed
        Assertions.assertFalse(updatedTreatments.contains(treatment1));
        Assertions.assertEquals(1, updatedTreatments.size());
    }

    @Test
    public void testAddTreatment() {
        // Create a sample treatment
        Treatment treatment = new Treatment();

        // Call the method under test
        menagerService.addTreatment(treatment);

        // Get the treatments from the repository
        ArrayList<Treatment> treatments = mainRepository.getTreatmentsRepository().getTreatmentsList();

        // Assert that the treatment has been added
        Assertions.assertTrue(treatments.contains(treatment));
    }

    @Test
    public void testGetTreatments() {
        // Create sample treatments
        Treatment treatment1 = new Treatment();
        Treatment treatment2 = new Treatment();

        // Add the treatments to the repository
        ArrayList<Treatment> treatments = new ArrayList<>();
        treatments.add(treatment1);
        treatments.add(treatment2);
        mainRepository.getTreatmentsRepository().setTreatmentsList(treatments);

        // Call the method under test
        ArrayList<Treatment> result = menagerService.getTreatments();

        // Assert that the returned list contains the treatments
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(treatment1));
        Assertions.assertTrue(result.contains(treatment2));
    }

    @Test
    public void testGetIncomeMonthly() {
        // Create sample appointments
        Appointment appointment1 = new Appointment();
        appointment1.setStatus(TreatmentStatus.ACCOMPLISHED);
        appointment1.setStartTime(LocalDateTime.of(2023, 4, 1, 9, 0));
        appointment1.setPrice(100.0);
        Appointment appointment2 = new Appointment();
        appointment2.setStatus(TreatmentStatus.ACCOMPLISHED);
        appointment2.setStartTime(LocalDateTime.of(2023, 4, 2, 10, 0));
        appointment2.setPrice(150.0);

        // Add the appointments to the repository
        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        // Call the method under test
        double result = menagerService.getIncomeMonthly(3);

        // Assert the monthly income
        Assertions.assertEquals(250.0, result);
    }
    @Test
    public void testGetIncomeMonthly_noAppointments() {
        // Arrange
        int monthIndex = 10; // November
        Appointment appointment1 = new Appointment();
        appointment1.setStatus(TreatmentStatus.SCHEDULED);
        appointment1.setStartTime(LocalDateTime.of(2023, 9, 5, 10, 0));
        appointment1.setPrice(50.0);
        Appointment appointment2 = new Appointment();
        appointment2.setStatus(TreatmentStatus.SCHEDULED);
        appointment2.setStartTime(LocalDateTime.of(2023, 9, 12, 15, 30));
        appointment2.setPrice(75.0);
        mainRepository.getAppointmentRepository().getAppointments().add(appointment1);
        mainRepository.getAppointmentRepository().getAppointments().add(appointment2);

        // Act
        double result = menagerService.getIncomeMonthly(monthIndex);

        // Assert
        Assertions.assertEquals(0.0, result);
    }
    @Test
    public void testGetOutcomeMonthly() {
        // Create sample workers with salaries
        Worker worker1 = new Worker();
        worker1.setBonus(0.0);
        worker1.setEducoef(EduCoef.SIXTH);
        worker1.setYearsOfService(1.0);
        worker1.setSalary(1000.0);
        Worker worker2 = new Worker();
        worker2.setBonus(0.0);
        worker2.setEducoef(EduCoef.SIXTH);
        worker2.setYearsOfService(1.0);
        worker2.setSalary(1500.0);

        // Add the workers to the repository
        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(worker1);
        workers.add(worker2);
        mainRepository.getWorkerRepository().setWorkers(workers);

        // Call the method under test
        double result = menagerService.getOutcomeMonthly(0);

        // Assert the monthly outcome
        Assertions.assertEquals(120000.0, result);
    }
    @Test
    public void testGetIncomeThisYear() {
        // Create sample appointments
        Appointment appointment1 = new Appointment();
        appointment1.setStatus(TreatmentStatus.ACCOMPLISHED);
        appointment1.setStartTime(LocalDateTime.of(2023, 2, 1, 9, 0));
        appointment1.setPrice(100.0);
        Appointment appointment2 = new Appointment();
        appointment2.setStatus(TreatmentStatus.ACCOMPLISHED);
        appointment2.setStartTime(LocalDateTime.of(2023, 2, 1, 10, 0));
        appointment2.setPrice(150.0);

        // Add the appointments to the repository
        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        // Call the method under test
        double result = menagerService.getIncomeThisYear();

        // Assert the yearly income
        Assertions.assertEquals(250.0, result);
    }
    @Test
    public void testGetOutcomeThisYear() {
        // Create sample workers with salaries
        Worker worker1 = new Worker();
        worker1.setBonus(0.0);
        worker1.setEducoef(EduCoef.SIXTH);
        worker1.setYearsOfService(1.0);
        worker1.setSalary(1000.0);
        Worker worker2 = new Worker();
        worker2.setBonus(0.0);
        worker2.setEducoef(EduCoef.SIXTH);
        worker2.setYearsOfService(1.0);
        worker2.setSalary(1500.0);

        // Add the workers to the repository
        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(worker1);
        workers.add(worker2);
        mainRepository.getWorkerRepository().setWorkers(workers);

        // Call the method under test
        double result = menagerService.getOutcomeThisYear();

        // Assert the yearly outcome
        Assertions.assertEquals(840000.0, result);
    }

    @Test
    public void testGetNumOfAppForCosm() {
        // Create a sample cosmetician
        Cosmetician cosmetician = new Cosmetician();
        cosmetician.setId(55);

        // Create sample appointments
        Appointment appointment1 = new Appointment();
        appointment1.setStatus(TreatmentStatus.ACCOMPLISHED);
        appointment1.setCosmeticianId(55);
        Appointment appointment2 = new Appointment();
        appointment2.setStatus(TreatmentStatus.ACCOMPLISHED);
        appointment2.setCosmeticianId(55);
        Appointment appointment3 = new Appointment();
        appointment3.setStatus(TreatmentStatus.SCHEDULED);
        appointment3.setCosmeticianId(55);

        // Add the appointments to the repository
        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        appointments.add(appointment3);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        // Call the method under test
        int result = menagerService.getNumOfAppForCosm(cosmetician);

        // Assert the number of appointments for the cosmetician
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testGetIncomeForCosm() {
        // Create a sample cosmetician
        Cosmetician cosmetician = new Cosmetician();
        cosmetician.setId(55);

        // Create sample appointments
        Appointment appointment1 = new Appointment();
        appointment1.setStatus(TreatmentStatus.ACCOMPLISHED);
        appointment1.setCosmeticianId(55);
        appointment1.setPrice(100.0);
        Appointment appointment2 = new Appointment();
        appointment2.setStatus(TreatmentStatus.ACCOMPLISHED);
        appointment2.setCosmeticianId(56);
        appointment2.setPrice(150.0);

        // Add the appointments to the repository
        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        // Call the method under test
        int result = menagerService.getIncomeForCosm(cosmetician);

        // Assert the income for the cosmetician
        Assertions.assertEquals(100, result);
    }

    @Test
    public void testGetNumOfAppByTreatment() {
        // Create a sample treatment
        Treatment treatment = new Treatment();
        treatment.setName("Treatment1");

        // Create sample appointments
        Appointment appointment1 = new Appointment();
        appointment1.setStatus(TreatmentStatus.ACCOMPLISHED);
        appointment1.setName("Treatment1");
        Appointment appointment2 = new Appointment();
        appointment2.setStatus(TreatmentStatus.ACCOMPLISHED);
        appointment2.setName("Treatment2");
        Appointment appointment3 = new Appointment();
        appointment3.setStatus(TreatmentStatus.SCHEDULED);
        appointment3.setName("Treatment1");

        // Add the appointments to the repository
        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        appointments.add(appointment3);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        // Call the method under test
        int result = menagerService.getNumOfAppByTreatment(treatment);

        // Assert the number of appointments for the treatment
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testGetIncomeByTreatment() {
        // Create a sample treatment
        Treatment treatment = new Treatment();
        treatment.setName("Treatment1");

        // Create sample appointments
        Appointment appointment1 = new Appointment();
        appointment1.setStatus(TreatmentStatus.ACCOMPLISHED);
        appointment1.setName("Treatment1");
        appointment1.setPrice(100.0);
        Appointment appointment2 = new Appointment();
        appointment2.setStatus(TreatmentStatus.ACCOMPLISHED);
        appointment2.setName("Treatment2");
        appointment2.setPrice(150.0);

        // Add the appointments to the repository
        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        // Call the method under test
        int result = menagerService.getIncomeByTreatment(treatment);

        // Assert the income for the treatment
        Assertions.assertEquals(100, result);
    }

}
