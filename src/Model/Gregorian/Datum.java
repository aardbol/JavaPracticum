package model.Gregorian;

import java.util.GregorianCalendar;

public class Datum implements Comparable<Datum> {
	
	final String[] MAAND_NAMEN = { "januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december" };
	final int[] MAAND_DAGEN = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};	
	private GregorianCalendar greg;
	
	public Datum()
	{
		greg = new GregorianCalendar();
	}
	
	public Datum( final int dag,final int maand,final int jaar )
	{
		greg = new GregorianCalendar(jaar, maand, dag);
	}
	
	public Datum( final Datum copy )
	{
		greg.set(copy.getYear(), copy.getMonth(), copy.getDay());
	}
	
	//@param teks = "DD-MM-JJJJ"
	public Datum( final String tekst )
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
	
	
	public void setDatum( final int dag, final int maand, final int jaar )
	{
		greg.set( jaar, maand, dag);
	}
	
	public String getDatumInAmerikaansFormaat() // vb: 2009/02/04 ## jjjj/mm/dd
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getYear());
		sb.append("/");
		sb.append(getMonth());
		sb.append("/");
		sb.append(getDay());
		return sb.toString();
		//return this.get(YEAR) +"/" + this.get(MONTH) +"/"+ this.get(DAY_OF_MONTH);
	}
	
	public String getDatumInEuropeesFormaat() // vb: 04/02/2009 ## dd/mm/dd
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getDay());
		sb.append("/");
		sb.append(getMonth());
		sb.append("/");
		sb.append(getYear());
		return sb.toString();
		//this.get(DAY_OF_MONTH) +"/" + this.get(MONTH) +"/"+ this.get(YEAR);
	}
	
	@Override
	public String toString() {	
		return getDay() + " " + MAAND_NAMEN[getMonth()-1] + " " + getYear();
	}
	 
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	public int verschilInJaren(Datum d)
	{
		if( getMonth() <= d.getMonth() && getDay() <= d.getDay() )
		{
			return d.getYear() - getYear();
		}
		else
			return d.getYear() - getYear() -1;
	}
	
	public int verschilInMaanden(Datum d)
	{
		return d.getMonth() - getMonth() + verschilInJaren(d) * 12;		
	}
	
	public int verschilInDagen(Datum d)
	{
		return getMonth() - d.getMonth();
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
	
	@Override
	public int compareTo(Datum anotherCalendar) {
		// TODO Auto-generated method stub
		return greg.compareTo(anotherCalendar.greg);
	}
}
