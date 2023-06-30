package gui.MenagerWindows.Charts;

import model.Appointment;
import model.Cosmetician;
import model.Enum.TreatmentStatus;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import java.awt.*;

public class NumOfAppStatus extends JFrame {
    private MainRepository mainRepository;
    private MainService mainService;
    public NumOfAppStatus(MainRepository mainRepository, MainService mainService){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        chart();
    }
    private void chart(){
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Percentage of treatment types").build();

        Color[] sliceColors = new Color[] { new Color(114, 180, 235), new Color(173, 36, 107), new Color(34, 139, 34), new Color(255, 140, 0), new Color(148, 0, 211) };
        chart.getStyler().setSeriesColors(sliceColors);

        chart.addSeries("SCHEDULED", scheduled());
        chart.addSeries("ACCOMPLISHED", accomplished());
        chart.addSeries("CANCELED BY CLIENT" , canceledClient());
        chart.addSeries("CANCELED BY SALON", canceledSalon());
        chart.addSeries("DIDN'T SHOW UP", didntShowUp());

        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setDefaultSeriesRenderStyle(PieSeries.PieSeriesRenderStyle.Pie);

        JPanel chartPnl = new XChartPanel<PieChart>(chart);
        this.getContentPane().add(chartPnl, BorderLayout.CENTER);

        pack();
    }

    private int scheduled(){
        int num = 0;
        for(Appointment app : mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getStatus().equals(TreatmentStatus.SCHEDULED)){
                num += 1;
            }
        }
        return num;
    }
    private int accomplished(){
        int num = 0;
        for(Appointment app : mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getStatus().equals(TreatmentStatus.ACCOMPLISHED)){
                num += 1;
            }
        }
        return num;
    }
    private int canceledClient(){
        int num = 0;
        for(Appointment app : mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getStatus().equals(TreatmentStatus.CANCELED_BY_CLIENT)){
                num += 1;
            }
        }
        return num;
    }
    private int canceledSalon(){
        int num = 0;
        for(Appointment app : mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getStatus().equals(TreatmentStatus.CANCELED_BY_SALON)){
                num += 1;
            }
        }
        return num;
    }
    private int didntShowUp(){
        int num = 0;
        for(Appointment app : mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getStatus().equals(TreatmentStatus.DIDNT_SHOW_UP)){
                num += 1;
            }
        }
        return num;
    }
    private int total(){
        int num = 0;
        for(Appointment app : mainRepository.getAppointmentRepository().getAppointments()){
            num += 1;
        }
        return num;
    }

}
