package model.gregorian;

import java.util.GregorianCalendar;

public class Datum implements Comparable<Datum> {
	
	final String[] MAAND_NAMEN = { "januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december" };
	final int[] MAAND_DAGEN = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};	
	private GregorianCalendar greg;
	
	/**
	 * 
	 */
	public Datum()
	{
		greg = new GregorianCalendar();
	}
	
	/**
	 * 
	 * @param dag
	 * @param maand
	 * @param jaar
	 */
	public Datum(final int dag, final int maand, final int jaar)
	{
		greg = new GregorianCalendar(jaar, maand, dag);
	}
	
	/**
	 * 
	 * @param copy
	 */
	public Datum(final Datum copy)
	{
		greg.set(copy.getYear(), copy.getMonth(), copy.getDay());
	}
	
	//@param teks = "DD-MM-JJJJ"
	public Datum(final String tekst)
	{
		int dag, maand, jaar;
		dag = Integer.parseInt(tekst.substring(0, 2));		
		maand = Integer.parseInt(tekst.substring(3, 5));
		jaar = Integer.parseInt(tekst.substring(6));
		greg = new GregorianCalendar(jaar, maand, dag);
		//greg.set(jaar, maand, dag);
	}
		
	public int getDay()
	{
		return greg.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	public int getMonth()
	{
		return greg.get(GregorianCalendar.MONTH);
	}
	
	public int getYear()
	{
		return greg.get(GregorianCalendar.YEAR);
	}
	
	
	public void setDatum(final int dag, final int maand, final int jaar)
	{
		greg.set( jaar, maand, dag);
	}
	
	public String getDatumInAmerikaansFormaat() // vb: 2009/02/04 ## jjjj/mm/dd
	{
		return String.format("%04d/%02d/%02d", getYear(), getMonth(), getDay()); 
	}
	
	public String getDatumInEuropeesFormaat() // vb: 04/02/2009 ## dd/mm/dd
	{
		return String.format("%02d/%02d/%04d", getDay(), getMonth(), getYear());
	}
	
    public boolean kleinerDan(Datum datum)
    {
    	return compareTo(datum) > 0;
    }
	
	@Override
	public String toString() {	
		return getDay() + " " + MAAND_NAMEN[getMonth()-1] + " " + getYear();
	}
	 
	@Override
	public boolean equals(Object obj)
	{
		return super.equals(obj);
	}
	
	/**
	 * 
	 * @param d
	 * @return
	 */
	public int verschilInJaren(Datum d)
	{
		int r;
		// 1/1/2015 en 1/10/2015
		// 1/8/2014 en 1/1/2015
		if( getMonth() <= d.getMonth() && getDay() <= d.getDay() )
		{
			r = d.getYear() - getYear();
		}
		else
			r = d.getYear() - getYear() -1;
		
		//int verschilInJaren = Math.abs(getYear() - d.getYear());
		
		return Math.abs(r);
	}
	
	/**
	 * verschilInMaanden() heeft deze nodig omdat negatieve resultaten behouden moeten blijven
	 * @param d
	 * @return
	 */
	private int verschilInJarenOokNegatief(Datum d)
	{
		int r;
		// 1/1/2015 en 1/10/2015
		// 1/8/2014 en 1/1/2015
		if( getMonth() <= d.getMonth() && getDay() <= d.getDay() )
		{
			r = d.getYear() - getYear();
		}
		else
			r = d.getYear() - getYear() -1;
		
		//int verschilInJaren = Math.abs(getYear() - d.getYear());
		
		return (r);
	}
	
	/**
	 * 
	 * @param d
	 * @return
	 */
	public int verschilInMaanden(Datum d)
	{
		return Math.abs(d.getMonth() - getMonth() + verschilInJarenOokNegatief(d) * 12);		
	}
	
	public void veranderThisDatum( int aantalDagen )
	{
		greg.roll(GregorianCalendar.DAY_OF_MONTH, aantalDagen);
	}
	
	public Datum veranderDatum( int aantalDagen )
	{
		Datum nieuw = new Datum(this);
		nieuw.veranderDatum(aantalDagen);
		return nieuw;
	}
	
	public int compareTo(Datum anotherCalendar)
	{
		return greg.compareTo(anotherCalendar.greg);
	}
}
