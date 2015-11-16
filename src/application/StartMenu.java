package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartMenu window = new StartMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 419, 244);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlMain = new JPanel();
		frame.getContentPane().add(pnlMain);
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		pnlMain.add(splitPane);
		
		JPanel pnlTitels = new JPanel();
		splitPane.setLeftComponent(pnlTitels);
		pnlTitels.setLayout(new BoxLayout(pnlTitels, BoxLayout.Y_AXIS));
		
		JLabel lblHouses = new JLabel("Houses:");
		pnlTitels.add(lblHouses);
		
		JLabel lblReservations = new JLabel("Reservations:");
		pnlTitels.add(lblReservations);
		
		JPanel pnlValues = new JPanel();
		splitPane.setRightComponent(pnlValues);
		pnlValues.setLayout(new BoxLayout(pnlValues, BoxLayout.Y_AXIS));
		
		JLabel lblValueHouses = new JLabel("value");
		pnlValues.add(lblValueHouses);
		
		JLabel lblValueReservations = new JLabel("value");
		pnlValues.add(lblValueReservations);
		
		JPanel pnlButtons = new JPanel();
		frame.getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		pnlButtons.setLayout(new GridLayout(0, 3, 0, 0));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		pnlButtons.add(horizontalStrut);
		
		JButton btnAdd = new JButton("Add reservation");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: add what to do when the button is pressed
	        	 //JOptionPane.showMessageDialog(null, "TODO!");
				AddReservation wnd = new AddReservation();
				wnd.setVisible(true);
			}
		});
		pnlButtons.add(btnAdd);
		
		JButton btnOverview = new JButton("Overview");
		pnlButtons.add(btnOverview);
	}

}