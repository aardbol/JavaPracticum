package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Datum;
import model.Maanden;


public class DatumTest {

	private Datum datum, datumVandaag, datumMinEenJaar, datumMinTienJaar,
	datumMinTweeHonderdJaar, datumMetSchrikkeljaar, datumPlusEenJaar, datumPlusTienJaar,
	datumFebruariGeenSchrikkeljaar, datumFebruariSchrikkeljaar;
	
	private int jaar250 = 250, jaar1000 = 1000, jaar1025 = 1025, jaar1996 = 1996, 
			jaar1999 = 1999, jaar2000 = 2000, jaar2005 = 2005, jaar2012 = 2012,
			jaar2015 = 2015, jaar2016 = 2016, jaar2250 = 2250;

	@Before
	public void setUp() throws Exception
	{
		datum = new Datum(1, 1, 2015);
		datumVandaag = new Datum(19, 10, 2015);
		datumMinEenJaar = new Datum(1, 1, 2014);
		datumMinTienJaar = new Datum(1, 1, 2005);
		datumMetSchrikkeljaar = new Datum(1, 1, 2000);
		datumMinTweeHonderdJaar = new Datum(1, 1, 1815);
		datumPlusEenJaar = new Datum(1, 1, 2016);
		datumPlusTienJaar = new Datum(1, 1, 2025);
		datumFebruariGeenSchrikkeljaar = new Datum(1, 2, 2015);
		datumFebruariSchrikkeljaar = new Datum(1, 2, 2016);
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
	
	@Test
	public void test_verschilInDatumZonderSchrikkeljaar_GelijkAan()
	{
		assertEquals(365, datum.verschilInDatum(datumMinEenJaar));
	}
	
	@Test
	public void test_verschilInDatumMetSchrikkeljaar_GelijkAan()
	{
		assertEquals(366, datum.verschilInDatum(datumPlusEenJaar));
	}
	
	@Test
	public void test_verschilInDatumMeerdereTests_GelijkAan()
	{
		//assertEquals(5479, datum.verschilInDatum(datumMetSchrikkeljaar));
		assertEquals(73049, datum.verschilInDatum(datumMinTweeHonderdJaar));
		assertEquals(3653, datum.verschilInDatum(datumPlusTienJaar));
		assertEquals(291, datum.verschilInDatum(datumVandaag));
		assertEquals(31, datum.verschilInDatum(datumFebruariGeenSchrikkeljaar));
		assertEquals(7426 , (new Datum(2,1, 2025).verschilInDatum(new Datum(3, 9, 2004))));
		assertEquals(396 , (new Datum(9, 9, 2009).verschilInDatum(new Datum(10, 10, 2010))));
		assertEquals(28212, (new Datum(6, 1, 1959).verschilInDatum(new Datum(3, 4, 2036))));
		assertEquals(364, (new Datum(31, 12, 2015).verschilInDatum(new Datum(1, 1, 2015))));
		assertEquals(4626, (new Datum(31, 12, 2000).verschilInDatum(new Datum(2, 5, 1988))));
		assertEquals(7463, (new Datum(6, 6, 1988).verschilInDatum(new Datum(31, 12, 1967))));
		assertEquals(4383, (new Datum(31, 12, 1988).verschilInDatum(new Datum(31, 12, 2000))));
		assertEquals(5479, (new Datum(1, 2, 2000).verschilInDatum(new Datum(1, 2, 2015))));
	}
	
	//--------------------------------
	// Enum Maanden testen
	//-------------------------------
	
	@Test
	public void test_MaandenIsSchrikkeljaar_GeeftTrue()
	{
		assertTrue(Maanden.isSchrikkeljaar(jaar2000));
		assertTrue(Maanden.isSchrikkeljaar(jaar1996));
		assertTrue(Maanden.isSchrikkeljaar(jaar2012));
		assertTrue(Maanden.isSchrikkeljaar(jaar2016));
	}
	
	@Test
	public void test_MaandenIsGeenSchrikkeljaar_GeeftFalse()
	{
		assertFalse(Maanden.isSchrikkeljaar(jaar1999));
		assertFalse(Maanden.isSchrikkeljaar(jaar2005));
		assertFalse(Maanden.isSchrikkeljaar(jaar2015));
		assertFalse(Maanden.isSchrikkeljaar(jaar2250));
		assertFalse(Maanden.isSchrikkeljaar(jaar250));
		assertFalse(Maanden.isSchrikkeljaar(jaar1000));
		assertFalse(Maanden.isSchrikkeljaar(jaar1025));
	}
	
	@Test
	public void test_Maanden_aantalDagenZonderParameter_GelijkAan()
	{
		assertEquals(28, Maanden.februari.aantalDagen());
		assertEquals(31, Maanden.januari.aantalDagen());
		assertEquals(30, Maanden.juni.aantalDagen());
		assertEquals(31, Maanden.december.aantalDagen());
	}
	
	@Test
	public void test_Maanden_aantalDagenFeburariZonderParameterSchrikkeljaar_Geeft28Terug()
	{
		assertNotEquals(29, Maanden.februari.aantalDagen());
	}
	
	@Test
	public void test_Maanden_aantalDagenMetJaarParameter_GelijkAan()
	{
		assertEquals(29, Maanden.februari.aantalDagen(jaar2016));
		assertEquals(28, Maanden.februari.aantalDagen(jaar2015));
		assertEquals(31, Maanden.augustus.aantalDagen(jaar2016));
		assertEquals(31, Maanden.augustus.aantalDagen(jaar2015));
		assertEquals(30, Maanden.juni.aantalDagen(jaar2015));
		assertEquals(30, Maanden.juni.aantalDagen(jaar2016));
		assertEquals(31, Maanden.december.aantalDagen(jaar2015));
		assertEquals(31, Maanden.december.aantalDagen(jaar2016));
	}
}
