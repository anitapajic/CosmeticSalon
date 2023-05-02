package gui.CosmeticianWindows;

import model.Appointment;
import model.Cosmetician;
import model.Treatment;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TreatmentsWindow extends JFrame {
    private JTable table = new JTable();
    private JToolBar toolBar = new JToolBar();
    Cosmetician cosmetician;
    MainRepository mainRepository;
    MainService mainService;
    private ArrayList<Treatment> treatments = new ArrayList<>();

    public TreatmentsWindow(MainRepository mainRepository, MainService mainService, Cosmetician cosmetician) {
        this.mainService = mainService;
        this.mainRepository = mainRepository;
        this.cosmetician = cosmetician;
        setTitle("My treatments");
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

        treatments = mainService.getCosmeticianService().getCosmeticianTreatments(cosmetician.getUsername());

        String [] zaglavlje = {"Id", "Treatment name", "Treatment Type", "Price", "Duration"};

        String [][] zahtevi = new String[treatments.size()][5];

        for (int i = 0; i<treatments.size(); i++){
            zahtevi[i][0] = String.valueOf(treatments.get(i).getId());
            zahtevi[i][1] = treatments.get(i).getName();
            zahtevi[i][2] = String.valueOf(treatments.get(i).getType());
            zahtevi[i][3] = String.valueOf(treatments.get(i).getPrice());
            zahtevi[i][4] = String.valueOf(treatments.get(i).getDuration());
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
