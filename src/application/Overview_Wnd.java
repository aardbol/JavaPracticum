package application;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Overview_Wnd extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Overview_Wnd(RegistrationManager registrationManager)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		DefaultListModel<Registration> model = new DefaultListModel<Registration>();
		JList<Registration> lijst = new JList<Registration>(model);
		
		List<Registration> registrations = registrationManager.getAllRegistrations();
		for( Registration reg : registrations )
		{
			model.addElement(reg);
		}
		contentPane.add(lijst, BorderLayout.CENTER);
		
		JPanel pblButtons = new JPanel();
		contentPane.add(pblButtons, BorderLayout.SOUTH);
		pblButtons.setLayout(new GridLayout(1, 0, 0, 0));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		pblButtons.add(horizontalStrut);
		
		JButton button = new JButton("Verwijder");
		button.addActionListener(e -> {
			for (int lijstIndex : lijst.getSelectedIndices())
			{
				model.remove(lijstIndex);
				registrationManager.verwijderRegistratie(lijstIndex);
				StartMenu.lblValueReservations.setText(registrationManager.getCountString());
			}
			JOptionPane.showMessageDialog(null, "De geselecteerde registratie(s) is/zijn verwijderd");
		});
		pblButtons.add(button);
		
		JButton button_1 = new JButton("Sluiten");
		button_1.addActionListener(e -> {
				setVisible(false);
		});
		pblButtons.add(button_1);
	}
}
