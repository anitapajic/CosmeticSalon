package gui.MenagerWindows;

import model.Menager;
import model.Worker;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllWorkersWindow extends JFrame {
    private MainRepository mainRepository;
    private MainService mainService;
    private Menager menager;
    private JButton btnAdd = new JButton("Add");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnUpdate = new JButton("Update");

    private JToolBar toolBar = new JToolBar();
    private JTable table = new JTable();

    public AllWorkersWindow(MainRepository mainRepository, MainService mainService, Menager menager){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        this.menager = menager;

        setTitle("All workers");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(214, 179, 171));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GUI();
        akcije();
    }

    private void toolBar(){
        Dimension d = new Dimension(150,30);
        this.btnAdd.setPreferredSize(d);
        this.btnDelete.setPreferredSize(d);
        this.btnUpdate.setPreferredSize(d);

        btnAdd.setBackground(new Color(214, 179, 171 ));
        btnDelete.setBackground(new Color(214, 179, 171 ));
        btnUpdate.setBackground(new Color(214, 179, 171 ));


        toolBar.add(btnAdd);
        toolBar.add(btnDelete);
        toolBar.add(btnUpdate);

        getContentPane().add(toolBar, BorderLayout.NORTH);
    }


    private void GUI() {

        toolBar();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        String[] zaglavlje = {"Name", "Last Name", "Gender", "Phone number", "Address", "Username", "Role", "Salary", "Proffessional qualiffication level", "Years of service", "Bonus"};
        String[][] matrica = tabela();


        DefaultTableModel dtm = new DefaultTableModel(matrica, zaglavlje);
        table = new JTable(dtm);
        JScrollPane srcPan = new JScrollPane(table);

        panel.add(srcPan);

        this.getContentPane().add(panel, BorderLayout.CENTER);
        table.getTableHeader().setBackground(new Color(214, 179, 171));

    }

    public String [][] tabela(){

        ArrayList<Worker> zaposleni;

        zaposleni = mainRepository.getWorkerRepository().getWorkers();

        String [][] zaposleniMatrica = new String [zaposleni.size()][11];
        for (int i = 0; i< zaposleni.size(); i++){
            zaposleniMatrica[i][0]= zaposleni.get(i).getName();
            zaposleniMatrica[i][1]= zaposleni.get(i).getLastname();
            zaposleniMatrica[i][2]= String.valueOf(zaposleni.get(i).getGender());
            zaposleniMatrica[i][3]= String.valueOf(zaposleni.get(i).getTelephone());
            zaposleniMatrica[i][4]= zaposleni.get(i).getAddress();
            zaposleniMatrica[i][5]= zaposleni.get(i).getUsername();
            zaposleniMatrica[i][6]= String.valueOf(zaposleni.get(i).getRole());
            zaposleniMatrica[i][7]= String.valueOf(zaposleni.get(i).getSalary());
            zaposleniMatrica[i][8]= String.valueOf(zaposleni.get(i).getEducoef());
            zaposleniMatrica[i][9]= String.valueOf(zaposleni.get(i).getYearsOfService());
            zaposleniMatrica[i][10]= String.valueOf(zaposleni.get(i).getBonus());

        }
        return zaposleniMatrica;
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
                    String username = (String) dtm.getValueAt(selektovaniRed, 5);
                    Worker z = mainService.getWorkerService().getWorkerByUsername(username);
                    AllWorkersWindow.this.mainService.getMenagerService().removeWorker(z.getUsername());
                    AllWorkersWindow.this.setVisible(false);
                    AddNewWorkerWindow adminDodajZaposlenog = new AddNewWorkerWindow(mainRepository, mainService, z);
                    adminDodajZaposlenog.setVisible(true);
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
                    int izbor = JOptionPane.showConfirmDialog(null, "Are you sure you want to fire this worker?", "Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (izbor == JOptionPane.YES_OPTION) {
                        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                        String username = (String) dtm.getValueAt(selektovaniRed, 5);
                        //mainService.getMenagerService().removeWorker(username);
                        Worker toBeDeleted = mainService.getWorkerService().getWorkerByUsername(username);
                        mainRepository.getWorkerRepository().getWorkers().remove(toBeDeleted);
                        AllWorkersWindow.this.setVisible(false);
                        dtm.fireTableDataChanged();
                        AllWorkersWindow allWorkers = new AllWorkersWindow(mainRepository, mainService, menager);
                        dtm.fireTableDataChanged();
                        allWorkers.setVisible(true);
                        dtm.fireTableDataChanged();


                    }
                }
//                DefaultTableModel dtm1 = (DefaultTableModel) table.getModel();
//                dtm1.fireTableDataChanged();
            }


        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewWorkerWindow adminDodajZaposlenog = new AddNewWorkerWindow(mainRepository, mainService, new Worker());
                AllWorkersWindow.this.setVisible(false);
                adminDodajZaposlenog.setVisible(true);
                DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                dtm.fireTableDataChanged();
            }
        });
    }

}

