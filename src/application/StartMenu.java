package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartMenu {

	private JFrame frame;
	private RegistrationManager manager;

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
		manager = new RegistrationManager();
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

		JLabel lblValueHouses = new JLabel("107");
		pnlValues.add(lblValueHouses);

		JLabel lblValueReservations = new JLabel(Integer.toString(manager.getCount()));
		pnlValues.add(lblValueReservations);

		JPanel pnlButtons = new JPanel();
		frame.getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		pnlButtons.setLayout(new GridLayout(0, 3, 0, 0));

		Component horizontalStrut = Box.createHorizontalStrut(20);
		pnlButtons.add(horizontalStrut);

		JButton btnAdd = new JButton("Add reservation");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddRegistration_Wnd wnd = new AddRegistration_Wnd();
				wnd.setModal(true);
				wnd.setVisible(true);
				Registration reg = wnd.getRegistration();
				if (reg != null) {
					manager.addRegistration(reg);
					lblValueReservations.setText(Integer.toString(manager.getCount()));
				}
			}
		});
		pnlButtons.add(btnAdd);

		JButton btnOverview = new JButton("Overview");
		btnOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Overview_Wnd wnd = new Overview_Wnd(manager);
				wnd.setVisible(true);
			}
		});
		pnlButtons.add(btnOverview);
	}

}
