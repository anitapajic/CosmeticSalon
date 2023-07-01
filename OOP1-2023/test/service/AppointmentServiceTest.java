package service;

import model.Appointment;
import model.Enum.TreatmentType;
import model.Salon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;

import java.util.ArrayList;

public class AppointmentServiceTest {
    private MainRepository mainRepository;
    private AppointmentService appointmentService;

    @BeforeEach
    public void setup() {
        Salon salon = new Salon("Initial name", "00h-00h");

        this.mainRepository = new MainRepository(salon, new WorkerRepository(), new UserRepository(), new ClientRepository(), new CosmeticianRepository(), new RecepcionistRepository(),new MenagerRepository() , new TreatmentsRepository(), new AppointmentRepository());
        this.appointmentService = new AppointmentService(mainRepository);
    }

    @Test
    public void testGetAppointmentById() {
        Appointment appointment = new Appointment();
        appointment.setId(55);
        this.mainRepository.getAppointmentRepository().getAppointments().add(appointment);

        Appointment result = appointmentService.getAppointmentById(55);

        Assertions.assertEquals(appointment, result);
    }

    @Test
    public void testDeleteAppointment() {
        Appointment appointment = new Appointment();
        appointment.setId(56);
        this.mainRepository.getAppointmentRepository().getAppointments().add(appointment);

        appointmentService.deleteAppointment(appointment);

        Assertions.assertFalse(this.mainRepository.getAppointmentRepository().getAppointments().contains(appointment));
    }

    @Test
    public void testUpdateAppointment() {
        Appointment appointment = new Appointment();
        appointment.setId(57);
        appointment.setName("appointment");
        this.mainRepository.getAppointmentRepository().getAppointments().add(appointment);

        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setId(57);
        updatedAppointment.setName("updatevoan appointment");

        appointmentService.updateAppointment(updatedAppointment);
        Appointment result = appointmentService.getAppointmentById(57);

        Assertions.assertEquals(updatedAppointment, result);
    }

    @Test
    public void testAddAppointment() {
        Appointment appointment = new Appointment();
        appointment.setId(58);
        appointmentService.addAppointment(appointment);

        ArrayList<Appointment> appointments = mainRepository.getAppointmentRepository().getAppointments();

        Assertions.assertTrue(appointments.contains(appointment));
    }

}
