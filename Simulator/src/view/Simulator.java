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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

public class Simulator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem miNew;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblSteps;
	private JPanel panel_2;
	private JLabel lblReplications;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;

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
		setResizable(false);
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

//		JPanel inputPanel = new JPanel(new SpringLayout());
//		JLabel lblSteps = new JLabel("Steps", JLabel.TRAILING);
//		JLabel lblReplications = new JLabel("Replications", JLabel.TRAILING);
//		JTextField tfSteps = new JTextField(10);
//		JTextField tfReplications = new JTextField(10);
//		lblSteps.setLabelFor(tfSteps);
//		lblReplications.setLabelFor(tfReplications);
//		inputPanel.add(lblSteps);
//		inputPanel.add(tfSteps);
//		inputPanel.add(lblReplications);
//		inputPanel.add(tfReplications);
//		SpringUtilities.makeCompactGrid(inputPanel, 2, 2, 6, 6, 6, 6);
//
//		JPanel outputPanel = new JPanel();
//		outputPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		outputPanel.setPreferredSize(new Dimension(100, 270));
//
//		JPanel sidePanel = new JPanel();
//		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
//		sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//		sidePanel.add(inputPanel);
//		sidePanel.add(outputPanel);
//
//		JPanel chartPanel = new JPanel();
//		chartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		chartPanel.setPreferredSize(new Dimension(300, 300));
//		chartPanel.setVisible(true);
//
//		contentPane.add(sidePanel, BorderLayout.LINE_START);
//		contentPane.add(chartPanel, BorderLayout.LINE_END);

		revalidate();
		repaint();
	}

	private void createContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setVisible(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Input", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 188, 127);
		contentPane.add(panel);
		
		lblSteps = new JLabel("Steps:");
		
		lblReplications = new JLabel("Replications:");
		
		JButton btnNewButton = new JButton("Run");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblReplications)
								.addComponent(lblSteps))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSteps)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReplications)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(212, 12, 494, 409);
		contentPane.add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(12, 151, 188, 270);
		contentPane.add(panel_2);
		
		lblNewLabel = new JLabel("Nothing to show.");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNewLabel)
					.addContainerGap(212, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
	}

	private void createAndShowWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(720, 480));
		pack();
		setLocationRelativeTo(null);
	}
}
