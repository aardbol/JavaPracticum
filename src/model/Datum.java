package model;

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

	public static void main(String[] args)
	{
		Datum d = new Datum(31, 12, 2000);
		int dagen = d.datumNaarDagen(d);
		System.out.println(dagen);
		System.out.println(d.dagenNaarDatum(dagen));

		//System.out.println(d.verschilInMaanden(new Datum(1, 1, 2001)));
	}

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
	public Datum(Date Datum)
	{
		try {
			setDag(Datum.getDate());
			setMaand(Datum.getMonth());
			setJaar(Datum.getYear());

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
     * Bereken het verschil tussen twee data. Er wordt rekening gehouden met schrikkeljaren
     * en het exact aantal dagen voor de maanden.
     *
     * @param datum
     * @return
     */
    public int verschilInDatum(Datum datum)
    {
    	int aantalDagen1 = 0,
    		aantalDagen2 = 0;
    	
    	// Zelfde data
    	if (getDag() == datum.getDag() &&
    		getMaand() == datum.getMaand() &&
    		getJaar() == datum.getJaar())
    	{
    		return 0;
    	}
    	
    	// De recentste datum moet afgetrokken worden door de oudste
    	Datum recentsteDatum = kleinerDan(datum) ? this : datum;
    	Datum oudsteDatum = kleinerDan(datum) ? datum : this;
    	
    	// Er doet zich een probleem voor van een dag te weinig geteld als
    	// de oudste datum een schrikkeljaar is en deelbaar door 100
    	if (Maanden.isSchrikkeljaar(oudsteDatum.getJaar()) && oudsteDatum.getJaar() % 100 == 0)
    	{
    		aantalDagen1 += 1;
    	}

    	// We berekenen eerst het aantal dagen voor de data, dan aftrekken van elkaar
		aantalDagen1 += datumNaarDagen(recentsteDatum);
		// debug
		//System.out.println(aantalDagen1);

		aantalDagen2 += datumNaarDagen(oudsteDatum);
		// debug
		//System.out.println(aantalDagen2);

    	return aantalDagen1 - aantalDagen2;
    }

    /**
     *
     * @param datum
     * @return
     */
    public int verschilInJaren(Datum datum)
    {
    	return (int) (verschilInDatum(datum) / 365.242375);
    }

    /**
     *
     * @param datum
     * @return
     */
    public int verschilInMaanden(Datum datum)
    {
    	return (int) (verschilInDatum(datum) / 365.242375 * 12);
    }
    
    /**
     * 
     * @param aantalDagen
     * @return
     */
    public Datum veranderDatum(int aantalDagen)
    {
    	Datum datum = this;
    	int dagen = datumNaarDagen(datum);
    	dagen += aantalDagen;
    	
    	while (dagen > 365 )
    	{
    		
    	}
    	
    	return datum;
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
            	if ((!Maanden.isSchrikkeljaar(jaar) && dag >= 29) || (Maanden.isSchrikkeljaar(jaar) && dag > 29))
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
     * Bereken het aantal dagen voor een gegeven jaar. Er wordt rekening gehouden met
     * schrikkeljaren
     *
     * @param jaar
     * @return
     */
    private int berekenSchrikkeldagen(int jaar)
    {
    	int aantalDagen = 0;
    	// debug
    	//System.out.println("Jaar: " + jaar);

    	for (int n = 0; n <= jaar; n++)
    	{
    		if (Maanden.isSchrikkeljaar(n))
    		{
    			aantalDagen += 366;
    			// debug
    			//System.out.println("jaar " + n + " sj: " + Maanden.isSchrikkeljaar(n) + " - 366");
    		}
    		else
    		{
    			aantalDagen += 365;
    			// debug
    			//System.out.println("jaar " + n + " sj: " + Maanden.isSchrikkeljaar(n) + " - 365");
    		}
    	}
    	return aantalDagen;
    }

    /**
     * 
     * @param maandNummer De numerieke waarde van de gegeven maand
     * @param jaar
     * @return
     */
    private int berekenDagenVoorMaanden(int maandNummer, int jaar)
    {
    	int aantalDagen = 0;
    	
    	// We doen -1 omdat de laatste maand er niet bij moet worden geteld, want vanaf hier moeten
    	// de dagen van de datum worden geteld. Bv: 10/10/2015, dagen tellen tot 30/10/2015 en
    	// dan plus 10
    	maandNummer -= 1;
    	
    	// debug
    	//System.out.println("Maand nr: " + maandNummer);

    	for (int n = 1; n <= maandNummer; n++)
    	{
    		aantalDagen += Maanden.get(n).aantalDagen(jaar);
    		
    		// debug
    		//System.out.println("Aantal dagen voor maand " + n +": " + Maanden.get(n).aantalDagen());
    	}
    	// debug
    	//System.out.println("Totaal aantal dagen tot maand " + maandNummer + ": " + aantalDagen);
    	
    	return aantalDagen;
    }
    
    /**
     * Converteer de datum naar het aantal dagen vanaf jaar 0
     * 
     * @param datum
     * @return
     */
    private int datumNaarDagen(Datum datum)
    {
    	int aantalDagen = 0;
    	
    	aantalDagen += berekenSchrikkeldagen(datum.getJaar());
		// debug
		//System.out.println(aantalDagen);
		aantalDagen += berekenDagenVoorMaanden(datum.getMaand(), datum.getJaar());
		// debug
		//System.out.println(berekenDagenVoorMaanden(datum.getMaand()));
		aantalDagen += datum.getDag();
		
		return aantalDagen;
    }
    
    /**
     * Doet het omgekeerde als de vorige methode
     * 
     * @param dagen
     * @return
     */
    private Datum dagenNaarDatum(int dagen)
    {
    	int maand = 1, jaar = 0;
    	
    	// Eerste de jaren optellen
    	while (dagen > 365)
    	{
    		System.out.println("1: "+dagen);
    		System.out.println(jaar);
    		if (Maanden.isSchrikkeljaar(jaar))
    		{
    			dagen -= 366;
    		}
    		else
    		{
    			dagen -= 365;
    		}
    		System.out.println("2: "+dagen);
    		System.out.println(jaar);
    		jaar++;
    	}
    	jaar--;
    	//System.out.println(jaar);
    	//System.out.println(dagen);
    	
    	// Dan de maanden
    	while (dagen > 27)
    	{
    		System.out.println(dagen);
    		System.out.println("ad: "+Maanden.get(maand).aantalDagen(jaar));
    		System.out.println(jaar);
    		if (Maanden.get(maand).aantalDagen(jaar) < dagen)
    		{
    			System.out.println(dagen+"-"+maand+"-"+jaar);
    			dagen -= Maanden.get(maand).aantalDagen(jaar);
    			maand++;
    			System.out.println("2: "+dagen+"-"+maand+"-"+jaar);
    			System.out.println(maand);
    			System.out.println(dagen);
    		}
    		else
    		{
    			break;
    		}
    	}
    	//System.out.println(maand);
    	return new Datum(dagen, maand, jaar);
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
	private void HuidigeSysteemDatum()
	{
		Date Datum = new Date();

		setDag(Datum.getDate());
		setMaand(Datum.getMonth());
		setJaar(Datum.getYear());
	}
}
