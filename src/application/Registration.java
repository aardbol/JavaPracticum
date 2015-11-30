package application;

import model.datum.Datum;

public class Registration {
	// wie
	String voornaam;
	String familienaam;
	// waar / wat
	int huisnummer; // rijnummer + volgnummer
	// wanneer
	Datum startDatum;
	int aantalVerblijfNachten;
	
	public Registration( String voornaam, String familienaam, int huisnummer, Datum startDatum, int aantalVerblijfNachten )
	{
		// TODO: check valid values: Throw exception
			// Datum > Now 
			// String != null | empty 
			// aantalVerblijfNachten > 0
		
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.huisnummer = huisnummer;
		this.startDatum = startDatum;
		this.aantalVerblijfNachten = aantalVerblijfNachten;
	}
	
	@Override
	public String toString()
	{
		return voornaam + " " + familienaam + " " + huisnummer + " " + startDatum.toString() + " " + aantalVerblijfNachten;
	}
}
