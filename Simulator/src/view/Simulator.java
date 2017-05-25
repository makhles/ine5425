package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class Simulator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem miNew;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simulator frame = new Simulator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Simulator() {
		createContentPane();
		createMenuBar();
		createActionListeners();
		createAndShowWindow();
	}

	private void createActionListeners() {
		miNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				startNewSimulation();
			}
		});
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		miNew = new JMenuItem("New simulation");
		fileMenu.add(miNew);

		ExitAction exitAction = new ExitAction();
		exitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));

		fileMenu.add(new JMenuItem(exitAction));
		menuBar.add(fileMenu);
		
	}
	
	private void startNewSimulation() {
//		JPanel inputPanel = new JPanel();
//		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
//		LabeledTextInput stepsPanel = new LabeledTextInput("Steps", "10");
//		LabeledTextInput replicationsPanel = new LabeledTextInput("Replications", "0");
//		inputPanel.add(stepsPanel);
//		inputPanel.add(replicationsPanel);
		JPanel inputPanel = new JPanel(new SpringLayout());
		JLabel lblSteps = new JLabel("Steps", JLabel.TRAILING);
		JLabel lblReplications = new JLabel("Replications", JLabel.TRAILING);
		JTextField tfSteps = new JTextField(10);
		JTextField tfReplications = new JTextField(10);
		lblSteps.setLabelFor(tfSteps);
		lblReplications.setLabelFor(tfReplications);
		inputPanel.add(lblSteps);
		inputPanel.add(tfSteps);
		inputPanel.add(lblReplications);
		inputPanel.add(tfReplications);
		SpringUtilities.makeCompactGrid(inputPanel, 2, 2, 6, 6, 6, 6);

		JPanel outputPanel = new JPanel();
		outputPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		outputPanel.setPreferredSize(new Dimension(100, 270));

		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		sidePanel.add(inputPanel);
		sidePanel.add(outputPanel);

		JPanel chartPanel = new JPanel();
		chartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		chartPanel.setPreferredSize(new Dimension(300, 300));
		chartPanel.setVisible(true);

		contentPane.add(sidePanel, BorderLayout.LINE_START);
		contentPane.add(chartPanel, BorderLayout.LINE_END);

		revalidate();
		repaint();
	}

	private void createContentPane() {
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setVisible(true);
		setContentPane(contentPane);
	}

	private void createAndShowWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 480));
		pack();
		setLocationRelativeTo(null);
	}
}
