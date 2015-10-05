package model.gregorian;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Datum extends GregorianCalendar{
	
	final String[] MAAND_NAMEN = { "januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december" };
	final int[] MAAND_DAGEN = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};	
	
	public Datum()
	{
		super();
	}
	
	public Datum( final int dag,final int maand,final int jaar )
	{
		super(jaar, maand, dag);
	}
	
	public Datum( final Datum copy )
	{
		set(copy.get(YEAR), copy.get(MONTH), copy.get(DAY_OF_MONTH));
	}
	
	//@param teks = "DD-MM-JJJJ"
	public Datum( final String tekst )
	{
		int dag, maand, jaar;
		dag = Integer.parseInt(tekst.substring(0, 2));		
		maand = Integer.parseInt(tekst.substring(3, 5));
		jaar = Integer.parseInt(tekst.substring(6));
		super.set(jaar, maand, dag);
	}
	
	public void setDatum( final int dag, final int maand, final int jaar )
	{
		set( jaar, maand, dag);
	}
	
	public String getDatumInAmerikaansFormaat() // vb: 2009/02/04 ## jjjj/mm/dd
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.get(MONTH));
		sb.append("/");
		sb.append(this.get(DAY_OF_MONTH));
		sb.append("/");
		sb.append(this.get(YEAR));
		return sb.toString();
		//return this.get(YEAR) +"/" + this.get(MONTH) +"/"+ this.get(DAY_OF_MONTH);
	}
	
	public String getDatumInEuropeesFormaat() // vb: 04/02/2009 ## dd/mm/dd
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.get(DAY_OF_MONTH));
		sb.append("/");
		sb.append(this.get(MONTH));
		sb.append("/");
		sb.append(this.get(YEAR));
		return sb.toString();//this.get(DAY_OF_MONTH) +"/" + this.get(MONTH) +"/"+ this.get(YEAR);
	}
	
	@Override
	public String toString() {	
		return this.get(DAY_OF_MONTH) + " " + MAAND_NAMEN[this.get(MONTH)-1] + " " + this.get(YEAR);
	}
	
	@Override
	public int compareTo(Calendar anotherCalendar) {
		// TODO Auto-generated method stub
		return super.compareTo(anotherCalendar);
	}
	 
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	public int verschilInJaren(Datum d)
	{
		if( this.get(MONTH) <= d.get(MONTH) && this.get(DAY_OF_MONTH) <= d.get(DAY_OF_MONTH) )
		{
			return d.get(YEAR) - this.get(YEAR);
		}
		else
			return d.get(YEAR) - this.get(YEAR) -1;
	}
	
	public int verschilInMaanden(Datum d)
	{
		return d.get(MONTH) - this.get(MONTH) + verschilInJaren(d) * 12;		
	}
	
	public int verschilInDagen(Datum d)
	{
		return this.get(DAY_OF_MONTH) - d.get(DAY_OF_MONTH);
	}
	
	public void veranderThisDatum( int aantalDagen )
	{
		this.roll(DAY_OF_MONTH, aantalDagen);
	}
	
	public Datum veranderDatum( int aantalDagen )
	{
		Datum nieuw = new Datum(this);
		nieuw.veranderDatum(aantalDagen);
		return nieuw;
	}
}
