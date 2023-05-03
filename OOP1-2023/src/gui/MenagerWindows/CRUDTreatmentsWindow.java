package gui.MenagerWindows;

import gui.RecepcionistWindows.AllAppointmentsWindow;
import gui.RecepcionistWindows.UpdateAppointmentWindow;
import model.Appointment;
import model.Menager;
import model.Treatment;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CRUDTreatmentsWindow extends JFrame {
    private MainRepository mainRepository;
    private MainService mainService;
    private Menager menager;
    private JButton btnAdd = new JButton("Add");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnUpdate = new JButton("Update");
    private JTable table = new JTable();
    private JToolBar toolBar = new JToolBar();
    private ArrayList<Treatment> treatments = new ArrayList<>();

    public CRUDTreatmentsWindow(MainRepository mainRepository, MainService mainService, Menager menager){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        this.menager = menager;
        setTitle("Treatments");
        setSize(1500, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(214, 179, 171));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GUI();
        pack();
        akcije();
    }
    private void toolBar(){
        Dimension d = new Dimension(150,30);
        this.btnAdd.setPreferredSize(d);
        this.btnDelete.setPreferredSize(d);
        this.btnUpdate.setPreferredSize(d);

        btnAdd.setBackground(new Color(214, 179, 171 ));
        btnUpdate.setBackground(new Color(214, 179, 171 ));
        btnDelete.setBackground(new Color(214, 179, 171 ));


        toolBar.add(btnAdd);
        toolBar.add(btnDelete);
        toolBar.add(btnUpdate);

        getContentPane().add(toolBar, BorderLayout.NORTH);
    }


    private void GUI() {

        toolBar();
        tabela();

    }

    public void tabela(){

        treatments = mainService.getMenagerService().getTreatments();

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


    private void akcije(){
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = table.getSelectedRow();
                if (selektovaniRed == (-1)){
                    JOptionPane.showMessageDialog(null, "Please select a row first.", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                    String id = (String) dtm.getValueAt(selektovaniRed, 0);
                    Treatment z = mainService.getTreatmentService().getTreatmentById(Integer.valueOf(id));
                    CRUDTreatmentsWindow.this.mainService.getMenagerService().deleteTreatment(Integer.valueOf(id));
                    CRUDTreatmentsWindow.this.setVisible(false);
                    AddNewTreatmentWindow updateAppointmentWindow = new AddNewTreatmentWindow(mainRepository, mainService, z);
                    updateAppointmentWindow.setVisible(true);
                    dtm.fireTableDataChanged();

                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = table.getSelectedRow();
                if (selektovaniRed == (-1)){
                    JOptionPane.showMessageDialog(null, "Please select a row first!", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else{
//
                    int izbor = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this treatment?", "Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (izbor == JOptionPane.YES_OPTION) {
                        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                        Integer id = (Integer) dtm.getValueAt(selektovaniRed, 0);
                        Treatment z = mainService.getTreatmentService().getTreatmentById(id);
                        mainService.getMenagerService().deleteTreatment(id);
                        CRUDTreatmentsWindow.this.setVisible(false);
                        CRUDTreatmentsWindow allWorkers = new CRUDTreatmentsWindow(mainRepository, mainService, menager);
                        allWorkers.setVisible(true);


                    }
                }
                DefaultTableModel dtm1 = (DefaultTableModel) table.getModel();
                dtm1.fireTableDataChanged();
            }


        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewTreatmentWindow adminDodajZaposlenog = new AddNewTreatmentWindow(mainRepository, mainService, new Appointment());
                CRUDTreatmentsWindow.this.setVisible(false);
                adminDodajZaposlenog.setVisible(true);
                DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                dtm.fireTableDataChanged();
            }
        });
    }

}
