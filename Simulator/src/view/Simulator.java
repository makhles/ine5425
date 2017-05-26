package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class Simulator extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnRun;
    private JTextField tfSteps;
    private JTextField tfReplications;

	private void checkValuesAndStartNewSimulation() {
	    int steps = 0;
	    int replications = 0;
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
	                if (replications < 0) {
	                    sb.append("The number of replications must be an integer between 0 and " + Integer.MAX_VALUE + ".\n");
	                }
	            }
            } catch (NumberFormatException e) {
                sb.append("Only integers between 0 and " + Integer.MAX_VALUE + "are permitted.");
            }
        }
	    
        String message = sb.toString();
	    if (message != null && message.length() > 0) {
	        JOptionPane.showMessageDialog(null, message, "Erros encontrados", JOptionPane.ERROR_MESSAGE);
	    } else {
	        System.out.println("Starting a new simulation...");
	        System.out.println("Steps.......: " + steps);
	        System.out.println("Replications: " + replications);
	    }
	}

	private void createActionListeners() {
	    btnRun.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	            checkValuesAndStartNewSimulation();
	        }
	    });
	}

    public void addComponentsToPane(Container pane) {
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(new TitledBorder("Charts"));
        rightPanel.setPreferredSize(new Dimension(500, 300));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel inputPanel = new JPanel(new MigLayout("wrap 2", "[r][l]", "[c][c][c]"));
        inputPanel.setBorder(new TitledBorder("Input"));
        tfSteps = new JTextField(10);
        tfReplications = new JTextField(10);
        btnRun = new JButton("Run");
        inputPanel.add(new JLabel("Steps:"));
        inputPanel.add(tfSteps);
        inputPanel.add(new JLabel("Replications:"));
        inputPanel.add(tfReplications);
        inputPanel.add(btnRun, "span,growx");

        JPanel outputPanel = new JPanel(new MigLayout("wrap 2"));
        outputPanel.setBorder(new TitledBorder("Output"));

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
