package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Overview_Wnd extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Overview_Wnd( RegistrationManager registrationManager ) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		DefaultListModel model = new DefaultListModel();
		JList list = new JList(model);
		
		List<Registration> registrations = registrationManager.getAllRegistrations();
		for( Registration reg : registrations )
		{
			model.addElement(reg);
		}
		contentPane.add(list, BorderLayout.CENTER);
		
		JPanel pblButtons = new JPanel();
		contentPane.add(pblButtons, BorderLayout.SOUTH);
		pblButtons.setLayout(new GridLayout(1, 0, 0, 0));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		pblButtons.add(horizontalStrut);
		
		JButton button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "TODO:");
			}
		});
		pblButtons.add(button);
		
		JButton button_1 = new JButton("Close");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		pblButtons.add(button_1);
		
	}

}
