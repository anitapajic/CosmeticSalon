package gui.MenagerWindows.Charts;

import org.knowm.xchart.*;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.*;

public class IncomeOutcomeMonthly extends JFrame {
    private double income;
    private double outcome;
    private int month;

    public IncomeOutcomeMonthly(double income, double outcome, int month){
        this.income = income;
        this.outcome = outcome;
        this.month = month;
        chart();
    }
    private void chart(){
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Income and outcome for " + month + "th month").build();

        Color[] sliceColors = new Color[] { new Color(114, 180, 235), new Color(173, 36, 107) };
        chart.getStyler().setSeriesColors(sliceColors);

        chart.addSeries("Income", this.income);
        chart.addSeries("Outcome", this.outcome);



        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setDefaultSeriesRenderStyle(PieSeries.PieSeriesRenderStyle.Pie);

        JPanel chartPnl = new XChartPanel<PieChart>(chart);
        this.getContentPane().add(chartPnl, BorderLayout.CENTER);

        pack();
    }
}
