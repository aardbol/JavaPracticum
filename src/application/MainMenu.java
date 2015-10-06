package application;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu {
	public static void addComponentsToPane(Container pane) {
		
		JPanel labelPanel = new JPanel();
		addLabelsToPane(labelPanel);
		labelPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		pane.add(labelPanel, BorderLayout.PAGE_START);

		JPanel buttonPanel = new JPanel();
		addButtonsToPane(buttonPanel);
		buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		pane.add(buttonPanel, BorderLayout.PAGE_END);
	}

	public static void addLabelsToPane(Container pane) {
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		JLabel emptyLabel = new JLabel("Huisjes: 107");
		// emptyLabel.setPreferredSize(new Dimension(175, 100));
		pane.add(emptyLabel, BorderLayout.CENTER);
		pane.add(Box.createRigidArea(new Dimension(0, 8)));
		pane.add(new JLabel("reservaties: 0"), BorderLayout.CENTER);
	}

	public static void addButtonsToPane(Container pane) {
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
	
		//pane.setLayout(new BorderLayout(4,0));

		addAButton("Nieuwe reservatie", true, pane);
		pane.add(Box.createRigidArea(new Dimension(4, 0)));
		addAButton("Filter reservaties", false, pane);
		pane.add(Box.createRigidArea(new Dimension(4, 0)));
		addAButton("Reservatie annuleren", false, pane);
	}

	private static void addAButton(String text, boolean enabled, Container container) {
		JButton button = new JButton(text);
		button.setEnabled(enabled);
		//button.setAlignmentX(Component.CENTER_ALIGNMENT);
		// button.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		container.add(button);
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Vakantiehuisjes reserveren");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		addComponentsToPane(frame.getContentPane());

		// Display the window.
		 frame.pack();
		 
		//frame.setSize(400, 200);
		frame.setLocationRelativeTo(null);
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
