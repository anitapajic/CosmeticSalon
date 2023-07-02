package service;

import model.Appointment;
import model.Receptionist;
import model.Salon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;

import java.util.ArrayList;

public class RecepcionistServiceTest {
    private MainRepository mainRepository;
    private RecepcionistService recepcionistService;
    private AppointmentService appointmentService;

    @BeforeEach
    public void setup() {
        Salon salon = new Salon("Initial name", "00h-00h");

        this.mainRepository = new MainRepository(salon, new WorkerRepository(), new UserRepository(), new ClientRepository(), new CosmeticianRepository(), new RecepcionistRepository(),new MenagerRepository() , new TreatmentsRepository(), new AppointmentRepository());
        this.recepcionistService = new RecepcionistService(mainRepository);
        this.appointmentService = new AppointmentService(mainRepository);
    }

    @Test
    public void testAddRecepcionist() {
        Receptionist recepcionist = new Receptionist();
        recepcionist.setId(55);
        recepcionistService.addRecepcionist(recepcionist);

        ArrayList<Receptionist> recepcionists = mainRepository.getRecepcionistRepository().getRecepcionists();

        Assertions.assertTrue(recepcionists.contains(recepcionist));
    }

    @Test
    public void testRemoveRecepcionist() {
        Receptionist recepcionist1 = new Receptionist();
        recepcionist1.setUsername("username1");
        Receptionist recepcionist2 = new Receptionist();
        recepcionist2.setUsername("username2");

        ArrayList<Receptionist> recepcionists = new ArrayList<>();
        recepcionists.add(recepcionist1);
        recepcionists.add(recepcionist2);
        mainRepository.getRecepcionistRepository().setRecepcionists(recepcionists);

        recepcionistService.removeRecepcionist("username1");

        Assertions.assertFalse(recepcionists.contains(recepcionist1));
    }
    @Test
    public void testRemoveRecepcionist_recepcionistExists_successfullyRemovesRecepcionist() {
        // Arrange
        Receptionist recepcionist1 = new Receptionist();
        recepcionist1.setUsername("username1");
        Receptionist recepcionist2 = new Receptionist();
        recepcionist2.setUsername("username2");

        ArrayList<Receptionist> recepcionists = new ArrayList<>();
        recepcionists.add(recepcionist1);
        recepcionists.add(recepcionist2);
        mainRepository.getRecepcionistRepository().setRecepcionists(recepcionists);

        // Act
        recepcionistService.removeRecepcionist("username1");

        // Assert
        Assertions.assertFalse(recepcionists.contains(recepcionist1));
        Assertions.assertTrue(recepcionists.contains(recepcionist2));
    }

    @Test
    public void testGetAllAppointments() {
        Appointment appointment1 = new Appointment();
        appointment1.setId(55);
        Appointment appointment2 = new Appointment();
        appointment2.setId(56);

        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        ArrayList<Appointment> result = recepcionistService.getAllAppointments();

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(appointment1));
        Assertions.assertTrue(result.contains(appointment2));
    }

    @Test
    public void testUpdateAppointment() {
        Appointment appointment1 = new Appointment();
        appointment1.setId(57);

        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setId(57);
        updatedAppointment.setName("updajtevon app");

        recepcionistService.updateAppointment(updatedAppointment);
        Appointment result = appointmentService.getAppointmentById(57);

        Assertions.assertEquals(updatedAppointment, result);
    }
    
    @Test
    public void testUpdateAppointment_appointmentDoesNotExist() {
        // Arrange
        Appointment appointment1 = new Appointment();
        appointment1.setId(57);

        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setId(58);
        updatedAppointment.setName("updated appointment");

        // Act
        recepcionistService.updateAppointment(updatedAppointment);
        Appointment result = appointmentService.getAppointmentById(57);

        // Assert
        Assertions.assertEquals(appointment1, result);
    }
}
