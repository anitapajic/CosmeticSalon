package service;

import model.Client;
import model.Enum.Gender;
import model.Enum.Role;
import model.Person;
import model.Salon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;

import java.util.ArrayList;

public class UserServiceTest {
    private MainRepository mainRepository;
    private UserService userService;

    @BeforeEach
    public void setup() {
        Salon salon = new Salon("Initial name", "00h-00h");

        this.mainRepository = new MainRepository(salon, new WorkerRepository(), new UserRepository(), new ClientRepository(), new CosmeticianRepository(), new RecepcionistRepository(),new MenagerRepository() , new TreatmentsRepository(), new AppointmentRepository());
        userService = new UserService(mainRepository);
    }

    @Test
    public void testRegistration_NewClient() {
        Client client = new Client();
        client.setUsername("username");
        client.setAddress("address");
        client.setTelephone("54658458+4");
        client.setPassword("123");
        client.setGender(Gender.OTHER);
        client.setName("novi");
        client.setLastname("user");

        userService.registration(client);

        ArrayList<Client> clients = mainRepository.getClientRepository().getClients();

        Assertions.assertTrue(clients.contains(client));
    }

    @Test
    public void testRegistration_ExistingClient() {
        Client client = new Client();
        client.setUsername("username");

        mainRepository.getClientRepository().getClients().add(client);

        userService.registration(client);

        ArrayList<Client> updatedClients = mainRepository.getClientRepository().getClients();
        Assertions.assertTrue(updatedClients.contains(client));
    }

    @Test
    public void testRemoveUser() {
        Person user1 = new Person();
        user1.setUsername("user1");
        Person user2 = new Person();
        user2.setUsername("user2");

        ArrayList<Person> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        mainRepository.getUserRepository().setUsers(users);

        userService.removeUser("user1");

        ArrayList<Person> updatedUsers = mainRepository.getUserRepository().getUsers();

        Assertions.assertFalse(updatedUsers.contains(user1));
        Assertions.assertEquals(1, updatedUsers.size());
    }

    @Test
    public void testAddUser() {
        Person user = new Person();
        user.setUsername("username");
        user.setId(55);
        user.setName("New");
        user.setLastname("User");
        user.setPassword("123");
        user.setRole(Role.CLIENT);
        user.setAddress("address");
        user.setGender(Gender.OTHER);
        user.setTelephone("6845684586");

        userService.addUser(user);

        ArrayList<Person> users = mainRepository.getUserRepository().getUsers();

        Assertions.assertTrue(users.contains(user));
    }

}
