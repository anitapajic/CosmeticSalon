package gui.RecepcionistWindows;

import model.Receptionist;
import repository.MainRepository;

import javax.swing.*;

public class RecepcionistMainWindow extends JFrame {
    MainRepository mainRepository;
    Receptionist receptionist;

    public RecepcionistMainWindow(){}

    public RecepcionistMainWindow(MainRepository mainRepository, Receptionist receptionist){
        this.mainRepository = mainRepository;
        this.receptionist = receptionist;
    }
}
