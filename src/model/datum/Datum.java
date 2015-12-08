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

	private String[] maanden = {"januari", "feburari", "maart", "april", "mei",
			"juni", "juli", "augustus", "september", "oktober", "november", "december"};

	/*public static void main(String[] args)
	{
		Datum d = new Datum();
	}*/

	/**
	 *
	 */
	public Datum()
	{
		try {
			HuidigeSysteemDatum();

			// Voor de zekerheid ook valideren, want misschien is er anders een security hole?
			datumValidatie();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 *
	 * @param Datum
	 */
	@SuppressWarnings("deprecation")
	public Datum(Date datum)
	{
		try {
			setDag(datum.getDate());
			setMaand(datum.getMonth() + 1);
			setJaar(datum.getYear() + 1900);

			datumValidatie();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param datum
	 */
	public Datum(Datum datum)
	{
		try {
			setDag(datum.getDag());
			setMaand(datum.getMaand());
			setJaar(datum.getJaar());

			datumValidatie();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 *
	 * @param dag
	 * @param maand
	 * @param jaar
	 */
	public Datum(int dag, int maand, int jaar)
	{
		try {
			setDag(dag);
			setMaand(maand);
			setJaar(jaar);

			datumValidatie();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

    /**
     * Datum als string DDMMJJJJ
     * @param datum
     */
	public Datum(String datum)
	{
		try {
			String[] datumDelen = datum.split("/");
			
			if (datumDelen.length != 3 || datumDelen[0].length() < 1 ||
				datumDelen[1].length() != 2 || datumDelen[2].length() != 4)
			{
				System.out.println(datum);
				throw new IllegalArgumentException("De gegeven datum is onjuist. "
													+ "Geldig formaat: (D)D/MM/YYYY");
			}

			setDag(Integer.parseInt(datumDelen[0]));
			setMaand(Integer.parseInt(datumDelen[1]));
			setJaar(Integer.parseInt(datumDelen[2]));

			datumValidatie();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 *
	 * @param dag
	 * @param maand
	 * @param jaar
	 * @return
	 */
    public boolean setDatum(int dag, int maand, int jaar)
    {
    	try {
	    	setDag(dag);
	        setMaand(maand);
	        setJaar(jaar);

	        datumValidatie();
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        }

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
     */
    public int verschilInJaren(Datum datum)
    {
    	return new DatumVerschil(this, datum).getJaren();
    }

    /**
     *
     * @param datum
     * @return
     */
    public int verschilInMaanden(Datum datum)
    {
    	return new DatumVerschil(this, datum).getMaanden();
    }
    
    /**
     * 
     * @param aantalDagen
     * @return
     */
    public Datum veranderDatum(int aantalDagen)
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
    	return dag + " " + maanden[maand - 1] + " " + jaar;
    }

    /**
     * Deze methode kijkt of de gegeven dag geldig is voor het gegeven jaar, rekening
     * houdend met schrikkeljaren. De details van de datum moeten al geset zijn
     *
     * @return true Als de datum geldig is
     */
    private boolean datumValidatie()
    {
        switch (maand)
        {
            case 2:
            	// 1) Als het geen schrikkeljaar is, heeft februari max 28 dagen
            	// 2) Wel een schrikkeljaar? Max 29 dagen
            	if ((!Maanden.isSchrikkeljaar(jaar) && dag >= 29) || (Maanden.isSchrikkeljaar(jaar) &&
            		dag > 29))
            	{
            		new Exception("De dag is niet juist voor de gegeven maand februari");
            	}
            	break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (dag > 30)
                {
                    new Exception("De dag is niet juist voor de gegeven maand");
                }
                break;
        }
    	return true;
    }

    /**
     *
     * @param dag
     * @return
     */
    private void setDag(int dag)
    {
    	if (dag < 1 || dag > 31)
        {
            new Exception("Ongeldige dag gegeven");
        }
    	this.dag = dag;
    }

    /**
     *
     * @param maand
     * @return
     */
    private void setMaand(int maand)
    {
    	if (maand < 1 || maand > 12)
        {
            new Exception("Ongeldige maand gegeven");
        }
    	this.maand = maand;
    }

    /**
     *
     * @param jaar
     * @return
     */
    private void setJaar(int jaar)
    {
    	if (jaar < 0)
        {
            new Exception("Ongeldig jaar gegeven");
        }
    	this.jaar = jaar;
    }


	/**
	 * Return de huidige datum van het systeem
	 * @return Date
	 */
	@SuppressWarnings("deprecation")
	private void HuidigeSysteemDatum()
	{
		Date datum = new Date();

		setDag(datum.getDate());
		setMaand(datum.getMonth() + 1);
		setJaar(datum.getYear() + 1900);
	}
}
