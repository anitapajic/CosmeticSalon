package gui.MenagerWindows;

import gui.RecepcionistWindows.AllAppointmentsWindow;
import gui.RecepcionistWindows.UpdateAppointmentWindow;
import model.Cosmetician;
import model.Enum.TreatmentType;
import model.Menager;
import model.Receptionist;
import model.Treatment;
import net.miginfocom.swing.MigLayout;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class AddNewTreatmentWindow extends JFrame {
    private MainRepository mainRepository;
    private MainService mainService;
    private Treatment treatment;

    private final JLabel id = new JLabel("ID:");
    private final JTextField txtId = new JTextField(20);
    private final JLabel name = new JLabel("Name:");
    private final JTextField txtName = new JTextField(20);

    private final JLabel type = new JLabel("Treatment Type:");
    private final JComboBox<TreatmentType> cbType = new JComboBox<TreatmentType>(TreatmentType.values());

    private final JLabel price = new JLabel("Price:");
    private final JTextField txtPrice = new JTextField(20);

    private final JLabel duration = new JLabel("Duration:");
    private final JTextField txtDuration = new JTextField(20);

    private final JLabel cosmetician = new JLabel("Cosmetician:");
    private String[] cosmeticians = {"sima@cosmetician.com", "jovana@cosmetician.com", "zika@cosmetician.com", "ana@cosmetician.com"};
    private final JComboBox<String> cbCosmetician = new JComboBox<>(cosmeticians);

    private final JButton btnOk = new JButton("Submit");
    private final JButton btnCancel = new JButton("Quit");

    public AddNewTreatmentWindow(MainRepository mainRepository, MainService mainService, Treatment treatment){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        this.treatment = treatment;
        this.setTitle("Add treatment");
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(214, 179, 171 ));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnOk);
        GUI();
        pack();
        actions();
    }
    public void GUI(){
        MigLayout ml = new MigLayout("wrap 2");

        if (!treatment.equals(null)){

            txtId.setText(String.valueOf(treatment.getId()));
            txtName.setText(treatment.getName());
            cbType.setSelectedItem(treatment.getType());
            txtPrice.setText(String.valueOf(treatment.getPrice()));
            txtDuration.setText(String.valueOf(treatment.getDuration()));
            ///TODO : cosmeticians
            //cbCosmetician.setSelectedItem(treatment.getComesticians());

        }
        this.setLayout(ml);
        this.add(id);
        this.add(txtId);
        this.add(name);
        this.add(txtName);
        this.add(type);
        this.add(cbType);
        this.add(price);
        this.add(txtPrice);
        this.add(duration);
        this.add(txtDuration);
        this.add(cosmetician);
        this.add(cbCosmetician);

        this.add(new JLabel());
        this.add(btnOk, "split 2");
        this.add(btnCancel);

    }

    public void treatment(){
        try{
            this.treatment.setId(Integer.valueOf(txtId.getText()));
            this.treatment.setName(txtName.getText());
            this.treatment.setType((TreatmentType) cbType.getSelectedItem());
            this.treatment.setPrice(Double.valueOf(txtPrice.getText()));
            this.treatment.setDuration(Integer.valueOf(txtDuration.getText()));

            String chosenCosmUsername = (String) cbCosmetician.getSelectedItem();
            Cosmetician cosmetician1 = mainRepository.getCosmeticianRepository().GetCosmeticianByUsername(chosenCosmUsername);
            this.treatment.setComesticians(Collections.singletonList(cosmetician1.getId()));

            AddNewTreatmentWindow.this.mainService.getMenagerService().addTreatment(treatment);
            this.setVisible(false);
            JOptionPane.showMessageDialog(null, "You have succesfully added treatment", "Succes", JOptionPane.INFORMATION_MESSAGE);
            CRUDTreatmentsWindow allWorkers = new CRUDTreatmentsWindow(mainRepository, mainService, new Menager());
            allWorkers.setVisible(true);


        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Wrong data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void actions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                treatment();
                AddNewTreatmentWindow.this.dispose();
                AddNewTreatmentWindow.this.setVisible(false);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewTreatmentWindow.this.mainService.getMenagerService().addTreatment(treatment);
                AddNewTreatmentWindow.this.dispose();
                AddNewTreatmentWindow.this.setVisible(false);

            }
        });
    }
}
