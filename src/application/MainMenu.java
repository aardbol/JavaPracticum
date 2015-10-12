package application;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainMenu extends JFrame{
	
	RegistrationManager manager;
	
	public MainMenu(String string) {
		// super set Title constructor
		super(string);
		
		// create the registrationManager
		manager = new RegistrationManager();

		// set up window/frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set up the contents pane.
		addComponentsToPane(this.getContentPane());

		// Resize frame & components to fit.
		this.pack();
	}

	private void addComponentsToPane(Container pane) {
		
		JPanel labelPanel = new JPanel();
		addLabelsToPane(labelPanel);
		labelPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		pane.add(labelPanel, BorderLayout.PAGE_START);

		JPanel buttonPanel = new JPanel();
		addButtonsToPane(buttonPanel);
		buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		pane.add(buttonPanel, BorderLayout.PAGE_END);
	}

	private void addLabelsToPane(Container pane) {
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		JLabel emptyLabel = new JLabel("Huisjes: 107");
		// emptyLabel.setPreferredSize(new Dimension(175, 100));
		pane.add(emptyLabel, BorderLayout.CENTER);
		pane.add(Box.createRigidArea(new Dimension(0, 8)));
		pane.add(new JLabel("reservaties: 0"), BorderLayout.CENTER);
	}

	private void addButtonsToPane(Container pane) {
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
	
		//pane.setLayout(new BorderLayout(4,0));

		JButton btnNewResevation = addAButton("Nieuwe reservatie", true, pane);
		btnNewResevation.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	             // TODO: add what to do when the button is pressed
	        	 JOptionPane.showMessageDialog(null, "TODO!");
	        	 // openen venster met de nodige invoer velden
	        	 
	        	 // toevoegen registratie als het toevoegen niet geannuleerd werdt
	        	 
	        	 // indien geen exception werdt opgeworpen: registraties wegschrijven naar bestand
	          }    
		});
		pane.add(Box.createRigidArea(new Dimension(4, 0)));
		addAButton("Filter reservaties", false, pane);
		pane.add(Box.createRigidArea(new Dimension(4, 0)));
		addAButton("Reservatie annuleren", false, pane);
	}

	private JButton addAButton(String text, boolean enabled, Container container) {
		JButton button = new JButton(text);
		button.setEnabled(enabled);
		//button.setAlignmentX(Component.CENTER_ALIGNMENT);
		// button.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		container.add(button);
		return button;
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new MainMenu("Vakantiehuisjes reserveren");
		
		// Center window on screen
		frame.setLocationRelativeTo(null);
		
		// Show
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
