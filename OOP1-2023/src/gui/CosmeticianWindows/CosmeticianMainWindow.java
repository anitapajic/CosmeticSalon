package gui.CosmeticianWindows;

import model.Cosmetician;
import repository.MainRepository;

import javax.swing.*;

public class CosmeticianMainWindow extends JFrame {

    MainRepository mainRepository;
    Cosmetician cosmetician;

    public CosmeticianMainWindow(){}

    public CosmeticianMainWindow(MainRepository mainRepository, Cosmetician cosmetician){
        this.cosmetician = cosmetician;
        this.mainRepository = mainRepository;
    }

}
