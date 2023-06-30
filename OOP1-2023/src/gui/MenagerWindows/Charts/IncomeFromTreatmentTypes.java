package gui.MenagerWindows.Charts;

import model.Appointment;
import model.Enum.TreatmentStatus;
import model.Enum.TreatmentType;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import repository.MainRepository;
import service.MainService;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class IncomeFromTreatmentTypes extends JFrame {
    private MainRepository mainRepository;
    private MainService mainService;
    public IncomeFromTreatmentTypes(MainRepository mainRepository, MainService mainService){
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        chart();
    }
    private void chart(){
        List<Integer> xData = Arrays.asList(7, 6, 5, 4, 3, 2, 1, 12, 11, 10, 9, 8);
        List<Double> manicureData = Arrays.asList(getIncomeManicure(7), getIncomeManicure(6), getIncomeManicure(5), getIncomeManicure(4), getIncomeManicure(3), getIncomeManicure(2), getIncomeManicure(1), getIncomeManicure(12), getIncomeManicure(11), getIncomeManicure(10), getIncomeManicure(9), getIncomeManicure(8));
        List<Double> pedicureData = Arrays.asList(getIncomePedicure(7), getIncomePedicure(6), getIncomePedicure(5), getIncomePedicure(4), getIncomePedicure(3), getIncomePedicure(2), getIncomePedicure(1), getIncomePedicure(12), getIncomePedicure(11), getIncomePedicure(10), getIncomePedicure(9), getIncomePedicure(8));
        List<Double> massageData = Arrays.asList(getIncomeMassage(7), getIncomeMassage(6), getIncomeMassage(5), getIncomeMassage(4), getIncomeMassage(3), getIncomeMassage(2), getIncomeMassage(1), getIncomeMassage(12), getIncomeMassage(11), getIncomeMassage(10), getIncomeMassage(9), getIncomeMassage(8));
        List<Double> totalData = Arrays.asList(getIncomeFromMonth(7), getIncomeFromMonth(6), getIncomeFromMonth(5), getIncomeFromMonth(4), getIncomeFromMonth(3), getIncomeFromMonth(2), getIncomeFromMonth(1), getIncomeFromMonth(12), getIncomeFromMonth(11), getIncomeFromMonth(10), getIncomeFromMonth(9), getIncomeFromMonth(8));

        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Income for the year").xAxisTitle("Months").yAxisTitle("Income").build();

        // Add Series
        chart.addSeries("Manicure", xData, manicureData);
        chart.addSeries("Pedicure", xData, pedicureData);
        chart.addSeries("Massage", xData, massageData);
        chart.addSeries("Total", xData, totalData);

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        this.getContentPane().add(chartPanel, BorderLayout.CENTER);

        pack();

    }

    private double getIncomeFromMonth(int month){
        double income = 0;
        for(Appointment app: mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getStatus().equals(TreatmentStatus.ACCOMPLISHED) && (month==app.getStartTime().getMonthValue())){
                income += app.getPrice();
            }
        }
        return income;
    }

    private double getIncomeManicure(int month){
        double income = 0;
        for(Appointment app: mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getStatus().equals(TreatmentStatus.ACCOMPLISHED) && (month==app.getStartTime().getMonthValue()) && app.getType().equals(TreatmentType.MANICURE)){
                income += app.getPrice();
            }
        }
        return income;
    }
    private double getIncomePedicure(int month){
        double income = 0;
        for(Appointment app: mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getStatus().equals(TreatmentStatus.ACCOMPLISHED) && (month==app.getStartTime().getMonthValue()) && app.getType().equals(TreatmentType.PEDICURE)){
                income += app.getPrice();
            }
        }
        return income;
    }
    private double getIncomeMassage(int month){
        double income = 0;
        for(Appointment app: mainRepository.getAppointmentRepository().getAppointments()){
            if(app.getStatus().equals(TreatmentStatus.ACCOMPLISHED) && (month==app.getStartTime().getMonthValue()) && app.getType().equals(TreatmentType.MASSAGE)){
                income += app.getPrice();
            }
        }
        return income;
    }
}
