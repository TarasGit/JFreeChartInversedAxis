package jFree;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class jFree {
	private static final int COUNT = 100;
    private static final int UNITS = 5;
    private static final Random r = new Random();

    public static void main(String[] args) {
        XYSeries series = new XYSeries("Data");
//        for (int i = 0; i < COUNT; i++) {
//            series.add(i, r.nextGaussian());
//        }
        series.add(2,2);
        series.add(3,3);
        XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart("TickUnits",
            "X", "Y", data, PlotOrientation.VERTICAL, true, true, false);

        chart.getXYPlot().getDomainAxis().setInverted(true);//invert x-axis
        
        XYPlot plot = (XYPlot) chart.getPlot();
        final NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setTickUnit(new NumberTickUnit(UNITS));
        EventQueue.invokeLater(new Runnable() {
      
        	
            public void run() {
                JFrame f = new JFrame("TickUnitDemo");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(new ChartPanel(chart));
                final JSpinner spinner = new JSpinner(
                    new SpinnerNumberModel(UNITS, 1, COUNT, 1));
                spinner.addChangeListener(new ChangeListener() {


                    public void stateChanged(ChangeEvent e) {
                        JSpinner s = (JSpinner) e.getSource();
                        Number n = (Number) s.getValue();
                        xAxis.setTickUnit(new NumberTickUnit(n.intValue()));
                    }
                });

                JPanel p = new JPanel();
                p.add(new JLabel(chart.getTitle().getText()));
                p.add(spinner);
                f.add(p, BorderLayout.SOUTH);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }
}
