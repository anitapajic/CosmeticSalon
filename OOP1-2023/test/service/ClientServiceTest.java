package service;

import model.Appointment;
import model.Client;
import model.Salon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;

import java.util.ArrayList;

public class ClientServiceTest {
    private MainRepository mainRepository;
    private ClientService clientService;

    @BeforeEach
    public void setup() {
        Salon salon = new Salon("Initial name", "00h-00h");

        this.mainRepository = new MainRepository(salon, new WorkerRepository(), new UserRepository(), new ClientRepository(), new CosmeticianRepository(), new RecepcionistRepository(),new MenagerRepository() , new TreatmentsRepository(), new AppointmentRepository());
        this.clientService = new ClientService(mainRepository);
    }

    @Test
    public void testAddClient() {
        Client client = new Client();
        client.setId(55);

        clientService.addClient(client);

        ArrayList<Client> clients = mainRepository.getClientRepository().getClients();

        Assertions.assertTrue(clients.contains(client));
    }

    @Test
    public void testGetClientAppointments() {
        Appointment appointment1 = new Appointment();
        appointment1.setClient("username");
        Appointment appointment2 = new Appointment();
        appointment2.setClient("username");

        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        mainRepository.getAppointmentRepository().setAppointments(appointments);
        ArrayList<Appointment> result = clientService.getClientAppointments("username");

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(appointment1));
        Assertions.assertTrue(result.contains(appointment2));
    }

    @Test
    public void testGetSpentMoney() {
        Appointment appointment1 = new Appointment();
        appointment1.setClient("username");
        appointment1.setPrice(50.0);
        Appointment appointment2 = new Appointment();
        appointment2.setClient("username");
        appointment2.setPrice(100.0);

        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        mainRepository.getAppointmentRepository().setAppointments(appointments);

        double result = clientService.getSpentMoney("username");

        Assertions.assertEquals(150, result);
    }
}
