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

	public Registration(String voornaam, String familienaam, int huisnummer, Datum startDatum,
			int aantalVerblijfNachten) throws Exception
	{		
		// Eerst wat validatie. De datum wordt in de Datum klasse gevalideerd, hier wordt datum > nu gecheckt.
		if (voornaam == null || !voornaam.chars().allMatch(letter -> Character.isLetter(letter)))
		{
			throw new Exception("De voornaam mag enkel letters bevatten");
		}
		else if (familienaam == null || !familienaam.chars().allMatch(letter -> Character.isLetter(letter)))
		{
			throw new Exception("De familienaam mag enkel letters bevatten");
		}
		else if (huisnummer < 1 || huisnummer > 107)
		{
			throw new Exception("De huisnummer moet een getal tussen 1 en 107 zijn");
		}
		else if (((new Datum()).kleinerDan(startDatum)))
		{
			throw new Exception("De datum mag niet kleiner dan de huidige datum zijn silly!");
		}
		else if (aantalVerblijfNachten < 1)
		{
			throw new Exception("Minder dan 1 nacht verblijven? Dat gaat toch niet!!!");
		}
		
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.huisnummer = huisnummer;
		this.startDatum = startDatum;
		this.aantalVerblijfNachten = aantalVerblijfNachten;
	}
	
	/**
	 * 
	 * @return
	 */
	public String formaatVoorBestandLijn()
	{
		return huisnummer + "," + startDatum.getDatumInEuropeesFormaat() + "," + aantalVerblijfNachten + ">" + familienaam + "," + voornaam;
	}

	@Override
	public String toString() {
		return "Voornaam: " + voornaam + ", Naam: " + familienaam + ", HN: " + huisnummer + ", Vanaf: " + startDatum.toString() +
				", Nachten: " + aantalVerblijfNachten;
	}
}
