package gui.CosmeticianWindows;

import model.Appointment;
import model.Client;
import model.Cosmetician;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ScheduleWindow extends JFrame {
    private JTable table = new JTable();
    private JToolBar toolBar = new JToolBar();
    Cosmetician cosmetician;
    MainRepository mainRepository;
    MainService mainService;
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public ScheduleWindow(MainRepository mainRepository, MainService mainService, Cosmetician cosmetician){
        this.mainService = mainService;
        this.mainRepository = mainRepository;
        this.cosmetician = cosmetician;
        setTitle("My schedule");
        setSize(1500, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(214, 179, 171));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GUI();
        pack();
    }

    private void toolBar(){

        getContentPane().add(toolBar, BorderLayout.NORTH);
    }


    private void GUI() {

        toolBar();
        tabela();

    }

    public void tabela(){

        appointments = mainService.getCosmeticianService().getCosmeticianSchedule(cosmetician.getUsername());

        String [] zaglavlje = {"Id", "Treatment name", "Treatment Type", "Price", "Duration", "Cosmetician", "Start time", "End time", "Status"};

        String [][] zahtevi = new String[appointments.size()][9];

        for (int i = 0; i<appointments.size(); i++){
            zahtevi[i][0] = String.valueOf(appointments.get(i).getId());
            zahtevi[i][1] = appointments.get(i).getName();
            zahtevi[i][2] = String.valueOf(appointments.get(i).getType());
            zahtevi[i][3] = String.valueOf(appointments.get(i).getPrice());
            zahtevi[i][4] = String.valueOf(appointments.get(i).getDuration());
            zahtevi[i][5] = appointments.get(i).getCosmetician();
            zahtevi[i][6] = String.valueOf(appointments.get(i).getStartTime());
            zahtevi[i][7] = String.valueOf(appointments.get(i).getEndTime());
            zahtevi[i][8] = String.valueOf(appointments.get(i).getStatus());
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,1));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        DefaultTableModel dtm = new DefaultTableModel(zahtevi, zaglavlje);
        table = new JTable(dtm);
        JScrollPane scPane = new JScrollPane(table);

        panel.add(scPane);

        this.getContentPane().add(panel, BorderLayout.CENTER);
        table.getTableHeader().setBackground(new Color(214, 179, 171 ));

    }


}
