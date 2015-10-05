package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datum {

	private int dag;
	private int maand;
	private int jaar;
	private DateFormat datumFormaat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public static void main(String[] args)
	{
		Datum Datum1 = new Datum();
		Datum1.HuidigeSysteemDatum();
	}
	
	public Datum()
	{
		this.HuidigeSysteemDatum();
	}
	
	public Datum(Date Datum)
	{
		
	}
	
	public Datum(int dag, int maand, int jaar)
	{
		
	}
	
        /**
         * Datum als string DDMMJJJJ
         * @param datum 
         */
	public Datum(String datum)
	{
		
	}
        
        private boolean setDatum(int dag, int maand, int jaar)
        {
            //--
            // Eerst basic validatie
            //--
            if (jaar < 0)
            {
                new Exception("Ongeldige jaar gegeven");
            } 
            
            if (maand < 1 || maand > 12)
            {
                new Exception("Ongeldige maand gegeven");
            }
            
            if (dag < 1 || dag > 31)
            {
                new Exception("Ongeldige dag gegeven");
            }
            
            // Nu kijken of de dag geldig is voor die maand, rekening houdend met
            // schrikkeljaren
            switch (maand)
            {
                case 2:
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
        }
        
        private boolean isSchrikkeljaar(int jaar)
        {
            if (jaar % 400 == 0)
                return true;
            
            return false;
        }
        
        private boolean setDag(int dag)
        {
            
        }
        
        private boolean setMaand(int maand)
        {
            
        }
        
        private boolean setJaar(int jaar)
        {
            
        }
	
	/**
	 * Return de huidige datum van het systeem
	 * @return Date
	 */
	private String HuidigeSysteemDatum()
	{
		Date Datum = new Date();
		
		return datumFormaat.format(Datum);
	}
}
