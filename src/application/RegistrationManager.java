package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import model.datum.Datum;

public class RegistrationManager {
	private List<Registration> registrations;
	
	/**
	 * 
	 */
	public RegistrationManager()
	{
		registrations = new Vector<Registration>();
		laadRegistratiesVanBestand();
	}
	
	/**
	 * 
	 * @param registration
	 */
	public void addRegistration(Registration registration)
	{
		// TODO: check if registration is valid: Throw exception
			// registraties van hetzelfde huisnr mogen niet overlappen in tijd
		
		// add registration to list
		registrations.add(registration);
	}
	
	public String getCountString()
	{
		return Integer.toString(registrations.size());
	}
	
	public List<Registration> getAllRegistrations()
	{
		return registrations;
	}
	
	/**
	 * Laad de registraties in de variabele
	 */
	public void laadRegistratiesVanBestand()
	{
	  File file = new File("reservering.txt");
	  
	  if (!file.exists())
	  {
		  return;
	  }
	  
	  try {
		Scanner scanner = new Scanner(file);
		
		while (scanner.hasNext())
		{
			String lijn = scanner.nextLine();
			
			// Leeg bestand
			if (lijn.isEmpty())
			{
				break;
			}
			
			String persoon = lijn.split(">")[1];
			String reservatie = lijn.split(">")[0];
			String voornaam = persoon.split(",")[1];
			String naam = persoon.split(",")[0];
			int huisnummer = Integer.parseInt(reservatie.split(",")[0]);
			Datum datum = new Datum(reservatie.split(",")[1]);
			int aantalNachten = Integer.parseInt(reservatie.split(",")[2]);
						
			registrations.add(new Registration(voornaam, naam, huisnummer, datum, aantalNachten));
		}
		
		if (scanner != null)
		{
			scanner.close();
		}
	  }
	  catch(Exception e) {
		  System.out.println(e.getMessage());
	  }
	}
	
	/**
	 * Gegevens opslaan in bestand
	 */
	public boolean opslaanRegistraties()
	{
        try {
			PrintWriter OpslaanInBestand = new PrintWriter("reservering.txt");
	        
	        for (int n = 0; n < registrations.size(); n++)
	        {
	        	OpslaanInBestand.println(registrations.get(n).formaatVoorBestandLijn());
	        }
	        OpslaanInBestand.close();
	        
	        return true;
        }
        catch (IOException e) {
            System.out.println(e.toString());
            return false;
        }
	}
	
	/**
	 * 
	 * @param index
	 */
	public void verwijderRegistratie(int index)
	{
		registrations.remove(index);
	}
}
