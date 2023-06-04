package gui.ClientWindows;

import model.Appointment;
import model.Client;
import model.Enum.TreatmentStatus;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyAppointmentsWindow extends JFrame {
    private JTable table = new JTable();
    private  JButton btnCancel = new JButton("CANCEL");
    private JToolBar toolBar = new JToolBar();
    Client client;
    MainRepository mainRepository;
    MainService mainService;
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public MyAppointmentsWindow(MainRepository mainRepository, MainService mainService, Client client){
        this.mainService = mainService;
        this.mainRepository = mainRepository;
        this.client = client;
        setTitle("My appointments");
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
        this.btnCancel.setPreferredSize(d);

        btnCancel.setBackground(new Color(214, 179, 171 ));

        toolBar.add(btnCancel);

        getContentPane().add(toolBar, BorderLayout.NORTH);
    }


    private void GUI() {

        toolBar();
        tabela();

    }

    public void tabela(){

        appointments = mainService.getClientService().getClientAppointments(client.getUsername());

        String [] zaglavlje = {"Id", "Treatment name", "Treatment Type", "Price", "Duration", "Cosmetician", "Start time", "End time", "Status"};

        String [][] zahtevi = new String[appointments.size()][9];

        for (int i = 0; i<appointments.size(); i++){
            zahtevi[i][0] = String.valueOf(appointments.get(i).getId());
            zahtevi[i][1] = appointments.get(i).getName();
            zahtevi[i][2] = String.valueOf(appointments.get(i).getType());
            zahtevi[i][3] = String.valueOf(appointments.get(i).getPrice());
            zahtevi[i][4] = String.valueOf(appointments.get(i).getDuration());
            String username = mainRepository.getCosmeticianRepository().GetCosmeticianById(appointments.get(i).getCosmeticianId()).getUsername();
            zahtevi[i][5] = username;
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


    private void akcije() {
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = table.getSelectedRow();
                if (selektovaniRed == (-1)) {
                    JOptionPane.showMessageDialog(null, "Please select a row!", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int izbor = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this appointment?", "Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (izbor == JOptionPane.YES_OPTION) {
                        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                        String id = (String) dtm.getValueAt(selektovaniRed, 0);
                        Appointment z = mainService.getAppointmentService().getAppointmentById(Integer.valueOf(id));
                        if(z.getStatus().equals(TreatmentStatus.SCHEDULED)){
                            MyAppointmentsWindow.this.mainRepository.getAppointmentRepository().getAppointments().remove(z);
                            appointments.remove(z);

                            z.setStatus(TreatmentStatus.CANCELED_BY_CLIENT);
                            MyAppointmentsWindow.this.mainRepository.getAppointmentRepository().getAppointments().add(z);
                            appointments.add(z);

                            setVisible(false);

                            MyAppointmentsWindow myReservationsWindow = new MyAppointmentsWindow(mainRepository, mainService, client);
                            myReservationsWindow.setVisible(true);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "You can't cancel past appointments!", "Error",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                }
                DefaultTableModel dtm1 = (DefaultTableModel) table.getModel();
                dtm1.fireTableDataChanged();
            }

        });
    }

}
