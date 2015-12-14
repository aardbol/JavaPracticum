package model.datum;

import java.util.Date;


/**
 *
 * @author Isaak
 *
 */
public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	/**
	 * @throws Exception 
	 *
	 */
	public Datum() throws Exception
	{
		HuidigeSysteemDatum();
	}

	/**
	 *
	 * @param Datum
	 * @throws Exception 
	 */
	@SuppressWarnings("deprecation")
	public Datum(Date datum) throws Exception
	{
		setDatum(datum.getDate(), datum.getMonth() + 1, datum.getYear() + 1900);
	}
	
	/**
	 * 
	 * @param datum
	 * @throws Exception 
	 */
	public Datum(Datum datum) throws Exception
	{
		setDatum(datum.getDag(), datum.getMaand(), datum.getJaar());
	}

	/**
	 *
	 * @param dag
	 * @param maand
	 * @param jaar
	 * @throws Exception 
	 */
	public Datum(int dag, int maand, int jaar) throws Exception
	{
		setDatum(dag, maand, jaar);
	}

    /**
     * Datum als string DDMMJJJJ
     * @param datum
     * @throws Exception 
     * @throws NumberFormatException 
     */
	public Datum(String datum) throws NumberFormatException, Exception
	{
		String[] datumDelen = datum.split("/");
		
		if (datumDelen.length != 3 || datumDelen[0].length() < 1 ||
			datumDelen[1].length() != 2 || datumDelen[2].length() != 4)
		{
			throw new IllegalArgumentException("De gegeven datum is onjuist. "
												+ "Geldig formaat: (D)D/MM/YYYY");
		}
		
		setDatum(Integer.parseInt(datumDelen[0]), Integer.parseInt(datumDelen[1]), Integer.parseInt(datumDelen[2]));
	}

	/**
	 * Stel de datumvariabelen in. Doe ook de validatie en werp exceptions indien nodig
	 * 
	 * @param dag
	 * @param maand
	 * @param jaar
	 * @return
	 * @throws Exception 
	 */
    public boolean setDatum(int dag, int maand, int jaar) throws Exception
    {
    	// Eerst de basale controle
    	if (dag < 1 || dag > 31)
        {
            throw new Exception("Ongeldige dag gegeven");
        }
    	
    	if (maand < 1 || maand > 12)
        {
            throw new Exception("Ongeldige numerieke maand gegeven");
        }
    	
    	if (jaar < 0)
        {
            throw new Exception("Ongeldig jaar gegeven");
        }
    	
    	// Nu een precieze controle
        switch (maand)
        {
            case 2:
            	// 1) Als het geen schrikkeljaar is, heeft februari max 28 dagen
            	// 2) Wel een schrikkeljaar? Max 29 dagen
            	if ((!Maanden.isSchrikkeljaar(jaar) && dag >= 29) || (Maanden.isSchrikkeljaar(jaar) &&
            		dag > 29))
            	{
            		throw new Exception("De dag is niet juist voor de gegeven maand februari");
            	}
            	break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (dag > 30)
                {
                    throw new Exception("De dag is niet juist voor de gegeven maand " + Maanden.get(maand));
                }
                break;
        }
        // Alles is goed verlopen
        this.dag = dag;
        this.maand = maand;
        this.jaar = jaar;

        return true;
    }

    /**
     *
     * @return
     */
    public String getDatumInAmerikaansFormaat()
    {
    	return String.format("%04d/%02d/%02d", jaar, maand, dag);
    }

    /**
     *
     * @return
     */
    public String getDatumInEuropeesFormaat()
    {
    	return String.format("%02d/%02d/%04d", dag, maand, jaar);
    }

    /**
     * Haal de dag van het Datum object op
     * @return
     */
    public int getDag()
    {
    	return dag;
    }

    /**
     * Haal de maand van het Datum object op
     * @return
     */
    public int getMaand()
    {
    	return maand;
    }

    /**
     * Haal het jaar van het Datum object op
     * @return
     */
    public int getJaar()
    {
    	return jaar;
    }

    /**
     * Is de gegeven datum kleiner dan het huidid datum object?
     *
     * @param datum
     * @return
     */
    public boolean kleinerDan(Datum datum)
    {
    	return compareTo(datum) > 0;
    }


    /**
     *
     * @param datum
     * @return
     * @throws Exception 
     */
    public int verschilInJaren(Datum datum) throws Exception
    {
    	return new DatumVerschil(this, datum).getJaren();
    }

    /**
     *
     * @param datum
     * @return
     * @throws Exception 
     */
    public int verschilInMaanden(Datum datum) throws Exception
    {
    	return new DatumVerschil(this, datum).getMaanden();
    }
    
    /**
     * 
     * @param aantalDagen
     * @return
     * @throws Exception 
     */
    public Datum veranderDatum(int aantalDagen) throws Exception
    {
		if (aantalDagen > 0)
		{
			while (aantalDagen + dag > Maanden.get(maand).aantalDagen(jaar))
			{
				aantalDagen -= Maanden.get(maand).aantalDagen(jaar) - dag + 1;
				// Jaar verhogen
				jaar += (maand == 12 ? 1 : 0);
				// Maand verhogen
				maand = (maand == 12 ? 1 : ++maand);
				// We hebben een nieuwe maand, dus terug van 1 beginnen
				dag = 1;
			}
		}
		// Negatieve waarde, dus terug in de tijd gaan
		else
		{
			while (-dag >= aantalDagen)
			{
				// Verminder met aantal dagen in huidige maand.
				aantalDagen += dag;
				// Verminder jaartal?
				jaar -= (maand == 1 ? 1 : 0);
				// Verminder maand
				maand = (maand == 1 ? 12 : --maand);
				// Zet als laatste dag van (vorige) maand
				dag = Maanden.get(maand).aantalDagen(jaar);
			}
		}
		return new Datum(dag += aantalDagen, maand, jaar);
    }

    /**
     *
     */
    @Override
    public boolean equals(Object obj)
    {
    	// Is het exact hetzelfde object?
    	if (this == obj)
    	{
    		return true;
    	}

    	// Is het hetzelfde type?
    	if (obj == null || !(obj instanceof Datum))
    	{
    		return false;
    	}
    	// Nu zien of de inhoud dezelfde is
    	return compareTo((Datum) obj) == 0;
    }

    /**
     * Ik snap er de ballen van
     */
    @Override
	public int hashCode()
	{
		final int prime = 37;
		int hash = 1;

		hash = prime * hash + dag;
		hash = prime * hash + maand;
		hash = prime * hash + jaar;
		return hash;
	}

    /**
     * Vergelijk de onze datum met de nieuwe
     */
    public int compareTo(Datum datum2)
    {
    	if (jaar > datum2.jaar)
    	{
    		return 1;
    	}
    	else if (jaar < datum2.jaar)
    	{
    		return -1;
    	}

    	if (maand > datum2.maand)
    	{
    		return 1;
    	}
    	else if (maand < datum2.maand)
    	{
    		return -1;
    	}

    	if (dag > datum2.dag)
    	{
    		return 1;
    	}
    	else if (dag < datum2.dag)
    	{
    		return -1;
    	}
    	return 0;
    }

    /**
     * Geef een string representatie terug van de datum
     * @return Datum in string formaat
     */
    public String toString()
    {
    	return dag + " " + Maanden.get(maand) + " " + jaar;
    }

    /**
	 * Return de huidige datum van het systeem
	 * @return Date
	 * @throws Exception 
	 */
	@SuppressWarnings("deprecation")
	private void HuidigeSysteemDatum() throws Exception
	{
		Date datum = new Date();
		
		setDatum(datum.getDate(), datum.getMonth() + 1, datum.getYear() + 1900);
	}
}
