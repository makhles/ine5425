package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class Simulator extends JFrame {

	private static final long serialVersionUID = 1L;
    private static JButton btnRun;
    private static JTextField tfSteps;
    private static JTextField tfReplications;

	private static void startNewSimulation() {
	    System.out.println("Starting a new simulation...");
	}

	private static void createActionListeners() {
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				startNewSimulation();
			}
		});
	}

    public static void addComponentsToPane(Container pane) {
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
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
        createActionListeners();

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
