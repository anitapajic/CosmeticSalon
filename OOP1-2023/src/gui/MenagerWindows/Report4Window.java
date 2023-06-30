package gui.MenagerWindows;

import model.Client;
import model.Enum.LoyalityCardStatus;
import model.Menager;
import model.Treatment;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Report4Window extends JFrame {
    private MainRepository mainRepository;
    private MainService mainService;
    private Menager menager;

    private JTable table = new JTable();

    public Report4Window(MainRepository mainRepository, MainService mainService, Menager menager){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        this.menager = menager;

        setTitle("Report about cosmeticians");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(214, 179, 171));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GUI();
    }
    private void GUI() {


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        String[] zaglavlje = {"Name", "Last Name", "Gender", "Phone number", "Address", "Username", "Card Status"};
        String[][] matrica = tabela();


        DefaultTableModel dtm = new DefaultTableModel(matrica, zaglavlje);
        table = new JTable(dtm);
        JScrollPane srcPan = new JScrollPane(table);

        panel.add(srcPan);

        this.getContentPane().add(panel, BorderLayout.CENTER);
        table.getTableHeader().setBackground(new Color(214, 179, 171));

    }

    public String [][] tabela(){

        ArrayList<Client> zaposleni = new ArrayList<>();

        for(Client c : mainRepository.getClientRepository().getClients()){
            if(c.getCardStatus().equals(LoyalityCardStatus.YES)){
                zaposleni.add(c);
            }
        }

        String [][] zaposleniMatrica = new String [zaposleni.size()][7];
        for (int i = 0; i< zaposleni.size(); i++) {
            zaposleniMatrica[i][0] = zaposleni.get(i).getName();
            zaposleniMatrica[i][1] = String.valueOf(zaposleni.get(i).getLastname());
            zaposleniMatrica[i][2] = String.valueOf(zaposleni.get(i).getGender());
            zaposleniMatrica[i][3] = String.valueOf(zaposleni.get(i).getTelephone());
            zaposleniMatrica[i][4] = String.valueOf(zaposleni.get(i).getAddress());
            zaposleniMatrica[i][5] = String.valueOf(zaposleni.get(i).getUsername());
            zaposleniMatrica[i][6] = "YES";
        }
        return zaposleniMatrica;
    }
}
