package application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import model.datum.Datum;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddRegistration_Wnd extends JDialog {
	private Registration myRegistration;
	private JTextField txtVoornaam;
	private JTextField txtHuisNr;
	private JTextField txtStartDatum;
	private JTextField txtAantalNachten;
	private JTextField txtFamilienaam;

	public Registration getRegistration()
	{
		return myRegistration;
	}

	/**
	 * Create the dialog.
	 */
	public AddRegistration_Wnd()
	{
		myRegistration = null;
		setBounds(100, 100, 452, 203);
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
					JLabel lblStartDatum = new JLabel("Start datum (dd/mm/yyyy)");
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
				okButton.addActionListener(e ->
				{
					if (txtVoornaam.getText().length() == 0) 
					{
						JOptionPane.showMessageDialog(null, "Voornaam is verplicht.");
						return;
					}
					else if (txtFamilienaam.getText().length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Familienaam is verplicht.");
						return;
					}
					else if (txtHuisNr.getText().length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Huis nr. is verplicht.");
						return;
					}
					else if (txtStartDatum.getText().length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Start datum is verplicht.");
						return;
					}
					else if (txtAantalNachten.getText().length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Aantal verblijvende nachten is verplicht.");
						return;
					}
					
					try {
						myRegistration = new Registration(txtVoornaam.getText(), txtFamilienaam.getText(),
								Integer.parseInt(txtHuisNr.getText()), new Datum(txtStartDatum.getText()),
								Integer.parseInt(txtAantalNachten.getText()));
						setVisible(false);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
						ex.printStackTrace();
					}
				});
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annuleer");
				cancelButton.addActionListener(e -> {
						setVisible(false);
				});
				cancelButton.setActionCommand("Annuleer");
				buttonPane.add(cancelButton);
			}
		}
	}
}
