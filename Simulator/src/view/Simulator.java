package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import controller.MonteCarloSimulation;
import model.Coord;
import net.miginfocom.swing.MigLayout;

public class Simulator extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnRun;
	private JButton btnWalk;
	private JButton btnDistance;
    private JTextField tfSteps;
    private JTextField tfReplications;
    private JPanel rightPanel;
    private MonteCarloSimulation simulation;

    private JFreeChart drunkWalkChart;
    private JFreeChart distanceComparisonChart;

    private void plotChart(JFreeChart chart) {
    	resetRightPanel();
    	ChartPanel chartPanel = new ChartPanel(chart);
        rightPanel.add(chartPanel, BorderLayout.CENTER);
        rightPanel.validate();
    }

    private void createChartForDistanceComparison() {
        List<Coord> data = simulation.getXYData();
        XYSeries estimatedDistanceSeries = new XYSeries("Estimated Distance", false);
        XYSeries walkedDistanceSeries = new XYSeries("Walked Distance", false);
        estimatedDistanceSeries.add(0.0d, 0.0d);
        walkedDistanceSeries.add(0.0d, 0.0d);
        int step = 0;
        double x = 0.0d;
        double y = 0.0d;
        for (Coord coord : data) {
        	x = coord.getX();
        	y = coord.getY();
        	estimatedDistanceSeries.add(step, Math.sqrt(step));
        	walkedDistanceSeries.add(step, Math.sqrt(x*x + y*y));
        	step++;
        }
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(estimatedDistanceSeries);
        collection.addSeries(walkedDistanceSeries);
        distanceComparisonChart = ChartFactory.createXYLineChart("Distance Comparison", "Steps", "Distance", collection);
    }

    private void createChartForDrunkWalk() {
        List<Coord> data = simulation.getXYData();
        XYSeries series = new XYSeries("Drunk Walk", false);
        series.add(0.0d, 0.0d);
        for (Coord coord : data) {
            series.add(coord.getX(), coord.getY());
        }
        drunkWalkChart = ChartFactory.createXYLineChart("Drunk Walk", "x", "y", new XYSeriesCollection(series));
    }

    private void checkValuesAndStartNewSimulation() {
	    int steps = 0;
	    int replications = 1;
	    StringBuilder sb = new StringBuilder();

	    String readSteps = tfSteps.getText();
        if (readSteps.equals("")) {
            sb.append("The number of steps must be an integer between 0 and " + Integer.MAX_VALUE + ".\n");
        } else {
            try {
	            steps = Integer.parseInt(readSteps);
	            if (steps <= 0) {
	                sb.append("The number of steps must be an integer between 0 and " + Integer.MAX_VALUE + ".\n");
	            }
	            String replicationsRead = tfReplications.getText();
	            if (!replicationsRead.equals("")) {
	                replications = Integer.parseInt(replicationsRead);
	                if (replications <= 0) {
	                    sb.append("The number of replications must be an integer between 0 and " + Integer.MAX_VALUE + ".\n");
	                }
	            }
            } catch (NumberFormatException e) {
                sb.append("Only integers between 0 and " + Integer.MAX_VALUE + "are permitted.");
            }
        }

        String message = sb.toString();
	    if (message != null && message.length() > 0) {
	        JOptionPane.showMessageDialog(null, message, "Ops!", JOptionPane.ERROR_MESSAGE);
	    } else {
	        System.out.println("    ===============================");
	        System.out.println("       Starting a new simulation");
	        System.out.println("    ===============================");
	        System.out.println();
	        System.out.println("Steps.......: " + steps);
	        System.out.println("Replications: " + replications);
	        System.out.println();

	        simulation = new MonteCarloSimulation(steps);
	        simulation.run(replications);
	        simulation.printReplications();

	        if (replications == 1) {
	            createChartForDrunkWalk();
	            createChartForDistanceComparison();
	        	btnWalk.setEnabled(true);
	            btnDistance.setEnabled(true);
	        } else {
	            btnWalk.setEnabled(false);
	            btnDistance.setEnabled(false);
	        }
	    }
	}
    
    private void resetRightPanel() {
        rightPanel.removeAll();
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void resetCharts() {
    	drunkWalkChart = null;
    	distanceComparisonChart = null;
    }

    private void resetButtons() {
        btnWalk.setEnabled(false);
        btnDistance.setEnabled(false);
    }

    private void createActionListeners() {
	    btnRun.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	            resetButtons();
	            resetCharts();
	            resetRightPanel();
	            checkValuesAndStartNewSimulation();
	        }
	    });
	    btnWalk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                plotChart(drunkWalkChart);
            }
        });
	    btnDistance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                plotChart(distanceComparisonChart);
            }
        });
	}

	public void addComponentsToPane(Container pane) {
        JPanel leftPanel = new JPanel();
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(new TitledBorder("Charts"));
        rightPanel.setPreferredSize(new Dimension(500, 300));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel inputPanel = new JPanel(new MigLayout("wrap 2", "[r][l]", "[c][c][c]"));
        inputPanel.setBorder(new TitledBorder("Input"));
        tfSteps = new JTextField(10);
        tfReplications = new JTextField(10);
        tfReplications.setText("1");
        btnRun = new JButton("Run");

        inputPanel.add(new JLabel("Steps:"));
        inputPanel.add(tfSteps);
        inputPanel.add(new JLabel("Replications:"));
        inputPanel.add(tfReplications);
        inputPanel.add(btnRun, "span,growx");

        JPanel outputPanel = new JPanel(new MigLayout("wrap 1"));
        outputPanel.setBorder(new TitledBorder("Output"));

        // Button for the XY walk plot
        btnWalk = new JButton("Show Drunk Walk");
        btnWalk.setEnabled(false);
        outputPanel.add(btnWalk, "span,growx");

        // Button for the distance comparison
        btnDistance = new JButton("Distance Chart");
        btnDistance.setEnabled(false);
        outputPanel.add(btnDistance, "span,growx");

        leftPanel.add(inputPanel);
        leftPanel.add(outputPanel);

        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(leftPanel);
        pane.add(rightPanel);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI() {
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        addComponentsToPane(frame.getContentPane());
        createActionListeners();

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Simulator() {
        createAndShowGUI();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Simulator();
            }
        });
    }
}
