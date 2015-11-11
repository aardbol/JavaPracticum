package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.datum.Datum;


public class DatumTest {

	private Datum datum, datumVandaag, datumMinEenJaar, datumMinTienJaar,
	datumMinTweeHonderdJaar, datumMetSchrikkeljaar, datumPlusTienJaar;

	@Before
	public void setUp() throws Exception
	{
		datum = new Datum(1, 1, 2015);
		datumVandaag = new Datum(19, 10, 2015);
		datumMinEenJaar = new Datum(1, 1, 2014);
		datumMinTienJaar = new Datum(1, 1, 2005);
		datumMetSchrikkeljaar = new Datum(1, 1, 2000);
		datumMinTweeHonderdJaar = new Datum(1, 1, 1815);
		new Datum(1, 1, 2016);
		datumPlusTienJaar = new Datum(1, 1, 2025);
		new Datum(1, 2, 2015);
		new Datum(1, 2, 2016);
	}

	@Test(expected = NullPointerException.class)
	public void test_kleinerDanAlsParameterNull_GeeftException()
	{
		datum.kleinerDan(null);
	}
	
	@Test
	public void test_kleinerDanAlsDatumGelijk_GeeftFalse()
	{
		assertFalse(datum.kleinerDan(datum));
	}
	
	@Test
	public void test_kleinerDanAlsParameterGroterDan_GeeftFalse()
	{
		assertFalse(datum.kleinerDan(datumVandaag));
		assertFalse(datum.kleinerDan(datumPlusTienJaar));
	}
	
	@Test
	public void test_kleinerDanAlsParameterKleinerDan_GeeftTrue()
	{
		assertTrue(datum.kleinerDan(datumMinEenJaar));
		assertTrue(datum.kleinerDan(datumMinTienJaar));
		assertTrue(datum.kleinerDan(datumMetSchrikkeljaar));
		assertTrue(datum.kleinerDan(datumMinTweeHonderdJaar));
	}
	
	@Test
	public void test_getDatumInEuropeesFormaat_GelijkAan()
	{
		assertEquals("Europees formaat", "01/01/2015", datum.getDatumInEuropeesFormaat());
		assertEquals("Europees formaat", "19/10/2015", datumVandaag.getDatumInEuropeesFormaat());
	}

	@Test
	public void test_getDatumInAmerikaansFormaat_GelijkAan()
	{
		assertEquals("Amerikaans formaat", "2015/01/01", datum.getDatumInAmerikaansFormaat());
		assertEquals("Amerikaans formaat", "2015/10/19", datumVandaag.getDatumInAmerikaansFormaat());
	}
}
