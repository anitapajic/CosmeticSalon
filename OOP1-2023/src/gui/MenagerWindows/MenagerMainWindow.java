package gui.MenagerWindows;

import model.Menager;
import repository.MainRepository;

import javax.swing.*;

public class MenagerMainWindow extends JFrame {
    MainRepository mainRepository;
    Menager menager;

    public MenagerMainWindow(){}

    public MenagerMainWindow(MainRepository mainRepository, Menager menager){
        this.mainRepository = mainRepository;
        this.menager = menager;
    }
}
