package gui.MenagerWindows.Charts;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.*;

public class IncomeOutcomeThisYear extends JFrame{
        private double income;
        private double outcome;

        public IncomeOutcomeThisYear(double income, double outcome){
            this.income = income;
            this.outcome = outcome;

            chart();
        }

        private void chart(){
            PieChart chart = new PieChartBuilder().width(800).height(600).title("Income and outcome for this year").build();

            Color[] sliceColors = new Color[] { new Color(114, 180, 235), new Color(173, 36, 107) };
            chart.getStyler().setSeriesColors(sliceColors);

            chart.addSeries("Income = " + this.income, this.income);
            chart.addSeries("Outcome = " + this.outcome, this.outcome);



            chart.getStyler().setChartTitleVisible(true);
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
            chart.getStyler().setDefaultSeriesRenderStyle(PieSeries.PieSeriesRenderStyle.Pie);

            JPanel chartPnl = new XChartPanel<PieChart>(chart);
            this.getContentPane().add(chartPnl, BorderLayout.CENTER);

            pack();
        }
}
