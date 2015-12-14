package model.datum;

public class DatumVerschil {

	private int dagen;
	private int maanden;
	
    /**
     * Constructor:
     * Bereken het verschil tussen twee data. Er wordt rekening gehouden met schrikkeljaren
     * en het exact aantal dagen voor de maanden.
     * 
     * @param datum
     * @return
     * @throws Exception 
     */
	DatumVerschil(Datum datum1, Datum datum2) throws Exception
    {
    	if (datum1.equals(datum2))
    	{
    		return;
    	}
    	
    	// De recentste datum moet afgetrokken worden door de oudste
    	Datum recentsteDatum = datum1.kleinerDan(datum2) ? datum1 : datum2;
    	Datum oudsteDatum = datum1.kleinerDan(datum2) ? datum2 : datum1;
    	//Datum oudsteDatumOrigineel = oudsteDatum;
    	//System.out.println(recentsteDatum);
    	//System.out.println(oudsteDatum);
    	
    	// Indien niet in dezelfde maand of hetzelfde jaar, plus 1 maand doen
    	while (datum1.getMaand() != datum2.getMaand() || datum1.getJaar() != datum2.getJaar())
    	{
    		int aantalDagenVoorMaand = Maanden.get(oudsteDatum.getMaand())
    									.aantalDagen(oudsteDatum.getJaar());
    		// Voeg een maand toe
    		oudsteDatum.veranderDatum(aantalDagenVoorMaand);
    		// Onthoud de dagen
    		dagen += aantalDagenVoorMaand;
    		// En nu maanden onthouden
    		maanden++;
    	}
    	// Vermeerder met resterende dagen van het verschil in datum
    	dagen += recentsteDatum.getDag() - oudsteDatum.getDag();
    	// Verminder met een maand als dag van recentsteDatum > oudsteDatum
    	maanden -= (recentsteDatum.getDag() < oudsteDatum.getDag() ? 1 : 0);
    }
	
	/**
	 * 
	 * @return
	 */
	int getDagen()
	{
		return dagen;
	}
	
	/**
	 * 
	 * @return
	 */
	int getMaanden()
	{
		return maanden;
	}
	
	/**
	 * 
	 * @return
	 */
	int getJaren()
	{
		return maanden / 12;
	}
}
