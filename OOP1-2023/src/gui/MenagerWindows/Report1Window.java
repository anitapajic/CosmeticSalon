package gui.MenagerWindows;

import model.Cosmetician;
import model.Menager;
import model.Worker;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Report1Window extends JFrame {
    private MainRepository mainRepository;
    private MainService mainService;
    private Menager menager;

    private JTable table = new JTable();

    public Report1Window(MainRepository mainRepository, MainService mainService, Menager menager){
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

        String[] zaglavlje = {"Name", "Last Name", "Username", "Number of appointments", "Income"};
        String[][] matrica = tabela();


        DefaultTableModel dtm = new DefaultTableModel(matrica, zaglavlje);
        table = new JTable(dtm);
        JScrollPane srcPan = new JScrollPane(table);

        panel.add(srcPan);

        this.getContentPane().add(panel, BorderLayout.CENTER);
        table.getTableHeader().setBackground(new Color(214, 179, 171));

    }

    public String [][] tabela(){

        ArrayList<Cosmetician> zaposleni;

        zaposleni = mainRepository.getCosmeticianRepository().getCosmeticians();

        String [][] zaposleniMatrica = new String [zaposleni.size()][5];
        for (int i = 0; i< zaposleni.size(); i++){
            zaposleniMatrica[i][0]= zaposleni.get(i).getName();
            zaposleniMatrica[i][1]= zaposleni.get(i).getLastname();
            zaposleniMatrica[i][2]= zaposleni.get(i).getUsername();
            zaposleniMatrica[i][3]= String.valueOf(mainService.getMenagerService().getNumOfAppForCosm(zaposleni.get(i)));
            zaposleniMatrica[i][4]= String.valueOf(mainService.getMenagerService().getIncomeForCosm(zaposleni.get(i)));
        }
        return zaposleniMatrica;
    }



}
