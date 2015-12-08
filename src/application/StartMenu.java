package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;

public class StartMenu {

	private JFrame frame;
	private RegistrationManager manager;
	public static JLabel lblValueReservations;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
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
	public StartMenu()
	{
		manager = new RegistrationManager();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 419, 244);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		// Bij het sluiten registraties opslaan
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				manager.opslaanRegistraties();
			}
		});

		JPanel pnlMain = new JPanel();
		frame.getContentPane().add(pnlMain);
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));

		JSplitPane splitPane = new JSplitPane();
		pnlMain.add(splitPane);

		JPanel pnlTitels = new JPanel();
		splitPane.setLeftComponent(pnlTitels);
		pnlTitels.setLayout(new BoxLayout(pnlTitels, BoxLayout.Y_AXIS));

		JLabel lblHouses = new JLabel("Huizen:");
		pnlTitels.add(lblHouses);

		JLabel lblReservations = new JLabel("Reservaties:");
		pnlTitels.add(lblReservations);

		JPanel pnlValues = new JPanel();
		splitPane.setRightComponent(pnlValues);
		pnlValues.setLayout(new BoxLayout(pnlValues, BoxLayout.Y_AXIS));

		JLabel lblValueHouses = new JLabel("107");
		pnlValues.add(lblValueHouses);

		lblValueReservations = new JLabel(manager.getCountString());
		pnlValues.add(lblValueReservations);

		JPanel pnlButtons = new JPanel();
		frame.getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		pnlButtons.setLayout(new GridLayout(0, 3, 0, 0));

		Component horizontalStrut = Box.createHorizontalStrut(20);
		pnlButtons.add(horizontalStrut);

		JButton btnAdd = new JButton("Reservatie Tvg");
		btnAdd.addActionListener(arg0 -> {
				AddRegistration_Wnd wnd = new AddRegistration_Wnd();
				wnd.setModal(true);
				wnd.setVisible(true);
				Registration reg = wnd.getRegistration();
				
				if (reg != null)
				{
					manager.addRegistration(reg);
					lblValueReservations.setText(manager.getCountString());
				}
		});
		pnlButtons.add(btnAdd);

		JButton btnOverview = new JButton("Overzicht");
		btnOverview.addActionListener(e -> {
				Overview_Wnd wnd = new Overview_Wnd(manager);
				wnd.setVisible(true);
			});
		pnlButtons.add(btnOverview);
	}

}
