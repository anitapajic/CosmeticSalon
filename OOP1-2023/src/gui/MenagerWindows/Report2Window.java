package gui.MenagerWindows;

import model.Appointment;
import model.Cosmetician;
import model.Enum.TreatmentStatus;
import model.Menager;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Report2Window extends JFrame {

    private MainRepository mainRepository;
    private MainService mainService;
    private Menager menager;

    private JTable table = new JTable();

    public Report2Window(MainRepository mainRepository, MainService mainService, Menager menager){
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

        String[] zaglavlje = {"SCHEDULED", "ACCOMPLISHED", "CANCELED BY CLIENT", "CANCELED BY SALON"};
        String[][] matrica = tabela();


        DefaultTableModel dtm = new DefaultTableModel(matrica, zaglavlje);
        table = new JTable(dtm);
        JScrollPane srcPan = new JScrollPane(table);

        panel.add(srcPan);

        this.getContentPane().add(panel, BorderLayout.CENTER);
        table.getTableHeader().setBackground(new Color(214, 179, 171));

    }

    public String [][] tabela(){

        String [][] zaposleniMatrica = new String [1][4];
        int scheduled = 0;
        int accomplished = 0;
        int canceledClient = 0;
        int canceledSalon = 0;
        for(Appointment app : mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getStatus().equals(TreatmentStatus.SCHEDULED)){
                scheduled += 1;
            }
            else if(app.getStatus().equals(TreatmentStatus.ACCOMPLISHED)){
                accomplished += 1;
            }
            else if(app.getStatus().equals(TreatmentStatus.CANCELED_BY_CLIENT)){
                canceledClient += 1;
            }
            else if(app.getStatus().equals(TreatmentStatus.CANCELED_BY_SALON)){
                canceledSalon += 1;
            }
        }

        zaposleniMatrica[0][0]= String.valueOf(scheduled);
        zaposleniMatrica[0][1]= String.valueOf(accomplished);
        zaposleniMatrica[0][2]= String.valueOf(canceledClient);
        zaposleniMatrica[0][3]= String.valueOf(canceledSalon);

        return zaposleniMatrica;
    }
}
