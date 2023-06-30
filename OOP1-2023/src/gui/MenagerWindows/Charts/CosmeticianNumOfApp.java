package gui.MenagerWindows.Charts;

import model.Appointment;
import model.Cosmetician;
import model.Enum.TreatmentStatus;
import model.Enum.TreatmentType;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CosmeticianNumOfApp extends JFrame {

    private MainRepository mainRepository;
    private MainService mainService;
    public CosmeticianNumOfApp(MainRepository mainRepository, MainService mainService){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        chart();
    }
    private void chart(){
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Number of appointments for each cosmetician").build();

        Color[] sliceColors = new Color[] { new Color(114, 180, 235), new Color(173, 36, 107), new Color(34, 139, 34), new Color(255, 140, 0) };
        chart.getStyler().setSeriesColors(sliceColors);

        for(Cosmetician c : mainRepository.getCosmeticianRepository().getCosmeticians()){
            chart.addSeries(c.getUsername() + " = " + numApp(c), numApp(c));
        }

        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setDefaultSeriesRenderStyle(PieSeries.PieSeriesRenderStyle.Pie);

        JPanel chartPnl = new XChartPanel<PieChart>(chart);
        this.getContentPane().add(chartPnl, BorderLayout.CENTER);

        pack();
    }

    private int numApp(Cosmetician cosmetician){
        int num = 0;
        for(Appointment app : mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getCosmeticianId().equals(cosmetician.getId()) && app.getStatus().equals(TreatmentStatus.ACCOMPLISHED)){
                if((LocalDateTime.now().getDayOfYear() - app.getStartTime().getDayOfYear()) <= 30){
                    num += 1;
                }
            }
        }
        return num;
    }
}
