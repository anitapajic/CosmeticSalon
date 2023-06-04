package gui.RecepcionistWindows;

import model.Appointment;
import model.Enum.TreatmentStatus;
import model.Receptionist;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllAppointmentsWindow extends JFrame {
    private MainRepository mainRepository;
    private MainService mainService;
    private Receptionist receptionist;
    private final JButton btnDelete = new JButton("Cancel");
    private final JButton btnUpdate = new JButton("Update");

    private final JToolBar toolBar = new JToolBar();
    private JTable table = new JTable();

    public AllAppointmentsWindow(MainRepository mainRepository, MainService mainService, Receptionist receptionist){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        this.receptionist = receptionist;

        setTitle("All appointments");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(214, 179, 171));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GUI();
        akcije();
    }
    private void toolBar(){
        Dimension d = new Dimension(150,30);
        this.btnDelete.setPreferredSize(d);
        this.btnUpdate.setPreferredSize(d);

        btnUpdate.setBackground(new Color(214, 179, 171 ));
        btnDelete.setBackground(new Color(214, 179, 171 ));


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

        String[] zaglavlje = {"Id", "Name", "Treatment type", "Price", "Duration", "Client", "Cosmetician", "Start time", "End time", "Status"};
        String[][] matrica = tabela();

        DefaultTableModel dtm = new DefaultTableModel(matrica, zaglavlje);
        table = new JTable(dtm);
        JScrollPane srcPan = new JScrollPane(table);

        panel.add(srcPan);

        this.getContentPane().add(panel, BorderLayout.CENTER);
        table.getTableHeader().setBackground(new Color(214, 179, 171));

    }

    public String [][] tabela(){

        ArrayList<Appointment> appointments;

        appointments = mainRepository.getAppointmentRepository().getAppointments();

        String [][] zaposleniMatrica = new String [appointments.size()][10];
        for (int i = 0; i< appointments.size(); i++){
            zaposleniMatrica[i][0]= String.valueOf(appointments.get(i).getId());
            zaposleniMatrica[i][1]= appointments.get(i).getName();
            zaposleniMatrica[i][2]= String.valueOf(appointments.get(i).getType());
            zaposleniMatrica[i][3]= String.valueOf(appointments.get(i).getPrice());
            zaposleniMatrica[i][4]= String.valueOf(appointments.get(i).getDuration());
            zaposleniMatrica[i][5]= appointments.get(i).getClient();
            String username = mainRepository.getCosmeticianRepository().GetCosmeticianById(appointments.get(i).getCosmeticianId()).getUsername();
            zaposleniMatrica[i][6] = username;
            zaposleniMatrica[i][7]= String.valueOf(appointments.get(i).getStartTime());
            zaposleniMatrica[i][8]= String.valueOf(appointments.get(i).getEndTime());
            zaposleniMatrica[i][9]= String.valueOf(appointments.get(i).getStatus());

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
                    String id = (String) dtm.getValueAt(selektovaniRed, 0);
                    Appointment z = mainService.getAppointmentService().getAppointmentById(Integer.valueOf(id));
                    mainRepository.getAppointmentRepository().getAppointments().remove(z);
                    AllAppointmentsWindow.this.setVisible(false);
                    dtm.fireTableDataChanged();
                    UpdateAppointmentWindow updateAppointmentWindow = new UpdateAppointmentWindow(mainRepository, mainService, z);
                    dtm.fireTableDataChanged();
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
                    int izbor = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this appointment?", "Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (izbor == JOptionPane.YES_OPTION) {
                        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                        String id = (String) dtm.getValueAt(selektovaniRed, 0);
                        Appointment z = mainService.getAppointmentService().getAppointmentById(Integer.valueOf(id));
                        if(z.getStatus().equals(TreatmentStatus.SCHEDULED)){
                            mainRepository.getAppointmentRepository().getAppointments().remove(z);
                            z.setStatus(TreatmentStatus.CANCELED_BY_SALON);
                            mainRepository.getAppointmentRepository().getAppointments().add(z);
                            AllAppointmentsWindow.this.setVisible(false);
                            dtm.fireTableDataChanged();
                            AllAppointmentsWindow allWorkers = new AllAppointmentsWindow(mainRepository, mainService, receptionist);
                            dtm.fireTableDataChanged();
                            allWorkers.setVisible(true);
                            dtm.fireTableDataChanged();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "You can't cancel past appointments!", "Error",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
    }

}
