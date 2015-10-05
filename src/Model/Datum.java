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
