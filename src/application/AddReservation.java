package application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class AddReservation extends JDialog {
	private JTextField txtVoornaam;
	private JTextField txtHuisNr;
	private JTextField txtStartDatum;
	private JTextField txtAantalNachten;
	private JTextField txtFamilienaam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddReservation dialog = new AddReservation();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddReservation() {
		setBounds(100, 100, 450, 225);
		getContentPane().setLayout(new BorderLayout());
		{
			JSplitPane splitPane = new JSplitPane();
			getContentPane().add(splitPane, BorderLayout.NORTH);
			{
				JPanel pnlTitels = new JPanel();
				splitPane.setLeftComponent(pnlTitels);
				pnlTitels.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JLabel lblVoornaam = new JLabel("Voornaam");
					pnlTitels.add(lblVoornaam);
				}
				{
					JLabel lblFamilienaam = new JLabel("Familienaam");
					pnlTitels.add(lblFamilienaam);
				}
				{
					JLabel lblHuisNr = new JLabel("Huis nr.");
					pnlTitels.add(lblHuisNr);
				}
				{
					JLabel lblStartDatum = new JLabel("Start datum (dd-mm-yy)");
					pnlTitels.add(lblStartDatum);
				}
				{
					JLabel lblAantalNachten = new JLabel("Aantal verblijvende nachten");
					pnlTitels.add(lblAantalNachten);
				}
			}
			{
				JPanel pnlValues = new JPanel();
				splitPane.setRightComponent(pnlValues);
				pnlValues.setLayout(new GridLayout(0, 1, 0, 0));
				{
					txtVoornaam = new JTextField();
					pnlValues.add(txtVoornaam);
					txtVoornaam.setColumns(10);
				}
				{
					txtFamilienaam = new JTextField();
					pnlValues.add(txtFamilienaam);
					txtFamilienaam.setColumns(10);
				}
				{
					txtHuisNr = new JTextField();
					pnlValues.add(txtHuisNr);
					txtHuisNr.setColumns(10);
				}
				{
					txtStartDatum = new JTextField();
					pnlValues.add(txtStartDatum);
					txtStartDatum.setColumns(10);
				}
				{
					txtAantalNachten = new JTextField();
					pnlValues.add(txtAantalNachten);
					txtAantalNachten.setColumns(10);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
